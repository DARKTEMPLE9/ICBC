package com.icbc.common.export_poi.exportMapper;

import com.icbc.common.export_poi.entity.Export_a;
import com.icbc.common.export_poi.entity.Export_a_1;
import com.icbc.common.export_poi.entity.Export_a_1_1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ExcelMapper {

    List<Export_a> queryExcel();

    Map<String, Object> queryExcelObject();

    List<Export_a_1> queryExcel_a_1(@Param("row_id") String row_id);

    List<Export_a_1_1> queryExcel_a_1_1(@Param("row_id") String row_id1);
}
