package me.reolcharm.ad.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.reolcharm.ad.constant.Constants;
import me.reolcharm.ad.dao.AdUserRepository;
import me.reolcharm.ad.entity.AdUser;
import me.reolcharm.ad.exception.AdException;
import me.reolcharm.ad.service.IUserService;
import me.reolcharm.ad.util.CommonUtils;
import me.reolcharm.ad.vo.CreateUserRequest;
import me.reolcharm.ad.vo.CreateUserResponse;
import org.springframework.stereotype.Service;

/**
 * @author K1
 * @date 2019/9/20 11:19
 * Description: impl
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private final AdUserRepository userRepository;

    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        /*Non-empty judgment*/
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser exitingUser = userRepository.findByUsername(request.getUserName());
        if (exitingUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        /*save a new user*/
        AdUser newUser = userRepository.save(
                new AdUser(request.getUserName(),
                        CommonUtils.md5(request.getUserName())));
        /*return a `body`*/
        return new CreateUserResponse(newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime());
    }
}
