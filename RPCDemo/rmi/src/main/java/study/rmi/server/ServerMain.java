package study.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;
import study.rmi.api.HelloService;

/**
 * 服务端RMI服务启动代码
 */
public class ServerMain {

  public static void main(String[] args) throws Exception {
    // 创建服务
    HelloService helloService = new HelloServiceImpl();
    // 注册服务
    LocateRegistry.createRegistry(8801);
    // 指定通信端口，防止被防火墙拦截
    RMISocketFactory.setSocketFactory(new CustomerSocketFactory());
    Naming.bind("rmi://localhost:8801/helloService", helloService);
    System.out.println("ServerMain provide RPC　service now");
  }
}
