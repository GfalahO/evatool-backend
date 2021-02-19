package com.evatool.variants.entities;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// renamed because the entity is owned by another module and they have already used up the name 'Requirement'
@Entity(name = "VARIANTS_REQUIREMENT")
@Getter
public class VariantsRequirement {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String title;
}
