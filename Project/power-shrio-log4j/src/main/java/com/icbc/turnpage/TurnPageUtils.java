package com.icbc.turnpage;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icbc.annotation.TurnPageInfo;
import com.icbc.turnpage.ITurnPageHandler;

import java.util.HashMap;
import java.util.Map;

public class TurnPageUtils {
    private static ThreadLocal<Integer> begNumT = new ThreadLocal<Integer>();

    private static ThreadLocal<Integer> fetchNumT = new ThreadLocal<Integer>();

    /*翻页方法，返回总记录数和数据*/
    public static <E> TurnPageInfo<E> queryPage(int begNum, int fetchNum, ISelect select) {
        TurnPageInfo<E> data = new TurnPageInfo<>();
        /*offsetc从0开始，begnum从1开始*/
        int offset = begNum - 1;
        if (offset < 0) {
            offset = 0;
        }
        PageInfo<E> page = PageHelper.offsetPage(offset, fetchNum).doSelectPageInfo(select);
        data.setData(page.getList());
        data.setTotalNum(page.getTotal());
        return null;
    }

    /*
     * 循环翻页查询，翻页查询的结果由ITurnPageHandler处理
     * */
    public static <E> void queryPage(int startRow, int fetchNum, ISelect iSelect, ITurnPageHandler<E> turnPageHandler) {
        PageInfo<E> page = PageHelper.offsetPage(startRow, fetchNum).doSelectPageInfo(iSelect);
        turnPageHandler.doHandler(page.getList());
        while (page.isHasNextPage()) {
            page = PageHelper.offsetPage(page.getEndRow(), fetchNum).doSelectPageInfo(iSelect);
            turnPageHandler.doHandler(page.getList());
        }
    }

    /*
    翻页方法，调用的方法有@TurnPage注解，自动翻页后执行此方法，否则失败
    * */
    public static <E> TurnPageInfo<E> queryPage(ISelect iSelect) {
        Map map = new HashMap();
        Integer begNum = TurnPageUtils.begNumT.get();
        Integer fetchNum = TurnPageUtils.fetchNumT.get();
        if (begNum == null || fetchNum == null) {
            begNum = 1;
            fetchNum = 20;
        }
        return TurnPageUtils.queryPage(begNum, fetchNum, iSelect);
    }


}
