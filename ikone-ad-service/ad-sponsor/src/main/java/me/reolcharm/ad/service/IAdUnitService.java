package me.reolcharm.ad.service;

import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.vo.*;

/**
 * <Strong>Desc</Strong>:
 *
 * @author K1
 * @date 2019/9/22 14:53
 */
public interface IAdUnitService {
    /**
     * @param req AdUnitRequest
     * @return
     * @throws AdException
     */
    AdUnitResponse createUnit(AdUnitRequest req) throws AdException;

    /**
     * @param request
     * @return
     * @throws AdException
     */
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request)
            throws AdException;

    /**
     * @param request
     * @return
     * @throws AdException
     */
    AdUnitItResponse createUnitIt(AdUnitItRequest request)
            throws AdException;

    /**
     * @param request
     * @return
     * @throws AdException
     */
    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
            throws AdException;

}
