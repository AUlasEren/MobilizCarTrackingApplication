package com.mobiliz.service;

import com.mobiliz.dto.request.CreateVehicleRequestDto;
import com.mobiliz.dto.request.TokenRequestDto;
import com.mobiliz.dto.request.UpdateVehicleRequestDto;
import com.mobiliz.dto.response.MessageResponseDto;
import com.mobiliz.exceptions.EErrorType;
import com.mobiliz.exceptions.VehicleManagerException;
import com.mobiliz.mapper.IVehicleMapper;
import com.mobiliz.repository.IVehicleRepository;
import com.mobiliz.repository.entity.Region;
import com.mobiliz.repository.entity.Vehicle;
import com.mobiliz.repository.enums.EStatus;
import com.mobiliz.utility.JwtTokenManager;
import com.mobiliz.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehicleService extends ServiceManager<Vehicle,Long> {
    private final IVehicleRepository iVehicleRepository;
    private final JwtTokenManager jwtTokenManager;
    private final RegionService regionService;

    public VehicleService(IVehicleRepository iVehicleRepository, JwtTokenManager jwtTokenManager, RegionService regionService) {
        super(iVehicleRepository);
        this.iVehicleRepository = iVehicleRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.regionService = regionService;
    }


    public MessageResponseDto create(CreateVehicleRequestDto dto, String bearerToken) {
        if (!dto.getCompanyId().equals(getCompanyIdFromBearerToken(bearerToken))) {
            throw new VehicleManagerException(EErrorType.COMPANY_ID_MISMATCH);
        }
        if (regionService.findById(dto.getRegionId()).isEmpty()) {
            throw new VehicleManagerException(EErrorType.REGION_NOT_FOUND);
        }
        if (!getRegionsFromBearerToken(bearerToken).contains(dto.getCompanyId())){
            throw new VehicleManagerException(EErrorType.REGION_MISMATCH);
        }
        Vehicle vehicle = IVehicleMapper.INSTANCE.toVehicle(dto);
        save(vehicle);
        return MessageResponseDto.builder().message("Vehicle has created successfully").build();
    }

    public MessageResponseDto update(UpdateVehicleRequestDto dto, String bearerToken) {
        Optional<Vehicle> vehicle = iVehicleRepository.findById(dto.getVehicleId());
        if (!vehicle.get().getCompanyId().equals(getCompanyIdFromBearerToken(bearerToken))) {
            throw new VehicleManagerException(EErrorType.COMPANY_ID_MISMATCH);
        }
        if (vehicle.isEmpty()) {
            throw new VehicleManagerException(EErrorType.VEHICLE_NOT_FOUND);
        }
        Vehicle toUpdateVehicle = vehicle.get();
        toUpdateVehicle.setBrand(dto.getBrand());
        toUpdateVehicle.setModel(dto.getModel());
        toUpdateVehicle.setLabel(dto.getLabel());
        toUpdateVehicle.setModelYear((dto.getModelYear()));
        toUpdateVehicle.setChasisNumber(dto.getChasisNumber());
        toUpdateVehicle.setPlate(dto.getPlate());
        update(toUpdateVehicle);
        return MessageResponseDto.builder().message("Vehicle has updated successfully").build();
    }

    public MessageResponseDto deleteVehicle(Long id, String bearerToken) {
        Optional<Vehicle> vehicle = iVehicleRepository.findById(id);
        if (!vehicle.get().getCompanyId().equals(getCompanyIdFromBearerToken(bearerToken))) {
            throw new VehicleManagerException(EErrorType.COMPANY_ID_MISMATCH);
        }
        if (vehicle.isEmpty()) {
            throw new VehicleManagerException(EErrorType.VEHICLE_NOT_FOUND);
        }
        vehicle.get().setStatus(EStatus.NOT_ACTIVE);
        update(vehicle.get());
        return MessageResponseDto.builder().message("Vehicle has deleted successfully").build();
    }

    public List<Vehicle> findAllVehicle(String bearerToken) {
        Long companyId = getCompanyIdFromBearerToken(bearerToken);
        List<Long> regions = jwtTokenManager.getRegionIdFromToken(bearerToken.substring(7));
        return findAll().stream()
                .filter(x -> x.getCompanyId().equals(companyId) && regions.contains(x.getRegionId()))
                .collect(Collectors.toList());
    }


    private Long getCompanyIdFromBearerToken(String bearerToken) {
        Optional<Long> companyIdFromToken = jwtTokenManager.getCompanyIdFromToken(bearerToken.substring(7));
        if (companyIdFromToken.isEmpty()) {
            throw new VehicleManagerException(EErrorType.COMPANY_ID_MISMATCH);
        }
        return companyIdFromToken.get();
    }

    private List<Long> getRegionsFromBearerToken(String bearerToken) {
        return jwtTokenManager.getRegionIdFromToken(bearerToken.substring(7));
    }

}
