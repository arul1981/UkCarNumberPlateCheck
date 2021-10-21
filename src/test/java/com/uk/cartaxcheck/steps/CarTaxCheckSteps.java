package com.uk.cartaxcheck.steps;

import com.uk.cartaxcheck.helpers.ApplicationHelper;
import com.uk.cartaxcheck.model.VehicleDetailsDto;
import com.uk.cartaxcheck.pages.CarTacCheckPageObjects;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class CarTaxCheckSteps {

    @Autowired
    private CarTacCheckPageObjects carTaxCheckPageObjects;

    @Autowired
    private ApplicationHelper applicationHelper;

    @Autowired
    private List<VehicleDetailsDto> listVehicleDetailsDto;

    private List<String> carRegValues;

    @Given("user is on the cartaxcheck.co.uk website")
    public void userIsOnTheCarTaxCheckCoUkWebsite() {
        carTaxCheckPageObjects.verifyCarRegInputTextVisible();
    }

    @And("user get all the UK car registration numbers from the file")
    public void getAllTheUKCarRegistrationNumbersFromTheFile() throws IOException {
        carRegValues = applicationHelper.getAllMatchesInList();
    }

    @Then("user get the details of the car registration numbers in carcheck home page")
    public void userVerifyTheDetailsOfTheCarRegistrationNumberInCarCheckHomePage(DataTable dataTable) {
        List<String> requiredDetails = dataTable.asList();
        for(String carNumber: carRegValues){
            searchCarRegistrationDetails(carNumber);
            getCarDetails(requiredDetails);
            carTaxCheckPageObjects.navigateToCarCheckHomePage();
        }
    }

    private void searchCarRegistrationDetails(String carRegistrationNumber){
        carTaxCheckPageObjects.verifyCarRegInputTextVisible();
        carTaxCheckPageObjects.typeInputCarRegistration(carRegistrationNumber);
        carTaxCheckPageObjects.clickFreeCarCheckBtn();
    }

    private void getCarDetails(List<String> requiredDetails){
        VehicleDetailsDto vehicleDetailsDto = new VehicleDetailsDto();
        for(String carDetail: requiredDetails) {
            switch (carDetail){
                case "Registration":
                    vehicleDetailsDto.setCarRegistration(carTaxCheckPageObjects.getVehicleIdentityDetailsFromCarTaxCheckPage(carDetail));
                    break;
                case "Make":
                    vehicleDetailsDto.setCarMake(carTaxCheckPageObjects.getVehicleIdentityDetailsFromCarTaxCheckPage(carDetail));
                    break;
                case "Model":
                    vehicleDetailsDto.setCarModel(carTaxCheckPageObjects.getVehicleIdentityDetailsFromCarTaxCheckPage(carDetail));
                    break;
                case "Colour":
                    vehicleDetailsDto.setCarColor(carTaxCheckPageObjects.getVehicleIdentityDetailsFromCarTaxCheckPage(carDetail));
                    break;
                case "Year":
                    vehicleDetailsDto.setCarYear(carTaxCheckPageObjects.getVehicleIdentityDetailsFromCarTaxCheckPage(carDetail));
                    break;
            }
        }
        listVehicleDetailsDto.add(vehicleDetailsDto);
    }


    @And("user compare the details with the output file")
    public void userCompareTheDetailsWithTheOutputFile() {
        for(VehicleDetailsDto vehicleDetailsDto:listVehicleDetailsDto){
            System.out.println(vehicleDetailsDto.getCarRegistration() + "  " + vehicleDetailsDto.getCarColor() + "  "+
                    vehicleDetailsDto.getCarMake() + "  " + vehicleDetailsDto.getCarModel() + "  "+ vehicleDetailsDto.getCarYear());
        }
    }

}