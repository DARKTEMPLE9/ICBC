package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImOcr;

import java.util.HashMap;
import java.util.List;

/**
 * 识别结果 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImOcrMapper {
    /**
     * 查询识别结果信息
     *
     * @return 识别结果信息
     */
    HashMap selectById(String id);

    /**
     * 查询识别结果列表
     *
     * @param imOcr 识别结果信息
     * @return 识别结果集合
     */
    List<ImOcr> selectImOcrList(HashMap imOcr);

    Integer count(HashMap map);

    /**
     * 新增识别结果
     *
     * @param imOcr 识别结果信息
     * @return 结果
     */
    int insertImOcr(ImOcr imOcr);

    /**
     * 修改识别结果
     *
     * @param imOcr 识别结果信息
     * @return 结果
     */
    int updateImOcr(ImOcr imOcr);

    /**
     * 删除识别结果
     *
     * @param id 识别结果ID
     * @return 结果
     */
    int deleteImOcrById(String id);

    /**
     * 批量删除识别结果
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImOcrByIds(String[] ids);

}