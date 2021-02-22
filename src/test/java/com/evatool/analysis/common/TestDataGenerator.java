package com.evatool.analysis.common;

import com.evatool.analysis.enums.StakeholderLevel;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.model.User;

public class TestDataGenerator {

    public static Analysis getAnalysis() {
        return new Analysis("AnalysisName","descriptionTitle");
    }

    public static Stakeholder getStakholder() {
        return new Stakeholder("StakeholderName", Integer.valueOf(10), StakeholderLevel.naturalPerson);
    }

    public static User getUser() {
        return new User("UserName","1234", "TestMail@gamil.com");
    }

}
