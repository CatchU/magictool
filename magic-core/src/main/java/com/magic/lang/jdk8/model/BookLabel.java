package com.magic.lang.jdk8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 定义一个实体对象，用于模拟从数据库取得值
 */
@Data
@Accessors(chain=true)
@AllArgsConstructor
public class BookLabel {

    /**
     * 书籍id
     */
    private Long bookId;

    /**
     * 标签id
     */
    private Long labelId;
}
