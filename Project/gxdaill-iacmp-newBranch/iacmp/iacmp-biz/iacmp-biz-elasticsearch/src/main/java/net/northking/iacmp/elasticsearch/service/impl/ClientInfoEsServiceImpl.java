package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.elasticsearch.domain.ClientInfoType;
import net.northking.iacmp.elasticsearch.repository.IClientInfoRepository;
import net.northking.iacmp.elasticsearch.service.IClientInfoEsService;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.framework.web.domain.server.Sys;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@DataSource(value = DataSourceType.SLAVE)
public class ClientInfoEsServiceImpl implements IClientInfoEsService {
    @Autowired
    private IClientInfoRepository clientInfoRepository;

    /**
     * 保存客户信息
     *
     * @param clientInfoType
     * @return
     */
    @Override
    public Long saveClientInfo(ClientInfoType clientInfoType) {
        clientInfoType = clientInfoRepository.save(clientInfoType);
        return clientInfoType != null ? clientInfoType.getId() : null;
    }

    /**
     * 查询全部客户信息
     *
     * @return
     */
    @Override
    public List<ClientInfoType> findAll() {
        Iterable<ClientInfoType> clientInfoTypeIterable = clientInfoRepository.findAll();
        List<ClientInfoType> list = new ArrayList<>();
        for (ClientInfoType clientInfoType : clientInfoTypeIterable) {
            list.add(clientInfoType);
        }
        return list;
    }

    ;

    /**
     * 删除客户信息
     *
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        try {
            clientInfoRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 局部更新客户信息
     *
     * @return
     */
    @Override
    public String updateById(ClientInfoType clientInfoType) {
        clientInfoRepository.save(clientInfoType);
        return null;
    }

    /**
     * 根据ID 查询客户信息
     *
     * @return
     */
    @Override
    public ClientInfoType findById(Long id) {
        Optional<ClientInfoType> clientInfoTypeOptional = clientInfoRepository.findById(id);
        return clientInfoTypeOptional.get();
    }

    /**
     * 根据身份证号查询客户信息
     */
    @Override
    public ClientInfoType findByIdCard(String idCard) {
        ClientInfoType clientInfoType = clientInfoRepository.findByCustomerIdCard(idCard);
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("customerName", "*张*"));
        Iterable<ClientInfoType> list = clientInfoRepository.search(query);
        for (ClientInfoType c : list) {
            System.out.println(c);
        }
        return clientInfoType;
    }

    /**
     * 根据多条件查询客户信息
     */
    @Override
    public List<ClientInfoType> findClientInfo(ClientInfoType clientInfoType) {
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (clientInfoType != null) {
            if (clientInfoType.getId() != null) {
                QueryBuilder queryBuilder = QueryBuilders.termQuery("id", clientInfoType.getId());
                query = query.must(queryBuilder);
            }
            if (clientInfoType.getBusino() != null && !"".equals(clientInfoType.getBusino())) {
                QueryBuilder queryBuilder = QueryBuilders.termQuery("busino", clientInfoType.getBusino());
                query = query.must(queryBuilder);
            }
            if (clientInfoType.getCustomerCode() != null && !"".equals(clientInfoType.getCustomerCode())) {
                QueryBuilder queryBuilder = QueryBuilders.termQuery("customerCode", clientInfoType.getCustomerCode());
                query = query.must(queryBuilder);
            }
            if (clientInfoType.getCustomerName() != null && !"".equals(clientInfoType.getCustomerName())) {
                QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("customerName", clientInfoType.getCustomerName());
                query = query.must(queryBuilder);
            }
        }
        return null;
    }
}
