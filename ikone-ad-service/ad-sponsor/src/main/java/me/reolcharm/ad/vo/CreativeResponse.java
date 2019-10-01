package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/29 9:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeResponse {

    private Long id;
    /**
     * name - 创意名称.
     */
    private String name;
}
