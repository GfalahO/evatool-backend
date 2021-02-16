package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO [hbuhl] Redo Event Testing (after async test behaviour has been validated in real application) [Config in root?]
// TODO [hbuhl & tzaika] Build Tool Chain (bis 19.02.2021)

// Features:
// TODO [hbuhl & tzaika] Events
// TODO [tzaika] Logging
// TODO [tzaika] add swagger API documentation (@Api, @ApiOperation, @ApiResponses, @ApiModel?, @ApiModelProperty?, @ApiParam)

// Tests:
// TODO [tzaika] Implement Impact API Tests
// TODO [hbuhl & tzaika] Event Tests (wait until 19.02.2021)

// TODO [hbuhl & tzaika] GitHub Issues in backend repo: https://github.com/EVATool/evatool-backend/labels/Team%20Impact
// TODO [tzaika] use new wireframe in impact wiki

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
