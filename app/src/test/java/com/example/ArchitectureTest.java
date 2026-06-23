package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ArchitectureTest {


    ApplicationModules modules = ApplicationModules.of(Application.class);
    @Test
    void verifyModularStructure() {
        // Enforces module isolation and detects illegal cyclic dependencies
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        // Generates architectural documentation and component diagrams automatically
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();
    }
}