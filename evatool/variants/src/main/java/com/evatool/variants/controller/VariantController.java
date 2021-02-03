package com.evatool.variants.controller;

import com.evatool.variants.entities.Variant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("variants")
public class VariantController {

    @GetMapping(value="/{id}", produces = "application/json")
    public Variant getVariant(@PathVariable String id) {
        return new Variant();
    }
}
