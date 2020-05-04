package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.RecordInfoType;

public interface IRecordInfoEsService {
    /**
     * 新增著录信息
     */
    RecordInfoType insertRecordInfo(RecordInfoType recordInfoType);

    /**
     * 根据流水号查询著录信息
     */
    RecordInfoType findByBusino(String busino);

    /**
     * 更新著录信息
     */
    RecordInfoType updateRecordInfo(RecordInfoType recordInfoType);

    /**
     * 根据流水号删除著录信息
     */
    int deleteByBusino(String busino);
}
