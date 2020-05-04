package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.constant.ImpConstants;
import net.northking.iacmp.imp.domain.ImFile;
import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.dto.ImFileImuserDTO;
import net.northking.iacmp.imp.service.IImFileService;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImFileVO;
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
 * 文件 信息操作处理
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@RestController
@RequestMapping("/uip/imFile")
public class ImFileController extends BaseController {

    @Autowired
    private IImFileService imFileService;

    /**
     * 查询文件列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImFile ImFile) {
        List<ImFile> list = imFileService.selectImFileList(ImFile);
        return getDataTable(list);
    }

    /**
     * 通过影像ids及流水号查询影像信息
     */
    @GetMapping("/selectFilesByIds")
    public List<ImFile> selectFilesByIds(@RequestParam("fileIds") String ids, @RequestParam("busino") String busino) {

        return imFileService.selectFilesByIds(ids, busino);
    }

    /**
     * 通过文件id查找文件信息
     *
     * @param busino
     * @param fileId
     * @return
     */
    @GetMapping("/queryImFileByFileId")
    public ImFile queryImFileByFileId(String busino, String fileId) {
        ImFile imFile = new ImFile();
        imFile.setBusino(busino);
        imFile.setId(fileId);
        return imFileService.selectImFileById(imFile);
    }

    /**
     * 根据busino查询文件列表
     *
     * @return
     */
    @PostMapping("/selectFilesByBusino")
    @DataSource(DataSourceType.IMP_HORIZONTAL)
    public List<ImFile> selectFilesByBusino(@RequestBody String busino) {
        List<ImFile> list = imFileService.selectFilesByBusino(busino);
        return list;
    }

    @PostMapping("/selectByBillId")
    @DataSource(DataSourceType.IMP_HORIZONTAL)
    public Integer selectByBillId(@RequestBody String billId) {
        Integer res = imFileService.selectByBillId(billId);
        return res;
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
        ImFile imFile = new ImFile();
        imFile.setBatchId(batchId);
        imFile.setStatus(status);
        return imFileService.selectImFileGroupByBill(imFile);
    }

    /**
     * 查看文件及客户信息
     *
     * @param busino
     * @param billId
     * @param status
     * @param order
     * @return
     */
    @GetMapping("/queryFileUserInfo")
    public List<ImFileImuserDTO> queryImFileUserInfo(String busino, @RequestParam(defaultValue = "") String billId, String status, String order) {
        ImFileVO fileVO = new ImFileVO();
        fileVO.setBusino(busino);
        fileVO.setBillId(billId);
        fileVO.setStatus(status);
        fileVO.setOrder(order);
        return imFileService.selectFileUserInfo(fileVO);

    }

    /**
     * 新增保存文件
     */
    @Log(title = "文件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ImFile ImFile) {
        return toAjax(imFileService.insertImFile(ImFile));
    }

    /**
     * 修改保存文件
     */
    @Log(title = "文件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody ImFile ImFile) {
        return toAjax(imFileService.updateImFile(ImFile));
    }

    /**
     * 删除文件
     */
    @Log(title = "文件", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(imFileService.deleteImFileByIds(ids));
    }

    /**
     * 查询文件列表
     */
    @PostMapping("/listFile")
    @ResponseBody
    public List<Map<String, Object>> listFile(@RequestBody Map<String, Object> paramMap) {

        ImFile imFile = new ImFile();
        imFile.setBusino((String) paramMap.get(ImpConstants.BUSINO));
        if (paramMap.containsKey(ImpConstants.ID)) {
            imFile.setId((String) paramMap.get(ImpConstants.ID));
        }
        List<ImFile> fileList = imFileService.selectImFileList(imFile);
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> fileMap = null;
        if (null != fileList) {
            for (ImFile imFile1 : fileList) {
                try {
                    fileMap = BeanUtils.bean2Map(imFile1);
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
                mapList.add(fileMap);
            }
        }
        return mapList;
    }

    /**
     * 修改文件
     */
    @Log(title = "文件", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody Map<String, Object> imFileMap) {

        ImFile imFile = new ImFile();
        imFile.setId((String) imFileMap.get(ImpConstants.ID));
        imFile.setBusino((String) imFileMap.get(ImpConstants.BUSINO));
        if (imFileMap.containsKey(ImpConstants.STATUS)) {
            imFile.setStatus((String) imFileMap.get(ImpConstants.STATUS));
        }
        if (imFileMap.containsKey(ImpConstants.SERIALNO)) {
            imFile.setSerialno(new BigDecimal((Integer) imFileMap.get(ImpConstants.SERIALNO)));
        }
        return toAjax(imFileService.updateImFile(imFile));
    }

    /**
     * 创建文件
     */
    @Log(title = "文件", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody Map<String, Object> imFileMap) {

        ImFile imFile = new ImFile();
        try {
            BeanUtils.map2bean(imFileMap, imFile);
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
        imFile.setBusino((String) imFileMap.get(ImpConstants.BUSINO));
        imFile.setCreateTime(new Date());
        return toAjax(imFileService.insertImFile(imFile));
    }

}
