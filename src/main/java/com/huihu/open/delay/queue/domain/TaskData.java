package com.huihu.open.delay.queue.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 元胡
 * @date 2020/08/20 2:14 下午
 */
@Data
public class TaskData {

    /**
     * 延时队列名称
     * 建议使用应用名+_+业务名
     */
    private String taskName;

    /**
     * 队列数据
     */
    private List<? extends Serializable> tasKDataList;

    /**
     * 任务执行时间 秒级别
     */
    private Double taskTime;
}
