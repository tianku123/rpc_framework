package study.jaxws;

import javax.xml.ws.Endpoint;

/**
 * Created by admin on 2018/9/15.
 */
public class Main {


  public static void main(String[] args) {
    // 发布该服务
    Endpoint.publish("http://localhost:8080/study/jaxws/HelloJaxWs", new HelloJaxWsImpl());
    System.out.println("发布服务： http://localhost:8080/study/jaxws/HelloJaxWs?wsdl ");
  }

}
