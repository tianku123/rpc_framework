package study.rmi.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 定义RMI对外服务接口
 */
public interface HelloService extends Remote {

  String sayHello(String someOne) throws RemoteException;
}
