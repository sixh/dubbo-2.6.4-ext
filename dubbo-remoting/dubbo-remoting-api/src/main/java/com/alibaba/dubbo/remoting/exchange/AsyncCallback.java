package com.alibaba.dubbo.remoting.exchange;

import com.alibaba.dubbo.remoting.Channel;

/**
 * <pre>
 * CreateDate: 2018/8/13 19:15
 * @author sixh chenbin
 * </pre>
 * <p>
 * </p>
 */
public interface AsyncCallback {
    /**
     * 完成;
     * @param channel 通道;
     * @param response response;
     */
    void done(Channel channel, Response response);
}
