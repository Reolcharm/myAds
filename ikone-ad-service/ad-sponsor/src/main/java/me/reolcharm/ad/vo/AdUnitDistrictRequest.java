package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/27 16:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitDistrictRequest {
    /**
     * Allows add in batches;
     */
    private List<UnitDistrict> unitDistricts;

    /**
     * Allows add in batches;
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitDistrict {
        private Long unitId;
        /**
         * todo Should be Regional dictionary, not just String.
         */
        private String province;
        private String city;
    }
}
