package com.huihu.open.delay.queue.netty.handler;

import com.caucho.hessian.io.HessianInput;
import com.huihu.open.delay.queue.netty.dto.InvokeHandlerDto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

/**
 * @author 元胡
 * @date 2020/08/20 3:48 下午
 */
@ChannelHandler.Sharable
public class SerializableDataInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] bytes = (byte[]) msg;
        ctx.fireChannelRead(msg);
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        //Hessian的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        InvokeHandlerDto invokeHandlerDto = (InvokeHandlerDto) hi.readObject();
        Class aClass = invokeHandlerDto.getAClass();
        Class<?> clz = Class.forName(aClass.getName());
        Object instance = clz.newInstance();
        Class<?>[] paramClss = new Class<?>[invokeHandlerDto.getParams().length];
        int i = 0;
        for (Object param : invokeHandlerDto.getParams()) {
            paramClss[i++] = param.getClass();
        }
        Method method = clz.getMethod(invokeHandlerDto.getMethodName(), paramClss);
        Object response = method.invoke(instance, invokeHandlerDto.getParams());

    }
}
