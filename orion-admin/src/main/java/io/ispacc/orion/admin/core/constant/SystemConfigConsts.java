package io.ispacc.orion.admin.core.constant;

/**
 * 系统配置相关常量
 */
public class SystemConfigConsts {

    /**
     * Http 请求认证 Header
     */
    public static final String HTTP_AUTH_HEADER_NAME = "Authorization";
    /**
     * 管理员登录标识
     */
    public static final String ORION_ADMIN_KEY = "Admin";
    /**
     * 用户登录标识
     */
    public static final String ORION_USER_KEY = "User";
    /**
     * 常量类实例化异常信息
     */
    public static final String CONST_INSTANCE_EXCEPTION_MSG = "Constant class";


    private SystemConfigConsts() {
        throw new IllegalStateException(CONST_INSTANCE_EXCEPTION_MSG);
    }

}
