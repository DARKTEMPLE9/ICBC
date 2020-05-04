package net.northking.iacmp.elasticsearch.repository;

import net.northking.iacmp.elasticsearch.domain.TransactionCodeType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ITransactionCodeRepository extends ElasticsearchRepository<TransactionCodeType, Long> {
    /**
     * 根据全局流水号查询
     */
    TransactionCodeType findByTransactionCode(String transactionCode);

    /**
     * 根据流水号查询
     */
    TransactionCodeType findByBusino(String busino);

    /**
     * 根据全局流水号删除
     */
    int deleteByTransactionCode(String transactionCode);
}
