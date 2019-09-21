package me.reolcharm.ad.service;

import me.reolcharm.ad.entity.AdPlan;
import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.vo.AdPlanGetRequest;
import me.reolcharm.ad.vo.AdPlanRequest;
import me.reolcharm.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * @author K1
 * @date 2019/9/20 13:02
 * Description: CRUD
 */
public interface IAdPlanService {
    /**
     * <h2>创建推广计划</h2>
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>获取推广计划</h2> todo 为啥没有 findOne?
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest getRequest) throws AdException;

    /**
     * todo 删除成功与否, 都应该返回给前台信息 而不是 void
     *
     * @param request
     * @throws AdException
     */
    void delAdPlan(AdPlanRequest request) throws AdException;

}
