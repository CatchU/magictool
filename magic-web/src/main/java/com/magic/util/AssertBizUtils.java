package com.magic.util;


import java.util.Collection;
import com.magic.constant.ResultCode;
import com.magic.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.CollectionUtils;

/**
 * 断言工具
 **/
public class AssertBizUtils {
    public static void isTrue(boolean bool, ResultCode resultCode, Object...objects){
        if (!bool){
            throw new BusinessException(resultCode,objects);
        }
    }

    public static void isNotNull(Object object, ResultCode resultCode,Object...objects){
        if (object == null){
            throw new BusinessException(resultCode,objects);
        }
    }

    public static void isNotEmpty(String str, ResultCode resultCode,Object...objects){
        if (StringUtils.isEmpty(str)){
            throw new BusinessException(resultCode,objects);
        }
    }

    public static void isNotEmpty(Collection collection, ResultCode resultCode, Object...objects){
        if (CollectionUtils.isEmpty(collection)){
            throw new BusinessException(resultCode,objects);
        }
    }
    public static void isEmpty(Collection collection, ResultCode resultCode, Object...objects){
        if (!CollectionUtils.isEmpty(collection)){
            throw new BusinessException(resultCode,objects);
        }
    }

    public static void isDigits(String str, ResultCode resultCode,Object...objects){
        if (str == null || !NumberUtils.isDigits(str)){
            throw new BusinessException(resultCode,objects);
        }
    }
}
