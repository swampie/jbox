package com.baasbox.jaasbox.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleModel {

  private String name;
  @JsonProperty("isrole")
  private boolean role;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isRole() {
    return role;
  }

  public void setRole(boolean role) {
    this.role = role;
  }

}
