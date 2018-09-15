package study.thrift.invoker;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import study.thrift.impl.HelloServiceImpl;
import thrift.gencode.server.HelloService;
import thrift.gencode.server.HelloService.Client;
import thrift.gencode.server.HelloService.Processor;
import thrift.gencode.server.User;

/**
 * 阻塞同步模式
 */
public class SimpleInvoker {

  /**
   * 启动服务
   */
  public void startServer() throws TTransportException {
    // 创建processor
    TProcessor tProcessor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
    // 服务端口
    int port = 8091;
    // 创建 transport
    TServerSocket serverTransport = new TServerSocket(port);
    // 创建protocol
    TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory();
    // 将 processor transport protocol 设置到服务器server中
    TServer.Args args = new TServer.Args(serverTransport);
    args.processor(tProcessor);
    args.protocolFactory(protocol);
    // 定义服务器类型设置参数
    TServer server = new TSimpleServer(args);
    // 开启服务
    server.serve();
  }

  /**
   * 客户端调用服务器
   */
  public void startClient() throws TException {
    String ip = "127.0.0.1";
    int port = 8091;
    int timeOut = 1000;
    // 创建 Transport
    TTransport tTransport = new TSocket(ip, port, timeOut);
    // 创建 TProtocol
    TProtocol protocol = new TBinaryProtocol(tTransport);
    // 基于 TTransport 和 TProtocol 创建 Client
    HelloService.Client client = new HelloService.Client(protocol);
    tTransport.open();;

    // 调用 client 方法
    User user = new User();
    user.setName("ruanhao");
    user.setEmail("163.com");
    String content = client.sayHello(user);
    System.out.println("content:" + content);

  }
}
