package com.evatool.requirements.application.dto;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.RequirementDimension;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementDimension;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class RequirementDTOTest {

    @Test
    public void testSetRootEntityId_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setRootEntityId(null));
    }

    @Test
    public void testSetRequirementTitle_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setRequirementTitle(null));
    }

    @Test
    public void testSetRequirementDescription_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setRequirementDescription(null));
    }

    @Test
    public void testSetDimensions_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setDimensions(null));
    }

    @Test
    public void testSetVariantsTitle_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setVariantsTitle(null));
    }

    @Test
    public void testSetRequirementImpactPoints_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setRequirementImpactPoints(null));
    }

    @Test
    public void testSetProjectID_NullValue_ThrowException() {
        // given
        RequirementDTO requirementDTO = new RequirementDTO();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementDTO.setProjectID(null));
    }


}
