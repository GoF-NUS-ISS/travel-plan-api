package com.gof.springcloud.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class TravelPlanModel {

    @Id
    private String id;
    private String name;
    private String to;
    private String from;
}

