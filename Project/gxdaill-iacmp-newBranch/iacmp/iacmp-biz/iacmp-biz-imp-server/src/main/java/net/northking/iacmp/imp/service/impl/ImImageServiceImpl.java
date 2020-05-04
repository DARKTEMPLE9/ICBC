package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.*;
import net.northking.iacmp.imp.dto.ImImageDTO;
import net.northking.iacmp.imp.dto.ImImageImUserDTO;
import net.northking.iacmp.imp.mapper.ImBatchMapper;
import net.northking.iacmp.imp.mapper.ImCustomerBusinoMapper;
import net.northking.iacmp.imp.mapper.ImImageMapper;
import net.northking.iacmp.imp.mapper.ImUserMapper;
import net.northking.iacmp.imp.service.IImImageService;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImImageVO;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 影像 服务层实现
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImImageServiceImpl implements IImImageService {
    @Autowired
    private ImImageMapper imImageMapper;

    @Autowired
    private ImBatchMapper imBatchMapper;

    @Autowired
    private ImUserMapper imUserMapper;

    @Autowired
    private ImCustomerBusinoMapper imCustomerBusinoMapper;

    /**
     * 查询影像信息
     *
     * @param imImage 影像
     * @return 影像信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public ImImage selectImImageById(ImImage imImage) {
        return imImageMapper.selectImImageById(imImage);
    }

    @Override
    public Integer selectByBillId(String billId) {
        return imImageMapper.selectByBillId(billId);
    }

    /**
     * 查询影像列表
     *
     * @param ImImage 影像信息
     * @return 影像集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImImage> selectImImageList(ImImage ImImage) {
        return imImageMapper.selectImImageList(ImImage);
    }

    @Override
    public List<ImImage> selectImagesByBusino(String busino) {
        return imImageMapper.selectImagesByBusino(busino);
    }

    /**
     * 新增影像
     *
     * @param ImImage 影像信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int insertImImage(ImImage ImImage) {
        return imImageMapper.insertImImage(ImImage);
    }

    /**
     * 修改影像
     *
     * @param ImImage 影像信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int updateImImage(ImImage ImImage) {
        return imImageMapper.updateImImage(ImImage);
    }

    /**
     * 删除影像对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImImageByIds(String ids) {
        return imImageMapper.deleteImImageByIds(Convert.toStrArray(ids));
    }

    /**
     * 按分类分组查找影像
     *
     * @param imImage
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImBillVO> selectImImageGroupByBill(ImImage imImage) {
        return imImageMapper.selectImImageGroupByBill(imImage);
    }

    /**
     * 根据影像获取接口查询影像列表
     *
     * @param paramMap 接口参数map
     * @return 影像集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImImage> selectImImageByMap(Map<String, Object> paramMap) {
        return imImageMapper.selectImImageByMap(paramMap);
    }

    /**
     * 查询影像和客户信息
     *
     * @param imImage
     * @return
     */
    @Override
    public List<ImImageImUserDTO> selectImageUserInfo(ImImageVO imImage) {

        List<ImImageImUserDTO> imImageImUserDTOS = new ArrayList<>();
        String busino = imImage.getBusino();
        if (StringUtils.isNotNull(busino)) {
            ImUser imUser = imUserMapper.selectImUserByBusino(busino);
            List<ImImage> imImages = imImageMapper.selectImImageVOList(imImage);

            imImages.stream().forEach(imImage1 -> {
                ImImageImUserDTO imageSmuserDTO = new ImImageImUserDTO();
                imageSmuserDTO.setImImage(imImage1);
                imageSmuserDTO.setImUser(imUser);
                imImageImUserDTOS.add(imageSmuserDTO);
            });

            return imImageImUserDTOS;

        }
        return null;
    }

    /**
     * 通过影像id及流水号查找影像信息
     *
     * @param ids
     * @param busino
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImImage> selectImagesByIds(String ids, String busino) {

        ImImageDTO imImageDTO = new ImImageDTO();
        imImageDTO.setIds(Convert.toStrArray(ids));
        imImageDTO.setBusino(busino);
        return imImageMapper.selectImagesByIds(imImageDTO);
    }

}
