package net.northking.iacmp.server.service.impl;

import java.util.*;

import iacmp.biz.common.dao.mapper.ams.AmsBillMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.system.domain.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsBorrowInfoMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsBorrowInfo;
import net.northking.iacmp.server.service.IAmsBorrowInfoService;
import net.northking.iacmp.core.text.Convert;

/**
 * 档案借阅历史 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsBorrowInfoServiceImpl implements IAmsBorrowInfoService {
    @Autowired
    private AmsBorrowInfoMapper amsBorrowInfoMapper;
    @Autowired
    private AmsBillMapper amsBillMapper;

    /**
     * 查询档案借阅历史信息
     *
     * @param id 档案借阅ID
     * @return 档案借阅信息
     */
    @Override
    public AmsBorrowInfo selectAmsBorrowInfoById(String id) {
        return amsBorrowInfoMapper.selectAmsBorrowInfoById(id);
    }

    @Override
    public AmsBorrowInfo selectAmsBorrowInfoByarcId(String arcid) {
        List<AmsBorrowInfo> amsBorrowInfos = amsBorrowInfoMapper.selectAmsBorrowInfoByarcId(arcid);
        AmsBorrowInfo amsBorrowInfo = new AmsBorrowInfo();
        if (amsBorrowInfos.size() > 0 && null != amsBorrowInfos) {
            BeanUtils.copyProperties(amsBorrowInfos.get(0), amsBorrowInfo);
        }
        return amsBorrowInfo;
    }

    @Override
    public AmsBorrowInfo selectAmsBorrowInfoByexaAppId(String exaAppId) {
        return amsBorrowInfoMapper.selectAmsBorrowInfoByexaAppId(exaAppId);
    }

    /**
     * 查询档案借阅历史列表
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 档案借阅历史集合
     */
    @Override
    public List<AmsBorrowInfo> selectAmsBorrowInfoList(AmsBorrowInfo amsBorrowInfo) {
        return amsBorrowInfoMapper.selectAmsBorrowInfoList(amsBorrowInfo);
    }

    /**
     * 查询档案借阅历史列表
     *
     * @param amsBorrowInfo 档案借阅历史信息(包含辅部门)
     * @return 档案借阅历史集合
     */
    @Override
    public List<AmsBorrowInfo> selectAmsBorrowInfoList(AmsBorrowInfo amsBorrowInfo, List<String> deptList) {
        return amsBorrowInfoMapper.selectAmsBorrowList(amsBorrowInfo, deptList);
    }

    //查询档案利用统计表
    public List<String> queryBorTypeByOneOrgan(String fillingTimeGt, String fillingTimeLt, String arcBillCode, List<String> treeList, List<String> orgCodeList) {
        List<String> listArcBillCodeAndNumber = new ArrayList<>();
        //System.out.println(fillingTime_gt);
        //起始时间与结束时间都存在
        List<Map<String, Object>> listBillCodeAndNumber = amsBillMapper.queryBorTypeByOneOrgan(fillingTimeGt, fillingTimeLt, arcBillCode, treeList, orgCodeList);
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listBillCodeAndNumber) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillCodeAndNumber.add(Arrays.toString(message));
        }
        return listArcBillCodeAndNumber;
    }


    /**
     * 新增档案借阅历史
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 结果
     */
    @Override
    public int insertAmsBorrowInfo(AmsBorrowInfo amsBorrowInfo) {
        return amsBorrowInfoMapper.insertAmsBorrowInfo(amsBorrowInfo);
    }

    /**
     * 修改档案借阅历史
     *
     * @param amsBorrowInfo 档案借阅历史信息
     * @return 结果
     */
    @Override
    public int updateAmsBorrowInfo(AmsBorrowInfo amsBorrowInfo) {
        return amsBorrowInfoMapper.updateAmsBorrowInfo(amsBorrowInfo);
    }

    /**
     * 删除档案借阅历史对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsBorrowInfoByIds(String ids) {
        return amsBorrowInfoMapper.deleteAmsBorrowInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询申请人列表
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<SysUser> selectAppUsersBySysUser(SysUser sysUser) {
        return amsBorrowInfoMapper.selectAppUsersBySysUser(sysUser);
    }
}
