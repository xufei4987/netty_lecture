package com.ssy.nettySrcAnalysis;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 *      +-------------------+------------------+------------------+
 *      | discardable bytes |  readable bytes  |  writable bytes  |
 *      |                   |     (CONTENT)    |                  |
 *      +-------------------+------------------+------------------+
 *      |                   |                  |                  |
 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
 *
 * --------------------------------------------------------------------------
 *
 *   BEFORE discardReadBytes()
 *
 *       +-------------------+------------------+------------------+
 *       | discardable bytes |  readable bytes  |  writable bytes  |
 *       +-------------------+------------------+------------------+
 *       |                   |                  |                  |
 *       0      <=      readerIndex   <=   writerIndex    <=    capacity
 *
 *
 *   AFTER discardReadBytes()
 *
 *       +------------------+--------------------------------------+
 *       |  readable bytes  |    writable bytes (got more space)   |
 *       +------------------+--------------------------------------+
 *       |                  |                                      |
 *  readerIndex (0) <= writerIndex (decreased)        <=        capacity
 *
 *  ----------------------------------------------------------------------------
 *
 *  BEFORE clear()
 *
 *        +-------------------+------------------+------------------+
 *        | discardable bytes |  readable bytes  |  writable bytes  |
 *        +-------------------+------------------+------------------+
 *        |                   |                  |                  |
 *        0      <=      readerIndex   <=   writerIndex    <=    capacity
 *
 *
 *  AFTER clear()
 *
 *        +---------------------------------------------------------+
 *        |             writable bytes (got more space)             |
 *        +---------------------------------------------------------+
 *        |                                                         |
 *        0 = readerIndex = writerIndex            <=            capacity
 *
 *Netty ByteBuf 三种缓冲区类型：
 * 1、heap buffer 堆内缓冲区
 * 2、direct buffer 堆外缓冲区
 * 3、composite buffer 复合缓冲区
 *
 * Heap Buffer: 将数据存储到堆空间中，并将实际的数据存放到ByteArray中实现。
 * 优点：由于数据是存储在堆中，因此可以快速创建与释放，并且提供了直接访问内部字节数组的方法
 * 缺点：每次读写数据时，需要将数据复制到直接缓冲区中再进行网络的传输。
 *
 * Direct Buffer：在堆之外直接分配内存空间，并不会占用堆的容量空间，它是由操作系统在本地内存进行的数据分配
 * 优点：在使用socket进行数据传递时性能非常好，因为数据直接位于操作系统的本地内存中，所有不需要从堆将数据复制到直接缓冲区中，性能很好。
 * 缺点：因为Direct Buffer是直接在操作系统内存中的，所有内存的分配与释放比堆空间更加的复杂。
 * Netty通过提供内存池来管理Direct Buffer,直接缓冲区并不支持通过数组的方式来访问数据。
 * 注意：对于后端的业务消息的编解码来说，推荐使用Heap Buffer，对于I/O通信线程在读写缓冲区时，推荐使用Direct Buffer。
 *
 * Composite Buffer：复合缓冲区
 *
 * JDK的ByteBuffer与netty的ByteBuf的区别：
 * 1、Netty采用读写索引分离的策略，一个初始化的ByteBuf的ReaderIndex与WriterIndex都为0
 * 2、当readerIndex==writerIndex时就读取完毕了
 * 3、对于ByteBuf的任何读写操作都会分别维护读索引与写索引，maxCapacity最大容量默认为Integer.Max_VALUE
 * JDK的ByteBuffer的缺点：
 * 1、final byte[] bh是jdk的HeapByteBuffer对象中用于存储数据的对象声明。可以看到，其字节数组被声明为final，也就是说长度时固定不变的，且bh的引用
 * 指向不能发生改变，一旦分配好了就不能进行动态的扩容。如果需要扩容，则需要创建一个新的HeapByteBuffer对象，并将旧的对象中的数据复制过去，这一切都
 * 需要我们开发者自己手动完成。
 * 2、ByteBuffer只使用一个position指针来标示位置信息，在进行写入buffer后进行读取时需要用flip方法进行切换，使用起来不方便。
 * Netty的ByteBuf的优点：
 * 1、存储数据的字节数组是动态的，最大值为Integer.Max_VALUE，这里的动态性体现在write方法中，write方法在执行时会判断buffer的容量，如果不足会
 * 进行自动扩容。
 * 2、ByteBuf的读写索引是分开的，使用起来很方便。
 *
 * AtomicIntegerFieldUpdater使用要点：
 * 1、更新器更新的必须是int类型的变量，不能是其他包装类型
 * 2、更新器更新的必须是volatile修饰的变量，确保在更新时共享变量在各个线程中的可见性
 * 3、变量不能是static的，必须要是实例变量，因为Unsafe.objectFieldOffset()方法不支持静态变量，CAS操作本质上是通过对象实例的偏移量来进行赋值的
 * 4、更新器只能修改它可见范围内的变量，因为更新器是通过反射来得到变量的
 * 如果更新的变量是保证类型，我们可以使用AtomicReferenceFieldUpdater
 *
 * volatile的作用
 * 1、防止指令重排
 * 2、保证变量在线程间的可见性
 */


public class ByteBufTest01 {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < buffer.capacity(); i++){
            buffer.writeByte(i);
        }

        for (int i = 0; i < buffer.capacity(); i++){
            System.out.println(buffer.getByte(i));
        }
    }
}
