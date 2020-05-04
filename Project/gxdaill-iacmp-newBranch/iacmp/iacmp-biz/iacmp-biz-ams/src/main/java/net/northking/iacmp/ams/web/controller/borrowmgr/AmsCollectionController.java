package net.northking.iacmp.ams.web.controller.borrowmgr;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.domain.ams.AmsCollection;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.server.service.IAmsCollectionService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 专题库 信息操作处理
 *
 * @author wxy
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/borrow/amsCollection")
public class AmsCollectionController extends BaseController {
    private String prefix = "borrow/amsCollection";

    @Autowired
    private IAmsCollectionService amsCollectionService;

    @Autowired
    private IAmsArchivesService amsArchivesService;

    @RequiresPermissions("borrow:amsCollection:view")
    @GetMapping("/index/{id}")
    public String amsCollection(@PathVariable String id, ModelMap mmap) {
        mmap.put("path", id);

        if (id.equals("fav")) {
            String userCode = ShiroUtils.getLoginName();
            mmap.put("userCode", userCode);
            return prefix + "/favorite";
        } else if (id.equals("col")) {
            return prefix + "/amsCollection";
        }

        return null;
    }

    /**
     * 查询专题库列表
     */
    @RequiresPermissions("borrow:amsCollection:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsCollection amsCollection) {
        startPage();
        List<AmsCollection> list = amsCollectionService.selectAmsCollectionList(amsCollection);
        return getDataTable(list);
    }

    /**
     * @param specArchId
     * @param mmap
     * @return
     */
    @GetMapping("/view/{specArchId}")
    public String view(@PathVariable("specArchId") String specArchId, ModelMap mmap) {
        String archivesId = "";
        String id = specArchId;
        AmsCollection amsCollection = new AmsCollection();
        amsCollection.setColId(Integer.valueOf(id));
        List<AmsCollection> list = amsCollectionService.selectAmsCollectionList(amsCollection);

        archivesId = list.get(0).getArchivesId();
        mmap.put("archivesId", archivesId);
        return prefix + "/view";
    }

    /**
     * 查询专题库的档案列表
     *
     * @param id
     * @return
     */
    @PostMapping("/arcList/{id}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("id") String id) {
        startPage();
        AmsArchivesVO amsArchives = new AmsArchivesVO();
        amsArchives.setId(id);
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
        return getDataTable(list);
    }

    /**
     * 导出专题库列表
     */
    @RequiresPermissions("borrow:amsCollection:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsCollection amsCollection) {
        List<AmsCollection> list = amsCollectionService.selectAmsCollectionList(amsCollection);
        ExcelUtil<AmsCollection> util = new ExcelUtil<>(AmsCollection.class);
        return util.exportExcel(list, "amsCollection");
    }

    /**
     * 新增专题库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存专题库
     */
    @RequiresPermissions("borrow:amsCollection:add")
    @Log(title = "专题库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsCollection amsCollection) {
        return toAjax(amsCollectionService.insertAmsCollection(amsCollection));
    }

    /**
     * 修改专题库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        AmsCollection amsCollection = amsCollectionService.selectAmsCollectionById(id);
        mmap.put("amsCollection", amsCollection);
        return prefix + "/edit";
    }

    /**
     * 修改保存专题库
     */
    @RequiresPermissions("borrow:amsCollection:edit")
    @Log(title = "专题库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsCollection amsCollection) {
        return toAjax(amsCollectionService.updateAmsCollection(amsCollection));
    }

    /**
     * 删除专题库
     */
    @RequiresPermissions("borrow:amsCollection:remove")
    @Log(title = "专题库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsCollectionService.deleteAmsCollectionByIds(ids));
    }

    /**
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTree")
    public String arcBillTree() {
        return prefix + "/arcBillTree";
    }

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTwoTree")
    public String arcBillTwoTree() {
        return prefix + "/arcBillTwoTree";
    }

    /**
     * 找到所有用户收藏
     *
     * @param userCode
     * @return
     */
    @PostMapping("/list/{userCode}")
    @ResponseBody
    public TableDataInfo queryAllRecord(@PathVariable("userCode") String userCode, AmsCollection amsCollection, HttpServletRequest request) {
        List<AmsCollection> favoriteIdList;
        favoriteIdList = queryAllFavoriteId(userCode, amsCollection);

        String name = request.getParameter("name");
        String arcBillCode = request.getParameter("arcBillCode");
        String fileNo = request.getParameter("fileNo");
        String arcType = request.getParameter("arcType");
        String opDepNo = request.getParameter("opDepNo");
        String arcStatus = request.getParameter("arcStatus");
        String respOpName = request.getParameter("respOpName");
        String valPeriod = request.getParameter("valPeriod");
        String deptId = request.getParameter("deptId");
        startPage();

        String ids = "";
        List<AmsArchivesDTO> archivesList = new ArrayList<>();
        if (!favoriteIdList.isEmpty()) {
            for (int i = 0; i < favoriteIdList.size(); i++) {
                if (i != favoriteIdList.size() - 1) {
                    ids += favoriteIdList.get(i).getArchivesId() + ",";
                } else {
                    ids += favoriteIdList.get(i).getArchivesId();
                }
            }
            AmsArchivesVO archives = new AmsArchivesVO();
            if (!"".equals(name) || !"".equals(arcBillCode) || !"".equals(fileNo) || !"".equals(arcType) || !"".equals(opDepNo)
                    || !"".equals(arcStatus) || !"".equals(respOpName) || !"".equals(valPeriod) || !"".equals(deptId)) {
                archives.setName(name);
                archives.setArcBillCode(arcBillCode);
                archives.setFileNo(fileNo);
                archives.setArcType(arcType);
                archives.setOpDepNo(opDepNo);
                archives.setStatus(arcStatus);
                archives.setRespOpName(respOpName);
                archives.setValPeriod(valPeriod);

            }
            archivesList = amsArchivesService.selectAmsArchivesByFavorite(ids, archives);
        }

        return getDataTable(archivesList);
    }

    /**
     * 查找在收藏界面非删除的档案
     *
     * @param userCode
     * @return
     */
    private List<AmsCollection> queryAllFavoriteId(String userCode, AmsCollection amsCollection) {
        amsCollection.setSearcher(userCode);
        amsCollection.setStatus(1);
        amsCollection.setFLAG(0);
        return amsCollectionService.selectAmsCollectionList(amsCollection);
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String archiveId, ModelMap mmap) {
        AmsArchivesVO amsArchive = new AmsArchivesVO();
        amsArchive.setId(archiveId);
        List<AmsArchivesDTO> archives = amsArchivesService.selectAmsArchivesListAll(amsArchive);
        //空值判断
        if (archives != null && archives.size() > 0) {
            mmap.put("amsArchives", archives.get(0));
        }
        return prefix + "/detail";
    }
}
