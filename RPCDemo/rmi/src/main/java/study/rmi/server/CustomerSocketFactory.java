package study.rmi.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * RMI 的通信接口是随机产生的，因此有可能会被防火墙拦截。
 * 为了防止被防火墙拦截，需要强制指定RMI的通信端口。
 * 一般通过自定义一个 RMISocketFactory类来实现
 */
public class CustomerSocketFactory extends RMISocketFactory{

  // 指定通信端口，防止被防火墙拦截
  public Socket createSocket(String host, int port) throws IOException {
    return new Socket(host, port);
  }

  public ServerSocket createServerSocket(int port) throws IOException {
    if (port == 0) {
      port = 8501;
    }
    System.out.println("rmi notify port:" + port);
    return new ServerSocket(port);
  }
}
