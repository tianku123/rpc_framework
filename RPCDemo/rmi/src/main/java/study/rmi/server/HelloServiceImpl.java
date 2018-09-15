package study.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import study.rmi.api.HelloService;

/**
 * UnicastRemoteObject类，该类定义了服务调用方与服务提供方对象实例，并建立一对一的连接。
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {


  public HelloServiceImpl() throws RemoteException {
    super();
  }

  public String sayHello(String someOne) throws RemoteException {
    return "Hello," + someOne;
  }
}
