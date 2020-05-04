package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.elasticsearch.domain.RecordInfoType;
import net.northking.iacmp.elasticsearch.repository.IRecordInfoRepository;
import net.northking.iacmp.elasticsearch.service.IRecordInfoEsService;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSource(value = DataSourceType.SLAVE)
public class RecordInfoEsServiceImpl implements IRecordInfoEsService {
    @Autowired
    private IRecordInfoRepository recordInfoRepository;

    /**
     * 新增著录信息
     */
    public RecordInfoType insertRecordInfo(RecordInfoType recordInfoType) {
        return recordInfoRepository.save(recordInfoType);
    }

    /**
     * 根据流水号查询著录信息
     */
    @Override
    public RecordInfoType findByBusino(String busino) {
        return recordInfoRepository.findByBusino(busino);
    }

    /**
     * 更新著录信息
     */
    @Override
    public RecordInfoType updateRecordInfo(RecordInfoType recordInfoType) {
        return recordInfoRepository.save(recordInfoType);
    }

    /**
     * 根据流水号删除著录信息
     */
    @Override
    public int deleteByBusino(String busino) {
        return recordInfoRepository.deleteByBusino(busino);
    }
}
