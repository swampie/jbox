package com.baasbox.jaasbox;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.baasbox.jaasbox.requests.UserLoginRequest;
import com.baasbox.jaasbox.requests.UserSignupRequest;
import com.baasbox.jaasbox.responses.LogoutResponse;
import com.baasbox.jaasbox.responses.UserResponse;

public interface BaasboxAPI {
	
  @POST(value = "/user")
  public void signupUser(@Body UserSignupRequest request, Callback<UserResponse> response);

  @POST(value = "/login")
  public void loginUser(@Body UserLoginRequest userLoginRequest, Callback<UserResponse> callback);
	
  @GET(value = "/me")
  public void me(Callback<UserResponse> response);

  @POST(value = "/logout/{pushToken}")
  public void logoutUser(@Path("user") String pushToken, Callback<LogoutResponse> callback);

  @POST(value = "/me/suspend")
  public void suspendMe(Callback<BBResponse> callback);

}
