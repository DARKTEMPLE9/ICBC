package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.TransactionCodeType;

public interface ITransactionCodeEsService {
    /**
     * 新增全局流水号信息
     */
    TransactionCodeType insertTransactionCode(TransactionCodeType transactionCodeType);

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
