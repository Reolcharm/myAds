package me.reolcharm.ad.service.impl;

import me.reolcharm.ad.constant.CommonStatus;
import me.reolcharm.ad.constant.Constants;
import me.reolcharm.ad.dao.AdPlanRepository;
import me.reolcharm.ad.dao.AdUserRepository;
import me.reolcharm.ad.entity.AdPlan;
import me.reolcharm.ad.entity.AdUser;
import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.service.IAdPlanService;
import me.reolcharm.ad.util.CommonUtils;
import me.reolcharm.ad.vo.AdPlanGetRequest;
import me.reolcharm.ad.vo.AdPlanRequest;
import me.reolcharm.ad.vo.AdPlanResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <Strong>Desc</Strong>:
 *
 * @author K1
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {
    private final AdPlanRepository planRepository;
    private final AdUserRepository userRepository;

    public AdPlanServiceImpl(AdPlanRepository planRepository, AdUserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    /**
     * <b>Desc</b>: <b>要确保 AdPlan 关联的 User 是存在的<b/>
     *
     * @param request
     * @return
     * @throws AdException
     * @author K1
     */
    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        /* Validate the param of request from client*/
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        // 确保关联的 User 是存在的
        Optional<AdUser> optionalAdUser = userRepository.findById(request.getUserId());
        if (!optionalAdUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        /*FIND whether has the same plan name in DB*/
        AdPlan exitsPlan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (exitsPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        AdPlan freshPlan = planRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getStartDate())));
        return new AdPlanResponse(freshPlan.getId(), freshPlan.getUserId());
    }

    /**
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        /*According id and userId, find existing plan, and then update it. */
        AdPlan existingPlan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (existingPlan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (existingPlan.getPlanName() != null) {
            existingPlan.setPlanName(request.getPlanName());
        }
        if (existingPlan.getStartDate() != null) {
            existingPlan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate()));
        }
        if (existingPlan.getEndDate() != null) {
            existingPlan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate()));
        }
        existingPlan.setUpdateTime(new Date());
        /*IMPORTANT!*/
        existingPlan = planRepository.save(existingPlan);
        return new AdPlanResponse(existingPlan.getId(), existingPlan.getUserId());
    }

    /**
     * @param getRequest
     * @return
     * @throws AdException
     * @author K1
     */
    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest getRequest) throws AdException {
        if (!getRequest.findValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdPlan> plans = planRepository.findAllByIdInAndUserId(getRequest.getIds(),
                getRequest.getUserId());
        if (plans == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        return planRepository.findAllByIdInAndUserId(getRequest.getIds(),
                getRequest.getUserId());
    }

    /**
     * @param request
     * @throws AdException
     */
    @Override
    @Transactional(rollbackFor = AdException.class)
    public void delAdPlan(AdPlanRequest request) throws AdException {
        if (!request.delValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());

    }
}
