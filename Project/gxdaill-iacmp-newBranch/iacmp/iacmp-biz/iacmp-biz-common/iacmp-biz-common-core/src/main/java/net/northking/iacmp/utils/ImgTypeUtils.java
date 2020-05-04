package net.northking.iacmp.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 图片验证类
 *
 * @author duxianchao
 */
public enum ImgTypeUtils {

    INSTANCE;

    private List<String> imgTypes = null;

    private ImgTypeUtils() {
        imgTypes = Arrays.asList("jpg", "jpeg", "png", "bmp", "gif", "tiff", "wmf", "pcx", "tga", "exif",
                "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "hdri", "raw", "lic", "emf");
    }

    public boolean validateIfImg(String fileType) {
        String type = fileType.toLowerCase();
        if (imgTypes != null) {
            return imgTypes.contains(type);
        }
        return false;
    }

}
