package com.evatool.impact.common;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.entity.ImpactStakeholder;

public class TestDataGenerator {

    public static Dimension createDummyDimension() {
        return new Dimension("dummyDimension", Dimension.Type.ECONOMIC, "dummyDimensionDescription");
    }

    public static ImpactStakeholder createDummyStakeholder() {
        return new ImpactStakeholder("dummyStakeholder");
    }

    public static Impact createDummyImpact() {
        return new Impact(0.0, "dummyImpactDescription", createDummyDimension(), createDummyStakeholder());
    }

    public static DimensionDto createDummyDimensionDto() {
        var dimensionDto = new DimensionDto();
        dimensionDto.setName("dummyDimension");
        dimensionDto.setType(Dimension.Type.ECONOMIC.toString());
        dimensionDto.setDescription("dummyDimensionDescription");
        return dimensionDto;
    }

    public static StakeholderDto createDummyStakeholderDto() {
        var stakeholderDto = new StakeholderDto();
        stakeholderDto.setName("dummyStakeholder");
        return stakeholderDto;
    }

    public static ImpactDto createDummyImpactDto() {
        var impactDto = new ImpactDto();
        impactDto.setValue(0.0);
        impactDto.setDescription("dummyImpactDescription");
        impactDto.setDimension(createDummyDimensionDto());
        impactDto.setStakeholder(createDummyStakeholderDto());
        return impactDto;
    }
}
