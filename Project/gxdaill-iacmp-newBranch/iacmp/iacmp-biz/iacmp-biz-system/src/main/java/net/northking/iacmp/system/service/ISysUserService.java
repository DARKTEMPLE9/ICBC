package net.northking.iacmp.system.service;

import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.domain.SysUserRole;
import net.northking.iacmp.system.domain.SysUserRoleVO;

import java.util.List;

/**
 * 用户 业务层
 *
 * @author wxy
 */
public interface ISysUserService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 查询用户列表
     *
     * @return 用户信息集合信息
     */
    List<String> selectUser();

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByLoginName(String userName);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    List<SysUser> selectUserByUserNameList(String userName);

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    SysUser selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    SysUser selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUser selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int deleteUserByIds(String ids) throws Exception;

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUser user);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);

    /**
     * 修改用户详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUserInfo(SysUser user);

    /**
     * 修改用户密码信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int resetUserPwd(SysUser user);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    String checkEmailUnique(SysUser user);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    String selectUserRoleGroup(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userId 用户ID
     * @return 结果
     */
    String selectUserPostGroup(Long userId);

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */
    int changeStatus(SysUser user);

    /**
     * 通过登录名修改用户状态
     *
     * @param loginName 用户信息
     * @return 结果
     */
    int changeStatusByLoginName(String loginName);

    /**
     * 根据角色id和部门id查询用户
     *
     * @param roleId
     * @param deptId
     * @return
     */
    List<SysUser> selectNextUserByroleId(String roleId, String deptId);

    /**
     * 根据角色查询用户名
     *
     * @param roleId
     * @return
     */
    List<SysUser> selectNextUserByrole(String roleId);

    /**
     * 根据角色查询用户名
     *
     * @param userId
     * @return
     */
    List<SysUser> selectNextUserByuserId(Long userId);

    /**
     * @return
     */
    List<SysUser> getArchManager();

    /**
     * 通过用户id查找该用户可查看用户列表
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectUsersBySysUser(SysUser sysUser);

    /**
     * 查询所有用户
     *
     * @param
     * @return
     */
    List<SysUser> selectAllUserList();

    /**
     * 查找所有用户名
     *
     * @return
     */
    List<String> selectLoginName();

    /**
     * 批量导入授权用户
     *
     * @param userList
     * @return
     */
    int importUserRole(List<SysUserRole> userList);

    /**
     * 用户关联表信息
     *
     * @param userList
     * @return
     */
    List<SysUserRole> selectUserRoleByUserInfo(List<SysUserRoleVO> userList);

    /**
     * 批量删除普通用户
     */
    int deleteUserRoles(Long roleId, List<SysUserRole> sysUserRoles);

    /**
     * 查询全部辅部门
     */
    List<String> selectAuxiliaryDeptList(SysUser sysUser);
}
