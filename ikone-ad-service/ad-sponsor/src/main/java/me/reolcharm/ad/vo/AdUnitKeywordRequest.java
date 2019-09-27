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
public class AdUnitKeywordRequest {
    /**
     * Allows add in batches;
     */
    private List<UnitKeyword> unitKeywords;

    /**
     * Allows add in batches;
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitKeyword{
        private Long unitId;
        private String keyword;
    }
}
