package net.northking.iacmp.ams.web.controller.system;


import net.northking.iacmp.common.bean.domain.ams.AmsAgent;
import net.northking.iacmp.config.Global;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsAgentService;
import net.northking.iacmp.system.domain.SysMenu;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysMenuService;
import net.northking.iacmp.system.service.ISysRoleService;
import net.northking.iacmp.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * 首页 业务处理
 *
 * @author wxy
 */
@Controller
public class SysIndexController extends BaseController {
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private IAmsAgentService amsAgentService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();

        //操作代理
        AmsAgent amsAgent = new AmsAgent();
        //代理人号
        amsAgent.setAgentCode(user.getLoginName());
        //查询代理信息
        List<AmsAgent> agentList = amsAgentService.selectAmsAgentList(amsAgent);
        if (agentList != null && agentList.size() > 0) {
            //角色ids
            String[] ids = null;
            //代理所有委托人角色
            List<SysRole> roles = new ArrayList<>();
            for (int i = 0; i < agentList.size(); i++) {
                //代理开始日期
                Date startDate = agentList.get(i).getAgentStartDate();
                //代理结束日期
                Date endDate = agentList.get(i).getAgentEndDate();
                //代理权限在当日24点[即次日0点]结束
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                Date tmpDate = calendar.getTime();
                //当前日期
                Date now = new Date();
                //判断代理是否结束
                if (now.getTime() >= startDate.getTime()) {
                    if (now.getTime() < calendar.getTimeInMillis()) {
                        Set<String> roleIds = new HashSet<>();
                        //委托人号
                        String trustorCode = agentList.get(i).getTrustorCode();
                        //委托人用户
                        SysUser trustorUser = new SysUser();
                        trustorUser.setLoginName(trustorCode);
                        List<SysUser> userList = sysUserService.selectUserList(trustorUser);
                        if (userList.size() > 0) {
                            trustorUser = userList.get(0);
                            Long userId = trustorUser.getUserId();
                            List<SysRole> roleList = sysRoleService.selectRolesByUserId(userId);
                            for (SysRole role : roleList) {
                                if (role.isFlag() == true) {
                                    roles.add(role);
                                }
                            }
                            //添加代理人角色
                            roles.addAll(user.getRoles());
                        }

                        for (SysRole role : roles) {
                            roleIds.add(String.valueOf(role.getRoleId()));
                        }
                        List<String> idList = new ArrayList<>(roleIds);
                        //角色ids
                        ids = new String[roleIds.size()];
                        for (int n = 0; n < ids.length; n++) {
                            ids[n] = idList.get(n);
                        }
                    } else {
                        if ("1".equals(agentList.get(i).getAgentStatus())) {
                            AmsAgent agent = agentList.get(i);
                            agent.setAgentStatus("0");
                            amsAgentService.updateAmsAgent(agent);
                        }
                    }
                } else {//当前时间小于代理开始时间（代理未开始）
                    AmsAgent agent = agentList.get(i);
                    agent.setAgentStatus("2");
                    amsAgentService.updateAmsAgent(agent);
                }
            }

            if (ids == null) {
                List<SysRole> roleList = user.getRoles();
                ids = new String[roleList.size()];
                for (SysRole role : roleList) {
                    int i = roleList.indexOf(role);
                    ids[i] = String.valueOf(role.getRoleId());
                }
            }
            //根据角色id取出菜单
            List<SysMenu> menus = menuService.selectMenusByRoles(ids);
            mmap.put("menus", menus);
            mmap.put("user", user);
            mmap.put("copyrightYear", Global.getCopyrightYear());
            mmap.put("demoEnabled", Global.isDemoEnabled());
            return "index";
        }
        //根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
