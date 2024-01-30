package com.xiaozhang.actruledemo.demos.rule;

import cn.hutool.json.JSONObject;
import com.xiaozhang.actruledemo.demos.common.RuleResult;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Component;

/**
 * @author:22603
 * @Date:2024/1/22 9:16
 */
@Rule(name = "R001",description = "获取当前时间")
@Component
@Slf4j
public class R001Rule extends BaseRule{
    @Override
    public boolean condition(Facts facts) {
        String ruleCode = facts.get("ruleCode");
        return "R001".equals(ruleCode);
    }

    @Override
    public void action(Facts facts) {
        log.info("R001Rule，获取当前时间");
        //获取当前时间
        long nowTime = System.currentTimeMillis();
        RuleResult ruleResult = new RuleResult();
        JSONObject data = new JSONObject();
        data.put("nowTime",nowTime);
        ruleResult.setData(data);
        facts.put("result",ruleResult);
    }
}
