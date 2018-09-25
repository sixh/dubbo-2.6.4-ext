package com.alibaba.dubbo.remoting.exchange.support;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.remoting.exchange.ResponseFuture;

/**
 * <pre>
 * CreateDate: 2018/8/14 10:14
 * @author sixh chenbin
 * </pre>
 * <p>
 * </p>
 */
public class AsyncDefaultFuture implements ResponseFuture {
    private static final Logger logger = LoggerFactory.getLogger(AsyncDefaultFuture.class);
    /**
     * 通道;
     */
    private Channel channel;
    /**
     * 请求体;
     */
    private Request request;
    /**
     * id;
     */
    private long id;
    /**
     * 超时时间;
     */
    private long timeout;
    /**
     * 初始化一个对象;
     * @param channel 通道;
     * @param request 请求体数据;
     * @param timeout timeout;
     */
    public AsyncDefaultFuture(Channel channel, Request request, int timeout) {
        this.channel = channel;
        this.request = request;
        this.id = request.getId();
        this.timeout = timeout > 0 ? timeout : channel.getUrl().getPositiveParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
     }

    @Override
    public Object get() throws RemotingException {
        return null;
    }

    @Override
    public Object get(int timeoutInMillis) throws RemotingException {
        return null;
    }

    @Override
    public void setCallback(ResponseCallback callback) {

    }

    @Override
    public boolean isDone() {
        return true;
    }
    @Override
    public void cancel(){

    }
    public static void received(Channel channel, Response response) {
        try {
            DefaultAsyncCallback.getIns().done(channel,response);
        } catch (Exception ex) {
            logger.error("async notify error {}", ex);
        }
    }
}
