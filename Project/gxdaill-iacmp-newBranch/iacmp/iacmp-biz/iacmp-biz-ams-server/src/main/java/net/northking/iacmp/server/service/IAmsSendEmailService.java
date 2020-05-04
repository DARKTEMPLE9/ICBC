package net.northking.iacmp.server.service;


import net.northking.iacmp.common.bean.domain.ams.AmsSendEmail;

import java.util.List;

/**
 * 邮件 服务层
 *
 * @author wxy
 * @date 2019-10-25
 */
public interface IAmsSendEmailService {
    /**
     * 查询邮件信息
     *
     * @param iD 邮件ID
     * @return 邮件信息
     */
    public AmsSendEmail selectAmsSendEmailById(Integer iD);

    /**
     * 查询邮件列表
     *
     * @param amsSendEmail 邮件信息
     * @return 邮件集合
     */
    public List<AmsSendEmail> selectAmsSendEmailList(AmsSendEmail amsSendEmail);

    /**
     * 新增邮件
     *
     * @param amsSendEmail 邮件信息
     * @return 结果
     */
    public int insertAmsSendEmail(AmsSendEmail amsSendEmail);

    /**
     * 修改邮件
     *
     * @param amsSendEmail 邮件信息
     * @return 结果
     */
    public int updateAmsSendEmail(AmsSendEmail amsSendEmail);

    /**
     * 删除邮件信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsSendEmailByIds(String ids);

}
