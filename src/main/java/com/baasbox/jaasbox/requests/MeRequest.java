package com.baasbox.jaasbox.requests;

import retrofit.Callback;

import com.baasbox.jaasbox.BBRequest;
import com.baasbox.jaasbox.BaasboxAPI;
import com.baasbox.jaasbox.responses.UserResponse;

public class MeRequest extends BBRequest<UserResponse> {

  @Override
  protected void makeRequest(BaasboxAPI api, Callback<UserResponse> callback) {
    api.me(callback);
  }

}
