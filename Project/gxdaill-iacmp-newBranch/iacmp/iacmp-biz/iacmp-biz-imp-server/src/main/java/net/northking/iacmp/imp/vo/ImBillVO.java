package net.northking.iacmp.imp.vo;

import lombok.Data;
import net.northking.iacmp.imp.domain.ImBill;
import net.northking.iacmp.imp.domain.ImFile;
import net.northking.iacmp.imp.domain.ImImage;

import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:ImBillVO
 * @Description：影像分类
 * @Date：Create in 7:48 PM2019/10/17
 * @Modified by:
 * @Version:1.0
 */
@Data
public class ImBillVO {
    /**
     * 影像分类
     */
    private ImBill imBill;
    /**
     * 分类下影像数
     */
    private Integer count;
    /**
     * 分类下影像数
     */
    private String billId;
    /**
     * 分类下影像列表
     */
    private List<ImImage> imageList;
    /**
     * 分类下文件列表
     */
    private List<ImFile> imFileList;

}
