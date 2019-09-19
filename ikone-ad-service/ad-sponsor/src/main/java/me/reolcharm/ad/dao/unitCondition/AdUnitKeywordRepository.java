package me.reolcharm.ad.dao.unitCondition;

import me.reolcharm.ad.entity.unit_condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUnitKeywordRepository extends
        JpaRepository<AdUnitKeyword, Long> {
}
