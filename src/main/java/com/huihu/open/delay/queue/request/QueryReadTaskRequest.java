package com.huihu.open.delay.queue.request;

import lombok.Data;

import java.io.Serializable;


/**
 * @author 元胡
 * @date 2020/08/20 2:58 下午
 */
@Data
public class QueryReadTaskRequest implements Serializable {

    private static final long serialVersionUID = -3456935496544655075L;
    private String name;

    private Long startTime;

    private Long endTime;
}
