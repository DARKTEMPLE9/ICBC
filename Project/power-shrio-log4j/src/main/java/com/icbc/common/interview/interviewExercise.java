package com.icbc.common.interview;

import java.util.HashMap;
import java.util.Map;
/*
* 此类为面试题总结
*
* */
public class interviewExercise {

    /*
    * 记录字符串数组中重复元素第一次出现的位置，每个元素出现的次数，及从高到低输出改元素
    *
    * */
    public static void main(String[] args) {
        String[] array =  {"a","b","c","d","a","e","f","b","b","d","d","d"};
        Map<String, Integer> indexMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            Integer count = 1;
            if(indexMap.containsKey(s)){
                Integer count2 = countMap.get(s);
                count2++;
                countMap.put(s,count2);
            }

            if (indexMap.containsKey(s)) {
                continue;
            }
            indexMap.put(s, i);
            countMap.put(s,count);
        }
        System.out.println(indexMap);
        System.out.println(countMap);
    }
}
