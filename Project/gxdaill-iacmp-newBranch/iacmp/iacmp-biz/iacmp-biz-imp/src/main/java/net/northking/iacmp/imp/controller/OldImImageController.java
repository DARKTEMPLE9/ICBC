package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.constant.ImpConstants;
import net.northking.iacmp.imp.domain.OldImImage;
import net.northking.iacmp.imp.service.IOldImImageService;
import net.northking.iacmp.utils.bean.BeanUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 影像 信息操作处理
 *
 * @author wei.chen
 * @date 2019-10-22
 */
@Controller
@RequestMapping("/uip/oldImImage")
public class OldImImageController extends BaseController {

    @Autowired
    private IOldImImageService oldImImageService;

    /**
     * 查询影像列表
     */
    @PostMapping("/list")
    @ResponseBody
    public List<OldImImage> list(OldImImage imImage) {
        List<OldImImage> list = oldImImageService.selectImImageList(imImage);
        return list;
    }


    /**
     * 导出影像列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OldImImage imImage) {
        List<OldImImage> list = oldImImageService.selectImImageList(imImage);
        ExcelUtil<OldImImage> util = new ExcelUtil<OldImImage>(OldImImage.class);
        return util.exportExcel(list, "imImage");
    }

    /**
     * 新增保存影像
     */
    @Log(title = "影像", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody OldImImage imImage) {
        return toAjax(oldImImageService.insertImImage(imImage));
    }

    /**
     * 修改保存影像
     */
    @Log(title = "影像", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OldImImage imImage) {
        return toAjax(oldImImageService.updateImImage(imImage));
    }

    /**
     * 删除影像
     */
    @Log(title = "影像", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(oldImImageService.deleteImImageByIds(ids));
    }

    /**
     * 查询影像列表
     */
    @PostMapping("/listImage")
    @ResponseBody
    public List<Map<String, Object>> listImage(@RequestBody Map<String, Object> paramMap) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        OldImImage oldImImage = new OldImImage();
        oldImImage.setBatchId((String) paramMap.get(ImpConstants.BATCHID));
        if (paramMap.containsKey(ImpConstants.ID)) {
            oldImImage.setId((String) paramMap.get(ImpConstants.ID));
        }
        List<OldImImage> oldImageList = oldImImageService.selectImImageList(oldImImage);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> imageMap;
        if (null != oldImageList) {
            for (OldImImage oldImImage1 : oldImageList) {
                try {
                    imageMap = BeanUtils.bean2Map(oldImImage1);
                } catch (IntrospectionException e) {
                    logger.error(e.getMessage());
                    continue;
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage());
                    continue;
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                    continue;
                }
                mapList.add(imageMap);
            }
        }
        return mapList;
    }

    /**
     * 修改影像
     */
    @Log(title = "影像", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody Map<String, Object> imImageMap) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OldImImage oldImImage = new OldImImage();
        oldImImage.setId((String) imImageMap.get(ImpConstants.ID));
        if (imImageMap.containsKey(ImpConstants.DELETETIME)) {
            try {
                oldImImage.setDeleteTime(sdf.parse((String) imImageMap.get(ImpConstants.DELETETIME)));
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        if (imImageMap.containsKey(ImpConstants.DELETEUSER)) {
            oldImImage.setDeleteUser((String) imImageMap.get(ImpConstants.DELETEUSER));
        }
        if (imImageMap.containsKey(ImpConstants.DELETEUSERNAME)) {
            oldImImage.setDeleteUserName((String) imImageMap.get(ImpConstants.DELETEUSERNAME));
        }
        if (imImageMap.containsKey(ImpConstants.STATUS)) {
            oldImImage.setStatus((String) imImageMap.get(ImpConstants.STATUS));
        }
        if (imImageMap.containsKey(ImpConstants.SERIALNO)) {
            oldImImage.setSerialno(new BigDecimal((Integer) imImageMap.get(ImpConstants.SERIALNO)));
        }
        if (imImageMap.containsKey(ImpConstants.USERCODEID)) {
            oldImImage.setUserCodeId((String) imImageMap.get(ImpConstants.USERCODEID));
        }
        return toAjax(oldImImageService.updateImImage(oldImImage));
    }

    /**
     * 创建影像
     */
    @Log(title = "影像", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody Map<String, Object> imImageMap) {

        OldImImage oldImImage = new OldImImage();
        try {
            BeanUtils.map2bean(imImageMap, oldImImage);
        } catch (IntrospectionException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        }
        oldImImage.setCreateTime(new Date());
        return toAjax(oldImImageService.insertImImage(oldImImage));
    }

}
