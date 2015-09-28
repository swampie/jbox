package com.baasbox.jaasbox;


public interface BBCallback<T extends BBResponse> {

  public void success(T response);

  public void failure(BBException exception);

  /**
   *
   * This is a synchronously implemented Callback. Consumers should use the `get` method which will either return the
   * result from the callback, or throw the exception that encountered
   *
   */
  public static class SyncFCCallback<T extends BBResponse> implements BBCallback<T> {
      private T resp;
      private BBException t;

      @Override
      public synchronized void success(T response) {
          this.resp = response;
          notify();
      }

      @Override
      public synchronized void failure(BBException exception) {
          this.t = exception;
          notify();
      }

      public synchronized T get() throws BBException, InterruptedException {
          while (this.t == null && this.resp == null) {
              wait();
          }
          if (this.t != null) {
              throw t;
          }
          return this.resp;
      }
  }
}