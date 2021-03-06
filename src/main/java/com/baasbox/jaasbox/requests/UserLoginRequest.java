package com.baasbox.jaasbox.requests;

import retrofit.Callback;

import com.baasbox.jaasbox.BBRequest;
import com.baasbox.jaasbox.BaasboxAPI;
import com.baasbox.jaasbox.responses.UserResponse;

public class UserLoginRequest extends BBRequest<UserResponse> {

  private String username;
  private String password;
  private String appcode;

  public UserLoginRequest(String username, String password, String appcode) {
    this.username = username;
    this.password = password;
    this.appcode = appcode;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getAppcode() {
    return appcode;
  }

  @Override
  protected void makeRequest(BaasboxAPI api, Callback<UserResponse> callback) {
    api.loginUser(this, callback);
  }

}
