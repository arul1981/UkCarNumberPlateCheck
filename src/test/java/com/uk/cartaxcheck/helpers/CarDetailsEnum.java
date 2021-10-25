package com.uk.cartaxcheck.helpers;

public enum CarDetailsEnum {
    REGISTRATION("Registration"),
    MAKE("Make"),
    MODEL("Model"),
    COLOUR("Colour"),
    YEAR("Year");

    public final String label;

    CarDetailsEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
