package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.evatool.impact.application.dto.mapper.DimensionMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionMapper.toDto;

@RestController
@RequestMapping("/api")
public class DimensionRestController {

    @Autowired
    private DimensionService dimensionService;

    @GetMapping("/dimension/{id}")
    public ResponseEntity<DimensionDto> getDimension(@PathVariable String id) throws EntityNotFoundException {
        var dimension = dimensionService.findDimensionById(id);
        return new ResponseEntity<>(toDto(dimension), HttpStatus.OK);
    }

    @GetMapping("/dimensions")
    public List<DimensionDto> getAllDimensions() {
        var dimensions = dimensionService.getAllDimensions();
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(s -> dimensionDtoList.add(toDto(s)));
        return dimensionDtoList;
    }

    @PostMapping("/dimension")
    public ResponseEntity<DimensionDto> createDimension(@RequestBody DimensionDto dimensionDto) {
        var dimension = dimensionService.createDimension(fromDto(dimensionDto));
        return new ResponseEntity<>(toDto(dimension), HttpStatus.CREATED);
    }

    @PutMapping("/dimension/{id}")
    public ResponseEntity<DimensionDto> updateDimension(@RequestBody DimensionDto dimensionDto) throws EntityNotFoundException {
        var dimension = dimensionService.updateDimension(fromDto(dimensionDto));
        return new ResponseEntity<>(toDto(dimension), HttpStatus.OK);

    }

    @DeleteMapping("/dimension/{id}")
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException {
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }
}
