package net.northking.iacmp.elasticsearch.controller;

import net.northking.iacmp.elasticsearch.domain.RecordInfoType;
import net.northking.iacmp.elasticsearch.service.IRecordInfoEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/es/recordInfoEs")
public class RecordInfoEsController {
    @Autowired
    private IRecordInfoEsService recordInfoEsService;

    /**
     * 新增著录信息
     */
    @GetMapping("/add")
    @ResponseBody
    public RecordInfoType insertRecordInfo() {
        for (int i = 1; i <= 50; i++) {
            RecordInfoType recordInfoType = new RecordInfoType();
            recordInfoType.setId(Long.valueOf(i));
            recordInfoType.setAdminName("张三" + i);
            recordInfoType.setBatchNo(Integer.toString(i));
            recordInfoType.setBusino(Integer.toString(i));
            recordInfoEsService.insertRecordInfo(recordInfoType);
        }
        return null;
    }

    /**
     * 根据流水号查询著录信息
     */
    @GetMapping("/findByBuniso")
    @ResponseBody
    public RecordInfoType findByBusino() {
        String busino = "18";
        RecordInfoType recordInfoType = recordInfoEsService.findByBusino(busino);
        System.out.println(recordInfoType);
        return null;
    }

    /**
     * 更新著录信息
     */
    @GetMapping("/updateRecordInfo")
    @ResponseBody
    public RecordInfoType updateRecordInfo() {
        String busino = "18";
        RecordInfoType recordInfoType = recordInfoEsService.findByBusino(busino);
        if (recordInfoType != null) {
            recordInfoType.setAdminName("李四");
            RecordInfoType update = recordInfoEsService.updateRecordInfo(recordInfoType);
            System.out.println(update);

        }
        return null;
    }

    /**
     * 根据流水号删除著录信息
     */
    @GetMapping("/deleteByBusino")
    @ResponseBody
    public int deleteByBusino() {
        String busino = "18";
        return recordInfoEsService.deleteByBusino(busino);
    }
}
