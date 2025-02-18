package ai.ednova.ticket.booking.service.configurations;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReddissonConfiguration {
  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config
        .useSingleServer()
        .setAddress(
            "redis://localhost:6379"); // Change this based on your Redis server configuration

    return Redisson.create(config);
  }
}
