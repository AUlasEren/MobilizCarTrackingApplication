package com.mobiliz.service;


import com.mobiliz.dto.request.CreateRegionDto;
import com.mobiliz.dto.response.RegionCreateResponseDto;
import com.mobiliz.mapper.IVehicleMapper;
import com.mobiliz.repository.IRegionRepository;
import com.mobiliz.repository.entity.Region;
import com.mobiliz.utility.ServiceManager;

import org.springframework.stereotype.Service;

@Service
public class RegionService extends ServiceManager<Region,Long> {
    private final IRegionRepository regionRepository;

    public RegionService(IRegionRepository regionRepository) {
        super(regionRepository);
        this.regionRepository = regionRepository;
    }

    public RegionCreateResponseDto create(CreateRegionDto dto) {
        Region region = IVehicleMapper.INSTANCE.toRegion(dto);
        Region savedRegion = save(region);
        return RegionCreateResponseDto.builder().regionId(savedRegion.getRegionId()).build();
    }
}
