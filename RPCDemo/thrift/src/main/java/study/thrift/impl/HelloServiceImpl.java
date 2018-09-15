package study.thrift.impl;

import thrift.gencode.server.HelloService;

/**
 * Created by admin on 2018/9/15.
 */
public class HelloServiceImpl implements HelloService.Iface {

  @Override
  public String sayHello(thrift.gencode.server.User user) throws org.apache.thrift.TException {
    return "hello," + user.getName() + user.getEmail();
  }
}
