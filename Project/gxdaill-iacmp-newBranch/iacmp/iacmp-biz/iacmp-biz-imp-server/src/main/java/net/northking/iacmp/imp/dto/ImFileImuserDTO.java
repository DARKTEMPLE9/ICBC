package net.northking.iacmp.imp.dto;

import lombok.Data;
import net.northking.iacmp.imp.domain.ImFile;
import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.domain.ImUser;

/**
 * @Author：Yanqingyu
 * @ClassName:ImImageSmuserDTO
 * @Description：存放用户及影像信息（调阅页面点击树，调用）
 * @Date：Create in 2:09 PM2019/10/19
 * @Modified by:
 * @Version:1.0
 */
@Data
public class ImFileImuserDTO {

    /**
     * 影像信息
     */
    private ImFile imFile;

    /**
     * 客户信息
     */
    private ImUser imUser;
}
