package com.huihu.open.delay.queue.netty.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 元胡
 * @date 2020/08/20 3:54 下午
 */
@Data
public class InvokeHandlerDto implements Serializable {

    private static final long serialVersionUID = -6701432118576883130L;

    /**
     * 类名
     */
    private Class aClass;
    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数列表
     */
    private Object[] params;
}
