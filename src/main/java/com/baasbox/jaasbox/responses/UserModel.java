package com.baasbox.jaasbox.responses;

import java.util.List;



public class UserModel {
  private String name;
  private String status;
  private List<RoleModel> roles;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<RoleModel> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleModel> roles) {
    this.roles = roles;
  }

}
