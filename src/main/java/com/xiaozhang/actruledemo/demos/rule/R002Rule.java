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
@Rule(name = "R002",description = "获取奖品信息")
@Component
@Slf4j
public class R002Rule extends BaseRule{
    @Override
    public boolean condition(Facts facts) {
        String ruleCode = facts.get("ruleCode");
        return "R002".equals(ruleCode);
    }

    @Override
    public void action(Facts facts) {
        log.info("R002Rule，获取奖品信息");
        RuleResult ruleResult = new RuleResult();
        JSONObject prize = new JSONObject();
        prize.put("phone",13);
        prize.put("100￥",100);
        JSONObject data = new JSONObject();
        data.put("name","拆红包");
        data.put("prizeInfo",prize);
        ruleResult.setData(data);
        facts.put("result",ruleResult);
    }
}
