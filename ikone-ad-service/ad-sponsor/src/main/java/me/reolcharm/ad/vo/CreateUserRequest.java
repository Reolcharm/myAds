package me.reolcharm.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author K1
 * @date 2019/9/20 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    /**
     * 用户创建账户时提供的 用户名称.
     */
    private String userName;

    /**
     * @return If userName is empty, return false.
     */
    public boolean validate() {
        return !StringUtils.isEmpty(userName);
    }
}
