package io.ispacc.orion.chat.core.utils;

import cn.hutool.core.util.ObjectUtil;
import io.ispacc.orion.common.api.IErrorCode;
import io.ispacc.orion.common.exception.OrionException;

/**
 * 校验工具类
 */
public class AssertUtil {

    //如果不是true，则抛异常
    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throwException(msg);
        }
    }

    public static void isTrue(boolean expression, IErrorCode errorEnum) {
        if (!expression) {
            throwException(errorEnum);
        }
    }

    public static void isTrue(boolean expression, OrionException exception) {
        if (!expression) {
            throwException(exception);
        }
    }

    //如果是true，则抛异常
    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            throwException(msg);
        }
    }

    //如果是true，则抛异常
    public static void isFalse(boolean expression, IErrorCode errorEnum) {
        if (expression) {
            throwException(errorEnum);
        }
    }

    public static void isFalse(boolean expression, OrionException exception) {
        if (expression) {
            throwException(exception);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isNotEmpty(Object obj, String msg) {
        if (isEmpty(obj)) {
            throwException(msg);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isNotEmpty(Object obj, IErrorCode errorEnum) {
        if (isEmpty(obj)) {
            throwException(errorEnum);
        }
    }

    public static void isNotEmpty(Object obj, OrionException exception) {
        if (isEmpty(obj)) {
            throwException(exception);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isEmpty(Object obj, String msg) {
        if (!isEmpty(obj)) {
            throwException(msg);
        }
    }

    public static void equal(Object o1, Object o2, String msg) {
        if (!ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }

    public static void notEqual(Object o1, Object o2, String msg) {
        if (ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }

    public static void isNotNull(Object o1, String msg) {
        if (o1 == null) {
            throwException(msg);
        }
    }

    public static void isNotNull(Object o1, IErrorCode errorCode) {
        if (o1 == null) {
            throwException(errorCode);
        }
    }

    private static boolean isEmpty(Object obj) {
        return ObjectUtil.isEmpty(obj);
    }

    private static void throwException(String msg) {
        throw new OrionException(msg);
    }

    private static void throwException(IErrorCode errorEnum) {
        throw new OrionException(errorEnum);
    }

    private static void throwException(OrionException exception) {
        throw exception;
    }
}
