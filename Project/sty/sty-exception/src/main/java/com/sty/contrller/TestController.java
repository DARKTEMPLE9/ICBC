package com.sty.contrller;

import com.sty.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/requestGet",method = RequestMethod.GET)
    @ResponseBody
    public String requestGet() {
        return "get请求";
    }

    @RequestMapping(value = "/requestPost",method = RequestMethod.POST)
    @ResponseBody
    public String requestPost() {
        return "post请求";
    }

    @RequestMapping(value = "runTimeException",method = RequestMethod.GET)
    @ResponseBody
    public String runTimeException() {
        ArrayList<String> strList = new ArrayList<>();
        strList.get(3);
        return "测试-runTimeException";
    }

    @RequestMapping(value = "businessException",method = RequestMethod.GET)
    public ModelAndView businessException() {
        throw new BusinessException("测试抛出业务异常");
    }

}
