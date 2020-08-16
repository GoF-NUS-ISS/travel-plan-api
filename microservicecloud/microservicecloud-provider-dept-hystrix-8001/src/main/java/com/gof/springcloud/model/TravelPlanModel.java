package com.gof.springcloud.model;

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
    private String id;
    @ApiModelProperty(value = "user name", required = true)
    private String name;
    @ApiModelProperty(value = "travel destination", required = true)
    private String to;
    @ApiModelProperty(value = "travel departure", required = true)
    private String from;
}

