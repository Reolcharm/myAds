package me.reolcharm.ad.dao;

import me.reolcharm.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    /**
     * @param planId
     * @param unitName
     * @return
     * @author K1
     * @date 2019/9/19 20:30
     * Description:
     */
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    /**
     * @param unitStatus 0 or 1.
     * @return List<AdUnit>
     * @author K1
     * @date 2019/9/19 20:31
     * Description: Inquire all the data which is valid in AdUnit.
     */
    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
