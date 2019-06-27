package com.ssy.nettySrcAnalysis;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @description
 * reactor(反应器)模式
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
