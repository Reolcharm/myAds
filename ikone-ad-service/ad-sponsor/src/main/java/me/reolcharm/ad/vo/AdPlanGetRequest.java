package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * <Strong>Desc</Strong>: Wraps request type of `get`. Client will send request with {@code ids} and {@code userId}.
 * </p>
 *
 * @author K1
 * @date 2019/9/20 13:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanGetRequest {
    private List<Long> ids;
    private Long userId;

    /**
     * @return - it returns true if <pre>{@code userId != null && CollectionUtils.isEmpty(ids);}</pre>.
     */
    public boolean findValidate() {
        return userId != null
                && !CollectionUtils.isEmpty(ids);
    }
}
