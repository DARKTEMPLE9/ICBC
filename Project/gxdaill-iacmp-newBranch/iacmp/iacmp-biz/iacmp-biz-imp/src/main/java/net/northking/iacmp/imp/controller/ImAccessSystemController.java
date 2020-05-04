package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAccessSystem;
import net.northking.iacmp.imp.service.IImAccessSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 接入系统 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imAccessSystem")
public class ImAccessSystemController extends BaseController {

    @Autowired
    private IImAccessSystemService imAccessSystemService;


    /**
     * 查询接入系统列表
     */
    @PostMapping("/list")
    public List<ImAccessSystem> list(ImAccessSystem imAccessSystem) {
        List<ImAccessSystem> list = imAccessSystemService.selectImAccessSystemList(imAccessSystem);
        return list;
    }

    @PostMapping("/queryAllSystem")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAccessSystem> queryAllSystem(@RequestBody HashMap<String, Object> map) {
        List<ImAccessSystem> list = imAccessSystemService.queryAllSystem(map);
        return list;
    }

    /**
     * 查询系统总数
     *
     * @return
     */
    @PostMapping("/count")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer systemCount(@RequestBody HashMap map) {
        Integer count = 0;
        count = imAccessSystemService.selectSystemCount(map);
        return count;
    }

    /**
     * 小眼睛
     */
    @PostMapping("/querySystemById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public HashMap querySystemById(@RequestBody String systemId) {
        HashMap<String, Object> map;
        map = imAccessSystemService.querySystemById(systemId);
        return map;
    }

    @PostMapping("/queryBySysFlagInt")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAccessSystem> queryBySysFlagInt(@RequestBody Integer sysFlagInt) {
        List<ImAccessSystem> list = imAccessSystemService.queryBySysFlagInt(sysFlagInt);
        return list;
    }

    @PostMapping("/queryBySysFlagInt2")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAccessSystem> queryBySysFlagInt2(@RequestBody HashMap map) {
        List<ImAccessSystem> list = imAccessSystemService.queryBySysFlagInt2(map);
        return list;
    }

    /**
     * 新增保存接入系统
     */
    @PostMapping("/addSystem")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer addSystem(@RequestBody HashMap map) {
        return imAccessSystemService.insertImAccessSystem(map);
    }

    /**
     * 修改接入系统
     */
    @GetMapping("/edit/{id}")
    public ImAccessSystem edit(@PathVariable("id") String id) {
        ImAccessSystem imAccessSystem = imAccessSystemService.selectImAccessSystemById(id);
        return imAccessSystem;
    }

    /**
     * 修改保存接入系统
     */
    @PostMapping("/updateById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer updateById(@RequestBody HashMap map) {
        return imAccessSystemService.updateById(map);
    }

    /**
     * 删除接入系统
     */
    @PostMapping("/deleteById")
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public Integer deleteById(@RequestBody String id) {
        return imAccessSystemService.deleteImAccessSystemById(id);
    }

}
