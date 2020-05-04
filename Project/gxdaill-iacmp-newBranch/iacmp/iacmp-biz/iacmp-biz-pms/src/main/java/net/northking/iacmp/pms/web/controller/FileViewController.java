package net.northking.iacmp.pms.web.controller;

import net.northking.iacmp.cms.service.ICmsFileService;
import net.northking.iacmp.cms.service.ICmsImageService;
import net.northking.iacmp.common.bean.domain.cms.CmsFile;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.framework.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/pdf/pms/fileView")
public class FileViewController {
    private DBstep.iMsgServer2000 msgObj = new DBstep.iMsgServer2000();
    private String txtString;
    private static Logger log = LoggerFactory.getLogger(FileViewController.class);
    @Autowired
    private ICmsFileService cmsFileService;
    @Autowired
    private ICmsImageService imageService;

    @PostMapping("/OfficeView/{fileImageId}")
    public void OfficeView(@PathVariable("fileImageId") String fileImageId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IWebOffice officeServer = new IWebOffice(cmsFileService, Long.valueOf(fileImageId));
        officeServer.executeRun(request, response);
    }

    /**
     * 验证文件是否可以预览
     *
     * @param fileImageId
     * @return
     */
    @GetMapping("/fileCheckEncryption/{fileImageId}")
    public AjaxResult fileCheckEncryption(@PathVariable("fileImageId") String fileImageId) {
        FileInputStream inputStream = null;
        InputStreamReader isr = null;
        File file = null;
        try {
            CmsFile cmsFile = cmsFileService.selectCmsFileByFileId(Long.valueOf(fileImageId));
            boolean b = UploadUtil.downloadTrans(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH), cmsFile.getRandomName(), cmsFile.getFilePath() + "&hadoopType=" + cmsFile.getHadoopType());
            if (!b) {
                throw new BusinessException("文件预览失败，服务器忙！");
            }
            file = new File(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH) + cmsFile.getRandomName());
            inputStream = new FileInputStream(file);

            //验证文件是否加密
            isr = new InputStreamReader(inputStream);
            char[] chars = new char[30];
            isr.read(chars);
            String str = new String(chars);
            if ("E-SafeNet".equalsIgnoreCase(str.substring(12, 21))) {
                file.delete();
                throw new BusinessException("加密文件无法预览");
            }
            return AjaxResult.success();

        } catch (IOException e) {
            return AjaxResult.error("文件预览失败，服务器忙！");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (Exception e) {
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e) {
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }

    }

    /**
     * 预览word
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/openWordView")
    public String openWordView(String id, ModelMap modelMap) {
        modelMap.put("fileImageId", id);
        return "pms/viewfile/fileDoc.html";
    }

    /**
     * 预览excel
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/openExcelView")
    public String openExcelView(String id, ModelMap modelMap) {
        modelMap.put("fileImageId", id);
        return "pms/viewfile/fileExcel.html";
    }

    /**
     * 预览pdf
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/openPdfView")
    public String openPdfView(String id, ModelMap modelMap) {
        modelMap.put("fileImageId", id);
        return "pms/viewfile/filePdf.html";
    }

    /**
     * 预览TXT
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/openTextView")
    public String openTextView(String id, ModelMap modelMap) {
        modelMap.put("fileImageId", id);
        return "pms/viewfile/fileTxt.html";
    }

    /**
     * pdf流下载
     *
     * @param fileImageId
     * @param response
     * @throws Exception
     */
    @PostMapping("/PdfView/{fileImageId}")
    public void PdfView(@PathVariable("fileImageId") String fileImageId, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream ops = null;
        FileInputStream inputStream = null;
        File file = null;
        try {
            ops = new ByteArrayOutputStream();
            CmsFile cmsFile = cmsFileService.selectCmsFileByFileId(Long.valueOf(fileImageId));
            file = new File(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH) + cmsFile.getRandomName());
            if (!file.exists()) {
                boolean b = UploadUtil.downloadTrans(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH), cmsFile.getRandomName(), cmsFile.getFilePath() + "&hadoopType=" + cmsFile.getHadoopType());
                if (!b) {
                    throw new BusinessException("文件预览失败，服务器忙！");
                }
            }

            inputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                ops.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            msgObj.MsgFileBody(ops.toByteArray()); // 将文件信息打包
            msgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
            msgObj.MsgError(""); // 清除错误信息
        } catch (Exception e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        } finally {
            try {
                ops.flush();
                ops.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
            try {
                inputStream.close();
            } catch (Exception e) {
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }
        sendPackage(response);
    }

    private void sendPackage(HttpServletResponse response) {
        try {
            ServletOutputStream outBinarry = response.getOutputStream();
            outBinarry.write(msgObj.MsgVariant());
            outBinarry.flush();
            outBinarry.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * txt流下载
     *
     * @param fileImageId
     * @param request
     * @return
     */
    @PostMapping("txtView/{fileImageId}")
    @ResponseBody
    public AjaxResult loadTxt(@PathVariable("fileImageId") String fileImageId, HttpServletRequest request) {
        ByteArrayOutputStream ops = null;
        FileInputStream inputStream = null;
        File file = null;
        try {
            ops = new ByteArrayOutputStream();
            CmsFile cmsFile = cmsFileService.selectCmsFileByFileId(Long.valueOf(fileImageId));
            file = new File(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH) + cmsFile.getRandomName());
            if (!file.exists()) {
                boolean b = UploadUtil.downloadTrans(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH), cmsFile.getRandomName(), cmsFile.getFilePath() + "&hadoopType=" + cmsFile.getHadoopType());
                if (!b) {
                    throw new BusinessException("文件预览失败，服务器忙！");
                }
            }

            inputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                ops.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            txtString = ops.toString("utf-8");
        } catch (IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        } finally {
            try {
                ops.flush();
                ops.close();
            } catch (Exception e) {
            }
            try {
                inputStream.close();
            } catch (Exception e) {
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }
        return AjaxResult.success("success", txtString);
    }
}

/**
 * IWebOffice2009
 */
class IWebOffice {
    private static Logger log = LoggerFactory.getLogger(IWebOffice.class);
    private int mFileSize;
    private byte[] mFileBody;
    private String mFileName;
    private String mFileType;
    private String mFileID;
    private int mTemplateId;
    private String mRecordID;
    private String mTemplate;
    private DBstep.iMsgServer2000 msgObj;
    private DBstep.iDBManager2000 dbaObj;
    private static final String STATUSINFO = "STATUS";
    private ICmsFileService cmsFileService;
    private Long fileImageId;

    public IWebOffice(ICmsFileService cmsFileService, Long fileImageId) {
        this.cmsFileService = cmsFileService;
        this.fileImageId = fileImageId;
    }

    //调出文档，将文档内容保存在mFileBody里
    private boolean loadFile() {
        boolean mResult = false;
        ByteArrayOutputStream ops = null;
        FileInputStream inputStream = null;
        File file = null;
        try {
            ops = new ByteArrayOutputStream();
            CmsFile cmsFile = cmsFileService.selectCmsFileByFileId(Long.valueOf(fileImageId));
            file = new File(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH) + cmsFile.getRandomName());
            if (!file.exists()) {
                boolean b = UploadUtil.downloadTrans(SysConfigInitParamsUtils.getConfig(CmsConstants.SERVER_PATH), cmsFile.getRandomName(), cmsFile.getFilePath() + "&hadoopType=" + cmsFile.getHadoopType());
                if (!b) {
                    throw new BusinessException("文件预览失败，服务器忙！");
                }

            }

            inputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                ops.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            mFileBody = ops.toByteArray();
            mResult = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        } finally {
            try {
                ops.flush();
                ops.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }
        return (mResult);
    }

    public void executeRun(HttpServletRequest request, HttpServletResponse response) {
        dbaObj = new DBstep.iDBManager2000(); //创建数据库对象
        msgObj = new DBstep.iMsgServer2000(); //创建信息包对象
        mRecordID = "";
        mTemplate = "";
        mFileBody = null;
        mFileName = "";
        mFileType = "";
        mFileSize = 0;
        mFileID = "";
        try {
            if (request.getMethod().equalsIgnoreCase("POST")) {
                if (loadFile()) {                                                   //从数据库调入文档
                    msgObj.MsgFileBody(mFileBody);                                    //将文件信息打包
                    msgObj.SetMsgByName(STATUSINFO, "打开成功!");                       //设置状态信息
                    msgObj.MsgError("");                                              //清除错误信息
                } else {
                    msgObj.MsgError("打开失败!");                                     //设置错误信息
                }
            } else {
                msgObj.MsgError("请使用Post方法");
                msgObj.MsgTextClear();
                msgObj.MsgFileClear();
            }
            log.debug("SendPackage");
            msgObj.Send(response);
        } catch (Exception e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
    }
}
