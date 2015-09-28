package com.baasbox.jaasbox.requests;

import java.util.Map;

import retrofit.Callback;

import com.baasbox.jaasbox.BBRequest;
import com.baasbox.jaasbox.BaasboxAPI;
import com.baasbox.jaasbox.responses.UserLoginResponse;
import com.google.common.collect.Maps;

public class UserSignupRequest extends BBRequest<UserLoginResponse> {
  private String username;
  private String password;
  private Map<String, Object> visibleByTheUser = Maps.newHashMap();
  private Map<String, Object> visibleByFriends = Maps.newHashMap();
  private Map<String, Object> visibleByRegisteredUser = Maps.newHashMap();
  private Map<String, Object> visibleByAnonymousUser = Maps.newHashMap();

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Map<String, Object> getVisibleByTheUser() {
    return visibleByTheUser;
  }

  public Map<String, Object> getVisibleByFriends() {
    return visibleByFriends;
  }

  public Map<String, Object> getVisibleByRegisteredUser() {
    return visibleByRegisteredUser;
  }

  public Map<String, Object> getVisibleByAnonymousUser() {
    return visibleByAnonymousUser;
  }

  private UserSignupRequest(){}
  
  @Override
  protected void makeRequest(BaasboxAPI api, Callback<UserLoginResponse> callback) {
    api.signupUser(this, callback);
  }
  
  public static class Builder extends BaseBuilder<UserSignupRequest> {
    UserSignupRequest request;
    
    public Builder() {
      this.request = new UserSignupRequest();

    }

    public Builder withUsername(String username) {
      this.request.username = username;
      return this;
    }

    public Builder withPassword(String password) {
      this.request.password = password;
      return this;
    }
    public Builder addToVisibleByUser(String key,Object value){
      addToMap(this.request.visibleByTheUser, key, value);
      return this;
    }

    public Builder addToVisibleByFriends(String key, Object value) {
      addToMap(this.request.visibleByFriends, key, value);
      return this;
    }
    
    public Builder addToVisibleByRegisteredUser(String key, Object value) {
      addToMap(this.request.visibleByRegisteredUser, key, value);
      return this;
    }

    public Builder addToVisibleByAnonymousUser(String key, Object value) {
      addToMap(this.request.visibleByAnonymousUser, key, value);
      return this;
    }

    private void addToMap(Map<String,Object> map,String key,Object value){
      map.put(key, value);
    }
    
    public UserSignupRequest createInstance() {
      return this.request;
    }

    @Override
    protected void validate() {
    }
  }

}
