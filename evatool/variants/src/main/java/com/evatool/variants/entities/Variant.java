package com.evatool.variants.entities;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "VARIANT")
@Getter
public class Variant {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String title;

    private String criterion;

    @OneToMany
    private List<Variant> sub_variant;

    private String description;

    private boolean st_flags_pot;

    private boolean st_flags_real;

    @OneToOne
    private VariantsStakeholder variantsStakeholder;

    @OneToMany
    private List<Analysis> analysis;

    @OneToMany
    private List<Requirement> requirements;
}
