package com.oauth.security.beans.entity;

import java.util.Set;
import javafx.scene.effect.SepiaTone;
import javax.print.DocFlavor.STRING;

/**
 * @author likai
 * @Date 2018/10/18
 */
public class LoginUser {
  private String userName;
  private String password;
  private Set<String> roles;
  private Set<String> permissions;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public Set<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<String> permissions) {
    this.permissions = permissions;
  }
}
