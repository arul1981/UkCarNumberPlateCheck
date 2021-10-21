package com.uk.cartaxcheck.steps;

import com.uk.cartaxcheck.pages.BasePage;
import com.uk.cartaxcheck.runners.Hook;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class BaseSteps {

    public static final Logger logger = LoggerFactory.getLogger(BaseSteps.class);

    @Autowired
    private List<BasePage> pages;

    @Autowired
    private Hook hook;

    @Before(order = 1)
    public void logBeforeScenario(final Scenario scenario) {
        logger.debug(StringUtils.rightPad("Starting scenario:", 20) + "[{}] - [{}]",
                getFeatureName(scenario),
                scenario.getName());
    }

    @Before(order = 2)
    public void initializeDriver() {
        final WebDriver driver = hook.getDriver();
        // Intialize all page elements
        pages.stream().forEach(p -> {
            initElements(driver, p);
        });
        // --
    }

    @After(order = 1)
    public void closeDriver(final Scenario scenario) {
        hook.tearDown(scenario);
        hook.closeDriver();
    }

    @After(order = Integer.MAX_VALUE)
    public void logAfterScenario(final Scenario scenario) {
        logger.debug(StringUtils.rightPad("Finished scenario:", 20) + "[{}] - [{}] [{}]",
                getFeatureName(scenario),
                scenario.getName(),
                scenario.getStatus());
    }

    private String getFeatureName(Scenario scenario) {
        String feature = scenario.getId();
        feature = StringUtils.substringBeforeLast(feature,".feature");
        feature = StringUtils.substringAfterLast(feature,"/");
        return feature;
    }
}
