package me.reolcharm.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA.
 * User: K1
 *
 * @author K1
 * @date 2019/9/18
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SponsorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SponsorApplication.class, args);
    }
}
