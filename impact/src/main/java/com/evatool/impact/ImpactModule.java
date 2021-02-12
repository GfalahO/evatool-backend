package com.evatool.impact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Features:
// TODO [hbuhl & tzaika] Events

// Tests:
// TODO [tzaika] Implement Impact API Tests
// TODO [hbuhl] ErrorMessage tests in RestController?
// TODO [hbuhl & tzaika] Event Tests

// Team:
// TODO [hbuhl & tzaika] use logger: Logger logger = LoggerFactory.getLogger(MyClass.class);
// TODO [hbuhl & tzaika] Use UUID class in Entity?
// TODO [hbuhl & tzaika] Keep id in DTO? (Rest Level 3 HATEOAS)
// TODO [hbuhl] Use poc-way of creating and inserting Rest Level 3 links
// TODO [hbuhl & tzaika] Test if real events are asynchronous
// TODO [tzaika] ImpactRest with Swagger
// TODO [tzaika] use new wireframe in impact wiki
// TODO [hbuhl & tzaika] GitHub Issues in backend repo
// TODO [hbuhl & tzaika] Clean dependencies and put in root pom
// TODO [hbuhl & tzaika] add swagger API documentation (@Api, @ApiOperation, @ApiResponses, @ApiModel, @ApiModelProperty, @ApiParam)
// TODO [hbuhl] use strings in request mappings for readability
// TODO [hbuhl & tzaika] Auth - @RolesAllowed
// TODO [hbuhl] Mandantenfähigkeit? (just for fun)
// TODO Install SonarLint IDE plugin

// SIG:
// TODO [hbuhl & tzaika] Build Tool Chain (bis 19.02.2021)
// TODO [hbuhl] Redo Event Testing (after async test behaviour has been validated in real application)

@SpringBootApplication
public class ImpactModule {
    public static void main(String[] args) {
        SpringApplication.run(ImpactModule.class, args);
    }
}
