package com.uk.cartaxcheck.steps;

import com.uk.cartaxcheck.helpers.ApplicationHelper;
import com.uk.cartaxcheck.helpers.CarDetailsEnum;
import com.uk.cartaxcheck.model.VehicleDetailsDto;
import com.uk.cartaxcheck.model.VehicleDetailsPredicates;
import com.uk.cartaxcheck.pages.CarTacCheckPageObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class CarTaxCheckSteps {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CarTacCheckPageObjects carTaxCheckPageObjects;

    @Autowired
    private ApplicationHelper applicationHelper;

    @Autowired
    private List<VehicleDetailsDto> listVehicleDetailsDto;

    @Autowired
    private VehicleDetailsPredicates vehicleDetailsPredicates;

    private List<String> carRegValues;

    @Given("user is on the cartaxcheck.co.uk website")
    public void userIsOnTheCarTaxCheckCoUkWebsite() {
        carTaxCheckPageObjects.verifyCarRegInputTextVisible();
    }

    @And("user get all the UK car registration numbers from the file")
    public void getAllTheUKCarRegistrationNumbersFromTheFile() throws IOException {
        carRegValues = applicationHelper.getAllInputCarNumbersFromInputFile();
    }

    @Then("user get the details of the car registration numbers in carcheck home page")
    public void userVerifyTheDetailsOfTheCarRegistrationNumberInCarCheckHomePage()  {
        carRegValues.forEach(carNumber -> {
            searchCarRegistrationDetails(carNumber);
            getCarDetails();
            carTaxCheckPageObjects.navigateToCarCheckHomePage();
        });
    }

    @And("user compare the details with the output file")
    public void userCompareTheDetailsWithTheOutputFile() throws Exception {
        List<VehicleDetailsDto> filterVehicleDetails =  vehicleDetailsPredicates.filterNullValues(listVehicleDetailsDto);
        filterVehicleDetails.forEach(p -> System.out.println("Car details after filter   " +p.getCarRegistration()));

        //expected output from the output file
        List<VehicleDetailsDto> expectedOutput = applicationHelper.getAllOutputCarNumberFromOutputFile();

        if(vehicleDetailsPredicates.filterMissedValues(expectedOutput,filterVehicleDetails).size() > 0)
            throw new Exception("Vehicle details missing in the cartaxcheck.com");
    }


    //Internal methods

    //This method search for registration number
    private void searchCarRegistrationDetails(String carRegistrationNumber){
        carTaxCheckPageObjects.verifyCarRegInputTextVisible();
        carTaxCheckPageObjects.typeInputCarRegistration(carRegistrationNumber);
        carTaxCheckPageObjects.clickFreeCarCheckBtn();
    }

    //Method get Vehicle Identity details from cartaxcheck and store the details to list
    private void getCarDetails() {
        VehicleDetailsDto vehicleDetailsDto = new VehicleDetailsDto();
            if (getVehicleIdentityDetails(CarDetailsEnum.REGISTRATION.toString()).length() > 0) {
                vehicleDetailsDto.setCarRegistration(getVehicleIdentityDetails(CarDetailsEnum.REGISTRATION.toString()));
                vehicleDetailsDto.setCarMake(getVehicleIdentityDetails(CarDetailsEnum.MAKE.toString()));
                vehicleDetailsDto.setCarModel(getVehicleIdentityDetails(CarDetailsEnum.MODEL.toString()));
                vehicleDetailsDto.setCarColor(getVehicleIdentityDetails(CarDetailsEnum.COLOUR.toString()));
                vehicleDetailsDto.setCarYear(getVehicleIdentityDetails(CarDetailsEnum.YEAR.toString()));
            }
        listVehicleDetailsDto.add(vehicleDetailsDto);
    }

    private String getVehicleIdentityDetails(String detail){
        return carTaxCheckPageObjects.getVehicleIdentityDetailsFromCarTaxCheckPage(detail);
    }


}
