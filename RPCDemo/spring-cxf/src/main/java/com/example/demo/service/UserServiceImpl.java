package com.example.demo.service;

import com.example.demo.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.jws.WebService;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/9/15.
 */
@WebService(serviceName="UserService",//对外发布的服务名
    targetNamespace="http://service.demo.example.com",//指定你想要的名称空间，通常使用使用包名反转
    endpointInterface="com.example.demo.service.UserService")//服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
@Component
public class UserServiceImpl implements UserService {

  private Map<String, User> userMap = new HashMap<String, User>();
  public UserServiceImpl() {
    System.out.println("向实体类插入数据");
    User user = new User();
    user.setUserId(UUID.randomUUID().toString().replace("-", ""));
    user.setUserName("test1");
    user.setEmail("maplefix@163.xom");
    userMap.put(user.getUserId(), user);

    user = new User();
    user.setUserId(UUID.randomUUID().toString().replace("-", ""));
    user.setUserName("test2");
    user.setEmail("maplefix@163.xom");
    userMap.put(user.getUserId(), user);

    user = new User();
    user.setUserId(UUID.randomUUID().toString().replace("-", ""));
    user.setUserName("test3");
    user.setEmail("maplefix@163.xom");
    userMap.put(user.getUserId(), user);
  }
  @Override
  public String getUserName(String userId) {
    return "userId为：" + userId;
  }
  @Override
  public User getUser(String userId) {
    System.out.println("userMap是:"+userMap);
    return userMap.get(userId);
  }

}
