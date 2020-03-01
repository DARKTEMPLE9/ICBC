package com.oauth.security.controller;

import com.oauth.security.common.RestResponse;
import com.oauth.security.constants.OAuthConstant.Permissions;
import com.oauth.security.constants.OAuthConstant.Roles;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likai
 * @Date 2018/10/18
 */
@RestController
public class RestApiController {

  @RequestMapping("/add")
  @RequiresPermissions(Permissions.INSERT)
  public RestResponse<String> add() {
    return RestResponse.OK("add success");
  }

  @PostMapping("/find")
  @RequiresPermissions(Permissions.SELECT)
  public RestResponse<String> find() {
    return RestResponse.OK("find success");
  }

  @PostMapping("/update")
  @RequiresPermissions(Permissions.UPDATE)
  public RestResponse<String> update() {
    return RestResponse.OK("update success");

  }

  @PostMapping("/delete")
  @RequiresPermissions(Permissions.DELETE)
  public RestResponse<String> delete() {
    return RestResponse.OK("delete success");

  }

  @PostMapping("/find/_user")
  @RequiresRoles(Roles.USER)
  public RestResponse<String> user() {
    return RestResponse.OK("user success");

  }

  @PostMapping("/find/_admin")
  @RequiresRoles(Roles.ADMIN)
  public RestResponse<String> admin() {
    return RestResponse.OK("admin success");

  }

  @GetMapping("/list")
  @RequiresRoles(Roles.USER)
  @RequiresPermissions(Permissions.SELECT)
  public RestResponse<String> list() {
    return RestResponse.OK("list success");

  }

  @RequestMapping("/access")
  public RestResponse<String> test() {
    return RestResponse.OK("access success");
  }

}
