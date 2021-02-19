package requirements.domain.repository;

import requirements.common.TestDataGenerator;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.repository.RequirementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static requirements.common.TestDataGenerator.getRequirement;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementRepositoryTest {
    @Autowired
    private RequirementRepository requirementRepository;

    @Test
    public void testFindById_ExistingRequirement_ReturnRequirement() {
        // given
        Requirement requirement = TestDataGenerator.getRequirement();
        requirementRepository.save(requirement);

        // when
        Requirement requirementFound = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(requirementFound.getId()).isEqualTo(requirement.getId());
    }

    @Test
    public void testSave_InsertedRequirement_IdIsNotNull() {
        // given
        Requirement requirement = getRequirement();

        // when
        requirementRepository.save(requirement);

        // then
        assertThat(requirement.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedRequirement_IdIsUuid() {
        // given
        Requirement requirement = getRequirement();

        // when
        requirementRepository.save(requirement);

        // then
        UUID.fromString(requirement.getId().toString());
    }

    @Test
    public void testDelete_DeletedRequirement_ReturnNull() {
        // given
        Requirement requirement = getRequirement();
        requirementRepository.save(requirement);

        // when
        requirementRepository.delete(requirement);
        Requirement requirementFound = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(requirementFound).isNull();
    }
}
