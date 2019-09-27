package me.reolcharm.ad.service.impl;

import me.reolcharm.ad.constant.Constants;
import me.reolcharm.ad.dao.AdPlanRepository;
import me.reolcharm.ad.dao.AdUnitRepository;
import me.reolcharm.ad.entity.AdPlan;
import me.reolcharm.ad.entity.AdUnit;
import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.service.IAdUnitService;
import me.reolcharm.ad.vo.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/26 14:57
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {
    private final AdUnitRepository unitRepository;
    private final AdPlanRepository planRepository;

    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
    }

    @Override
    public AdUnitResponse createService(AdUnitRequest req) throws AdException {
        if (!req.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        /*Find existing AdPlan which is the unit match.*/
        Optional<AdPlan> planOptional = planRepository.findById(req.getPlanId());
        if (!planOptional.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdUnit unit = unitRepository.findByPlanIdAndUnitName(req.getPlanId(), req.getUnitName());
        if (unit != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit save = unitRepository.save(
                new AdUnit(req.getPlanId(), req.getUnitName(),
                        req.getPositionType(), req.getBudget()));
        return new AdUnitResponse(save.getId(), save.getPlanId());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(
            AdUnitKeywordRequest request) throws AdException {

        return null;
    }

    @Override
    public AdUnitItResponse createUnitIt(
            AdUnitItRequest request) throws AdException {
        return null;
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(
            AdUnitDistrictRequest request) throws AdException {
        return null;
    }
}
