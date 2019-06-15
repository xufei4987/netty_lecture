package com.ssy.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/15 16:32
 **/
public class ThriftClient {

    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost",8899), 600);
        TCompactProtocol tCompactProtocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(tCompactProtocol);

        try {
            tTransport.open();

            Person person = client.getPersonByUsername("张三");
            System.out.println(person.toString());

            System.out.println("---------");

            Person person1 = new Person();
            person1.setName("李四").setAge(30).setMarried(true);

            client.savePerson(person1);


        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }
    }

}
