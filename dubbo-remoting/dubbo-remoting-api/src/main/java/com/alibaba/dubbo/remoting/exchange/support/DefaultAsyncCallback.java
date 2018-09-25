package com.alibaba.dubbo.remoting.exchange.support;

import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.exchange.AsyncCallback;
import com.alibaba.dubbo.remoting.exchange.Response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * CreateDate: 2018/8/13 19:37
 * @author sixh chenbin
 * </pre>
 * <p>
 * </p>
 */
public class DefaultAsyncCallback implements AsyncCallback {

    /**
     * 接收监听的数据;
     */
    private Map<String,AsyncCallback> callbackMap = new ConcurrentHashMap<String,AsyncCallback>();
    /**
     * 单列持有对象;
     */
    private static class DefaultAsyncCallbackHolder{
        private final static DefaultAsyncCallback INSTALL = new DefaultAsyncCallback();
    }

    /**
     * 获取这个单例对象;
     * @return 单例对象;
     */
    public static DefaultAsyncCallback getIns(){
        return DefaultAsyncCallbackHolder.INSTALL;
    }

    private DefaultAsyncCallback(){

    }
    /**
     * 注册;
     * @param name name;
     * @param callback callback;
     */
    public void register(String name,AsyncCallback callback){
        callbackMap.put(name,callback);
    }

    /**
     * 注册;
     * @param callback callback
     */
    public void register(AsyncCallback callback){
        register(callback.getClass().getName(),callback);
    }

    /**
     * 移除一个注册;
     * @param callback callback
     */
    public void remove(AsyncCallback callback){
        remove(callback.getClass().getName());
    }
    /**
     * 移除一个注册;
     * @param name name
     */
    public void remove(String name){
        callbackMap.remove(name);
    }

    @Override
    public void done(Channel channel, Response response) {
        for(String key : callbackMap.keySet()){
            AsyncCallback asyncCallback = callbackMap.get(key);
            asyncCallback.done(channel,response);
        }

    }
}
