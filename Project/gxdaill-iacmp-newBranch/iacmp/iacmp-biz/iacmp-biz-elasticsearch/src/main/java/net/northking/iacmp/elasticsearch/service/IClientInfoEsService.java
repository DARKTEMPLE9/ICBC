package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.ClientInfoType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IClientInfoEsService {

    /**
     * 保存客户信息
     *
     * @param clientInfoType
     * @return
     */
    Long saveClientInfo(ClientInfoType clientInfoType);

    /**
     * 查询全部客户信息
     *
     * @return
     */
    List<ClientInfoType> findAll();

    /**
     * 根ID流水号删除客户信息
     *
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 局部更新客户信息
     *
     * @return
     */
    String updateById(ClientInfoType clientInfoType);

    /**
     * 根据ID 查询客户信息
     *
     * @return
     */
    ClientInfoType findById(Long id);

    /**
     * 根据身份证号查询客户信息
     */
    ClientInfoType findByIdCard(String idCard);

    /**
     * 根据条件查询客户信息
     */
    List<ClientInfoType> findClientInfo(ClientInfoType clientInfoType);
}
