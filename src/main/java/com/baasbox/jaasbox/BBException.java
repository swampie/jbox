package com.baasbox.jaasbox;

public class BBException extends Exception {

  private Integer errorCode;

  public Integer getErrorCode() {
    return errorCode;
  }

  public BBException(String message, Throwable e) {
    super(message, e);
  }

  public BBException(String reason, Integer statusCode, Throwable ex) {
    this(reason, ex);
    this.errorCode = statusCode;
  }

}
