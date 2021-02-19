package requirements.domain.repository;

import com.evatool.requirements.entity.RequirementsVariants;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static requirements.common.TestDataGenerator.getRequirementsVariants;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VariantsRepositoryTest {

    @Autowired
    private RequirementsVariantsRepository requirementsVariantsRepository;


    @Test
    public void testFindById_InsertedVariants_ReturnImpact() {
        // given
        RequirementsVariants requirementsVariants = getRequirementsVariants();
        requirementsVariantsRepository.save(requirementsVariants);

        // when
        RequirementsVariants requirementsVariantsFound = requirementsVariantsRepository.findById(requirementsVariants.getId()).orElse(null);

        // then
        assertThat(requirementsVariantsFound.getId()).isEqualTo(requirementsVariants.getId());
    }

    @Test
    public void testSave_InsertedVariants_IdIsNotNull() {
        // given
        RequirementsVariants requirementsVariants = getRequirementsVariants();

        // when
        requirementsVariantsRepository.save(requirementsVariants);

        // then
        assertThat(requirementsVariants.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedVariants_IdIsUuid() {
        // given
        RequirementsVariants requirementsVariants = getRequirementsVariants();

        // when
        requirementsVariantsRepository.save(requirementsVariants);

        // then
        UUID.fromString(requirementsVariants.getId().toString());
    }

    @Test
    public void testDelete_DeletedVariants_ReturnNull() {
        // given
        RequirementsVariants requirementsVariants = getRequirementsVariants();
        requirementsVariantsRepository.save(requirementsVariants);

        // when
        requirementsVariantsRepository.delete(requirementsVariants);
        RequirementsVariants requirementsVariantsFound = requirementsVariantsRepository.findById(requirementsVariants.getId()).orElse(null);

        // then
        assertThat(requirementsVariantsFound).isNull();
    }
}
