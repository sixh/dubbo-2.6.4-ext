/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.exchange.AsyncCallback;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.remoting.exchange.support.DefaultAsyncCallback;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class Consumer {

    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        DefaultAsyncCallback.getIns().register(new AsyncCallback() {
            @Override
            public void done(Channel channel, Response response) {
                if (response.getStatus() != Response.OK) {
                    System.out.println(response.getErrorMessage());
                } else {
                    System.out.println("正常调用----->");
                }
            }
        });
//        ServiceMethodTypeCache.init(context);
//        Properties properties = new Properties();
//        properties.setProperty("kafka.server.hosts","10.40.6.151:909DefaultFuture2,10.40.6.152:9092,10.40.6.153:9092");
//        new ConfigLoaderBean(properties);
        int count = 1;
//       / ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(5,count,1000, TimeUnit.MICROSECONDS,new LinkedBlockingQueue<>(1000));
//        for (int i=0;i<count;i++){
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
        for (; ; ) {
            demoService.hello(); // 执行远程方法
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {

            }
        }
//                }
//            });
//        }
//        System.in.read(); // 按任意键退出
    }
}
