package net.northking.iacmp.server.service.impl;

import java.util.List;

import iacmp.biz.common.dao.mapper.ams.AmsSendEmailMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsSendEmail;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.server.service.IAmsSendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;


/**
 * 邮件 服务层实现
 *
 * @author wxy
 * @date 2019-10-25
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsSendEmailServiceImpl implements IAmsSendEmailService {
    @Autowired
    private AmsSendEmailMapper amsSendEmailMapper;

    /**
     * 查询邮件信息
     *
     * @param iD 邮件ID
     * @return 邮件信息
     */
    @Override
    public AmsSendEmail selectAmsSendEmailById(Integer iD) {
        return amsSendEmailMapper.selectAmsSendEmailById(iD);
    }

    /**
     * 查询邮件列表
     *
     * @param amsSendEmail 邮件信息
     * @return 邮件集合
     */
    @Override
    public List<AmsSendEmail> selectAmsSendEmailList(AmsSendEmail amsSendEmail) {
        return amsSendEmailMapper.selectAmsSendEmailList(amsSendEmail);
    }

    /**
     * 新增邮件
     *
     * @param amsSendEmail 邮件信息
     * @return 结果
     */
    @Override
    public int insertAmsSendEmail(AmsSendEmail amsSendEmail) {
        return amsSendEmailMapper.insertAmsSendEmail(amsSendEmail);
    }

    /**
     * 修改邮件
     *
     * @param amsSendEmail 邮件信息
     * @return 结果
     */
    @Override
    public int updateAmsSendEmail(AmsSendEmail amsSendEmail) {
        return amsSendEmailMapper.updateAmsSendEmail(amsSendEmail);
    }

    /**
     * 删除邮件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsSendEmailByIds(String ids) {
        return amsSendEmailMapper.deleteAmsSendEmailByIds(Convert.toStrArray(ids));
    }

}
