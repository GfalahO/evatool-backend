package com.evatool.variants.entities;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// renamed entity because analysis is owner of the entity and they had already used up the name 'Stakeholder'
@Entity(name = "VARIANTS_STAKEHOLDER")
@Getter
public class VariantsStakeholder {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String title;
}
