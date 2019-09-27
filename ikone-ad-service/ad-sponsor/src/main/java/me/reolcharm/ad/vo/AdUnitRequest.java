package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static me.reolcharm.ad.util.CommonUtils.match;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/26 10:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;
    private Integer positionType;
    private Long budget;

    public boolean createValidate() {
        return match(planId) && match(positionType) && match(budget);
    }
}
