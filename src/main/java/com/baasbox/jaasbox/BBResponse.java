package com.baasbox.jaasbox;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BBResponse {

  @JsonProperty("http_code")
  private int status;

  @JsonProperty("data")
  protected Map<String, Object> data;

  public int getStatus() {
    return status;
  }

  protected Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + ":" + status;
  }
}
