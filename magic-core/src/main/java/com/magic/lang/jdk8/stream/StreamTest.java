package com.magic.lang.jdk8.stream;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.magic.lang.jdk8.model.BookLabel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * lambda表达式和流相关测试
 */
public class StreamTest {

    public static void main(String[] args) {
        //模拟从数据库查询出的list集合，
        // bookid是1的书有1,2两个标签，bookid是2的有1,3,4两个标签
        BookLabel label1 = new BookLabel(1L,1L);
        BookLabel label2 = new BookLabel(1L,2L);
        BookLabel label3 = new BookLabel(2L,1L);
        BookLabel label4 = new BookLabel(2L,3L);
        BookLabel label5 = new BookLabel(2L,4L);
        List<BookLabel> bookLabelList = Lists.newArrayList(label1,label2,label3,label4,label5);

        //使用stream操作进行分组，bookid为key,对象为value
        Map<Long, List<BookLabel>> listMap = bookLabelList.stream().collect(Collectors.groupingBy(BookLabel::getBookId));
        for(Map.Entry<Long,List<BookLabel>> map : listMap.entrySet()){
            System.err.println(map.getKey()+"====="+JSONObject.toJSONString(map.getValue()));
        }

        //使用stream操作进行分组，bookid为key,label组成的list为value
        Map<Long, List<Long>> collect = bookLabelList.stream().
                collect(Collectors.groupingBy(BookLabel::getBookId, Collectors.mapping(BookLabel::getLabelId, Collectors.toList())));
        for(Map.Entry<Long,List<Long>> map : collect.entrySet()){
            System.err.println(map.getKey()+"====="+JSONObject.toJSONString(map.getValue()));
        }

        //如果一个特定的书籍标签是1,2,3，需要找到上面集合中交集可以使用list的retainAll 方法
        List<Long> targetList = Lists.newArrayList(1L,2L,3L);
        for(Map.Entry<Long,List<Long>> map : collect.entrySet()){
            List<Long> value = map.getValue();
            value.retainAll(targetList);
            System.err.println(map.getKey()+"====="+JSONObject.toJSONString(value));
        }
    }
}
