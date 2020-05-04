package net.northking.iacmp.pms.web.controller;

import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.vo.cms.PmsBatchVO;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author：Yanqingyu
 * @ClassName:Test
 * @Description：比较俩个对象执行
 * @Date：Create in 8:43 PM2019/12/20
 * @Modified by:
 * @Version:1.0
 */
@Component
public class DealPmsBatchList {


    /**
     * 获取到俩个项目集合不同的项目
     *
     * @return
     */
    private List<PmsBatchVO> getDifferentPmsBatch(List<PmsBatchVO> olds, List<PmsBatchVO> news) {

        if (olds.size() > news.size()) {
            return olds.stream().filter(pmsBatchVO -> !news.contains(pmsBatchVO)).collect(Collectors.toList());
        } else {
            return news.stream().filter(pmsBatchVO -> !olds.contains(pmsBatchVO)).collect(Collectors.toList());
        }

    }

    /**
     * 获取到俩个项目集合交集（以第一个参数为准）
     *
     * @param one
     * @param two
     * @return
     */
    private List<PmsBatchVO> getIdenticalPmsBatch(List<PmsBatchVO> one, List<PmsBatchVO> two) {
        return one.stream().filter(pmsBatchVO -> two.contains(pmsBatchVO)).collect(Collectors.toList());
    }

    /**
     * 对比这俩集合，取各个项目最大分类及权限
     *
     * @param one
     * @return
     */
    private List<PmsBatchVO> compareTwoPmsBatch(List<PmsBatchVO> one, List<PmsBatchVO> two) {

        //对这俩个分类排序
        Collections.sort(one);
        Collections.sort(two);

        for (int i = 0; i < one.size(); i++) {
            //获取分类集合
            List<CmsBill> cmsBillsOne = one.get(i).getCmsBillList();
            List<CmsBill> cmsBillsTwo = two.get(i).getCmsBillList();
            //获取去重并集
            cmsBillsOne.addAll(cmsBillsTwo);
            List<CmsBill> retCmsBills = cmsBillsOne.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId()))), ArrayList::new));

            one.get(i).setCmsBillList(retCmsBills);

        }


        return one;
    }

    /**
     * 对比俩个项目返回并集
     *
     * @return
     */
    public List<PmsBatchVO> retPmsBatchs(List<PmsBatchVO> one, List<PmsBatchVO> two) {
        List<PmsBatchVO> retPmsBatchs = new ArrayList<>();
        //添加俩个集合的差集
        retPmsBatchs.addAll(getDifferentPmsBatch(one, two));

        //获得俩个集合交集
        List<PmsBatchVO> onePmsBatchs = getIdenticalPmsBatch(one, two);
        List<PmsBatchVO> twoPmsBatchs = getIdenticalPmsBatch(two, one);

        //对比这俩集合，取各个项目最大分类及权限
        retPmsBatchs.addAll(compareTwoPmsBatch(onePmsBatchs, twoPmsBatchs));
        one.clear();
        two.clear();
        return retPmsBatchs;
    }


}
