package com.example.mvvm.datalayer.model.bdd

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["."], // The package where your feature files are located
    glue = ["."], // The package where your step definitions are located
    plugin = ["pretty", "html:reports/test-report"] // Report plugins
)
class CucumberTestRunner