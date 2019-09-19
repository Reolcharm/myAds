/**
 * Created by IntelliJ IDEA.
 * User: K1
 * Date: 2019/9/19
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
package me.reolcharm.ad.constant;

import lombok.Getter;

/**
 * @author K1
 */

@Getter
public enum CreativeType {
    /**
     * one type of creative.
     */
    IMAGE(1, "图片"),
    /**
     * the type of creative.
     */
    VIDEO(2, "视频"),
    /**
     * the type of creative.
     */
    TEXT(3, "文字"),
    /**
     * the type of creative.
     */
    AUDIO(4, "音频");

    private Integer status;
    private String desc;

    CreativeType(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
