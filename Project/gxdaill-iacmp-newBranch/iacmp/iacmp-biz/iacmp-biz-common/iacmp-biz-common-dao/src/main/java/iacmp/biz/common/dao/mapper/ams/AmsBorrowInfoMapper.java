package iacmp.biz.common.dao.mapper.ams;

import net.northking.iacmp.common.bean.domain.ams.AmsBorrowInfo;
import net.northking.iacmp.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 档案借阅历史 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsBorrowInfoMapper {
    /**
     * 查询档案借阅历史信息
     *
     * @param id 档案借阅ID
     * @return 档案借阅信息
     */
    AmsBorrowInfo selectAmsBorrowInfoById(String id);

    /**
     * 根据档案编号查询档案借阅信息
     *
     * @param id
     * @return
     */
    List<AmsBorrowInfo> selectAmsBorrowInfoByarcId(String id);

    /**
     * 根据审批编号查询档案借阅信息
     *
     * @param exaAppId
     * @return
     */
    AmsBorrowInfo selectAmsBorrowInfoByexaAppId(String exaAppId);

    /**
     * 查询档案借阅历史列表
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 档案借阅历史集合
     */
    List<AmsBorrowInfo> selectAmsBorrowInfoList(AmsBorrowInfo amsBorrowInfo);

    /**
     * 查询档案借阅历史列表
     *
     * @param amsBorrowInfo 档案借阅历史信息（包含辅部门）
     * @return 档案借阅历史集合
     */
    List<AmsBorrowInfo> selectAmsBorrowList(@Param("amsBorrowInfo") AmsBorrowInfo amsBorrowInfo, @Param("deptList") List<String> deptList);

    /**
     * 新增档案借阅历史
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 结果
     */
    int insertAmsBorrowInfo(AmsBorrowInfo amsBorrowInfo);

    /**
     * 修改档案借阅历史
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 结果
     */
    int updateAmsBorrowInfo(AmsBorrowInfo amsBorrowInfo);

    /**
     * 删除档案借阅历史
     *
     * @param id 档案借阅ID
     * @return 结果
     */
    int deleteAmsBorrowInfoById(String id);

    /**
     * 批量删除档案借阅历史
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsBorrowInfoByIds(String[] ids);

    /**
     * 查询申请人列表
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectAppUsersBySysUser(SysUser sysUser);
}