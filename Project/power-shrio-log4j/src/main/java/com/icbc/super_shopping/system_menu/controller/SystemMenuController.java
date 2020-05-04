package com.icbc.super_shopping.system_menu.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import com.icbc.common.httpResult.HttpResult;
import com.icbc.common.system_menu.menuService.MenuService;
import com.icbc.super_shopping.entity.MenuInf;
import com.icbc.super_shopping.entity.UserManage;
import com.icbc.super_shopping.system_menu.service.SystemtMenuService;
import com.icbc.upload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/menu")
public class SystemMenuController {


    @Autowired
    private SystemtMenuService menuService;

    @RequestMapping(value = {"/upload"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView upload() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/upload/uploadFile");
        return mv;
    }

    /*
     * 上传文件内存溢出问题  NIO在大文件及多个文件上传时处理内存溢出
     * */
    @RequestMapping(value = {"/gradeUploadFile"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean gradeUploadFile(HttpServletRequest request) {
        boolean uploadFileT;
        MultipartFile file = null;
        MultipartHttpServletRequest multRquest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multRquest.getFileMap();
        Iterator<String> iterator = fileMap.keySet().iterator();
        String fileName = "";
        while (iterator.hasNext()) {
            String next = iterator.next();
            file = multRquest.getFile(next);
            fileName = file.getOriginalFilename();
        }
        try {
            String temporaryPath = "D:\\fileupload\\temp";
            String filePath = "D:\\fileupload\\file";
            uploadFileT = FileUploadUtil.uploadFileT(file, temporaryPath, request);
            copyFileAdd(temporaryPath, filePath, fileName);
            return uploadFileT;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * 上传临时文件成功后 调用移动至正式目录
     *
     * */
    private void copyFileAdd(String realPath, String dstDir, String gradeName) throws IOException {
        String path = realPath + File.separator + gradeName;
        File file = new File(path);
        File dstFile = new File(dstDir);
        FileUploadUtil.copyFile(file, dstFile);
    }

    /*
     * 下载文件
     * */
    @RequestMapping(value = {"/downloadTemporayFile"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void downloadTemporayFile(HttpServletRequest request, HttpServletResponse response, String fileName, String uploadFileName) {
        String decode = URLDecoder.decode(URLDecoder.decode(uploadFileName));

        try {
            String filePath = "D:\\fileupload\\file";
            String serverGBK = "UTF-8";
            String serverISO = "UTF-8";
            String de = URLDecoder.decode(uploadFileName, "UTF-8");
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            String reg = "^[0-9]+(.[0-9]+)?$";
            if (fileName.matches(reg)) {
                FileUploadUtil.downFilesEncoding(request, response, filePath, fileName, de, serverGBK, serverISO);
            } else {
                throw new Exception("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String generalMenu(HttpServletRequest request, HttpServletResponse response, Model data) {
        return "";
    }

    public String blank() {
        String retHtml = "system/menu/blank";
        return retHtml;
    }


    /*
     * 跳转权限首页
     * */
    @RequestMapping(value = {"/toMenu0"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toMenu() {
        ModelAndView mv = new ModelAndView();
        UserManage userManage = new UserManage();
        userManage.setRowId("123");
        userManage.setUserName("测试");
        userManage.setUserLoginNumber("测试");
        userManage.setUserPassword("测试");
        userManage.setUserStatus(0);
        userManage.setUserPhone("测试");
        userManage.setUserDeptRowId("测试");
        userManage.setUserPositionRowId("测试");
        userManage.setUserAppRowId("测试");
        userManage.setCreateUser("测试");
        userManage.setCreateDate(new Date());
        userManage.setModifyUser("测试");
        userManage.setModifyDate(new Date());
        userManage.setPageSize(0);
        userManage.setPageNum(0);
        /*获取菜单数据*/
        Map<String, Object> accessedMenus = menuService.getMenuTree();
        mv.addObject("blank", "/logout");
        mv.addObject("accessedMenus", accessedMenus);
        String tabMaxCount = "10";
        mv.addObject("tabMaxCount", tabMaxCount);
        mv.addObject("user", userManage);
        mv.setViewName("/system/mall-web-main");
        return mv;
    }

    private void setValue(HttpServletRequest request, Model data) {
        try {
            Map<String, String[]> paramsMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                data.addAttribute(entry.getKey(), ((String[]) entry.getValue())[0]);
            }
        } catch (Exception exception) {
        }
    }


}
