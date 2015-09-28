package com.baasbox.jaasbox.requests;

import retrofit.Callback;

import com.baasbox.jaasbox.BBRequest;
import com.baasbox.jaasbox.BaasboxAPI;
import com.baasbox.jaasbox.responses.LogoutResponse;

public class LogoutRequest extends BBRequest<LogoutResponse> {

  private String pushToken;

  @Override
  protected void makeRequest(BaasboxAPI api, Callback<LogoutResponse> callback) {
    api.logoutUser(this.pushToken, callback);
  }

}
