package com.xiaozhang.actruledemo.demos.rule;

import org.jeasy.rules.api.Facts;

/**
 * 基础方法
 * @author:22603
 * @Date:2024/1/22 9:12
 */
public interface IBaseRule {
   abstract boolean condition(Facts facts);

   abstract void action(Facts facts);
}
