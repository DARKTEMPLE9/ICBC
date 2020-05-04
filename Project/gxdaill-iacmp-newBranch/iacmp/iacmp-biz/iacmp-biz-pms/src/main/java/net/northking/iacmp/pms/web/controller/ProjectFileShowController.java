package net.northking.iacmp.pms.web.controller;

import net.northking.iacmp.common.bean.domain.cms.PmsBatch;
import net.northking.iacmp.common.bean.vo.cms.PmsBatchVO;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:ProjectFileShowController
 * @Description：项目文档展示
 * @Date：Create in 11:57 AM2019/12/22
 * @Modified by:
 * @Version:1.0
 */
@Controller
@RequestMapping("/pms/pmsBatch")
public class ProjectFileShowController {

    private String prefix = "pms/fileShow";

    @Autowired
    private DealPmsBatch dealPmsBatch;

    @Autowired
    private DealPmsBatchList dealPmsBatchList;

    @RequestMapping("/init")
    public String projectFileShow() {
        //获取用户下有权限看的所有项目
        List<PmsBatchVO> rolePmstBatchs = getPmsBatchByRole();

        //组装项目树


        return prefix + "fileShow";
    }

    private List<PmsBatchVO> getPmsBatchByRole() {

        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roles = sysUser.getRoles();

        List<PmsBatchVO> retPmsBatchs = new ArrayList<>();

        for (int i = 0; i < roles.size(); i++) {
            List<PmsBatchVO> starts = dealPmsBatch.dealPmsBatchs(roles.get(i).getRoleId(), sysUser);
            if (starts != null && starts.size() > 0) {
                retPmsBatchs = dealPmsBatchList.retPmsBatchs(starts, retPmsBatchs);
            }
        }

        return retPmsBatchs;
    }


}
