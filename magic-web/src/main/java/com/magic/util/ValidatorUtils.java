package com.magic.util;

import com.magic.constant.ResultCode;
import com.magic.exception.BusinessException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * hibernate-validator校验工具类
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
    	
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws BusinessException  校验不通过，则报BusinessException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
    		throws BusinessException{
    	Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
    	if (!constraintViolations.isEmpty()) {
    		ConstraintViolation<Object> constraint = (ConstraintViolation<Object>)constraintViolations.iterator().next();
            if (constraint == null) {
                return;
            }
            String errorMessage = constraint.getMessage();
            throw new BusinessException(ResultCode.PARAMETER_VALID_ERROR, new String[] {errorMessage});
    	}
    }
}
