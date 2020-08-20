package com.huihu.open.delay.queue.response;

import com.huihu.open.delay.queue.domain.TaskData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 元胡
 * @date 2020/08/20 3:08 下午
 */
@Data
public class TaskResponse implements Serializable {

    private static final long serialVersionUID = 7248472938774045844L;

    private List<TaskData> taskData;
}
