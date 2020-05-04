package net.northking.iacmp.elasticsearch.repository;

import net.northking.iacmp.elasticsearch.domain.RecordInfoType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IRecordInfoRepository extends ElasticsearchRepository<RecordInfoType, Long> {
    /**
     * 根据流水号查询著录信息
     */
    RecordInfoType findByBusino(String busino);

    /**
     * 根据流水号删除著录信息
     */
    int deleteByBusino(String busino);
}
