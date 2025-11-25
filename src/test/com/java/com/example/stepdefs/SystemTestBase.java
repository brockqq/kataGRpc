package com.example.stepdefs;

import io.cucumber.java.After;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public abstract  class SystemTestBase {
    @Autowired
    private ApplicationContext applicationContext;
    @After
    public void tearDown() {
        System.out.println("--- 執行 Cucumber @After 清理 ---");
        resetAllFakeBean();
    }

    /**
     * 執行 Mock Bean 的重置 (從 SystemTestBase 搬移過來)
     */
    private void resetAllFakeBean() {
        if (applicationContext == null) {
            // 這可能發生在 Spring Context 未啟動的情況下
            // System.out.println("WARNING: ApplicationContext is null. Cannot reset beans.");
            return;
        }
        System.out.println("--- 執行 Cucumber @After 清理 --- 1-2");
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(beanName);
            if (MockUtil.isMock(bean) || MockUtil.isSpy(bean)) {
                Mockito.reset(bean);
            }
        }
        // System.out.println("--- Mock Beans 已重置 ---");
    }
}
