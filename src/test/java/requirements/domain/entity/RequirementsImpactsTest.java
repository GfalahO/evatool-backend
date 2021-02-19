package requirements.domain.entity;

import com.evatool.requirements.entity.RequirementsImpacts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static requirements.common.TestDataGenerator.getRequirementsImpacts;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementsImpactsTest {

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpacts.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpacts.setTitle(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {3,2,4})
    public void testSetValue_NullValue_ThrowException(int value) {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpacts.setValue(value));
    }

}
