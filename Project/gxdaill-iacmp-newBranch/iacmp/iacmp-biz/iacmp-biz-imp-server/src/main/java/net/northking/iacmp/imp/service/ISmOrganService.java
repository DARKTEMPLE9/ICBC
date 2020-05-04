package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.SmOrgan;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * 部门机构 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ISmOrganService {
    /**
     * 查询部门机构信息
     *
     * @param iD 部门机构ID
     * @return 部门机构信息
     */
    SmOrgan selectSmOrganById(String iD);

    /**
     * 查询部门机构列表
     *
     * @param smOrgan 部门机构信息
     * @return 部门机构集合
     */
    List<SmOrgan> selectSmOrganList(HashMap smOrgan);

    Integer count(HashMap map);

    List<SmOrgan> findByOrganCode(String code);

    /**
     * 新增部门机构
     *
     * @param smOrgan 部门机构信息
     * @return 结果
     */
    Integer insertSmOrgan(SmOrgan smOrgan);

    /**
     * 修改部门机构
     *
     * @param smOrgan 部门机构信息
     * @return 结果
     */
    int updateSmOrgan(SmOrgan smOrgan);

    /**
     * 删除部门机构信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmOrganByIds(String ids);

}
