package com.gof.springcloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "activity", parent = TravelPlanModel_DayNode.class)
public class TravelPlanModel_Activity extends TravelPlanModel_DayNode {

    @ApiModelProperty(value = "activity category")
    public String category;

    @ApiModelProperty(value = "cost")
    public Double cost;

    @ApiModelProperty(value="rating")
    public int rating;

    @ApiModelProperty(value="review")
    public String review;

    @ApiModelProperty(value="location")
    public String location;

    @ApiModelProperty(value = "time start")
    public Date timeStart;

    @ApiModelProperty(value = "time end")
    public Date timeEnd;
}
