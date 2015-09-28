package com.baasbox.jaasbox.responses;

import com.baasbox.jaasbox.BBResponse;

public class UserLoginResponse extends BBResponse {


  public String getSessionToken() {
    return (String) super.data.get("X-BB-SESSION");
  }

}
