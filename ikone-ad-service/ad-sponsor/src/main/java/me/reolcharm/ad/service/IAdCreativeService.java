package me.reolcharm.ad.service;

import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.vo.CreativeRequest;
import me.reolcharm.ad.vo.CreativeResponse;

/**
 * <Strong>Desc</Strong>:
 *
 * @author K1
 * @date 2019/9/29 9:28
 */
public interface IAdCreativeService {
    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
