package study.rmi;

import java.rmi.Naming;
import study.rmi.api.HelloService;

/**
 * 客户端远程调用RMI服务代码
 */
public class ClientMain {

  public static void main(String[] args) throws Exception{
    // 服务引入
    HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8801/helloService");
    // 调用远程方法
    System.out.println("RMI 服务器返回的结果是：" + helloService.sayHello("ruanhao"));
  }
}
