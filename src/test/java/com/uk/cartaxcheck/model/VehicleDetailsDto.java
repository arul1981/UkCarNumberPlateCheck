package com.uk.cartaxcheck.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VehicleDetailsDto {

    private String carRegistration;

    private String carModel;

    private String carMake;

    private String carColor;

    private String carYear;

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleDetailsDto that = (VehicleDetailsDto) o;
        return carRegistration.equals(that.carRegistration) &&
                carModel.equals(that.carModel) && carMake.equals(that.carMake) &&
                carColor.equals(that.carColor) && carYear.equals(that.carYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carRegistration, carModel, carMake, carColor, carYear);
    }
}
