package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsCode;

import java.util.List;

/**
 * 编号生成 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsCodeMapper {
    /**
     * 查询编号生成信息
     *
     * @param id 编号生成ID
     * @return 编号生成信息
     */
    AmsCode selectAmsCodeById(String id);

    /**
     * 查询编号生成列表
     *
     * @param amsCode 编号生成信息
     * @return 编号生成集合
     */
    List<AmsCode> selectAmsCodeList(AmsCode amsCode);

    /**
     * 新增编号生成
     *
     * @param amsCode 编号生成信息
     * @return 结果
     */
    int insertAmsCode(AmsCode amsCode);

    /**
     * 修改编号生成
     *
     * @param amsCode 编号生成信息
     * @return 结果
     */
    int updateAmsCode(AmsCode amsCode);

    /**
     * 删除编号生成
     *
     * @param id 编号生成ID
     * @return 结果
     */
    int deleteAmsCodeById(String id);

    /**
     * 批量删除编号生成
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsCodeByIds(String[] ids);

}