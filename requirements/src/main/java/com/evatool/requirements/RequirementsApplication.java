package com.evatool.requirements;

import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.entity.RequirementsVariants;
import com.evatool.requirements.repository.InpactsRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementGRRepository;
import com.evatool.requirements.repository.ScenarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RequirementsApplication {

    Logger logger = LoggerFactory.getLogger(RequirementsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RequirementsApplication.class, args);
    }

   @Bean
    public CommandLineRunner initData(ScenarioRepository scenarioRepository,
                                      RequirementRepository requirementRepository,
                                      InpactsRepository inpactsRepository,
                                      RequirementGRRepository requirement_grRepository) {
        return (args) -> {
            logger.info("Begin start Requirement Service");
            RequirementsVariants home = new RequirementsVariants("Home-Office","If people only work from home.");
            RequirementsVariants office = new RequirementsVariants("Office","If people only work in the office.");
            scenarioRepository.save(home);
            scenarioRepository.save(office);



            Requirement requirement1 = new Requirement("Documentation","Documentation for the Software");
            requirement1.getVariants().add(home);
            requirement1.getVariants().add(office);
            requirementRepository.save(requirement1);

            Requirement requirement2 = new Requirement("Data encoding","Privacy data show encoding (not visible at home)");
            requirement2.getVariants().add(office);
            requirementRepository.save(requirement2);

            RequirementsImpacts requirementsImpacts1 = new RequirementsImpacts("Inpact titel", "Inpact description",-1, RequirementsImpacts.Dimension.PRIVAT);
            inpactsRepository.save(requirementsImpacts1);

            RequirementGR requirement_gr1 = new RequirementGR(requirementsImpacts1,requirement1,1);
            RequirementGR requirement_gr2 = new RequirementGR(requirementsImpacts1,requirement2,1);

            requirement_grRepository.save(requirement_gr1);
            requirement_grRepository.save(requirement_gr2);
            logger.info("Sucessfull start Requirement Service");
        };
    }
}
