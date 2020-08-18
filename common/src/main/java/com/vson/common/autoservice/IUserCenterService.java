package com.vson.common.autoservice;

/**
 * @author vson
 * @date 2020/8/18
 * Company:上海动博士企业发展有限公司
 * E-mail :vson1718@163.com
 * 项目描述:
 */
public interface IUserCenterService {
    /**
     * 是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 执行登录
     */
    void login();

}
