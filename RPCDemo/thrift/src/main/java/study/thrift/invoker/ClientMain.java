package study.thrift.invoker;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by admin on 2018/9/15.
 */
public class ClientMain {

  public static void main(String[] args) throws TException {
    SimpleInvoker invoker = new SimpleInvoker();
    invoker.startClient();
  }
}
