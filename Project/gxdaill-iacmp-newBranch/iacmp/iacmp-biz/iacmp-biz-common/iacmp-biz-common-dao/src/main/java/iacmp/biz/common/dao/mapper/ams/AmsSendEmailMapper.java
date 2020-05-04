package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsSendEmail;

import java.util.List;

/**
 * 邮件 数据层
 *
 * @author wxy
 * @date 2019-10-25
 */
public interface AmsSendEmailMapper {
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
     * 删除邮件
     *
     * @param iD 邮件ID
     * @return 结果
     */
    public int deleteAmsSendEmailById(Integer iD);

    /**
     * 批量删除邮件
     *
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsSendEmailByIds(String[] iDs);

}