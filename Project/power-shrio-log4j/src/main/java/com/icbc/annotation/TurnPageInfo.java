package com.icbc.annotation;

import lombok.Data;

import java.util.List;

@Data
public class TurnPageInfo<E> {

    private long totalNum;

    private List<E> data;

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
