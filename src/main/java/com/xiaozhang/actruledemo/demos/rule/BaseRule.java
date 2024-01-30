package com.xiaozhang.actruledemo.demos.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.api.Facts;

/**
 * 基础规则
 * @author:22603
 * @Date:2024/1/22 9:13
 */
public abstract class BaseRule implements IBaseRule{

    /**
     * 条件
     * @param facts
     * @return
     */
    @Condition
    public boolean when (Facts facts){
        return condition(facts);
    }

    /**
     * 结果
     * @param facts
     */
    @Action
    public void then(Facts facts){
        action(facts);
    }

}
