package com.xiaozhang.actruledemo.demos.web;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.xiaozhang.actruledemo.demos.common.ResultUtils;
import com.xiaozhang.actruledemo.demos.common.RuleResult;
import com.xiaozhang.actruledemo.demos.model.dto.ActivityRuleDto;
import com.xiaozhang.actruledemo.demos.common.BaseResponse;
import com.xiaozhang.actruledemo.demos.rule.RuleLauncher;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规则请求入口
 * @author:22603
 * @Date:2024/1/22 10:02
 */
@RestController
@RequestMapping("/actRule")
public class DemoController {

    private RuleLauncher ruleLauncher =new RuleLauncher();

    /**
     * 接口请求入口
     * @return
     */
    @GetMapping("/execute")
    public BaseResponse execute(@RequestBody ActivityRuleDto activityRuleDto) {
        Facts facts = new Facts();
        JSONObject data = new JSONObject();
        for (String rule : activityRuleDto.getRules()) {
            facts.put("ruleCode", rule);
            facts.put("result", new RuleResult());
            RuleResult excute = ruleLauncher.execute(facts);
            System.out.println(excute);
            data.put(rule, excute.getData().toString());
        }
        return ResultUtils.success(data);
    }
}
