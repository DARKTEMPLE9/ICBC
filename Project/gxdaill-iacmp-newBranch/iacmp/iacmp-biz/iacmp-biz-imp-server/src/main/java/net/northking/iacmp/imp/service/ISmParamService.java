package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.SmParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * 参数配置 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ISmParamService {
    /**
     * 查询参数配置信息
     *
     * @param iD 参数配置ID
     * @return 参数配置信息
     */
    SmParam selectSmParamById(String iD);

    HashMap queryById(String id);

    /**
     * 查询参数配置列表
     *
     * @param smParam 参数配置信息
     * @return 参数配置集合
     */
    List<SmParam> selectSmParamList(SmParam smParam);

    List<SmParam> listAll(HashMap smParam);

    Integer count(HashMap smParam);

    /**
     * 新增参数配置
     *
     * @param smParam 参数配置信息
     * @return 结果
     */
    int insertSmParam(SmParam smParam);

    /**
     * 修改参数配置
     *
     * @param smParam 参数配置信息
     * @return 结果
     */
    Integer updateSmParam(HashMap smParam);

    /**
     * 删除参数配置信息
     *
     * @return 结果
     */
    Integer deleteById(String id);

}
