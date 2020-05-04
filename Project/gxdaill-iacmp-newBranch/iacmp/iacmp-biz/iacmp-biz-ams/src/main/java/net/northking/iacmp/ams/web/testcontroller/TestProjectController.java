package net.northking.iacmp.ams.web.testcontroller;

import lombok.Data;
import net.northking.iacmp.core.domain.Ztree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:TestController
 * @Description：TODO
 * @Date：Create in 11:23 AM2019/8/15
 * @Modified by:
 * @Version:1.0
 */
@Controller
public class TestProjectController {


    /**
     * 加载测试列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> getZtreeData() {


        List<Folder> folderList = new ArrayList<>();

        Folder pFolder = new Folder(1, "规划部", 0);
        folderList.add(pFolder);

        Folder folder1 = new Folder(10, "影像平台二期-2018-0079", 1);
        folderList.add(folder1);

        Folder folder2 = new Folder(100, "1-立项相关", 10);
        Folder folder100 = new Folder(1000, "立项及采购请示签报", 100);

        Folder folder1000 = new Folder(10000, "立项及采购请示签报", 1000);
        Folder folder1001 = new Folder(10001, "立项及采购请示签报", 1000);

        folderList.add(folder1000);
        folderList.add(folder1001);

        Folder folder101 = new Folder(1001, "立项申请材料", 100);
        Folder folder1003 = new Folder(1010, "xx文件", 1001);
        folderList.add(folder1003);

        Folder folder102 = new Folder(1002, "立项信息委审批单", 100);
        Folder folder1004 = new Folder(1011, "xx文件", 1002);
        folderList.add(folder1004);

        Folder folder103 = new Folder(1003, "立项预审纪要", 100);
        Folder folder1005 = new Folder(1012, "xx文件", 1003);
        folderList.add(folder1005);

        folderList.add(folder100);
        folderList.add(folder101);
        folderList.add(folder102);
        folderList.add(folder103);


        Folder folder3 = new Folder(101, "2-采购文件", 10);
        Folder folder104 = new Folder(1010, "xx文件", 101);
        folderList.add(folder104);

        Folder folder4 = new Folder(102, "3-合同相关", 10);
        Folder folder105 = new Folder(1010, "xx文件", 102);
        folderList.add(folder105);

        Folder folder5 = new Folder(103, "4-需求相关", 10);
        Folder folder106 = new Folder(1010, "xx文件", 103);
        folderList.add(folder106);

        Folder folder6 = new Folder(104, "5-方案相关", 10);
        Folder folder107 = new Folder(1010, "xx文件", 104);
        folderList.add(folder107);

        Folder folder7 = new Folder(105, "6-变更相关", 10);
        Folder folder108 = new Folder(1010, "xx文件", 105);
        folderList.add(folder108);

        Folder folder8 = new Folder(106, "7-测试相关", 10);
        Folder folder109 = new Folder(1010, "xx文件", 106);
        folderList.add(folder109);

        Folder folder9 = new Folder(107, "9-安全相关", 10);
        Folder folder110 = new Folder(1010, "xx文件", 107);
        folderList.add(folder110);

        Folder folder10 = new Folder(108, "10-投产相关", 10);
        Folder folder111 = new Folder(1010, "xx文件", 108);
        folderList.add(folder111);

        Folder folder11 = new Folder(109, "11-运维相关", 10);
        Folder folder112 = new Folder(1010, "xx文件", 109);
        folderList.add(folder112);

        Folder folder12 = new Folder(110, "12-大数据相关", 10);
        Folder folder113 = new Folder(1010, "xx文件", 110);
        folderList.add(folder113);

        Folder folder13 = new Folder(111, "13-培训相关", 10);
        Folder folder114 = new Folder(1010, "xx文件", 111);
        folderList.add(folder114);

        Folder folder14 = new Folder(112, "14-结项相关", 10);
        Folder folder115 = new Folder(1010, "xx文件", 112);
        folderList.add(folder115);

        folderList.add(folder2);
        folderList.add(folder3);
        folderList.add(folder4);
        folderList.add(folder5);
        folderList.add(folder6);
        folderList.add(folder7);
        folderList.add(folder8);
        folderList.add(folder9);
        folderList.add(folder10);
        folderList.add(folder11);
        folderList.add(folder12);
        folderList.add(folder13);
        folderList.add(folder14);

        Folder folder15 = new Folder(113, "中关村-结项准备文档影像管理平台二期", 10);
        folderList.add(folder15);


        Folder folder20 = new Folder(20, "影像平台一期-2018-0079", 1);
        folderList.add(folder20);

        Folder folder200 = new Folder(200, "1-立项相关", 20);
        Folder folder2000 = new Folder(2000, "立项及采购请示签报", 200);

        Folder folder20000 = new Folder(20000, "立项及采购请示签报", 2000);
        Folder folder20001 = new Folder(20001, "立项及采购请示签报", 2000);

        folderList.add(folder20000);
        folderList.add(folder20001);

        Folder folder2001 = new Folder(2001, "立项申请材料", 200);
        Folder folder2010 = new Folder(2010, "xx文件", 2001);
        folderList.add(folder2010);

        Folder folder2002 = new Folder(2002, "立项信息委审批单", 200);
        Folder folder2011 = new Folder(2011, "xx文件", 2002);
        folderList.add(folder2011);

        Folder folder2003 = new Folder(2003, "立项预审纪要", 200);
        Folder folder2012 = new Folder(2012, "xx文件", 2003);
        folderList.add(folder2012);

        folderList.add(folder2000);
        folderList.add(folder2001);
        folderList.add(folder2002);
        folderList.add(folder2003);


        Folder folder201 = new Folder(201, "2-采购文件", 20);
        Folder folder2020 = new Folder(2020, "xx文件", 201);
        folderList.add(folder2020);

        Folder folder202 = new Folder(202, "3-合同相关", 20);
        Folder folder2021 = new Folder(2021, "xx文件", 202);
        folderList.add(folder2021);

        Folder folder203 = new Folder(203, "4-需求相关", 20);
        Folder folder2022 = new Folder(2022, "xx文件", 203);
        folderList.add(folder2022);

        Folder folder204 = new Folder(204, "5-方案相关", 20);
        Folder folder2023 = new Folder(2023, "xx文件", 204);
        folderList.add(folder2023);

        Folder folder205 = new Folder(205, "6-变更相关", 20);
        Folder folder2024 = new Folder(2024, "xx文件", 205);
        folderList.add(folder2024);

        Folder folder206 = new Folder(206, "7-测试相关", 20);
        Folder folder2025 = new Folder(2025, "xx文件", 206);
        folderList.add(folder2025);

        Folder folder207 = new Folder(207, "9-安全相关", 20);
        Folder folder2026 = new Folder(2026, "xx文件", 207);
        folderList.add(folder2026);

        Folder folder208 = new Folder(208, "10-投产相关", 20);
        Folder folder2027 = new Folder(2027, "xx文件", 208);
        folderList.add(folder2027);

        Folder folder209 = new Folder(209, "11-运维相关", 20);
        Folder folder2028 = new Folder(2028, "xx文件", 209);
        folderList.add(folder2028);

        Folder folder210 = new Folder(210, "12-大数据相关", 20);
        Folder folder2029 = new Folder(2029, "xx文件", 210);
        folderList.add(folder2029);

        Folder folder211 = new Folder(211, "13-培训相关", 20);
        Folder folder2030 = new Folder(2030, "xx文件", 211);
        folderList.add(folder2030);

        Folder folder212 = new Folder(212, "14-结项相关", 20);
        Folder folder2031 = new Folder(2031, "xx文件", 212);
        folderList.add(folder2031);

        folderList.add(folder200);
        folderList.add(folder201);
        folderList.add(folder202);
        folderList.add(folder203);
        folderList.add(folder204);
        folderList.add(folder205);
        folderList.add(folder206);
        folderList.add(folder207);
        folderList.add(folder208);
        folderList.add(folder209);
        folderList.add(folder210);
        folderList.add(folder211);
        folderList.add(folder212);

        Folder folder213 = new Folder(213, "中关村-结项准备文档影像管理平台一期", 20);
        folderList.add(folder213);

        List<Ztree> list = new ArrayList<>();
        for (Folder folder : folderList) {
            Ztree ztree = new Ztree();
            ztree.setId(folder.getFolderId().longValue());
            ztree.setpId(folder.getFolderPId().longValue());
            ztree.setName(folder.getName());
            ztree.setTitle(folder.getName());
            list.add(ztree);
        }
        return list;
    }


    @PostMapping("/uip/upload")
    public String aa() {

        return null;
    }

}

@Data
class Folder {

    private Integer folderId;

    private String name;

    private Integer folderPId;

    public Folder(Integer folderId, String name, Integer folderPId) {
        this.folderId = folderId;
        this.name = name;
        this.folderPId = folderPId;
    }

}