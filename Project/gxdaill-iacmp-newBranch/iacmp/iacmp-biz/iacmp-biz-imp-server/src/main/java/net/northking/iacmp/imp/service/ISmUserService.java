package net.northking.iacmp.imp.service;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmUser;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ISmUserService {
    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    SmUser selectSmUserById(String usreId);

    HashMap selectUserAndOrgNameByUserId(String userId);

    /**
     * 查询用户列表
     *
     * @return 用户集合
     */
    List<SmUser> selectSmUserList(Map map);

    Integer selectSmUserCount(HashMap map);

    List<SmUser> findByUserCode(String userCode);

    /**
     * 新增用户
     *
     * @param smUser 用户信息
     */
    Integer insertSmUser(SmUser smUser);

    /**
     * 修改用户
     *
     * @param smUser 用户信息
     */
    Integer updateSmUser(SmUser smUser);

    /**
     * 删除用户信息
     *
     * @return 结果
     */
    Integer deleteSmUserById(String id);


}
