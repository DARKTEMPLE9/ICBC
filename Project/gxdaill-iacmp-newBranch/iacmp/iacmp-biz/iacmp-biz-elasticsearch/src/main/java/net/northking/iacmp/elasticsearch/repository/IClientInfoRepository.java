package net.northking.iacmp.elasticsearch.repository;

import net.northking.iacmp.elasticsearch.domain.ClientInfoType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IClientInfoRepository extends ElasticsearchRepository<ClientInfoType, Long> {
    /**
     * 根据身份证号查询客户信息
     */
    ClientInfoType findByCustomerIdCard(String idCard);

}
