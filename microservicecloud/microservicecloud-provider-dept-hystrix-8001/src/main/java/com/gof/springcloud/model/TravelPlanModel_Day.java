package com.gof.springcloud.model;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "day")
public class TravelPlanModel_Day {
    @ApiModelProperty(value = "current date")
    private Date date;

    @ApiModelProperty(value = "nodes")
    private List<TravelPlanModel_DayNode> nodes;
}
