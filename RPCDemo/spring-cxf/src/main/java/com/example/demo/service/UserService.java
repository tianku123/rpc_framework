package com.example.demo.service;

import com.example.demo.model.User;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by admin on 2018/9/15.
 */
@WebService
public interface UserService {
  @WebMethod//标注该方法为webservice暴露的方法,用于向外公布，它修饰的方法是webservice方法，去掉也没影响的，类似一个注释信息。
  public User getUser(@WebParam(name = "userId") String userId);

  @WebMethod
  @WebResult(name="String",targetNamespace="")
  public String getUserName(@WebParam(name = "userId") String userId);

}
