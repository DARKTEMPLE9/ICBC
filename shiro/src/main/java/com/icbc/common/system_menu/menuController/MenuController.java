package com.icbc.common.system_menu.menuController;

import com.icbc.common.system_menu.menuService.MenuService;
import com.icbc.entity.menu.TreeNode;
import com.icbc.utils.TreeUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    private Logger logger = LoggerFactory.getLogger(this.getClass());




    /**
     * 首页树
     * @return
     */
    @RequestMapping(value = "/getTree" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<TreeUtils> getTree(){
        List<TreeNode> treeList =  menuService.getTree();
        List<TreeUtils> treeUtilsList = null;
        try {
            treeUtilsList = TreeUtils.getTreeList(treeList,"id", "pid", "name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeUtilsList;
    }
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = "/toMain",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView toMain(){
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        logger.info("hello world");
        return view;
    }


}
