package com.uk.cartaxcheck.pages;

import com.uk.cartaxcheck.helpers.VisibilityHelper;
import com.uk.cartaxcheck.runners.Hook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarTacCheckPageObjects implements BasePage {

    @Autowired
    private VisibilityHelper visibilityHelper;

    @Autowired
    private Hook hooks;

    @FindBy(how = How.ID, using = "vrm-input")
    private WebElement inputCarRegistration;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Free Car Check')]")
    private WebElement freeCarCheckBtn;

    public void verifyCarRegInputTextVisible(){
        visibilityHelper.waitForVisibilityOf(inputCarRegistration);
    }

    public void typeInputCarRegistration(String carRegNumber){
        visibilityHelper.waitForVisibilityOf(inputCarRegistration);
        inputCarRegistration.sendKeys(carRegNumber);
    }

    public void clickFreeCarCheckBtn(){
        visibilityHelper.waitForVisibilityOf(freeCarCheckBtn);
        freeCarCheckBtn.click();
    }

    public String getVehicleIdentityDetailsFromCarTaxCheckPage(String vehicleIdentityText){
        return getVehicleDetailsWebElement(vehicleIdentityText).getText();
    }

    public void navigateToCarCheckHomePage(){
        hooks.getDriver().navigate().to("http://cartaxcheck.co.uk");
        visibilityHelper.waitForVisibilityOf(freeCarCheckBtn);
    }

    private WebElement getVehicleDetailsWebElement(String vehicleIdentityText){
        String value = String.format("//*[contains(text(),'Vehicle Identity')]//following::*[contains(text(),'%s')]//following-sibling::*",vehicleIdentityText);
        By xpath = By.xpath(value);
        visibilityHelper.waitForPresenceOf(xpath);
        return hooks.getDriver().findElement(xpath);
    }

}
