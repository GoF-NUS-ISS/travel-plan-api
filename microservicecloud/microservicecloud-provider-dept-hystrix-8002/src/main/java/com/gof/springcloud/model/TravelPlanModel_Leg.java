package com.gof.springcloud.model;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@ApiModel(value = "leg", parent = TravelPlanModel_DayNode.class)
public class TravelPlanModel_Leg extends TravelPlanModel_DayNode {

	@Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "depart from")
    private String from;

	@Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "arrive at")
    private String to;

	@Field(type = FieldType.Date)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "start on")
	private Date startOn;

	@Field(type = FieldType.Date)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "return date")
	private Date returnDate;

	@Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "transport mode")
	private String transportMode;

}

