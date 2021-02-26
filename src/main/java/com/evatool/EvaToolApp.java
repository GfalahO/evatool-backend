package com.evatool;

import com.evatool.requirements.entity.*;
import com.evatool.requirements.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class EvaToolApp {
    public static void main(String[] args) {
        SpringApplication.run(EvaToolApp.class, args);
    }
    @Bean
    public CommandLineRunner initData(RequirementsVariantsRepository requirementsVariantsRepository,
                                      RequirementRepository requirementRepository,
                                      RequirementsImpactsRepository requirementsImpactsRepository,
                                      RequirementPointRepository requirementPointRepository,
                                      RequirementAnalysisRepository requirementAnalysisRepository,
                                      RequirementDimensionRepository requirementDimensionRepository) {
        return (args) -> {
            RequirementsAnalysis requirementsAnalysis = new RequirementsAnalysis();
            requirementAnalysisRepository.save(requirementsAnalysis);

            RequirementsVariant home = new RequirementsVariant("Home-Office","If people only work from home.");
            RequirementsVariant office = new RequirementsVariant("Office","If people only work in the office.");
            requirementsVariantsRepository.save(home);
            requirementsVariantsRepository.save(office);

            Requirement requirement1 = new Requirement("Documentation","Documentation for the Software",requirementsAnalysis, Arrays.asList(home,office));
            requirementRepository.save(requirement1);

            Requirement requirement2 = new Requirement("Data encoding","Privacy data show encoding (not visible at home)",requirementsAnalysis, Arrays.asList(office));
            requirementRepository.save(requirement2);

            RequirementDimension requirementDimension = new RequirementDimension("Privat");
            requirementDimensionRepository.save(requirementDimension);

            RequirementsImpact requirementsImpact1 = new RequirementsImpact("Impact titel", "Impact description",-1,requirementDimension);
            requirementsImpactsRepository.save(requirementsImpact1);

            RequirementPoint requirement_gr1 = new RequirementPoint(requirementsImpact1,requirement1,1);
            RequirementPoint requirement_gr2 = new RequirementPoint(requirementsImpact1,requirement2,1);

            requirementPointRepository.save(requirement_gr1);
            requirementPointRepository.save(requirement_gr2);
        };
    }
}
