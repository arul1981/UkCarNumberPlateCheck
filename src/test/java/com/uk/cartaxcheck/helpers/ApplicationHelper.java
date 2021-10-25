package com.uk.cartaxcheck.helpers;

import com.uk.cartaxcheck.model.VehicleDetailsDto;
import com.uk.cartaxcheck.services.impl.InputOutputFileReaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ApplicationHelper {

    @Autowired
    private InputOutputFileReaderImpl inputOutputFileReader;

    public List<String> getAllInputCarNumbersFromInputFile() throws IOException {
        return inputOutputFileReader.getMatchValuesFromInputFile();
    }

    public List<VehicleDetailsDto> getAllOutputCarNumberFromOutputFile() throws IOException {
        return inputOutputFileReader.getExpDetailsFromOutputFile();
    }

}
