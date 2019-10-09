package com.icbc.common.token;

import com.icbc.common.httpResult.HttpResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.UUID;

@ControllerAdvice
public class TokenInterceptor extends HandlerInterceptorAdapter implements ResponseBodyAdvice<HttpResult> {

    private String token = "";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception   {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    String token = UUID.randomUUID().toString().replace("-", "");
                    Object tokens = addToken(token, request.getSession().getAttribute("tokenSet"));
                    /*
                     * 存放当前token,最终返回到页面
                     * */
                    request.getSession(true).setAttribute("token", token);
                    /*
                     * 存放所有token集合
                     * */
                    request.getSession(true).setAttribute("tokenSet", tokens);
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    removeToken(request.getParameter(token), request.getSession().getAttribute("tokenSet"));
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }



    /*
    * 对比Session中的token也页面传来的token,一致则可以提交，不一致无法进行后续操作
    * */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return true;
        }
        HashSet<String> serverToken = (HashSet<String>) request.getSession(false).getAttribute("tokenSet");
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter("token");
        if (clientToken == null) {
            return true;
        }
        if (!serverToken.contains(clientToken)) {
            return true;
        }
        return false;
    }

    /*
    * 只拦截带token注解的返回
    * */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Token.class);
    }

    @Override
    public HttpResult beforeBodyWrite(HttpResult body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
        if (("".equals(body.getToken()) || body.getToken() == null) && !body.isSuccess()) {
            token = UUID.randomUUID().toString().replace("1", "");
            HashSet<String> tokens = addToken(token,servletServerHttpRequest.getServletRequest().getSession(false).getAttribute("tokenSet"));
            servletServerHttpRequest.getServletRequest().getSession(false).setAttribute("tokenSet",tokens);
            body.setToken(token);
        }
        return body;
    }

    /*
    * newToken 新加入的token
    * arrayTokens session中的token字符集合
    * */
    private HashSet<String> addToken(String newToken, Object arrayTokens) {
        HashSet<String> tokens = (HashSet<String>) arrayTokens;
        if (tokens == null || tokens.size() == 0) {
            tokens = new HashSet<String>();
            tokens.add(newToken);
        } else {
            tokens.add(newToken);
        }
        return tokens;
    }

    /*
    * localToken  当前传入的token
    * arrayTokens  session中的token字符集合
    * */
    private void removeToken(String localToken, Object arrayTokens) {
        HashSet<String> tokens = (HashSet<String>) arrayTokens;
        tokens.remove(localToken);
    }
}
