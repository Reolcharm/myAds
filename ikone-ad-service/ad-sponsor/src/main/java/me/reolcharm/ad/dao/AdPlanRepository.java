package me.reolcharm.ad.dao;

import me.reolcharm.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author K1
 * @date 2019/9/19 19:02
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {
    /**
     * @param id     Adplan's IDENTITY
     * @param userId AdPlan's userId
     * @return List<AdPlan>
     * @author K1
     * @date 2019/9/19 20:23
     */
    AdPlan findByIdAndUserId(Long id, Long userId);

    /**
     * <b>Desc</b>: One client get all the AdPlans.
     *
     * @param ids    List<Long>
     * @param userId client
     * @return - List<AdPlan>
     */
    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    /**
     * <b>Desc</b>:
     *
     * @param userId
     * @param planName
     * @return
     * @author K1
     * @date 2019/9/21 17:07
     */
    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    /**
     * <b>Desc</b>:
     *
     * @param planStatus
     * @return
     * @author K1
     * @date 2019/9/21 17:07
     */
    List<AdPlan> findAllByPlanStatus(Long planStatus);
}
