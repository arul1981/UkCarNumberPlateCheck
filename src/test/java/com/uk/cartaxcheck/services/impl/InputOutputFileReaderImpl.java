package com.uk.cartaxcheck.services.impl;

import com.uk.cartaxcheck.model.VehicleDetailsDto;
import com.uk.cartaxcheck.services.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InputOutputFileReaderImpl implements FileUtil {

    @Value("${input.file.location}")
    private String inputFileLocation;

    @Value("${output.file.location}")
    private String outputFileLocation;

    @Autowired
    private UKCarNumberCheckPatternMatchImpl ukCarNumberCheckPatternMatch;

    @Autowired
    private InputOutputFileReaderImpl inputOutputFileReader;

    public List<String> getMatchValuesFromInputFile() throws IOException {
        List<String> matchValues = new ArrayList<>();
        BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFileLocation));
        String line;
        while((line = inputFileReader.readLine()) != null) {
            ukCarNumberCheckPatternMatch.getMatcherGroup(matchValues,line);
        }
        matchValues.forEach(System.out::println);
        return matchValues;
    }

    public List<VehicleDetailsDto> getExpDetailsFromOutputFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(outputFileLocation));
        VehicleDetailsDto vehicleDetailsDto;
        List<VehicleDetailsDto> outputFileCarDetails = new ArrayList<>();
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
