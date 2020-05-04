package net.northking.iacmp.utils.base64;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Description:base64解析类,实现MultipartFile接口重写方法
 * @Author: weizhe.fan
 * @CreateDate: 2019/8/28
 */
public class BASE64DecodedMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private final String header;
    private final String fileName;

    public BASE64DecodedMultipartFile(String fileName, byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return fileName + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return fileName + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException {
        FileOutputStream fileOS = null;
        try {
            fileOS = new FileOutputStream(dest);
            fileOS.write(imgContent);
        } finally {
            if (fileOS != null) {
                fileOS.close();
            }
        }
    }
}
