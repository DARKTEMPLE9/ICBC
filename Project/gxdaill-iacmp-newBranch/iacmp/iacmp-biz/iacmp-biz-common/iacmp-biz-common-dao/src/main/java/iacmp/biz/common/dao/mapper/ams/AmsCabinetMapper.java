package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsCabinet;
import net.northking.iacmp.common.bean.vo.ams.AmsCabinetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库柜 数据层
 *
 * @author wxy
 * @date 2019-08-08
 */
public interface AmsCabinetMapper {
    /**
     * 查询库柜信息
     *
     * @param id 库柜ID
     * @return 库柜信息
     */
    AmsCabinet selectAmsCabinetById(String id);

    /**
     * 查询库柜列表
     *
     * @param amsCabinet 库柜信息
     * @return 库柜集合
     */
    List<AmsCabinet> selectAmsCabinetList(AmsCabinet amsCabinet);

    List<AmsCabinetVO> selectAmsCabList(@Param("amsCabinet") AmsCabinet amsCabinet, @Param("deptList") List<String> deptList);

    /**
     * 新增库柜
     *
     * @param amsCabinet 库柜信息
     * @return 结果
     */
    int insertAmsCabinet(AmsCabinet amsCabinet);

    /**
     * 修改库柜
     *
     * @param amsCabinet 库柜信息
     * @return 结果
     */
    int updateAmsCabinet(AmsCabinet amsCabinet);

    /**
     * 删除库柜
     *
     * @param id 库柜ID
     * @return 结果
     */
    int deleteAmsCabinetById(String id);

    /**
     * 批量删除库柜
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsCabinetByIds(String[] ids);

}