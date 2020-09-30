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
@ApiModel(value = "activity", parent = TravelPlanModel_DayNode.class)
public class TravelPlanModel_Activity extends TravelPlanModel_DayNode {

	@Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "activity category")
	private String category;

	@Field(type = FieldType.Double)
    @ApiModelProperty(value = "cost")
	private Double cost;

	@Field(type = FieldType.Integer)
    @ApiModelProperty(value="rating")
	private int rating;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
    @ApiModelProperty(value="review")
	private String review;

	@Field(type = FieldType.Keyword)
    @ApiModelProperty(value="location")
	private String location;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Field(type = FieldType.Date)
    @ApiModelProperty(value = "time start")
	private Date timeStart;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Field(type = FieldType.Date)
    @ApiModelProperty(value = "time end")
	private Date timeEnd;
}
