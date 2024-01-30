package com.xiaozhang.actruledemo.demos.rule;

import com.xiaozhang.actruledemo.demos.common.RuleResult;
import com.xiaozhang.actruledemo.demos.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 规则引擎启动器类
 * 通过 Spring 管理的组件，用于加载和执行通过 @Rule 注解标记的规则。
 */
@Component
@DependsOn("springContextUtils")  // 确保在初始化本组件之前，"springContextUtils" 已经被初始化
@Slf4j  // 使用 lombok 提供的日志注解
public class RuleLauncher {

    /**
     * 规则引擎实例
     */
    private static DefaultRulesEngine rulesEngine;

    /**
     * 规则集合
     */
    private static Rules rules;

    // 在 Bean 初始化完成后自动执行的方法
    @PostConstruct
    public void init() {
        // 创建规则引擎参数，跳过第一个被应用的规则
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);

        // 初始化规则集合
        rules = new Rules();

        // 获取所有带有 @Rule 注解的 bean
        Map<String, Object> beans = SpringContextUtils.getApplicationContext().getBeansWithAnnotation(Rule.class);

        // 遍历所有带有 @Rule 注解的 bean
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            // 将 bean 注册到规则集合中
            rules.register(entry.getValue());

            // 获取规则的信息，并打印日志
            Rule ruleInfo = entry.getValue().getClass().getAnnotation(Rule.class);
            log.info("rule name:{}", ruleInfo.name());
        }

        // 创建规则引擎实例，并传入规则引擎参数
        rulesEngine = new DefaultRulesEngine(parameters);
    }

    /**
     * 执行规则引擎
     *
     * @param facts 包含事实的容器
     * @return 规则执行结果
     */
    public RuleResult execute(Facts facts) {
        // 规则引擎执行规则集合，并将执行结果存储在 facts 中的 "result" 事实中
        rulesEngine.fire(rules, facts);

        // 从 facts 中获取名为 "result" 的事实，并返回给调用者
        return facts.get("result");
    }

}
