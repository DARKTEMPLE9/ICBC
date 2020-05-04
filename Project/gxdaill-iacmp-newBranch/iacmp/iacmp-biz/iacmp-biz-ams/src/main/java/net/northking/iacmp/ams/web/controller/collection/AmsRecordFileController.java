package net.northking.iacmp.ams.web.controller.collection;

import net.northking.iacmp.common.bean.domain.ams.ImFile;
import net.northking.iacmp.common.bean.domain.ams.ImImage;
import net.northking.iacmp.server.service.IImFileService;
import net.northking.iacmp.server.service.IImImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 著录文件操作处理
 *
 * @author yin.rui
 * @date 2019-10-31
 */
@Controller
@RequestMapping("/file/archCollection/amsRecord")
public class AmsRecordFileController {

    private final Logger log = LoggerFactory.getLogger(AmsRecordController.class);
    @Autowired
    private IImFileService imFileService;

    @Autowired
    private IImImageService imImageService;

//    @Autowired
//    private DocumentConverter converter;

    /**
     * 转化非PDF文件为PDF并在线预览，如果没有文件则需要下载
     *
     * @return
     */
    @RequestMapping("/toPdfFile")
    @ResponseBody
    public void toPdfFile(HttpServletResponse response, String fileId) {
        ImFile cmsFile = imFileService.selectImFileById(fileId);
        ImImage imImage = null;
        if (cmsFile == null) {
            imImage = imImageService.selectImImageById(fileId);
        }
        String filePath = cmsFile != null ? cmsFile.getFilePath() : imImage.getImagePath();
        String fileType = cmsFile != null ? cmsFile.getFileType() : "";
        String fileName = cmsFile != null ? cmsFile.getFileName() : imImage.getImageName();
        log.info("filePath=" + filePath + "---fileType=" + fileType + "---fileName=" + fileName);
//        FileConversionUtils.fileToPdf(converter, response, filePath, fileType, fileName);
    }

}
