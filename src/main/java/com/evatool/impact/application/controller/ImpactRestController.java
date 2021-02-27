package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.evatool.impact.application.controller.UriUtil.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ImpactRestController {

    private static final Logger logger = LoggerFactory.getLogger(ImpactRestController.class);

    private final ImpactService impactService;

    public ImpactRestController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @GetMapping(IMPACTS_ID)
    @ApiOperation(value = "Get Impact by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<EntityModel<ImpactDto>> findById(@ApiParam("Impact ID") @Valid @PathVariable UUID id) {
        logger.info("GET " + IMPACTS_ID);
        var impactDto = impactService.findById(id);
        return new ResponseEntity<>(getImpactWithLinks(impactDto), HttpStatus.OK);
    }

    @GetMapping(IMPACTS)
    @ApiOperation(value = "Get all Impacts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<EntityModel<ImpactDto>>> findAll() {
        logger.info("GET " + IMPACTS);
        return new ResponseEntity<>(getImpactsWithLinks(impactService.findAll()), HttpStatus.OK);
    }

    @PostMapping(IMPACTS)
    @ApiOperation(value = "Create a new Impact")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable")})
    public ResponseEntity<EntityModel<ImpactDto>> insert(@ApiParam("Impact") @Valid @RequestBody ImpactDto impactDto) {
        logger.info("POST " + IMPACTS);
        var insertedImpactDto = impactService.insert(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(insertedImpactDto), HttpStatus.CREATED);
    }

    @PutMapping(IMPACTS)
    @ApiOperation(value = "Update an Impact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable")})
    public ResponseEntity<EntityModel<ImpactDto>> update(@ApiParam("Impact") @Valid @RequestBody ImpactDto impactDto) {
        logger.info("PUT " + IMPACTS);
        var updatedImpactDto = impactService.update(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(updatedImpactDto), HttpStatus.OK);
    }

    @DeleteMapping(IMPACTS_ID)
    @ApiOperation(value = "Delete Impact by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<Void> deleteById(@ApiParam("Impact ID") @Valid @PathVariable UUID id) {
        logger.info("DELETE " + IMPACTS_ID);
        impactService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private EntityModel<ImpactDto> getImpactWithLinks(ImpactDto impactDto) {
        logger.debug("Adding HATEOAS Rest Level 3 links");
        var entityModel = EntityModel.of(impactDto);
        entityModel.add(linkTo(methodOn(ImpactRestController.class).findById(impactDto.getId())).withSelfRel());
        entityModel.add(linkTo(methodOn(ImpactRestController.class).update(impactDto)).withRel(UPDATE_IMPACT));
        entityModel.add(linkTo(methodOn(ImpactRestController.class).deleteById(impactDto.getId())).withRel(DELETE_IMPACT));
        entityModel.add(linkTo(ImpactRestController.class).slash(STAKEHOLDERS).slash(impactDto.getStakeholder().getId()).withRel(GET_STAKEHOLDER));
        entityModel.add(linkTo(ImpactRestController.class).slash(DIMENSIONS).slash(impactDto.getDimension().getId()).withRel(GET_DIMENSION));
        return entityModel;
    }

    private List<EntityModel<ImpactDto>> getImpactsWithLinks(List<ImpactDto> impactDtoList) {
        var entityModelList = new ArrayList<EntityModel<ImpactDto>>();
        impactDtoList.forEach(impactDto -> entityModelList.add(getImpactWithLinks(impactDto)));
        return entityModelList;
    }
}
