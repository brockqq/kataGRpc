package com.example.stepdefs.utils;

import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringTestUtils {
    @Autowired
    ApplicationContext applicationContext;
    /**
     * 重置 Spring Context 中所有 Mockito Bean 的狀態。
     * @param applicationContext 當前的 Spring 應用程式上下文。
     */
    public  void resetAllFakeBean() {
        if (applicationContext == null) {
            System.out.println("WARNING: ApplicationContext is null. Cannot reset beans.");
            return;
        }
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(beanName);
            if (MockUtil.isMock(bean) || MockUtil.isSpy(bean)) {
                Mockito.reset(bean);
            }
        }
        System.out.println("--- 所有 Mock Beans 已重置 ---");
    }
}
