package com.xiaozhang.actruledemo.demos.rule;

import cn.hutool.json.JSONObject;
import com.xiaozhang.actruledemo.demos.common.RuleResult;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author:22603
 * @Date:2024/1/22 9:16
 */
@Rule(name = "R003",description = "抽取奖品信息")
@Component
@Slf4j
public class R003Rule extends BaseRule{

    private RuleLauncher ruleLauncher =new RuleLauncher();

    @Override
    public boolean condition(Facts facts) {
        String ruleCode = facts.get("ruleCode");
        return "R003".equals(ruleCode);
    }

    @Override
    public void action(Facts facts) {
        log.info("R003Rule，抽取奖品信息");
        RuleResult ruleResult = new RuleResult();
        facts.put("ruleCode","R002");
        ruleLauncher.execute(facts);
        //调用R002规则获取奖品信息
        RuleResult result = facts.get("result");
        //获取奖品信息
//        JSONObject prize = prizeInfo.getJSONObject("prizeInfo");
        JSONObject object = (JSONObject) result.getData();
        JSONObject prizeInfo = (JSONObject) object.get("prizeInfo");
        Set<String> prizes = prizeInfo.keySet();
        //随机获取Strings中的一个
        String[] string = prizes.toArray(new String[prizes.size()]);
        int i = (int) (Math.random() * string.length);
        //获取抽取的奖品
        String prizeName = string[i];
        JSONObject data = new JSONObject();
        //将奖品信息放入data中
        data.put("prizeName",prizeName);
        ruleResult.setData(data);
        facts.put("result",ruleResult);
    }
}
