package com.baasbox.jaasbox.responses;

import java.util.Map;

import com.baasbox.jaasbox.BBResponse;

public class UserResponse extends BBResponse {


  public String getSessionToken() {
    return (String) super.data.get("X-BB-SESSION");
  }

  public UserModel getUser() {
    Map<String, Object> map = (Map<String, Object>) super.data.get("user");
    return super.om.convertValue(map, UserModel.class);
  }

  public String getId() {
    return (String) super.data.get("id");
  }

  public String getSignupDate() {
    return (String) super.data.get("signUpDate");
  }

}
