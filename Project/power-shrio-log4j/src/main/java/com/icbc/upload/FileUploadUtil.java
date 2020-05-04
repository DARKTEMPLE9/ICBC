package com.icbc.upload;

import com.icbc.common.httpResult.HttpResult;
import com.icbc.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUploadUtil {

    public static boolean copyFile(File srcFile, File dstDir) {

        FileInputStream fileInput = null;
        FileOutputStream fileOutInput = null;
        FileChannel fileIn = null;
        FileChannel fileOut = null;
        try {
            String suffix, nameBody;
            if (!srcFile.exists() || srcFile.isDirectory()) {
                return false;
            }
            if (!dstDir.getCanonicalFile().exists()) {
                dstDir.getCanonicalFile().mkdirs();
            }
            String oldFileName = srcFile.getName();
            Pattern suffixPattern = Pattern.compile("\\.\\w+");
            Matcher matcher = suffixPattern.matcher(oldFileName);

            if (matcher.find()) {
                nameBody = oldFileName.substring(0, matcher.start());
                suffix = oldFileName.substring(matcher.start());
            } else {
                nameBody = oldFileName;
                suffix = "";
            }
            int fileNumber = 0;
            File newFile = new File(dstDir, oldFileName);
            while (newFile.exists()) {
                fileNumber++;

                String newFileName = nameBody + "-copy" + fileNumber + suffix;
                newFile = new File(dstDir, newFileName);
            }
            fileInput = new FileInputStream(srcFile);
            fileOutInput = new FileOutputStream(newFile);
            fileIn = fileInput.getChannel();
            fileOut = fileOutInput.getChannel();
            fileIn.transferTo(0L, fileIn.size(), fileOut);
        } catch (Exception e) {
            if (fileInput != null)
                try {
                    fileInput.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            if (fileIn != null)
                try {
                    fileIn.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            if (fileOutInput != null)
                try {
                    fileOutInput.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }

        } finally {

            if (fileInput != null)
                try {
                    fileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fileIn != null)
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fileOutInput != null)
                try {
                    fileOutInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }

    public static boolean uploadFileT(
            MultipartFile file, String filePath, HttpServletRequest request, Integer fileType) {

        try {
            if (null != file.getOriginalFilename() || "".equals(file.getOriginalFilename().trim())) {
                String uploadFileName = file.getOriginalFilename();
                String pattern = "";
                if (uploadFileName.contains(".")) {
                    pattern = uploadFileName.substring(uploadFileName.lastIndexOf("."));
                }

                int random6 = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
                String fileName = DateTimeUtil.farmat(new Date(), "yyyyMMddHHmmssSSS") + random6;

                String tmpFilePath = filePath + fileName;

                String str = tmpFilePath;
                File tmpFile = new File(str);
                if (!tmpFile.getParentFile().exists()) {
                    tmpFile.getParentFile().mkdirs();
                }
                file.transferTo(tmpFile);

                System.err.println(fileName);
                System.out.println(tmpFilePath);


                System.out.println(DateTimeUtil.DateToStr(new Date()));
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }

    public static boolean uploadFileT(
            MultipartFile file, String temporaryPath, HttpServletRequest request) {
        try {
            if (null != file.getOriginalFilename() || "".equals(file.getOriginalFilename().trim())) {

                String uploadFileName = file.getOriginalFilename();
                String pattern = "";

                if (uploadFileName.contains(".")) {
                    pattern = uploadFileName.substring(uploadFileName.lastIndexOf("."));
                }

                /*int random6 = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
                String fileName = DateTimeUtil.farmat(new Date(), "yyyyMMddHHmmssSSS") + random6;
*/
                String tmpFilePath = temporaryPath + File.separator + uploadFileName;

                String filterStr = tmpFilePath;
                File tmpFile = new File(filterStr);

                if (!tmpFile.getParentFile().exists()) {
                    tmpFile.getParentFile().mkdirs();
                }
                file.transferTo(tmpFile);


                System.err.println(uploadFileName);
                System.out.println(tmpFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static void downFilesEncoding(
            HttpServletRequest request,
            HttpServletResponse response,
            String filePath,
            String fileName,
            String uploadFileName,
            String serverGBK,
            String serverISO) {
        InputStream in = null;
        ServletOutputStream object = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader(
                    "Content-Disposition",
                    "attachment;fileName=" + new String(uploadFileName.getBytes(), serverGBK));
            String fileNameString = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            String path = filePath + fileNameString;
            File file = new File(path);
            in = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            object = response.getOutputStream();
            int len;
            while ((len = in.read(buffer)) > 0) {
                object.write(buffer, 0, len);
            }
            in.close();
            object.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                in.close();
                object.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        try {
            if (in != null) {
                in.close();
            }
            if (object != null) {
                object.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downFiles(
            HttpServletRequest request,
            HttpServletResponse response,
            String filePath,
            String fileName,
            String uploadFileName) {
        InputStream in = null;
        ServletOutputStream object = null;
        try {
            String name = uploadFileName;
            name = new String(name.getBytes("UTF-8"), "iso8859-1");
            response.setHeader("Content-Disposition", "attachment;fileName=" + name);
            String fileNameString = new String(fileName.getBytes("Gb2312"), "ISO-8859-1");
            String path = filePath + fileNameString;
            File file = new File(path);
            in = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            object = response.getOutputStream();
            int len;
            while ((len = in.read(buffer)) > 0) {
                object.write(buffer, 0, len);
            }
            in.close();
            object.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }
        try {
            if (in != null) {
                in.close();
            }
            if (object != null) {
                object.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
