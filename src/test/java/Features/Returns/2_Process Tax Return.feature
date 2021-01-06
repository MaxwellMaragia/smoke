Feature: [SUC:09-06]-Process Tax Return

  @SUC:09-06 @UAT_M4-06-01 @UAT_M4-02-11
  Scenario Outline: UAT_M4-06-01-Verify the Process of Process Tax Return
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields "<ReturnDocument>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"
    Examples:
      | ReturnDocument                  |
      | Domestic VAT Return             |

  @SUC:09-06 @UAT_M4-06-08
  Scenario: UAT_M4-06-08-Verify the Process of Portal Submission
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "Domestic VAT Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as "2020"
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields name as "MR PAYE RETURNS", designation as "Software developer", declaration date as "01/01/2018"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"


