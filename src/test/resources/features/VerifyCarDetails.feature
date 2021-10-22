Feature: As a user, I am able to read the given input file and get the car registration numbers
  so that I can retrieve details from cartaxcheck.co.uk and compare with the output file.


  Scenario: User successfully verify the car registration details in cartaxcheck.co.uk and compare the
  results with the output file

    Given user is on the cartaxcheck.co.uk website
    When user get all the UK car registration numbers from the file
    Then user get the details of the car registration numbers in carcheck home page
      | Registration |
      | Make         |
      | Model        |
      | Colour       |
      | Year         |
    And user compare the details with the output file