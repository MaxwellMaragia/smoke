Feature: [SUC:09-09] Print Return Reports

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click reporting > reports

  @SUC:09-09 @UAT_M4-09-01
  Scenario: UAT_M4-09-01 Verify the Process of Print Reports- Amended Returns Report
    Then Select report to print "Amended Returns Report"
    Then Enter start date as "01/08/2020"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Amended Returns Report.pdf" has been downloaded in downloads directory "C:\\Users\\v-maxmar\\Downloads"

