package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsBorrowInfo;
import net.northking.iacmp.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 档案借阅历史 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsBorrowInfoService {
    /**
     * 查询档案借阅历史信息
     *
     * @param id 档案借阅ID
     * @return 档案借阅信息
     */
    public AmsBorrowInfo selectAmsBorrowInfoById(String id);

    /**
     * 根据档案编号查询借阅信息
     *
     * @param arcid
     * @return
     */
    public AmsBorrowInfo selectAmsBorrowInfoByarcId(String arcid);

    /**
     * 根据档案编号查询借阅信息
     *
     * @param exaAppId
     * @return
     */
    public AmsBorrowInfo selectAmsBorrowInfoByexaAppId(String exaAppId);

    /**
     * 查询档案借阅历史列表
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 档案借阅历史集合
     */
    public List<AmsBorrowInfo> selectAmsBorrowInfoList(AmsBorrowInfo amsBorrowInfo);


    /**
     * 查询档案借阅历史列表(包含辅部门)
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 档案借阅历史集合
     */
    public List<AmsBorrowInfo> selectAmsBorrowInfoList(AmsBorrowInfo amsBorrowInfo, List<String> deptList);

    /**
     * 查询档案利用统计表
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    public List<String> queryBorTypeByOneOrgan(String fillingTimeGt, String fillingTimeLt, String arcBillCode, List<String> treeList, List<String> orgCodeList);

    /**
     * /* 新增档案借阅历史
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 结果
     */
    public int insertAmsBorrowInfo(AmsBorrowInfo amsBorrowInfo);

    /**
     * 修改档案借阅历史
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 结果
     */
    public int updateAmsBorrowInfo(AmsBorrowInfo amsBorrowInfo);

    /**
     * 删除档案借阅历史信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsBorrowInfoByIds(String ids);

    /**
     * 查询申请人列表
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectAppUsersBySysUser(SysUser sysUser);
}
