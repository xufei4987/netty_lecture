package com.ssy.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @description protobuf序列化和反序列化
 * @Author YouXu
 * @Date 2019/6/13 19:38
 **/
public class ProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAge(12).setAddress("深圳市").build();
        byte[] student2ByteArray = student.toByteArray();
        DataInfo.Student stu = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(stu.getName());
        System.out.println(stu.getAge());
        System.out.println(stu.getAddress());
    }
}
