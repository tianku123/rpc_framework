package study.thrift.invoker;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
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
 * 异步非阻塞模式
 */
public class NonblockingInvoker {

  /**
   * 启动服务
   */
  public void startServer() throws TTransportException {
    // 创建processor
    TProcessor tProcessor = new Processor<HelloService.Iface>(new HelloServiceImpl());
    // 服务端口
    int port = 8091;
    // 创建 transport
    TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
    // 创建protocol
    TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();
    // 创建 transport 数据传输方式，非阻塞需要用这种方式传输
    TFramedTransport.Factory transport = new TFramedTransport.Factory();
    TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
    args.processor(tProcessor);
    args.transportFactory(transport);
    args.protocolFactory(protocol);
    // 定义服务器, 类型是非阻塞
    TServer server = new TNonblockingServer(args);
    // 开启服务
    server.serve();
  }

  /**
   * 客户端调用服务器
   */
  public void startClient() throws Exception {
    String ip = "127.0.0.1";
    int port = 8091;
    int timeOut = 1000;
    TAsyncClientManager clientManager = new TAsyncClientManager();
    // 创建 Transport
    TNonblockingTransport tTransport = new TNonblockingSocket(ip, port, timeOut);
    // 创建 TProtocol
    TProtocolFactory protocol = new TCompactProtocol.Factory();
    HelloService.AsyncClient asyncClient = new HelloService.AsyncClient(protocol, clientManager, tTransport);

    // 调用 client 方法
    User user = new User();
    user.setName("ruanhao");
    user.setEmail("163.com");
    CountDownLatch latch = new CountDownLatch(1);
    // 设置回调接口实现
    AsynInvokerCallback callback = new AsynInvokerCallback(latch);
    asyncClient.sayHello(user, callback);
    // 等待调用返回
    latch.await(5, TimeUnit.SECONDS);

  }
}
