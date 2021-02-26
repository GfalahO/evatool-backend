package com.evatool.variants.services;

import com.evatool.variants.controller.VariantController;
import com.evatool.variants.entities.Variant;
import com.evatool.variants.entities.VariantDto;
import com.evatool.variants.events.VariantCreatedEventPublisher;
import com.evatool.variants.events.VariantDeletedEventPublisher;
import com.evatool.variants.events.VariantUpdatedEventPublisher;
import com.evatool.variants.repositories.VariantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * VariantService serves the VariantController
 */
@Service
public class VariantService {

    VariantRepository variantRepository;
    VariantCreatedEventPublisher variantCreatedEventPublisher;
    VariantUpdatedEventPublisher variantUpdatedEventPublisher;
    VariantDeletedEventPublisher variantDeletedEventPublisher;
    Logger logger = LoggerFactory.getLogger(VariantService.class);

    @Autowired
    public VariantService(VariantRepository variantRepository,
                          VariantCreatedEventPublisher variantCreatedEventPublisher,
                          VariantUpdatedEventPublisher variantUpdatedEventPublisher,
                          VariantDeletedEventPublisher variantDeletedEventPublisher) {
        this.variantRepository = variantRepository;
        this.variantCreatedEventPublisher = variantCreatedEventPublisher;
        this.variantDeletedEventPublisher = variantDeletedEventPublisher;
        this.variantUpdatedEventPublisher = variantUpdatedEventPublisher;
    }

    @Autowired
    VariantMapper variantMapper;

    public List<VariantDto> findAll(List<Variant> variantList){
        logger.info("Find all Variants ");
        return variantMapper.mapAll(variantList);
    }

    /**
     * Returns an existing Variant from the Repository
     *
     * @param id Existing id to return the related Variant
     * @return A ResponseEntity containing a message and corresponding Http-Status Code
     */
    public ResponseEntity<Variant> getVariant(UUID id) {
        Variant variant = variantRepository.findVariantById(id);
        if (variant == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            variant.add(linkTo(VariantController.class).slash(id).withSelfRel());
            return new ResponseEntity<>(variant, HttpStatus.OK);
        }
    }


    /**
     * Returns all Variants from the Repository
     *
     * @return A ResponseEntity containing a message and corresponding Http-Status Code
     */
    public ResponseEntity<CollectionModel<Variant>> getAllVariants() {
        List<Variant> variants = variantRepository.findAll();

        variants.forEach(variant -> variant.add(linkTo(VariantController.class).slash(variant.getId()).withSelfRel()));

        Link variantsLink = linkTo(methodOn(VariantController.class).getAllVariants()).withSelfRel();
        CollectionModel<Variant> variantCollectionModel = CollectionModel.of(variants);
        variantCollectionModel.add(variantsLink);

        return new ResponseEntity<>(variantCollectionModel, HttpStatus.OK);
    }

    /**
     * Updates an existing Variant from the Repository and publishes a corresponding event
     *
     * @param id             Existing id for updating related Variant
     * @param updatedVariant Overwrites the Variant that will be updated
     * @return A ResponseEntity containing a message and corresponding Http-Status Code
     */
    public ResponseEntity<Variant> updateVariant(UUID id, Variant updatedVariant) {
        Variant variant = variantRepository.findVariantById(id);
        if (variant == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            //TODO validate updateVariant
            Variant savedVariant = variantRepository.save(updatedVariant);
            variantUpdatedEventPublisher.publishVariantUpdatedEvent(savedVariant);
            return new ResponseEntity<>(savedVariant, HttpStatus.OK);
        }
    }


    /**
     * Deletes an existing Variant from the Repository and publishes a corresponding event
     *
     * @param id Existing id for deleting related Variant
     * @return A ResponseEntity containing a message and corresponding Http-Status Code
     */
    public ResponseEntity<String> deleteVariant(UUID id) {
        Variant variant = variantRepository.findVariantById(id);
        if (variant == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        } else {
            variantRepository.delete(variant);
            variantDeletedEventPublisher.publishVariantDeletedEvent(variant);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
    }

    /**
     * Creates a new Variant, saves it using the repository and publishes a corresponding event
     *
     * @param newVariant New Variant-Object to be saved
     * @return A ResponseEntity containing a message and corresponding Http-Status Code
     */
    public ResponseEntity<Variant> createVariant(Variant newVariant) {
        //TODO Validate newVariant
        Variant savedVariant = variantRepository.save(newVariant);
        variantCreatedEventPublisher.publishVariantCreatedEvent(savedVariant);
        return new ResponseEntity<>(savedVariant, HttpStatus.CREATED);
    }
}