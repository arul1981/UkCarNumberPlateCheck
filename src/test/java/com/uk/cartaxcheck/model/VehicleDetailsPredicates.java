package com.uk.cartaxcheck.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class VehicleDetailsPredicates {

    public List<VehicleDetailsDto> filterNullValues(List<VehicleDetailsDto> filterVehicleDetails){
        return filterVehicleDetails.stream().filter(vehicleDetailsDto -> vehicleDetailsDto.getCarRegistration() != null
                || vehicleDetailsDto.getCarYear() != null
                || vehicleDetailsDto.getCarModel() != null
                || vehicleDetailsDto.getCarColor() != null
                || vehicleDetailsDto.getCarMake() != null).collect(Collectors.toList());
    }

    /*public List<VehicleDetailsDto> filterMissingValue(List<VehicleDetailsDto> listOne, List<VehicleDetailsDto> listTwo){
        return listOne.stream().filter
                (e -> (listTwo.stream().noneMatch(d -> d.equals(e)))).collect(Collectors.toList());
    }*/

    public List<VehicleDetailsDto> filterMissedValues(List<VehicleDetailsDto> listOne, List<VehicleDetailsDto> listTwo){
        List<VehicleDetailsDto> matchedValues = new ArrayList<>(listOne);
        matchedValues.removeAll(listTwo);
        matchedValues.retainAll(listOne);
        matchedValues.forEach(p -> System.out.println("Missing car details in cartaxcheck   " +p.getCarRegistration()));
        return matchedValues;
    }

}
