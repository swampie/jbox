package com.baasbox.api.jbox;

import retrofit.client.OkClient;

import com.baasbox.jaasbox.AuthPolicy;
import com.baasbox.jaasbox.BBCallback;
import com.baasbox.jaasbox.BBException;
import com.baasbox.jaasbox.BBRequest;
import com.baasbox.jaasbox.BBResponse;
import com.baasbox.jaasbox.JboxHttp;
import com.baasbox.jaasbox.auth.AppCodeInterceptor;
import com.baasbox.jaasbox.auth.SessionTokenInterceptor;
import com.baasbox.jaasbox.requests.UserLoginRequest;
import com.baasbox.jaasbox.requests.UserSignupRequest;
import com.baasbox.jaasbox.responses.UserLoginResponse;
import com.squareup.okhttp.OkHttpClient;

public class Jbox {

  String baseUrl;
  String username;
  String password;
  String appcode;
  JboxHttp http;
  OkHttpClient client;
  String sessionToken;
  public AuthPolicy policy;

  protected Jbox(String baseUrl) {
    this.baseUrl = baseUrl;
  }




  public <T extends BBResponse> T sendRequest(BBRequest<T> req) throws BBException {
    return http.sendRequest(req);
  }

  public <T extends BBResponse> void sendRequest(BBRequest<T> req, BBCallback<T> callback) throws BBException {
    http.sendRequest(req, callback);
  }

  public static class Builder{

    private Jbox jbox;
    private AuthPolicy authPolicy = AuthPolicy.TOKEN;
    private OkHttpClient client = new OkHttpClient();

    public Builder(String baseUrl, String appcode) {
      jbox = new Jbox(baseUrl);
      jbox.appcode = appcode;
    }

    public Jbox login(String username, String password, String appcode, BBCallback<UserLoginResponse> callback) {
      jbox = build();
      jbox.http.sendRequest(new UserLoginRequest(username, password, appcode), new BBCallback<UserLoginResponse>() {

        @Override
        public void success(UserLoginResponse response) {
          jbox.sessionToken = response.getSessionToken();
          callback.success(response);
        }

        @Override
        public void failure(BBException exception) {
          callback.failure(exception);
        }

      });
      return jbox;
    }

    public Jbox build() {
      this.client.interceptors().add(new AppCodeInterceptor(() -> jbox.appcode));
      if (this.authPolicy.equals(AuthPolicy.TOKEN)) {
        this.client.interceptors().add(new SessionTokenInterceptor(() -> jbox.sessionToken));
      }
      jbox.http = new JboxHttp(new OkClient(this.client), jbox.baseUrl, new Integer(4), this.authPolicy);
      return jbox;
    }

    public Builder withSessionToken(String sessionToken) {
      jbox.sessionToken = sessionToken;
      authPolicy = AuthPolicy.TOKEN;
      return this;
    }

    public Builder withUsernameAndPassword(String username, String password) {
      jbox.username = username;
      jbox.password = password;
      jbox.policy = AuthPolicy.BASIC_AUTH;
      return this;
    }

    public Builder withClient(OkHttpClient client) {
      this.client = client;
      return this;
    }

    public Jbox signup(UserSignupRequest req, BBCallback<UserLoginResponse> callback) {
      jbox = build();
      jbox.http.sendRequest(req, new BBCallback<UserLoginResponse>() {

        @Override
        public void success(UserLoginResponse response) {
          jbox.sessionToken = response.getSessionToken();
          callback.success(response);
        }

        @Override
        public void failure(BBException exception) {
          callback.failure(exception);
        }

      });
      return jbox;
    }

  }
}
