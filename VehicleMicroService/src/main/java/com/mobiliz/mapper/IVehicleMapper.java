package com.mobiliz.mapper;

import com.mobiliz.dto.request.CreateRegionDto;
import com.mobiliz.dto.request.CreateVehicleRequestDto;
import com.mobiliz.dto.request.UpdateVehicleRequestDto;
import com.mobiliz.repository.entity.Region;
import com.mobiliz.repository.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IVehicleMapper {
    IVehicleMapper INSTANCE = Mappers.getMapper(IVehicleMapper.class);
    Vehicle toVehicle(final CreateVehicleRequestDto dto);
    Region toRegion(final CreateRegionDto dto);

}
