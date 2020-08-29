Feature: [SUC:09-01]-Lodge Paper Return

  @SUC:09-01 @UAT_M4-01-02 @UAT_M4-01-03 @UAT_M4-08-01 @UAT_M4-08-03
  Scenario Outline: UAT_M4-01-02-Verify the process of Lodge Paper Return (FOF)
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    Then Search for document with by filling Tin as "" Return document as "<ReturnDocument>" Period number as "" and Period year as ""
    Then Click search button "SearchForm:j_idt42"
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
    Then Click continue "SearchForm:j_id14"
    Then Enter liability as "9000.00" and date of lodgement as ""
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
#    Then Switch to frame
#    Then Close print modal
    Then Verify save success message "Returns Lodgement is Successfull with Reference Number"
    Examples:
      | ReturnDocument      |
      | Domestic VAT Return |
      #Returns Lodgement is Late


  @SUC:09-01 @UAT_M4-01-04
  Scenario: UAT_M4-01-04-Verify the process of lodgement failure
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    #Then Search for document with by filling Tin as "" Return document as "PAYE Tax Return" Period number as "1" and Period year as "2018"
    Then Click search button "SearchForm:j_idt42"
    Then Verify error message "At least one field is required"
    Then Search for document with by filling Tin as "" Return document as "Domestic VAT Return " Period number as "" and Period year as ""
    Then Click search button "SearchForm:j_idt42"
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
    Then Click continue "SearchForm:j_id14"
    Then Enter liability as "-9000.00" and date of lodgement as ""
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify error message "Liability cannot be negative for the selected Return Type"





