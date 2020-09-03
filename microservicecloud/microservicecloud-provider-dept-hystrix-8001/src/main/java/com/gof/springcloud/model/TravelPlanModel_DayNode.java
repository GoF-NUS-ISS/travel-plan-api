package com.gof.springcloud.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(subTypes = {TravelPlanModel_Activity.class, TravelPlanModel_Leg.class}, discriminator = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = TravelPlanModel_Activity.class, name = "activity"),
        @JsonSubTypes.Type(value = TravelPlanModel_Leg.class, name = "leg")})
public abstract class TravelPlanModel_DayNode {

//    @ApiModelProperty(value = "type")
//    private String type;

}
