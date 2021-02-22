package com.evatool.analysis.dto;

import com.evatool.analysis.enums.StakeholderLevel;
import com.evatool.analysis.model.AnalysisImpacts;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class StakeholderDTO {
    private UUID rootEntityID;
    private String stakeholderName;
    private StakeholderLevel stakeholderLevel;
    private Integer priority;
    private AnalysisImpacts impacts;
    private UUID impactsUUID;
    private Map<UUID, String> impactsTitles = new HashMap<>();

}
