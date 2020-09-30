package com.gof.springcloud.model;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "queryParam")
public class TravelPlanQueryParam {
	@ApiModelProperty(value = "totalCost Start")
	private Double totalCostStart;

	@ApiModelProperty(value = "totalCost End")
	private Double totalCostEnd;

	@ApiModelProperty(value = "site -> location in activity, from/to in leg")
	private String site;

	@ApiModelProperty(value = "keyword -> title, review in activity")
	private String keyword;

	public Boolean checkEmpty() {
		if (null != totalCostStart || null != totalCostEnd || StringUtils.isNotBlank(site)
				|| StringUtils.isNotBlank(keyword)) {
			return false;
		}
		return true;
	}
}
