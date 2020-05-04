package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImBatch;
import net.northking.iacmp.imp.domain.ImFile;
import net.northking.iacmp.imp.domain.ImImage;
import net.northking.iacmp.imp.domain.ImUser;
import net.northking.iacmp.imp.dto.ImFileDTO;
import net.northking.iacmp.imp.dto.ImFileImuserDTO;
import net.northking.iacmp.imp.dto.ImImageImUserDTO;
import net.northking.iacmp.imp.mapper.ImBatchMapper;
import net.northking.iacmp.imp.mapper.ImFileMapper;
import net.northking.iacmp.imp.mapper.ImUserMapper;
import net.northking.iacmp.imp.service.IImFileService;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImFileVO;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件 服务层实现
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImFileServiceImpl implements IImFileService {
    @Autowired
    private ImFileMapper imFileMapper;

    @Autowired
    private ImBatchMapper imBatchMapper;

    @Autowired
    private ImUserMapper imUserMapper;


    /**
     * 查询文件信息
     *
     * @param imFile 文件
     * @return 文件信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public ImFile selectImFileById(ImFile imFile) {
        return imFileMapper.selectImFileById(imFile);
    }


    @Override
    public Integer selectByBillId(String billId) {
        return imFileMapper.selectByBillId(billId);
    }

    /**
     * 查询文件列表
     *
     * @param ImFile 文件信息
     * @return 文件集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImFile> selectImFileList(ImFile ImFile) {
        return imFileMapper.selectImFileList(ImFile);
    }

    @Override
    public List<ImFile> selectFilesByBusino(String busino) {
        return imFileMapper.selectFilesByBusino(busino);
    }

    /**
     * 新增文件
     *
     * @param ImFile 文件信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int insertImFile(ImFile ImFile) {
        return imFileMapper.insertImFile(ImFile);
    }

    /**
     * 修改文件
     *
     * @param ImFile 文件信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public int updateImFile(ImFile ImFile) {
        return imFileMapper.updateImFile(ImFile);
    }

    /**
     * 删除文件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImFileByIds(String ids) {
        return imFileMapper.deleteImFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 按分类分组查找文件
     *
     * @param imFile
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImBillVO> selectImFileGroupByBill(ImFile imFile) {
        return imFileMapper.selectImFileGroupByBill(imFile);
    }

    /**
     * 根据影像获取接口查询文件列表
     *
     * @param paramMap 接口参数map
     * @return 文件集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImFile> selectImFileByMap(Map<String, Object> paramMap) {
        return imFileMapper.selectImFileByMap(paramMap);
    }

    /**
     * 查看影像及客户信息
     *
     * @param fileVO
     * @return
     */
    @Override
    public List<ImFileImuserDTO> selectFileUserInfo(ImFileVO fileVO) {

        List<ImFileImuserDTO> imFileImUserDTOS = new ArrayList<>();
        String busino = fileVO.getBusino();
        if (StringUtils.isNotNull(busino)) {
            ImUser imUser = imUserMapper.selectImUserByBusino(busino);
            fileVO.setBusino(busino);
            List<ImFile> imFiles = imFileMapper.selectImFileVOList(fileVO);

            imFiles.stream().forEach(file -> {
                ImFileImuserDTO imFileImuserDTO = new ImFileImuserDTO();
                imFileImuserDTO.setImFile(file);
                imFileImuserDTO.setImUser(imUser);
                imFileImUserDTOS.add(imFileImuserDTO);
            });

            return imFileImUserDTOS;

        }
        return null;
    }


    /**
     * 通过文件编号查找文件信息
     *
     * @param ids
     * @param busino
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    public List<ImFile> selectFilesByIds(String ids, String busino) {

        ImFileDTO imFileDTO = new ImFileDTO();
        imFileDTO.setBusino(busino);
        imFileDTO.setIds(Convert.toStrArray(ids));
        return imFileMapper.selectFilesByIds(imFileDTO);
    }

}
