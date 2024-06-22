package com.kr.lg.common.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class VisitHistoryRepository {


    private final RedisTemplate<String, Object> redisTemplate;

    public VisitHistoryRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addVisitHistory(String prefix, String id, String userId) {
        try {
            redisTemplate.opsForZSet().add(prefix + id, userId, System.currentTimeMillis());
        } catch (Exception e) {
            log.error("[레디스 오류] ================> ");
        }
    }

    public Long getRecentVisitCount(String prefix, String id, long startTime, long endTime) {
        try {
            return redisTemplate.opsForZSet().count(prefix + id, startTime, endTime);
        } catch (Exception e) {
            log.error("[레디스 오류] ================> ");
            return 1L;
        }
    }

}
