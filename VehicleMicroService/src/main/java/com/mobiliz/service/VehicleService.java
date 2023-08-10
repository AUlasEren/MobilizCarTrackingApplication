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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService extends ServiceManager<Vehicle,Long> {
    private final IVehicleRepository iVehicleRepository;
    private final JwtTokenManager jwtTokenManager;
    private final RegionService regionService;

    public VehicleService(IVehicleRepository iVehicleRepository, JwtTokenManager jwtTokenManager, RegionService regionService) {
        super(iVehicleRepository);
        this.iVehicleRepository=iVehicleRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.regionService = regionService;
    }


    public MessageResponseDto create(CreateVehicleRequestDto dto) {
        Vehicle vehicle = IVehicleMapper.INSTANCE.toVehicle(dto);
        Region region = regionService.saveRegion(dto.getCreateRegionDto());
        vehicle.setRegionId(region.getRegionId());
        save(vehicle);
        return MessageResponseDto.builder().message("Vehicle has created successfully").build();
    }
    public MessageResponseDto update(UpdateVehicleRequestDto dto){
        Optional<Vehicle> vehicle = iVehicleRepository.findById(dto.getVehicleId());
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
    public MessageResponseDto deleteVehicle(Long id) {
        Optional<Vehicle> vehicle = iVehicleRepository.findById(id);
        if (vehicle.isEmpty()) {
            throw new VehicleManagerException(EErrorType.VEHICLE_NOT_FOUND);
        }
        vehicle.get().setStatus(EStatus.NOT_ACTIVE);
        update(vehicle.get());
        return MessageResponseDto.builder().message("Vehicle has deleted successfully").build();
    }

    public List<Vehicle> findAllVehicle(TokenRequestDto dto) {
        Optional<Long> companyId = jwtTokenManager.getCompanyIdFromToken(dto.getToken());
        List<Long> regions = jwtTokenManager.getRegionIdFromToken(dto.getToken());
        List<Long> parentList = regionService.findAll().stream().filter(x->regions.contains(x.getParentId())).map(y->y.getRegionId()).toList();
        List<Vehicle> vehicleList = findAll().stream()
                .filter(x -> x.getCompanyId().equals(companyId.get()) && regions.contains(x.getRegionId()))
                .collect(Collectors.toList());
        List<Vehicle> vehicleList1 = findAll().stream()
                .filter(x ->x.getCompanyId().equals(companyId.get()) &&parentList.contains(x.getRegionId()))
                .collect(Collectors.toList());
        vehicleList.stream().forEach(x-> vehicleList1.add(x));
        return vehicleList1;

    }



}
