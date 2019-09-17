package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Qinyi.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse1<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResponse1(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
