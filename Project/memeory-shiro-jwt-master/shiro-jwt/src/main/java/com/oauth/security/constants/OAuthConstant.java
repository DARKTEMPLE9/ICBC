package com.oauth.security.constants;

/**
 * @author likai
 * @Date 2018/10/18
 */
public interface OAuthConstant {

  interface Roles {
    String USER = "user";
    String ADMIN = "admin";
  }

  interface Permissions {
    String SELECT = "select";
    String INSERT = "insert";
    String UPDATE = "update";
    String DELETE = "delete";
  }

  interface Code {
    int OK = 200;
    int UNAUTHENTICATED = 401;
    int UNAUTHORIZED = 403;
    int NOT_FOUND = 404;
    int SERVER_ERROR = 500;

    int BUSINESS_ERROR = 600;
  }
}
