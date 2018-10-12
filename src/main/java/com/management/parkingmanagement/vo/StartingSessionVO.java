package com.management.parkingmanagement.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class StartingSessionVO implements Serializable {

    @JsonProperty("licensePlateNumber")
    @NotNull
    private String licensePlateNumber;
}
