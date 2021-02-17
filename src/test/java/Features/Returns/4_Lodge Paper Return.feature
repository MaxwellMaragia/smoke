Feature: [SUC:09-01]-Lodge Paper Return

  #Change return document, tin, period number and period year
  #If you enter specific tin, comment out line 15 and 16 before running
  @Returns
  Scenario Outline: UAT_M4-01-02-Verify the process of Lodge Paper Return for <ReturnDocument>
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    Then Search for document with by filling Tin as "<Tin>" Return document as "<ReturnDocument>" Period number as "<PeriodNumber>" and Period year as "<Year>"
    Then Click search button "SearchForm:j_idt42"
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
    Then Click continue "SearchForm:j_id14"
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify error message "Liability - Value required."
    Then Enter liability as "9000.00" and date of lodgement as ""
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify save success message "Returns Lodgement is Successfull with Reference Number"
    Examples:
      | ReturnDocument  | PeriodNumber | Year | Tin |
      | PAYE Tax Return | 2            | 2020 | %   |

