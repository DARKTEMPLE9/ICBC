package com.icbc.common.export_poi.exportContrtoller;

import com.icbc.common.export_poi.entity.Export_a;
import com.icbc.common.export_poi.exportService.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * 导出excel
 * */
@RequestMapping("excel")
@Controller
public class ExcelController {

    @Autowired
    private ExcelService excelService;


    @RequestMapping(value = "/excel", method = {RequestMethod.GET, RequestMethod.POST})
    public void execl(HttpServletResponse response) {
        excelService.excel();
    }


    @GetMapping("/inworkTest")
    public void inworkTest() {
        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();
        map.put("row_id", "测试一");
        map.put("name", "测试一");
        map.put("sex", 1);
        map.put("height", "测试一");
        list.add(map);

        List<String> valList = new ArrayList<>();
        valList.add("row_id");
        valList.add("name");
        valList.add("sex");
        valList.add("height");

        Export_a export_a = new Export_a();
        for (String s : valList) {
            Object o = map.get(s);
            try {
                setPropertyVal(s, export_a, o);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("row_id", "测试一");
        map.put("name", "测试一");
        map.put("sex", 1);
        map.put("height", "测试一");
        list.add(map);

        List<String> valList = new ArrayList<>();
        valList.add("row_id");
        valList.add("name");
        valList.add("sex");
        valList.add("height");

        Export_a export_a = new Export_a();
        for (String s : valList) {
            Object o = map.get(s);
            try {
                setPropertyVal(s, export_a, o);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setPropertyVal(String propertyName, Object s, Object newVal)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, s.getClass());
        Method setMethod = pd.getWriteMethod();
        setMethod.invoke(s, newVal);
    }


}



