package com.icbc.common.export_poi.exportContrtoller;

import com.icbc.common.export_poi.exportService.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("excel")
@Controller
public class excelController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/excel" ,method = {RequestMethod.GET,RequestMethod.POST})
    public void execl(HttpServletResponse response){
        excelService.excel();
    }

}
