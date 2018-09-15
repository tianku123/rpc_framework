package study.jaxws;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Created by admin on 2018/9/15.
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface HelloJaxWs {

  @WebMethod
  String sayHello();

  /**
   * 有参数
   */
  @WebMethod
  void SayHi(@WebParam(name = "name")String name);

  /**
   * 有参数，有返回值
   */
  @WebMethod
  @WebResult(name = "valid")
  int checkTime(@WebParam(name = "clientTime")Date clientTime);

}
