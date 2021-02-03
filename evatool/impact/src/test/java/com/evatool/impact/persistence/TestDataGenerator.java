package com.evatool.impact.persistence;

import com.evatool.impact.common.dto.DimensionDto;
import com.evatool.impact.common.dto.ImpactDto;
import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.persistence.entity.*;

import java.util.UUID;

public class TestDataGenerator {
    public static Dimension getDimension() {
        return new Dimension("dimension", "dimension");
    }

    public static Stakeholder getStakeholder() {
        return new Stakeholder("person");
    }

    public static Requirement getRequirement() {
        return new Requirement();
    }

    public static Impact getImpact() {
        return new Impact(0.0, "impact", getDimension(), getStakeholder());
    }

    public static Analysis getAnalysis() {
        return new Analysis();
    }

    public static DimensionDto getDimensionDto() {
        var dimensionDto = new DimensionDto();

        dimensionDto.setName("name");
        dimensionDto.setDescription("description");

        return dimensionDto;
    }

    public static StakeholderDto getStakeholderDto() {
        var stakeholderDto = new StakeholderDto();

        stakeholderDto.setName("name");

        return stakeholderDto;
    }

    public static ImpactDto getImpactDto() {
        var impactDto = new ImpactDto();

        impactDto.setValue(0.0);
        impactDto.setDescription("description");

        return impactDto;
    }

    public static DimensionDto getEmptyDimensionDto() {
        return new DimensionDto();
    }

    public static StakeholderDto getEmptyStakeholderDto() {
        return new StakeholderDto();
    }

    public static ImpactDto getEmptyImpactDto() {
        return new ImpactDto();
    }
}
