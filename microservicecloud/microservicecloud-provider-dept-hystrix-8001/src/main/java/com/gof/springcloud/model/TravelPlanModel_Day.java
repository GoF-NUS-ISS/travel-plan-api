package com.gof.springcloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;

@ApiModel(value = "day")
public class TravelPlanModel_Day {
    @ApiModelProperty(value = "current date")
    private Date date;

    @ApiModelProperty(value = "nodes")
    private ArrayList<TravelPlanModel_DayNode> nodes;
}
