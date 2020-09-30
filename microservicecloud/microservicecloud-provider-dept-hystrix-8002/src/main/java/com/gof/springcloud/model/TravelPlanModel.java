package com.gof.springcloud.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Document(indexName = "travel-plan")
@ApiModel(value = "plan")
@Data
public class TravelPlanModel {

	@Id
	private String id;

	@Field(type = FieldType.Keyword)
	private String name;

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String title;

	@Field(type = FieldType.Nested)
	private List<TravelPlanModel_Day> days;

	@Field(type = FieldType.Double)
	@ApiModelProperty(value = "totalCost")
	private Double totalCost;

	public Double getTotalCost() {
		totalCost = 0.0;
		if (null != days && days.size() > 0) {
			for (TravelPlanModel_Day day : days) {
				if (null != day.getNodes() && day.getNodes().size() > 0) {
					for (TravelPlanModel_DayNode node : day.getNodes()) {
						totalCost += node.getCost();
					}
				}
			}
		}
		return totalCost;
	}
}

