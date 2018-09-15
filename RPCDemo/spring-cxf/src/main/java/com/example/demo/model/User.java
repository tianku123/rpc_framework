package com.example.demo.model;

import java.io.Serializable;

/**
 * Created by admin on 2018/9/15.
 */
public class User implements Serializable {
  private String userId;
  private String userName;
  private String email;
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  @Override
  public String toString() {
    return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + "]";
  }
}
