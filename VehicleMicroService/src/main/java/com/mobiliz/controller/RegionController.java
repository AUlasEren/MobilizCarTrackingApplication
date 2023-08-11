package com.mobiliz.controller;

import com.mobiliz.dto.request.CreateRegionDto;
import com.mobiliz.dto.response.RegionCreateResponseDto;
import com.mobiliz.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mobiliz.constants.EndPoints.CREATE;
import static com.mobiliz.constants.EndPoints.REGION;

@RequestMapping(REGION)
@RequiredArgsConstructor
@RestController
public class RegionController {
    private final RegionService regionService;

    @PostMapping(CREATE)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<RegionCreateResponseDto> create(@RequestBody CreateRegionDto dto) {
        return ResponseEntity.ok(regionService.create(dto));
    }

}
