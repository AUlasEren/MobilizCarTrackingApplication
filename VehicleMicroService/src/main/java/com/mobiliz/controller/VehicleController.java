package com.mobiliz.controller;

import com.mobiliz.dto.request.CreateRegionDto;
import com.mobiliz.dto.request.CreateVehicleRequestDto;
import com.mobiliz.dto.request.TokenRequestDto;
import com.mobiliz.dto.request.UpdateVehicleRequestDto;
import com.mobiliz.dto.response.MessageResponseDto;
import com.mobiliz.repository.entity.Region;
import com.mobiliz.repository.entity.Vehicle;
import com.mobiliz.service.RegionService;
import com.mobiliz.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.mobiliz.constants.EndPoints.*;

@RequestMapping(VEHICLE)
@RequiredArgsConstructor
@RestController
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping(CREATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<MessageResponseDto> create(@RequestBody CreateVehicleRequestDto dto){
        return ResponseEntity.ok(vehicleService.create(dto));
    }
    @PostMapping(UPDATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<MessageResponseDto> update(@RequestBody UpdateVehicleRequestDto dto){
        return ResponseEntity.ok(vehicleService.update(dto));
    }
    @DeleteMapping(DELETE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<MessageResponseDto> deleteVehicle(@RequestParam Long id){
        return ResponseEntity.ok(vehicleService.deleteVehicle(id));
    }
    @PostMapping(FIND_ALL)
    public ResponseEntity<List<Vehicle>> findAllVehicle(@RequestBody TokenRequestDto dto){
        return ResponseEntity.ok(vehicleService.findAllVehicle(dto));
    }

}
