package com.evatool.variants.controller;

import com.evatool.variants.entities.Variant;
import com.evatool.variants.services.VariantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(value = "VariantsController", description = "REST API for variants", tags = "variants")
@RestController
@RequestMapping("variants")
public class VariantController {

    @Autowired
    VariantService variantService;

    @ApiOperation(value = "postVariant", notes = "post a new variant", nickname = "postVariant")
    @ApiResponse(code = 201, message = "Created successfully", response = Variant.class, responseContainer = "Variant")
    @PostMapping
    public ResponseEntity<Variant> createVariant(@RequestBody Variant newVariant) {
        return variantService.createVariant(newVariant);
    }

    @ApiOperation(value = "getVariant", notes = "get a variant", nickname = "getVariant")
    @ApiResponse(code = 201, message = "Created successfully", response = Variant.class, responseContainer = "Variant")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Variant> getVariant(
            @ApiParam (name = "variantId", value = "identification of a Variant", required = true)
            @PathVariable UUID id) {
        return variantService.getVariant(id);
    }

    @ApiOperation(value = "getAllVariants", notes = "get list of all variants", nickname = "getAllVariants")
    @ApiResponse(code = 200, message = "Successful retrieval", response = Variant.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity<CollectionModel<Variant>> getAllVariants() {
        return variantService.getAllVariants();
    }

    @ApiOperation(value = "putVariants", notes = "put a variants", nickname = "putVariant")
    @ApiResponse(code = 200, message = "Successful changed", response = Variant.class, responseContainer = "List")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Variant> updateVariant(
            @ApiParam (name = "variantId", value = "identification of a Variant", required = true)
            @PathVariable UUID id,
            @RequestBody Variant updatedVariant) {
        return variantService.updateVariant(id, updatedVariant);
    }

    @ApiOperation(value = "deleteVariants", notes = "delete a variants", nickname = "deleteVariant")
    @ApiResponse(code = 200, message = "Successful deleted", response = Variant.class, responseContainer = "List")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteVariant(
            @ApiParam (name = "variantId", value = "identification of a Variant", required = true)
            @PathVariable UUID id) {
        return variantService.deleteVariant(id);
    }
}