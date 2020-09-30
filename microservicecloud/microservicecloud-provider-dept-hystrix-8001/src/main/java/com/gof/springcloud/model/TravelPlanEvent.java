package com.gof.springcloud.model;

import java.util.Date;

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
@ApiModel(value = "TravelPlanModel", description = "Travel Plan Entity")

public class TravelPlanEvent {
	@ApiModelProperty(value = "optType: SAVE/REMOVE")
	private String optType;

	@ApiModelProperty(value = "optTime")
	private Date optTime;

	@ApiModelProperty(value = "model")
	private TravelPlanModel model;

}
