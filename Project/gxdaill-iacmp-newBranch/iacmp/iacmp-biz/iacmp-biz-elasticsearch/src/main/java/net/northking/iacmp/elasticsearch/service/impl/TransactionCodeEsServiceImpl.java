package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.elasticsearch.domain.TransactionCodeType;
import net.northking.iacmp.elasticsearch.repository.ITransactionCodeRepository;
import net.northking.iacmp.elasticsearch.service.ITransactionCodeEsService;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSource(value = DataSourceType.SLAVE)
public class TransactionCodeEsServiceImpl implements ITransactionCodeEsService {
    @Autowired
    private ITransactionCodeRepository transactionCodeRepository;

    /**
     * 新增全局流水号信息
     */
    @Override
    public TransactionCodeType insertTransactionCode(TransactionCodeType transactionCodeType) {
        return transactionCodeRepository.save(transactionCodeType);
    }

    /**
     * 根据全局流水号查询
     */
    @Override
    public TransactionCodeType findByTransactionCode(String transactionCode) {
        return transactionCodeRepository.findByTransactionCode(transactionCode);
    }

    /**
     * 根据流水号查询
     */
    @Override
    public TransactionCodeType findByBusino(String busino) {
        return transactionCodeRepository.findByBusino(busino);
    }

    /**
     * 根据全局流水号删除
     */
    @Override
    public int deleteByTransactionCode(String transactionCode) {
        return transactionCodeRepository.deleteByTransactionCode(transactionCode);
    }
}
