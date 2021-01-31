package com.evatool.impact.common.mapper;

import com.evatool.impact.persistence.repository.DimensionRepository;
import com.evatool.impact.service.impl.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ImpactMapperTest {


//    @Test
//    public void testToDot_NewImpact_EqualsImpactDto() {
//        // given
//        var impactMapper = new ImpactMapper();
//        var impact = getImpact();
//
//        // when
//        var impactDto = impactMapper.toDto(impact);
//
//        // then
//        assertThat(impact.getId()).isEqualTo(impactDto.getId());
//        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
//        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
//        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimensionId());
//        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholderId());
//    }
//
//    @Test
//    public void testFromDto_NewImpactDto_EqualsImpact() throws EntityNotFoundException {
//        // given
//        var impactMapper = new ImpactMapper();
//        var impactDto = getImpactDto();
//
//        // when
//        var impact = impactMapper.fromDto(impactDto);
//
//        // then
//        System.out.println(impact);
//        System.out.println(impactDto);
//
//        assertThat(impact.getId()).isEqualTo(impactDto.getId());
//        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
//        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
//        //assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimensionId());
//        //assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholderId());
//    }

//    @Test
//    public void testToAndFromDto_ImpactToDtoAndFromDto_Equals() throws EntityNotFoundException {
//        // given
//        var impactMapper = new ImpactMapper();
//        var impact = getImpact();
//
//        // when
//        var impactDto = impactMapper.toDto(impact);
//        var impactFromDto = impactMapper.fromDto(impactDto);
//
//        // then
//        System.out.println(impact);
//        System.out.println(impactDto);
//        System.out.println(impactFromDto);
//
//        assertThat(impact.getId()).isEqualTo(impactFromDto.getId());
//        assertThat(impact.getValue()).isEqualTo(impactFromDto.getValue());
//        assertThat(impact.getDescription()).isEqualTo(impactFromDto.getDescription());
//        //assertThat(impact.getDimension()).isEqualTo(impactFromDto.getDimension());
//        //assertThat(impact.getStakeholder()).isEqualTo(impactFromDto.getStakeholder());
//    }
//
//    // TODO: normally retrieve the related entity (need to persist?)
//    @Test
//    public void testToAndFromDto_ImpactToDtoAndFromDto_EqualsWithRelation() throws EntityNotFoundException {
//        // given
//        var impactMapper = new ImpactMapper();
//        var impact = getImpact();
//
//        var dimension = getDimension();
//        dimensionRepository.save(dimension);
//        impact.setDimension(dimension);
//
//        // when
//        var impactDto = impactMapper.toDto(impact);
//        var impactFromDto = impactMapper.fromDto(impactDto);
//
//        // then
//        assertThat(impact.getId()).isEqualTo(impactFromDto.getId());
//        assertThat(impact.getValue()).isEqualTo(impactFromDto.getValue());
//        assertThat(impact.getDescription()).isEqualTo(impactFromDto.getDescription());
//        assertThat(impact.getDimension()).isEqualTo(impactFromDto.getDimension());
//        assertThat(impact.getStakeholder()).isEqualTo(impactFromDto.getStakeholder());
//    }

    // TODO: What if impactDto.dimensionId changes? Can modelmapper still resolve?

}
