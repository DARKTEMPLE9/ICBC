package net.northking.iacmp.cms.web.controller.filetrans;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fileTrans")
public class FileTransController {

    /*@RequestMapping("/upload/{fileName}")
    public String fileTransUpload(Model model, @PathVariable(value = "fileName") String fileName) {
        if (UploadUtil.uploadTrans(
                "/opt/cms/software/alidata/data/buffer/" + fileName,
                fileName, "user/testCUT/image_dir/opt/cms/software/alidata/data/dest/" + fileName)) {
            model.addAttribute("errorMessage", "文件[" + fileName + "]上传成功！");
        } else {
            model.addAttribute("errorMessage", "文件[" + fileName + "]上传失败！");
        }

        return "error/business";
    }

    @RequestMapping("/download/{fileName}")
    public String fileTransDownload(Model model, @PathVariable(value = "fileName") String fileName) {
        if (UploadUtil.downloadTrans(
                "/opt/cms/software/alidata/data/dest/",
                fileName, "user/testCUT/image_dir/opt/cms/software/alidata/data/dest/" + fileName)) {
            model.addAttribute("errorMessage", "文件[" + fileName + "]下载成功！");
        } else {
            model.addAttribute("errorMessage", "文件[" + fileName + "]下载失败！");
        }

        return "error/business";
    }*/
}
