Feature: Print Registration Reports_TaxType

  Background:
    Given Open trips URL
    And Login as Revenue Officer
      | tripsuser | Passw0rd |
    Then Click reporting > reports

  @UAT_TCS-01.32.1
  Scenario: UAT_TCS 01.32.1-To Verify the Process of printing Tax Type Reports - Reactivated Tax Type List
    Then Select report to print "Reactivated Tax Type List"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Reactivated Tax Type List.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Reactivated Tax Type List.xls" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @UAT_TCS-01.27.1
  Scenario: UAT_TCS 01.27.1-To Verify the Process of printing Taxpayer Reports - Taxpayer Registrations Summary of Transactions
    Then Select report to print "Taxpayer Registrations Summary of Transactions"
    Then Select report file type "PDF"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Registrations Summary of Transactions.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Registrations Summary of Transactions.xls" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"
