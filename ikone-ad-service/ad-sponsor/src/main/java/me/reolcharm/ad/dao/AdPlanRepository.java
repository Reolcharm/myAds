package me.reolcharm.ad.dao;

import me.reolcharm.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @author K1
 * @date 2019/9/19
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {
    /**
     * @param id Adplan's IDENTITY
     * @param userId AdPlan's userId
     * @return AdPlan AdPlan
     * @author K1
     * @date 2019/9/19 20:23
     * Description:
     */
    AdPlan findByIdAndUserId(Long id, Long userId);
}
