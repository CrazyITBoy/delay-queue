package com.huihu.open.delay.queue.task;

import com.alibaba.fastjson.JSON;
import com.huihu.open.delay.queue.provider.client.TaskOptionClient;
import com.huihu.open.delay.queue.request.CreateTaskRequest;
import com.huihu.open.delay.queue.request.QueryReadTaskRequest;
import com.huihu.open.delay.queue.domain.TaskData;
import com.huihu.open.delay.queue.response.TaskResponse;
import com.huihu.open.delay.queue.utils.redis.CacheServiceUtils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.plugin.javascript.navig.Array;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 元胡
 * @date 2020/08/20 11:44 上午
 */
@Component
public class TaskOption implements TaskOptionClient {

    private final static String APP_TASK_NAME = "delay_task_redis";

    @Resource
    private CacheServiceUtils cacheServiceUtils;

    @Override
    public Boolean createDelayTask(CreateTaskRequest request) {
        TaskData taskData = request.getTaskData();
        if (taskData == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(taskData.getTasKDataList())) {
            return false;
        }
        if (taskData.getTaskTime() == null) {
            return false;
        }
        String taskName = taskData.getTaskName();
        String key = APP_TASK_NAME + "_" + taskName;
        Boolean addSortSetOk =
            cacheServiceUtils.zAdd(key, taskData.getTaskTime(), JSON.toJSONString(taskData.getTasKDataList()));
        return addSortSetOk;
    }

    @Override
    public TaskResponse queryReadQueue(QueryReadTaskRequest request) {
        String taskName = request.getName();
        String key = APP_TASK_NAME + "_" + taskName;
        Set<ZSetOperations.TypedTuple<String>> zRangeWithScore = cacheServiceUtils.zRangeWithScore(key, request.getStartTime(), request.getEndTime());

        TaskResponse taskResponse = new TaskResponse();
        List<TaskData> list = new ArrayList<>();
        taskResponse.setTaskData(list);
        for (ZSetOperations.TypedTuple<String> zSetOptionSet : zRangeWithScore) {
            String value = zSetOptionSet.getValue();
            Double score = zSetOptionSet.getScore();

            TaskData data = new TaskData();
            data.setTaskName(taskName);
            data.setTaskTime(score);
            data.setTasKDataList(JSON.parseArray(value,Serializable .class));
            list.add(data);
        }
        return taskResponse;
    }
}
