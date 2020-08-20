package com.huihu.open.delay.queue.request;

import com.huihu.open.delay.queue.domain.TaskData;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 元胡
 * @date 2020/08/20 2:29 下午
 */
@Data
public class CreateTaskRequest implements Serializable {

    private static final long serialVersionUID = -2360461271360716555L;
    /**
     * 队列数据
     */
    private TaskData taskData;
}
