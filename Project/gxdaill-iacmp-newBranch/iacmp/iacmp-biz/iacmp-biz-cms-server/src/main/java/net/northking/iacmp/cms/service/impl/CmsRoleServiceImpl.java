package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.*;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsRoleService;
import net.northking.iacmp.common.bean.domain.cms.*;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.system.domain.*;
import net.northking.iacmp.system.mapper.SysUserMapper;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类角色 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsRoleServiceImpl implements ICmsRoleService {
    @Autowired
    private CmsRoleMapper cmsRoleMapper;

    @Autowired
    private CmsRoleBillMapper cmsRoleBillMapper;

    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    private CmsRoleProjectMapper cmsRoleProjectMapper;
    @Autowired
    private PmsBatchMapper pmsBatchMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询分类角色信息
     *
     * @param id 分类角色ID
     * @return 分类角色信息
     */
    @Override
    public CmsRole selectCmsRoleById(Long id) {
        return cmsRoleMapper.selectCmsRoleById(id);
    }

    /**
     * 查询分类角色列表
     *
     * @param cmsRole 分类角色信息
     * @return 分类角色集合
     */
    @Override
    public List<CmsRole> selectCmsRoleList(CmsRole cmsRole) {
        return cmsRoleMapper.selectCmsRoleList(cmsRole);
    }

    /**
     * 新增分类角色
     *
     * @param cmsRole 分类角色信息
     * @return 结果
     */
    @Override
    public int insertCmsRole(CmsRole cmsRole) {
        cmsRoleMapper.insertCmsRole(cmsRole);
        CmsRole cmsRoleNew = cmsRoleMapper.selectCmsRoleByName(cmsRole.getRoleName());
        cmsRole.setId(cmsRoleNew.getId());
        return insertRoleBill(cmsRole);
    }

    /**
     * 新增角色分类信息
     *
     * @param role 角色对象
     */
    public int insertRoleBill(CmsRole role) {
        int rows = 1;
        if (StringUtils.isNull(role) || StringUtils.isNull(role.getBillIds())) {
            return rows;
        }
        // 新增用户与角色管理
        List<CmsRoleBill> list = new ArrayList<>();
        for (Long billId : role.getBillIds()) {
            CmsRoleBill rm = new CmsRoleBill();
            rm.setRoleId(role.getId());
            rm.setBillId(billId.intValue());
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = cmsRoleBillMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 修改分类角色
     *
     * @param cmsRole 分类角色信息
     * @return 结果
     */
    @Override
    public int updateCmsRole(CmsRole cmsRole) {
        // 修改角色信息
        cmsRoleMapper.updateCmsRole(cmsRole);
        // 删除角色与菜单关联
        cmsRoleBillMapper.deleteCmsRoleBillByRoleId(cmsRole.getId());
        return insertRoleBill(cmsRole);
    }

    /**
     * 删除分类角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsRoleByIds(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long id : roleIds) {
            CmsRole role = selectCmsRoleById(id);
            if (countUserRoleByRoleId(id) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return cmsRoleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param id 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long id) {
        return cmsUserRoleMapper.countUserRoleByRoleId(id);
    }

    /**
     * 分配数据权限
     *
     * @param role
     * @return
     */
    @Override
    public int authDataScope(CmsRole role) {
        // 修改角色信息
        cmsRoleMapper.updateCmsRole(role);
        // 删除角色与部门关联
        cmsRoleProjectMapper.deleteCmsRoleProjectByRoleId(role.getId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(CmsRole role) {
        int rows = 1;
        // 新增角色与项目（数据权限）管理
        List<CmsRoleProject> list = new ArrayList<>();
        for (Long projectId : role.getProjectIds()) {
            //根据id查询项目编号
            PmsBatch pmsBatch = pmsBatchMapper.selectPmsBatchById(projectId);
            CmsRoleProject cp = new CmsRoleProject();
            cp.setRoleId(role.getId());
            cp.setPmsBatchId(pmsBatch.getBatchId());
            list.add(cp);
        }
        if (list.size() > 0) {
            rows = cmsRoleProjectMapper.batchRoleProject(list);
        }
        return rows;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(CmsRole role) {
        Long id = StringUtils.isNull(role.getId()) ? -1L : role.getId();
        CmsRole info = cmsRoleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(CmsRole role) {
        Long id = StringUtils.isNull(role.getId()) ? -1L : role.getId();
        CmsRole info = cmsRoleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 批量选择用户授权
     *
     * @param id      分类角色Id
     * @param userIds 需要授权的用户Id
     * @return
     */
    @Override
    public int insertAuthUsers(Long id, String userIds) {
        Long[] users = Convert.toLongArray(userIds);
        // 新增用户与角色管理
        List<CmsUserRole> list = new ArrayList<>();
        for (Long userId : users) {
            CmsUserRole ur = new CmsUserRole();
            ur.setUserId(userId);
            ur.setRoleId(id);
            list.add(ur);
        }
        return cmsUserRoleMapper.batchUserRole(list);
    }

    /**
     * 取消授权
     *
     * @param userRole
     * @return
     */
    @Override
    public int deleteAuthUser(CmsUserRole userRole) {
        return cmsUserRoleMapper.deleteUserRoleInfo(userRole);
    }

    /**
     * 批量取消授权
     *
     * @param id
     * @param userIds
     * @return
     */
    @Override
    public int deleteAuthUsers(Long id, String userIds) {
        return cmsUserRoleMapper.deleteUserRoleInfos(id, Convert.toLongArray(userIds));
    }

    /**
     * 用户关联表信息
     *
     * @param userList
     * @return
     */
    @Override
    public List<CmsUserRole> selectUserRoleByUserInfo(List<CmsUserRoleVO> userList) {
        if (userList.isEmpty()) {
            return null;
        }
        //通过用户登录名获得用户id
        userList.stream().forEach(sysUserRoleVO -> {
            SysUser sysUser = userMapper.selectUserByLoginName(sysUserRoleVO.getLoginName());
            if (sysUser == null) {
                throw new BusinessException("您输入的登录名称" + sysUserRoleVO.getLoginName() + "不存在，请重新输入！");
            }
            sysUserRoleVO.setUserId(sysUser.getUserId());
        });
        //通过角色名称来获得角色id
        userList.stream().forEach(sysUserRoleVO -> {
            CmsRole cmsRole = cmsRoleMapper.selectCmsRoleByName(sysUserRoleVO.getRoleName());
            if (cmsRole == null) {
                throw new BusinessException("您输入的角色名称" + sysUserRoleVO.getRoleName() + "不存在，请重新输入！");
            }
            sysUserRoleVO.setRoleId(cmsRole.getId());
        });

        List<CmsUserRole> cmsUserRoles = new ArrayList<>();

        userList.stream().forEach(sysUserRoleVO -> {
            CmsUserRole cmsUserRole = new CmsUserRole();
            cmsUserRole.setRoleId(sysUserRoleVO.getRoleId());
            cmsUserRole.setUserId(sysUserRoleVO.getUserId());
            cmsUserRoles.add(cmsUserRole);
        });

        return cmsUserRoles;
    }

    /**
     * 批量导入授权用户
     *
     * @param userList
     * @return
     */
    @Override
    public int importUserRole(List<CmsUserRole> userList) {
        if (userList != null && !userList.isEmpty()) {
            return cmsUserRoleMapper.batchUserRole(userList);
        }

        return 0;
    }

    /**
     * 批量删除普通用户
     */
    public int deleteUserRoles(Long roleId, List<CmsUserRole> sysUserRoles) {
        if (sysUserRoles != null && !sysUserRoles.isEmpty()) {
            return cmsUserRoleMapper.deleteUserRoles(roleId, sysUserRoles);
        }
        return 0;
    }

    /**
     * 修改角色状态
     *
     * @param cmsRole
     * @return
     */
    @Override
    public int changeStatus(CmsRole cmsRole) {
        return cmsRoleMapper.updateCmsRole(cmsRole);
    }


}
