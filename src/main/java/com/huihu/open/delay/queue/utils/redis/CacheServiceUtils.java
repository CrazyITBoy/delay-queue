package com.huihu.open.delay.queue.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 元胡
 * @date 2020/08/20 11:20 上午
 */
@Component
public class CacheServiceUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * zAdd
     * 
     * @param key
     * @param score
     *            分数
     * @param value
     * @return
     */
    public Boolean zAdd(String key, Double score, String value) {
        return stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * zRangeWithScore
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScore(String key, Long start, Long end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }
}
