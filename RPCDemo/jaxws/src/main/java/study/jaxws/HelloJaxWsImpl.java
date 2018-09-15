package study.jaxws;

import java.util.Date;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Created by admin on 2018/9/15.
 */
@WebService(endpointInterface = "study.jaxws.HelloJaxWs")
@SOAPBinding(style = Style.RPC)
public class HelloJaxWsImpl implements HelloJaxWs {

  public String sayHello() {
    return "Hello, JAX-WS";
  }

  public void SayHi(String name) {
    System.out.println("Hi, " + name);
  }

  public int checkTime(Date clientTime) {
    return new Date().equals(clientTime) ? 1 : 0;
  }

}
