package com.ssy.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/6/15 16:14
 **/
public class ThriftServer {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(serverSocket);
        arg.minWorkerThreads(2).maxWorkerThreads(4);

        PersonService.Processor<PersonServiceImpl> personServiceProcessor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(personServiceProcessor));

        THsHaServer server = new THsHaServer(arg);

        System.out.println("Thrift Server Started!");

        server.serve();

    }
}
