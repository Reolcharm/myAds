package me.reolcharm.ad.service.impl;

import me.reolcharm.ad.constant.Constants;
import me.reolcharm.ad.dao.AdPlanRepository;
import me.reolcharm.ad.dao.AdUnitRepository;
import me.reolcharm.ad.dao.CreativeRepository;
import me.reolcharm.ad.dao.unitCondition.AdUnitDistrictRepository;
import me.reolcharm.ad.dao.unitCondition.AdUnitItRepository;
import me.reolcharm.ad.dao.unitCondition.AdUnitKeywordRepository;
import me.reolcharm.ad.dao.unitCondition.CreativeUnitRepository;
import me.reolcharm.ad.entity.AdPlan;
import me.reolcharm.ad.entity.AdUnit;
import me.reolcharm.ad.entity.unit_condition.AdUnitDistrict;
import me.reolcharm.ad.entity.unit_condition.AdUnitIt;
import me.reolcharm.ad.entity.unit_condition.AdUnitKeyword;
import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.service.IAdUnitService;
import me.reolcharm.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/26 14:57
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {
    private final AdPlanRepository planRepository;
    private final AdUnitRepository unitRepository;

    private final AdUnitKeywordRepository unitKeywordRepository;
    private final AdUnitItRepository unitItRepository;
    private final AdUnitDistrictRepository unitDistrictRepository;

    private final CreativeRepository creativeRepository;
    private final CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository planRepository,
                             AdUnitRepository unitRepository,
                             AdUnitKeywordRepository unitKeywordRepository,
                             AdUnitItRepository unitItRepository,
                             AdUnitDistrictRepository unitDistrictRepository, CreativeRepository creativeRepository,
                             CreativeUnitRepository creativeUnitRepository) {
        this.planRepository = planRepository;
        this.unitRepository = unitRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest req) throws AdException {
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
        /* 1.获取 - unitIds*/
        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        /* 2.校验 - 用户勾选的单元 id 集合是否存在*/
        if (!isRelatedUnitIdExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())) {
            /* 3.封装 - 将 请求传递来的批量 id 和 keyword 封装到 List<AdUnitKeyword> 中*/
            List<AdUnitKeyword> adUnitKeywords = request.getUnitKeywords().stream()
                    .map(i -> new AdUnitKeyword(i.getUnitId(), i.getKeyword()))
                    .collect(Collectors.toList());
            /* 4.批量保存 - adUnitKeywords不同业务, 不同需要!*/
            ids = unitKeywordRepository.saveAll(adUnitKeywords).stream()
                    .map(AdUnitKeyword::getUnitId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(
            AdUnitItRequest request) throws AdException {
        /* 1.获取 - unitIds*/
        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        /* 2.校验 - 用户勾选的单元 id 集合是否存在*/
        if (!isRelatedUnitIdExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<Long> ids = Collections.emptyList();

        if (!CollectionUtils.isEmpty(request.getUnitIts())) {
            List<AdUnitIt> newAdUnitIts = request.getUnitIts().stream()
                    .map(i -> new AdUnitIt(i.getUnitId(), i.getItTag()))
                    .collect(Collectors.toList());
            ids = unitItRepository.saveAll(newAdUnitIts).stream()
                    .map(AdUnitIt::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(
            AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitIdExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        ArrayList<AdUnitDistrict> adUnitDistricts;
        List<Long> ids = null;
        if (!CollectionUtils.isEmpty(request.getUnitDistricts())) {
            adUnitDistricts = request.getUnitDistricts().stream()
                    .map(i -> new AdUnitDistrict(i.getUnitId(), i.getProvince(), i.getCity()))
                    .collect(Collectors.toCollection(ArrayList::new));
            ids = unitDistrictRepository.saveAll(adUnitDistricts).stream()
                    .map(AdUnitDistrict::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitDistrictResponse(ids);
    }

    /**
     * 相关联的推广单元是否存在:
     * 1. 非空判断;
     * 2. 数量上判断, 数据库中的单元数量, 与去重后用户勾选的是否一致
     *
     * @param unitIds
     * @return
     */
    private boolean isRelatedUnitIdExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        /* 根据 unitList 找到对应的推广单元.
         * 去单元表中查到所有的用户打勾的单元, 数量上应该保持一致.
         */
        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }
}
