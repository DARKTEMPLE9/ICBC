package net.northking.iacmp.system.mapper;

import java.util.List;

import net.northking.iacmp.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 用户表 数据层
 *
 * @author wxy
 */
@Service
public interface SysUserMapper {
    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 查询用户列表
     *
     * @return 用户信息集合信息
     */
    List<String> selectUser();

    /**
     * 根据条件分页查询未已配用户角色列表
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
     */
    int deleteUserByIds(Long[] ids);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);

    /**
     * 通过登录名修改用户状态
     *
     * @param loginName 用户信息
     * @return 结果
     */
    int changeStatusByLoginName(String loginName);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUser user);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    SysUser checkEmailUnique(String email);

    /**
     * 根据角色id和部门id查询用户名
     *
     * @param roleId
     * @param deptId
     * @return
     */
    List<SysUser> selectNextUserByroleId(@Param("roleId") String roleId, @Param("deptId") String deptId);

    /**
     * 根据角色查询用户
     *
     * @param roleId
     * @return
     */
    List<SysUser> selectNextUserByrole(String roleId);

    /**
     * 根据userid查询用户
     *
     * @param userId
     * @return
     */
    List<SysUser> selectNextUserByuserId(Long userId);


    List<SysUser> getArchManager();


    /**
     * 通过用户角色查找该用户可查看用户列表
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectUsersByUserId(SysUser sysUser);

    /**
     * 查询所有用户
     *
     * @param
     * @return
     */
    List<SysUser> selectAllUserList();

    /**
     * 查找所有登录名称
     *
     * @return
     */
    List<String> selectLoginName();
}
