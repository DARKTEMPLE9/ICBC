package net.northking.iacmp.ams.web.controller.system;

/*import cn.com.agree.bxbank.sso.domain.TokenAuthResponse;
import cn.com.agree.bxbank.sso.domain.UserInfoResponse;*/

import lombok.extern.slf4j.Slf4j;
import net.northking.iacmp.ams.web.sso.service.TokenAuthService;
import net.northking.iacmp.ams.web.sso.service.UserInfoService;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.ServletUtils;
import net.northking.iacmp.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 登录验证
 *
 * @author wxy
 */
@Slf4j
@Controller
public class SysLoginController extends BaseController {
    /**
     * 登录方式
     */
    @Value("${loginType}")
    private String loginType;

    @Autowired
    private TokenAuthService tokenAuthService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        log.info("to login");
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        if (username == null || password == null) {
            return error("用户名和密码不能为空");
        }
        log.info("login by username:{" + username + "}");
        if (loginType.equalsIgnoreCase("SSO")) {
            return error("请使用SSO登录");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    /*@PostMapping({"/login"})
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        log.debug("login by username:{}", username);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe.booleanValue());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }*/

    public static final String BCGSSO = "BCGSSO00000";
    /* */

    /**
     * 单点登录
     *//*
    @GetMapping("/ssoLogin")
    @ResponseBody
    public void ssoLogin() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setContentType("text/html;charset=utf-8");
        String code = ServletUtils.getRequest().getParameter("userid");
        log.info("单点登录userid="+code);
        String ssoToken = ServletUtils.getRequest().getParameter("token");
        log.info("单点登录token="+ssoToken);
//        String remoteAddr = ServletUtils.getRequest().getRemoteAddr();
        String remoteAddr = ServletUtils.getRequest().getHeader("X-Forwarded-For");   //NGINX代理获取真实ip
        log.info("----------remoteAddr:"+remoteAddr+"-----------");
        try {
            if (code == null || ssoToken == null || code.equals("") || ssoToken.equals("")) {
                log.info("token或者userid为空");
            }
            log.info("进行令牌认证");
            String [] addrs = remoteAddr.split(",");
            for (int i=0;i<addrs.length;i++){
                String addr = addrs[i].trim();
                TokenAuthResponse tokenAuthResponse = tokenAuthService.verifyToken(code, ssoToken, "ams", addr, false, true);
                String retCode = tokenAuthResponse.getCode();
                String setMsg = tokenAuthResponse.getMsg();
                log.info("发送第"+i+"次ip："+addr+"，令牌认证结果:" + retCode + "_" + setMsg);
                if (!BCGSSO.equals(retCode) && i <addrs.length-1){
                    continue;
                }
                if (!BCGSSO.equals(retCode)) {
                    try(PrintWriter out = response.getWriter()) {
                        out.write("<script>alert('令牌认证未通过')</script>");
                    } catch (IOException e1) {
                        log.error("ssoError:",e1);
                    }
                } else if (!tokenAuthResponse.isChkResult()){
                    log.info("ssoError:"+"错误码：" + retCode + "错误信息：" + setMsg);
                }else if (BCGSSO.equals(retCode)){
                    log.info("ssoLogin by username:{"+ code+"}");
                    SysUser user = userService.selectUserByLoginName(code);
                    if (user == null){      //用户不存在，同步用户
                        UserInfoResponse userInfoRsp = userInfoService.queryUserInfo(code, "ams");
                        if (BCGSSO.equals(userInfoRsp.getCode())) {
                            SysUser newUser = new SysUser();
                            newUser.setLoginName(userInfoRsp.getUserId());
                            newUser.setUserName(userInfoRsp.getUserName());
                            newUser.setEmail(userInfoRsp.getUserMail());
                            newUser.setPhonenumber(userInfoRsp.getPhoneno());
                            newUser.setStatus("0");
                            newUser.setDelFlag("0");
                            newUser.setCreateBy("admin");
                            newUser.setCreateTime(new Date());
                            userService.insertUser(newUser);
                        }
                    }
                    UsernamePasswordToken token = new UsernamePasswordToken(code, Constants.SSO_PW, false);
                    Subject subject = SecurityUtils.getSubject();
                    try {
                        subject.login(token);
                        response.sendRedirect("index");
                    } catch (AuthenticationException e) {
                        log.error("ssoError",e);

                    }
                    break;
                }

            }

        } catch (Exception e) {
            log.error("ssoError:",e.fillInStackTrace());
            try(PrintWriter out = response.getWriter()) {
                out.write("<script>alert('令牌认证未通过')</script>");
            } catch (IOException e1) {
                log.error("ssoError:",e1.fillInStackTrace());
            }
        }

    }*/
    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
