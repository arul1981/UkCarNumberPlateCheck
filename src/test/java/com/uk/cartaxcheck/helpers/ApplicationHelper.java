package com.uk.cartaxcheck.helpers;

import com.uk.cartaxcheck.model.VehicleDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ApplicationHelper {

    private static final String carRegistrationRegExp = "[A-Z]{2}[0-9]{2} [A-Z]{3}[a-zA-Z]*|[A-Z]{2}[0-9]{2}[A-Z]{3}[a-zA-Z]*";

    public List<String> getAllMatchesInList() throws IOException {
        String fileLocation = "\\src\\test\\resources\\input\\car_input.txt";
        String iFile = System.getProperty("user.dir")+fileLocation;
        List<String> values = new ArrayList<>();

        Pattern atPattern = Pattern.compile(carRegistrationRegExp);
        BufferedReader br = new BufferedReader(new FileReader(iFile));
        String line;
        while((line = br.readLine()) != null) {
            Matcher atMatcher = atPattern.matcher(line);
            while(atMatcher.find()){
                values.add(atMatcher.group());
            }
        }
        values.forEach(System.out::println);
        return values;
    }

    public List<VehicleDetailsDto> getAllOutputFileDetails() throws IOException {
        String fileLocation = "\\src\\test\\resources\\input\\car_output.txt";
        String iFile = System.getProperty("user.dir")+fileLocation;
        BufferedReader br = new BufferedReader(new FileReader(iFile));
        VehicleDetailsDto vehicleDetailsDto = null;
        List<VehicleDetailsDto> outputFileCarDetails = new ArrayList<VehicleDetailsDto>();
        String line;
        int i = 0;
        while((line = br.readLine()) != null){
            String[] carDetails = line.split(",");
            vehicleDetailsDto = new VehicleDetailsDto();
            vehicleDetailsDto.setCarRegistration(carDetails[0]);
            vehicleDetailsDto.setCarMake(carDetails[1]);
            vehicleDetailsDto.setCarModel(carDetails[2]);
            vehicleDetailsDto.setCarColor(carDetails[3]);
            vehicleDetailsDto.setCarYear(carDetails[4]);
            if(i >= 1)
                outputFileCarDetails.add(i-1,vehicleDetailsDto);
            i++;
        }
        return outputFileCarDetails;
    }

}
