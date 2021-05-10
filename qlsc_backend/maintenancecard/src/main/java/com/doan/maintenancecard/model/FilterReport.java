package com.doan.maintenancecard.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class FilterReport {

    @NotEmpty(message = "from must not be empty")
    private String from;
    @NotEmpty(message = "to must not be empty")
    private String to;
}
