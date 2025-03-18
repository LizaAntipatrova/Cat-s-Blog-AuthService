package ru.meow.meowauth.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import ru.meow.meowauth.data.entity.session.Session;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisSessionService {
    // Ключевое пространство для сессий в Redis
    private static final String SESSION_PREFIX = "session:";
    private final RedissonClient redissonClient;

    private final ObjectMapper mapper;

    @SneakyThrows
    public void saveSessionToRedis(Session session) {
        RBucket<String> bucket = getRBucket(session.getSessionId());
        String json = mapper.writeValueAsString(session);

        bucket.set(json, 2, TimeUnit.HOURS);
    }

    public RBucket<String> getRBucket(String sessionId) {
        String redisKey = SESSION_PREFIX + sessionId;
        return redissonClient.getBucket(redisKey);
    }

}
