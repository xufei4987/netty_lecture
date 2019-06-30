package com.ssy.nettySrcAnalysis;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @description
 * reactor(反应器)模式
 * 传统IO网络编程模式：
 *      1、一个线程对应一个socket，因此会产生大量的线程
 *      2、不是所有socket每时每刻都有数据，大量线程在空转，浪费资源
 *      3、线程越多，线程切换带来的性能开销就越大
 *
 * reactor模式的角色构成
 *      1、handle（句柄或是描述符）：本质上表示一种资源，该资源用于表示一个个的时间，比如文件描述符，或是针对网络编程中的socket描述符。
 *      事件既可以来自于外部，也可以来自于内部，外部事件比如说客户端的连接请求，或者是客户端发过来的数据等。内部事件比如说是操作系统的定时器
 *      事件。handle是事件产生的发源地。
 *
 *      2、synchronize event demultiplexer(同步事件分离器)：他本身是一个系统调用，用于等待事件的发生。调用方在调用它的时候会被阻塞，
 *      一直阻塞到同步事件分离器上有事件产生为止。对于linux来说，同步事件分离器就是常用的I/O多路复用的机制，比如select、poll、epoll。
 *
 *      3、event handler（事件处理器）：本身是由多个回调方法构成，这些回调方法构成了与应用相关的对于某个事件的反馈机制。
 *
 *      4、concrete event handler（具体的事件处理器）：是事件处理器的实现，它本身实现事件处理器所提供的各个回调方法，从而实现了特定于
 *      业务的逻辑，本质上是我们所编写的一个个的处理器的实现。
 *
 *      5、Initiation dispatcher（初始分发器）：实际上就是reactor角色，它本身定义了一些规范，这些规范用于控制事件的调度方式，同时又提供了
 *      应用进行事件处理器的注册、删除等设施。它是整个事件处理器的核心所在，Initiation dispatcher 会通过 synchronize event demultiplexer
 *      来等待时间的发生，一旦事件发生，initiation dispatcher 首先会分离出一个事件，然后调用事件处理器，最后调用相关的回调方法来处理这些事件。
 *
 * reactor模式的流程
 *      1、当应用向Initiation dispatcher注册具体的事件处理器时，应用会标示出该事件处理器希望Initiation dispatcher在某个事件发生时向其通知该
 *      事件，该事件与handle关联。
 *      2、Initiation dispatcher会要求每个事件处理器向其传递内部的handle，该handle向操作系统标示了事件处理器。
 *      3、当所有的事件处理器注册完毕后，应用会调用handle_events方法来启动Initiation dispatcher的事件循环。这时，Initiation dispatcher
 *      会将每个注册的事件管理器的Handle合并起来，并使用synchronize event demultiplexer等待这些事件的发生。比如说TCP协议层会使用select
 *      同步事件分离器操作来等待客户端发送的数据到达socket handle上。
 *      4、当某个事件源对应的handle变为ready状态时，synchronize event demultiplexer就会通知Initiation dispatcher。
 *      5、Initiation dispatcher会出发事件处理器的回调方法，从而响应处于ready状态的handle，当事件发生时，Initiation dispatcher会将事件
 *      源激活的handle作为【key】来寻找并分发恰当的事件处理器回调方法。
 *      6、Initiation dispatcher会回调事件处理器的handle_events回调方法来执行特定于应用的功能，从而响应这个事件。
 *
 * EventLoopGroup的概念
 *      1、一个EventLoopGroup当中包含一个或多个EventLoop
 *      2、一个EventLoop在它的整个生命周期中只会与唯一的一个Thread进行绑定
 *      3、所有有EventLoop所处理的各种I/O事件都将在与它关联的Thread上进行处理
 *      4、一个channel在它的整个生命周期中只会注册在一个EventLoop上
 *      5、一个EventLoop在运行过程中，会被分配给一个或者多个channel
 *
 * 重要的结论：在netty中，channel的实现一定是线程安全的，基于此，我们可以存储一个channel的引用，并且在需要向远程端点发送数据时，通过这个引用来调用
 * channel的方法，即便当时有很多线程都在使用它也不会出现多线程问题，而且，消息一定是按顺序发送出去的。
 *
 * 注意：在我们的业务开发中，一定不要将耗时任务放在EventLoop的执行队列中，因为它会阻塞该线程所对应的所有channel上的其他执行任务，如果我们需要阻塞
 * 调用或者是耗时操作，那么我们就需要使用一个专门的EventExecutor（业务线程池）。
 *
 * 业务线程池的两种实现方式：
 * 1、在channelHandler的回调方法中，使用自己的业务线程池，这样就可以实现异步调用。
 * 2、借助于Netty提供的向ChannelPipeline添加channelHandler时调用的addLast方法来传递EventExecutor。
 * 说明：在默认情况下（调用addLast（handler）），channelHandler中的回调方法是有EventLoop中的线程所执行的，
 * 如果调用addLast（EventExecutorGroup group， ChannelHandler... handlers）方法，那么channelHandler中的回调方法
 * 就是由group参数所传递的线程组执行。
 *
 * JDK1.5的Future，netty的Future、ChannelFuture、ChannelPromise间的关系
 * 1、JDK提供的Future只能进行手动查询结果，并且会阻塞。
 * 2、Netty对FChannelFuture进行了增强，通过ChannelFutureListener以回调方法的形式来获取执行的结果
 * 3、ChannelFutureListener的operationComplete方法是由EventLoop中的线程执行的，所以不要在这个方法里面执行耗时操作
 *
 * 在netty中有两种消息发送的方式，一种是可以直接写到channel中，另一种是写到与channelHandler所关联的channelHandlerContext中，
 * 对于前一种方式来说，消息会从channelPipeLine的末尾开始流动，对于后一种方式来说，消息将会从channelPipeline的下一个handler
 * 开始流动
 *
 * @Author YouXu
 * @Date 2019/6/27 11:31
 *
 **/
public class Test01 {
    public static void main(String[] args) {
        //线程数=cpu核心数量*2
        int max = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(max);
    }
}
