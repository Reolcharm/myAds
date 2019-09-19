/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/19
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.constant;

import lombok.Getter;

/**
 * 将状态处理成常量, user, plan, unit表都可以共用.
 *
 * @author K1
 */
@Getter
public enum CommonStatus {
    /**
     * 用户, 计划, 单元等等的有效状态
     */
    VALID(1, "有效状态"),
    /**
     * 用户, 计划, 单元等等的无效状态
     */
    INVALID(0, "无效状态");
    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
