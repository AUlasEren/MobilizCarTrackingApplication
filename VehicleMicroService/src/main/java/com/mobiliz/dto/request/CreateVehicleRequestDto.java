package com.mobiliz.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequestDto {
    private Long companyId;
    @NotBlank(message = "Brand can not be blank")
    private String plate;
    private String chasisNumber;
    private String label;
    @NotBlank(message = "Brand can not be blank")
    private String brand;
    @NotBlank(message = "Model can not be blank")
    private String model;
    private String modelYear;
    private CreateRegionDto createRegionDto;


}
