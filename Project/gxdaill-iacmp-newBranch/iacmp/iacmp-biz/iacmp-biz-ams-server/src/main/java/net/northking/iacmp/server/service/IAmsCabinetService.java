package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsCabinet;
import net.northking.iacmp.common.bean.vo.ams.AmsCabinetVO;

import java.util.List;

/**
 * 库柜 服务层
 *
 * @author wxy
 * @date 2019-08-08
 */
public interface IAmsCabinetService {
    /**
     * 查询库柜信息
     *
     * @param id 库柜ID
     * @return 库柜信息
     */
    public AmsCabinet selectAmsCabinetById(String id);

    /**
     * 查询库柜列表
     *
     * @param amsCabinet 库柜信息
     * @return 库柜集合
     */
    public List<AmsCabinet> selectAmsCabinetList(AmsCabinet amsCabinet);

    public List<AmsCabinetVO> selectAmsCabList(AmsCabinet amsCabinet, List<String> deptList);
    /**
     * 查询库柜列表
     */

    /**
     * 新增库柜
     *
     * @param amsCabinet 库柜信息
     * @return 结果
     */
    public int insertAmsCabinet(AmsCabinet amsCabinet);

    /**
     * 修改库柜
     *
     * @param amsCabinet 库柜信息
     * @return 结果
     */
    public int updateAmsCabinet(AmsCabinet amsCabinet);

    /**
     * 删除库柜信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsCabinetByIds(String ids);

}
