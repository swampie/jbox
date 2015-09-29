package com.baasbox.jaasbox.requests;

import retrofit.Callback;

import com.baasbox.jaasbox.BBRequest;
import com.baasbox.jaasbox.BBResponse;
import com.baasbox.jaasbox.BaasboxAPI;

public class SuspendMeRequest extends BBRequest<BBResponse> {

  @Override
  protected void makeRequest(BaasboxAPI api, Callback<BBResponse> callback) {
    api.suspendMe(callback);
  }

}
