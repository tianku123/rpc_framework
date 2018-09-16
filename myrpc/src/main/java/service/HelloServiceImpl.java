package service;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String hello) {
        return "hello," + hello;
    }
}
