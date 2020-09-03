package com.gof.springcloud.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "TravelPlanModel", description = "Travel Plan Entity")

public class TravelPlanModel {

    @Id
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "user name", required = true)
    private String name;
    @ApiModelProperty(value = "title", required = true)
    private String title;

    @ApiModelProperty(value = "days")
    private List<TravelPlanModel_Day> days;
}

