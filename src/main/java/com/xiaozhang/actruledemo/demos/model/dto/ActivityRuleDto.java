package com.xiaozhang.actruledemo.demos.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author:22603
 * @Date:2024/1/22 10:10
 */
@Data
public class ActivityRuleDto {

    /**
     * 活动ID
     */
    String  activityId;

    /**
     * 活动规则
     */
    List<String> rules;

}
