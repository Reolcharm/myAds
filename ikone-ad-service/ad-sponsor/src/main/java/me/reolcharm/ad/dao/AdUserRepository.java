package me.reolcharm.ad.dao;

import me.reolcharm.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author K1
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {
    /**
     * <h2>根据用户名查找用户记录</h2>
     */
    AdUser findByUsername(String userName);
}
