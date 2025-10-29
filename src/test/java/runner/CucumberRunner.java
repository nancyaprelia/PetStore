package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"runner", "steps"},
        tags = "@UserFeature or @StoreFeature or @PetFeature",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CucumberRunner {}
