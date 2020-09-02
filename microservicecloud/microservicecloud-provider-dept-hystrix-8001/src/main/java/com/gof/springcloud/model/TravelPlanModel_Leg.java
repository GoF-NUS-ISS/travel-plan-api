package com.gof.springcloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "leg")
public class TravelPlanModel_Leg extends TravelPlanModel_DayNode {

    @ApiModelProperty(value = "depart from")
    private String from;

    @ApiModelProperty(value = "arrive at")
    private String to;
}

