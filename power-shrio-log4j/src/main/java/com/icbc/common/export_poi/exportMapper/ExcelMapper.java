package com.icbc.common.export_poi.exportMapper;

import com.icbc.common.export_poi.entity.Export_a;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ExcelMapper {

    List<Export_a> queryExcel();

    Map<String, Object> queryExcelObject();
}
