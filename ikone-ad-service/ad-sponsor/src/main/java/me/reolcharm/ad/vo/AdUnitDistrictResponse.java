package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/27 16:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitDistrictResponse {
    /**
     * ID in database
     */
    private List<Long> ids;
}
