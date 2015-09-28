package com.baasbox.jaasbox;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import com.baasbox.api.jbox.Jbox;
import com.baasbox.jaasbox.requests.UserSignupRequest;
import com.baasbox.jaasbox.responses.UserLoginResponse;
import com.squareup.okhttp.OkHttpClient;

public class ClientBuildTest {
  @Test
  public void testLogin() throws Exception {
    final CountDownLatch latch = new CountDownLatch(1);
    Jbox jbox = new Jbox.Builder("http://localhost:9000", "1234567890").withClient(new OkHttpClient()).login("admin", "admin", "1234567890",
      new BBCallback<UserLoginResponse>() {

        @Override
        public void success(UserLoginResponse response) {
          latch.countDown();
        }

        @Override
        public void failure(BBException exception) {
          exception.printStackTrace();
          latch.countDown();
        }

      });
    
    latch.await();
    


  }

  @Test
  public void testSignup() throws Exception {
    final CountDownLatch latch = new CountDownLatch(1);
    UserSignupRequest.Builder b = new UserSignupRequest.Builder().withUsername("newuser").withPassword("newpassword");
    Jbox jbox = new Jbox.Builder("http://localhost:9000", "1234567890").withClient(new OkHttpClient()).signup(b.createInstance(),
      new BBCallback<UserLoginResponse>() {

        @Override
        public void success(UserLoginResponse response) {
          latch.countDown();
        }

        @Override
        public void failure(BBException exception) {
          exception.printStackTrace();
          latch.countDown();
        }

      });

    latch.await();

  }
}
