package com.baasbox.jaasbox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RequestExecutor {
 
  protected final ExecutorService executorService;
  
  public RequestExecutor(Integer threadCount) {
    this.executorService = Executors.newFixedThreadPool(threadCount);
  }

  
  public <T extends BBResponse> void sendRequestAsync(final BaasboxAPI api, final BBRequest<T> req,
    final BBRetrofitCallback<T> callback){
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        req.makeRequest(api, callback);
      }
    });
  }
}

  
