package com.management.parkingmanagement.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class FinishingSessionVO implements Serializable {

    @JsonProperty("status")
    @NotNull
    private String status;
}
