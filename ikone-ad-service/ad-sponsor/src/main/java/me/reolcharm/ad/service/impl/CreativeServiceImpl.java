package me.reolcharm.ad.service.impl;

import me.reolcharm.ad.dao.CreativeRepository;
import me.reolcharm.ad.entity.Creative;
import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.service.IAdCreativeService;
import me.reolcharm.ad.vo.CreativeRequest;
import me.reolcharm.ad.vo.CreativeResponse;
import org.springframework.stereotype.Service;

/**
 * <Strong>Desc</Strong>: <p></p>
 *
 * @author K1
 * @date 2019/9/29 9:30
 */
@Service
public class CreativeServiceImpl implements IAdCreativeService {
    private final CreativeRepository creativeRepository;

    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(
            CreativeRequest request) throws AdException {
        /* todo 校验多个参数, 网上查了下 Validate 类*/
        Creative save = creativeRepository.save(request.convert2Entity());
        return new CreativeResponse(save.getId(), save.getName());
    }
}
