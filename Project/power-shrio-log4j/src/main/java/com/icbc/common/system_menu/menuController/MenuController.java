package com.icbc.common.system_menu.menuController;

import com.icbc.common.system_menu.menuService.MenuService;
import com.icbc.entity.mapper.menu.TreeNode;
import com.icbc.utils.TreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 * 首页菜单类
 * */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/setRedis", method = {RequestMethod.GET, RequestMethod.POST})
    public void setRedis() {
        //redisTemplate.opsForSet().add("asd_fgh", 1231);
        redisTemplate.opsForSet().add("testredis", "qwe");
        redisTemplate.opsForSet().add("testredis1", "testredis1");
        redisTemplate.opsForSet().add("testredis2", "testredis2");
        redisTemplate.opsForSet().add("testredis3", "testredis3");
        redisTemplate.opsForSet().add("testredis4", "testredis4");
        redisTemplate.opsForSet().add("testredis5", "testredis5");
        System.out.println(1);
    }

    /**
     * 跳转测试elementui-table页面
     *
     * @return
     */
    @RequestMapping(value = "/toTable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toTable() {
        ModelAndView view = new ModelAndView();
        view.setViewName("table-element/table-js");
        return view;
    }

    /*
     * 测试获取JSON数据
     * */
    @RequestMapping(value = "/getJson", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getJson() {
        String s = "dataItemTaskList\":[{\"success\":true,\"dataItemId\":\"144002\",\"protocolId\":\"1\",\"mpedId\":\"9000001028765\",\"tmnlTaskId\":\"TK$9000001028765_608\",\"dataItemDescList\":null,\"dataItemName\":\"终端保电解除\",\"errNo\":1,\"errMessage\":null,\"mpedDesc\":null,\"mpedDescMap\":null}]";
        return s;
    }

    /*
     * 跳转至EL-Tree-lazy页面
     * */
    @RequestMapping(value = "/toTreeLazy", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toTreeLazy() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo/EL-Tree-lazy");
        return modelAndView;
    }

    /*
     * 获取当前父节点下的子节点数据
     * */
    @RequestMapping(value = "/getTreeChild", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TreeNode> getTreeChild(String id) {
        List<TreeNode> childList = menuService.getTreeChild(id);
        return childList;

    }

    /*
     * 获取全部父节点
     * */
    @RequestMapping(value = "/getTreeParent", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TreeNode> getTreeParent() {
        List<TreeNode> treeList = menuService.getTreeParent();

        return treeList;
    }

    /**
     * 首页树
     *
     * @return
     */
    @RequestMapping(value = "/getTree", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TreeUtils> getTree() {
        List<TreeNode> treeList = menuService.getTree();
        List<TreeUtils> treeUtilsList = null;
        try {
            treeUtilsList = TreeUtils.getTreeList(treeList, "id", "pid", "name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeUtilsList;
    }


    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = "/toTest2", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView     toTest2() {
        ModelAndView view = new ModelAndView();
        view.setViewName("toTest2");
        return view;
    }

    /**
     * 跳转员工信息管理
     *
     * @return
     */
    @RequestMapping(value = "/toStaffInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toStaffInfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("userInfo/staffInfo");
        return view;
    }

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = "/toMain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toMain() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        //logger.info("hello world");
        return view;
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("TestModule");
        return modelAndView;
    }
}
