package requirements.domain.repository;

import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static requirements.common.TestDataGenerator.getRequirementsImpacts;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementsImpactsRepositoryTest {

    @Autowired
    private RequirementsImpactsRepository requirementsImpactsRepository;


    @Test
    public void testFindById_InsertedImpact_ReturnImpact() {
        // given
        RequirementsImpacts impact = getRequirementsImpacts();
        requirementsImpactsRepository.save(impact);

        // when
        RequirementsImpacts found = requirementsImpactsRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(impact.getId());
    }

    @Test
    public void testSave_InsertedImpact_IdIsNotNull() {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();

        // when
        requirementsImpactsRepository.save(requirementsImpacts);

        // then
        assertThat(requirementsImpacts.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedImpact_IdIsUuid() {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();

        // when
        requirementsImpactsRepository.save(requirementsImpacts);

        // then
        UUID.fromString(requirementsImpacts.getId().toString());
    }

    @Test
    public void testDelete_DeletedImpact_ReturnNull() {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();
        requirementsImpactsRepository.save(requirementsImpacts);

        // when
        requirementsImpactsRepository.delete(requirementsImpacts);
        var found = requirementsImpactsRepository.findById(requirementsImpacts.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
