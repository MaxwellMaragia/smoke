Feature: [SUC:09-06]-Process Tax Return

  @SUC:09-06 @UAT_M4-06-01 @UAT_M4-02-11 @UAT_M4-06-08
  Scenario Outline: UAT_M4-06-01-Verify the Process of Process Tax Return for <ReturnDocument>
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "<Tin>" and period number as "<PeriodNumber>" and year as "<PeriodYear>"
    Then Click search
#    Then Click table column in submit returns "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
#    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields "<ReturnDocument>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"
    Examples:
      | ReturnDocument  | Tin      | PeriodNumber | PeriodYear |
      | PAYE Tax Return | P0020797 | 1            | 2020       |


