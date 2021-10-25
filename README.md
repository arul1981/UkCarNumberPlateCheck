# UkCarNumberPlateCheck

Selenium/Java and Spring

This project repo consists of base template using Selenium, Java and Spring. Additionally a sample project which follow the below steps

UKCarNumberPlateCheck project test steps
1. Reads given input file: car_input.txt
2. Extracts vehicle registration numbers based on pattern(s).
3. Each number extracted from input file is fed to https://cartaxcheck.co.uk/
(Peform Free Car Check)
4. Compare the output returned by https://cartaxcheck.co.uk/ with given car_output.txt
5. Highlight/fail the test for any mismatch

Project Requirements

JDK 8
Maven 3.6

Test Execution
Download or clone the repository
Open a terminal
From the project root directory run: 
mvn clean verify
