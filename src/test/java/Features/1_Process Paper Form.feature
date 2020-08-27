Feature: [SUC:09-02]-Process Paper Form

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return

  @SUC:09-02 @UAT_M4-02-11
  Scenario: UAT_M4-02-11-Verify the Process Paper Form-VAT Return
    Then Select return document as "Domestic VAT Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as "2019"
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[1]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in Transaction details
    Then Click add "FlexibleFormEntity:TIOnInpVatLocPur_Table:j_id1"
    Then Switch to frame
    Then Enter transaction information for local purchases: date "01/07/2020", inv number "123", VAT "100000"
    Then Click ok
    Then Fill in declaration fields name as "DR HAMISI", designation as "Software developer", declaration date as "01/01/2018"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"

