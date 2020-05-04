package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.constant.ImpConstants;
import net.northking.iacmp.imp.domain.ImCustomerBusino;
import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.domain.ImUser;
import net.northking.iacmp.imp.dto.ImImageImUserDTO;
import net.northking.iacmp.imp.service.IImCustomerBusinoService;
import net.northking.iacmp.imp.service.IImImageService;
import net.northking.iacmp.imp.service.IImUserService;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImImageVO;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 影像 信息操作处理
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@RestController
@RequestMapping("/uip/imImage")
public class ImImageController extends BaseController {

    @Autowired
    private IImImageService imImageService;

    @Autowired
    private IImUserService imUserService;

    @Autowired
    private IImCustomerBusinoService imCustomerBusinoService;

    /**
     * 查询影像列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImImage ImImage) {
        startPage();
        List<ImImage> list = imImageService.selectImImageList(ImImage);
        return getDataTable(list);
    }

    /**
     * 通过影像id查找影像信息
     *
     * @param busino
     * @param imageId
     * @return
     */
    @GetMapping("/queryImImageByImageId")
    public ImImage queryImImageByImageId(String busino, String imageId) {
        ImImage imImage = new ImImage();
        imImage.setBusino(busino);
        imImage.setId(imageId);
        return imImageService.selectImImageById(imImage);
    }

    /**
     * 根据busino查询影像列表
     *
     * @return
     */
    @PostMapping("/selectImagesByBusino")
    @DataSource(DataSourceType.IMP_HORIZONTAL)
    public List<ImImage> selectImagesByBusino(@RequestBody String busino) {
        List<ImImage> list = imImageService.selectImagesByBusino(busino);
        return list;
    }

    @PostMapping("/selectByBillId")
    @DataSource(DataSourceType.IMP_HORIZONTAL)
    public Integer selectByBillId(@RequestBody String billId) {
        Integer res = imImageService.selectByBillId(billId);
        return res;
    }

    /**
     * 通过影像ids及流水号查询影像信息
     */
    @GetMapping("/selectImagesByIds")
    public List<ImImage> selectImagesByIds(@RequestParam("imageIds") String ids, @RequestParam("busino") String busino) {

        return imImageService.selectImagesByIds(ids, busino);
    }

    /**
     * 通过批次号及状态查询分类下文件数
     *
     * @param batchId
     * @param status
     * @return
     */
    @GetMapping("/queryBillNum")
    public List<ImBillVO> queryBillNum(String batchId, String status) {
        ImImage imImage = new ImImage();
        imImage.setBatchId(batchId);
        imImage.setStatus(status);
        return imImageService.selectImImageGroupByBill(imImage);
    }

    /**
     * 查看影像及客户信息
     *
     * @param busino
     * @param billId
     * @param status
     * @param order
     * @return
     */
    @GetMapping("/queryImageUserInfo")
    public List<ImImageImUserDTO> queryImageUserInfo(String busino, @RequestParam(defaultValue = "") String billId, String status, String order) {
        ImImageVO imImage = new ImImageVO();
        imImage.setBusino(busino);
        imImage.setBillId(billId);
        imImage.setStatus(status);
        imImage.setOrder(order);
        return imImageService.selectImageUserInfo(imImage);

    }

    /**
     * 新增保存影像
     */
    @Log(title = "影像", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImImage ImImage) {
        return toAjax(imImageService.insertImImage(ImImage));
    }

    /**
     * 修改保存影像
     */
    @Log(title = "影像", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImImage ImImage) {
        return toAjax(imImageService.updateImImage(ImImage));
    }

    /**
     * 删除影像
     */
    @Log(title = "影像", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(imImageService.deleteImImageByIds(ids));
    }

    /**
     * 查询影像列表
     */
    @PostMapping("/listImage")
    @ResponseBody
    public List<Map<String, Object>> listImage(@RequestBody Map<String, Object> paramMap) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        ImImage imImage = new ImImage();
        imImage.setBusino((String) paramMap.get(ImpConstants.BUSINO));
        if (paramMap.containsKey(ImpConstants.ID)) {
            imImage.setId((String) paramMap.get(ImpConstants.ID));
        }
        List<ImImage> imageList = imImageService.selectImImageList(imImage);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> imageMap = null;
        if (null != imageList) {
            for (ImImage imImage1 : imageList) {
                try {
                    imageMap = BeanUtils.bean2Map(imImage1);
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

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ImImage imImage = new ImImage();
        imImage.setId((String) imImageMap.get(ImpConstants.ID));
        /*try {
            imImage.setDeleteTime(sdf.parse((String) imImageMap.get("deleteTime")));
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }*/
        imImage.setDeleteTime(new Date());
        imImage.setDeleteUser((String) imImageMap.get(ImpConstants.DELETEUSER));
        imImage.setDeleteUserName((String) imImageMap.get(ImpConstants.DELETEUSERNAME));
        imImage.setStatus((String) imImageMap.get(ImpConstants.STATUS));
        imImage.setSerialno(new BigDecimal((Integer) imImageMap.get(ImpConstants.SERIALNO)));
        imImage.setBusino((String) imImageMap.get(ImpConstants.BUSINO));
        return toAjax(imImageService.updateImImage(imImage));
    }

    /**
     * 创建影像
     */
    @Log(title = "影像", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody Map<String, Object> imImageMap) {

        ImImage imImage = new ImImage();
        try {
            BeanUtils.map2bean(imImageMap, imImage);
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
        imImage.setCreateTime(new Date());
        imImage.setBusino((String) imImageMap.get(ImpConstants.BUSINO));
        AjaxResult ajaxResult = toAjax(imImageService.insertImImage(imImage));
        if (AjaxResult.Type.SUCCESS.value() == ajaxResult.getCode()) {
            if (StringUtils.isNotEmpty((String) imImageMap.get(ImpConstants.USERCODEID))) {
                ImUser imUser = imUserService.selectImUserById(Long.valueOf((String) imImageMap.get(ImpConstants.USERCODEID)));
                if (null == imUser) {
                    return toAjax(false);
                }
                ImCustomerBusino imCustomerBusino;
                List<ImCustomerBusino> imCustomerBusinoList = imCustomerBusinoService.selectImCustomerBusinoByUserCodes(imUser.getUserCode());
                if (null != imCustomerBusinoList && !imCustomerBusinoList.isEmpty()) {
                    imCustomerBusino = imCustomerBusinoList.get(0);
                    if (imCustomerBusino.getBusino().indexOf(imImage.getBusino()) == -1) {
                        imCustomerBusino.setBusino(
                                imCustomerBusino.getBusino().concat(
                                        "," + imImage.getBusino()));
                        imCustomerBusinoService.updateImCustomerBusino(imCustomerBusino);
                    }
                } else {
                    imCustomerBusino = new ImCustomerBusino();
                    imCustomerBusino.setUserCode(imUser.getUserCode());
                    imCustomerBusino.setBusino(imImage.getBusino());
                    imCustomerBusinoService.insertImCustomerBusino(imCustomerBusino);
                }
            }
        }
        return ajaxResult;
    }
}
