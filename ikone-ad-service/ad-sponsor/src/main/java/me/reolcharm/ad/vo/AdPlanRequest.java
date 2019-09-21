package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * Description: wrap request about AdPlan
 *
 * @author K1
 * @date 2019/9/20 13:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanRequest {
    private Long id;
    private Long userId;
    private String planName;
    private String startDate;
    private String endDate;

    /**
     * <p>Validate the create request</p>
     *
     * @return if one param from request is null, return false
     */
    public boolean createValidate() {
        return userId != null
                && StringUtils.isEmpty(planName)
                && StringUtils.isEmpty(startDate)
                && StringUtils.isEmpty(endDate);
    }

    /**
     * <p>Validate the update request</p>
     *
     * @return if id && userId from request is null, return false
     */
    public boolean updateValidate() {
        return id != null && userId != null;
    }

    /**
     * <p>Validate the del request</p>
     *
     * @return if id && userId from request is null, return false
     */
    public boolean delValidate() {
        return id != null && userId != null;
    }
}

