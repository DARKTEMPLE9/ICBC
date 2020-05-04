package net.northking.iacmp.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.northking.iacmp.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsParamMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsParam;
import net.northking.iacmp.server.service.IAmsParamService;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;

/**
 * 档案参数 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsParamServiceImpl implements IAmsParamService {
    @Autowired
    private AmsParamMapper amsParamMapper;

    /**
     * 查询档案参数信息
     *
     * @param id 档案参数ID
     * @return 档案参数信息
     */
    @Override
    public AmsParam selectAmsParamById(String id) {
        return amsParamMapper.selectAmsParamById(id);
    }

    /**
     * 查询档案参数列表
     *
     * @param amsParam 档案参数信息
     * @return 档案参数集合
     */
    @Override
    public List<AmsParam> selectAmsParamList(AmsParam amsParam) {
        return amsParamMapper.selectAmsParamList(amsParam);
    }

    /**
     * 新增档案参数
     *
     * @param amsParam 档案参数信息
     * @return 结果
     */
    @Override
    public int insertAmsParam(AmsParam amsParam) {
        //添加档案参数主键
        if (amsParam.getId() == null || amsParam.getId().equals("")) {
            String id = UUID.randomUUID().toString().replace("-", "");
            amsParam.setId(id);
        }
        return amsParamMapper.insertAmsParam(amsParam);
    }

    /**
     * 修改档案参数
     *
     * @param amsParam 档案参数信息
     * @return 结果
     */
    @Override
    public int updateAmsParam(AmsParam amsParam) {
        return amsParamMapper.updateAmsParam(amsParam);
    }

    /**
     * 删除档案参数对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsParamByIds(String ids) {
        return amsParamMapper.deleteAmsParamByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询实物类型
     *
     * @param amsParam
     * @return
     */
    @Override
    public List<Ztree> selectAmsParamMatterType(AmsParam amsParam) {
        List<AmsParam> amsParamList = amsParamMapper.selectAmsParamList(amsParam);
        List<Ztree> ztrees = this.initZtree(amsParamList);
        return ztrees;
    }

    /**
     * 实物类型树
     *
     * @param amsParamList 档案类型列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<AmsParam> amsParamList) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (AmsParam amsParam : amsParamList) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(amsParam.getId()));
            ztree.setpId(Long.valueOf(amsParam.getId()));
            ztree.setName(amsParam.getParamName());
            ztree.setTitle(amsParam.getParamCode());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
