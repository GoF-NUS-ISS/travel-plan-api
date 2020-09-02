package com.gof.springcloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.DecimalFormat;

@ApiModel(value = "activity")
public class TravelPlanModel_Activity extends TravelPlanModel_DayNode {

    @ApiModelProperty(value = "activity type")
    public String type;

    @ApiModelProperty(value = "cost")
    public String cost;
}
