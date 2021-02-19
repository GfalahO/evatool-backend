package requirements.domain.entity;

import com.evatool.requirements.entity.RequirementGR;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static requirements.common.TestDataGenerator.getRequirementGR;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementGRTest {
    @Test
    public void testSetRequirement_NullValue_ThrowException() {
        // given
        RequirementGR requirementGR = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementGR.setRequirement(null));
    }

    @Test
    public void testSetRequirementsImpacts_NullValue_ThrowException() {
        // given
        RequirementGR requirementGR = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementGR.setRequirementsImpacts(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void testSetValue_NullValue_ThrowException(int value) {
        // given
        RequirementGR requirementGR = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementGR.setPoints(value));
    }
}
