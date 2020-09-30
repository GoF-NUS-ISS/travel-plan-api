package com.gof.springcloud.model;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "dayNode")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = TravelPlanModel_Activity.class, name = "activity"),
        @JsonSubTypes.Type(value = TravelPlanModel_Leg.class, name = "leg")})
public abstract class TravelPlanModel_DayNode {

	@Field(type = FieldType.Double)
    @ApiModelProperty(value = "cost")
    private Double cost;

//    @ApiModelProperty(value = "type")
//    private String type;

}
