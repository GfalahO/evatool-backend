package com.evatool.impact.common.controller.rest;

import com.evatool.impact.common.dto.DimensionDto;
import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.DimensionMapper;
import com.evatool.impact.service.EntityNotFoundException;
import com.evatool.impact.service.api.rest.DimensionRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DimensionRestController {
    @Autowired
    private DimensionRestService dimensionRestService;

    private DimensionMapper dimensionMapper = new DimensionMapper();

    @GetMapping("/dimension/{id}")
    public ResponseEntity<DimensionDto> getDimension(@PathVariable String id) throws EntityNotFoundException {
        var dimension = dimensionRestService.getDimensionById(id);
        var dimensionDto = dimensionMapper.toDto(dimension);
        var responseEntity = new ResponseEntity<>(dimensionDto, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/dimensions")
    public List<DimensionDto> getAllDimensions() {
        var dimensions = dimensionRestService.getAllDimensions();
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(s -> dimensionDtoList.add(dimensionMapper.toDto(s)));
        return dimensionDtoList;
    }

    @PostMapping("/dimension")
    public ResponseEntity<DimensionDto> insertStakeholder(@RequestBody DimensionDto dimensionDto) {
        var dimension = dimensionMapper.fromDto(dimensionDto);
        var insertedDimension = dimensionRestService.insertDimension(dimension);
        var insertedDimensionDto = dimensionMapper.toDto(insertedDimension);
        var responseEntity = new ResponseEntity<>(insertedDimensionDto, HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("/dimension/{id}")
    public ResponseEntity<DimensionDto> updateStakeholder(@RequestBody DimensionDto dimensionDto) throws EntityNotFoundException {
        var dimension = dimensionMapper.fromDto(dimensionDto);
        var updateDimension = dimensionRestService.updateDimension(dimension);
        var updateDimensionDto = dimensionMapper.toDto(updateDimension);
        var responseEntity = new ResponseEntity<>(updateDimensionDto, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/dimension/{id}")
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException {
        dimensionRestService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }
}
