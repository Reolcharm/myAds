package me.reolcharm.ad.service;

import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.vo.CreateUserRequest;
import me.reolcharm.ad.vo.CreateUserResponse;

/**
 * @author K1
 * @date 2019/9/20
 */
public interface IUserService {
    /**
     * @param request
     * @return
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
