package study.thrift.invoker;

import org.apache.thrift.transport.TTransportException;

/**
 * Created by admin on 2018/9/15.
 */
public class ServerMain {

  public static void main(String[] args) throws TTransportException {
    SimpleInvoker invoker = new SimpleInvoker();
    invoker.startServer();
  }
}
