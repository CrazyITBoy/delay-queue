package com.huihu.open.delay.queue.provider.client;

import com.huihu.open.delay.queue.request.CreateTaskRequest;
import com.huihu.open.delay.queue.request.QueryReadTaskRequest;
import com.huihu.open.delay.queue.response.TaskResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @author 元胡
 * @date 2020/08/20 11:46 上午
 */
public interface TaskOptionClient {
    /**
     * 提交一个延时队列
     * @return
     */
    Boolean createDelayTask(CreateTaskRequest request);

    TaskResponse queryReadQueue(QueryReadTaskRequest readTaskRequest);


}
