Feature: [SUC:22-01] Generate Taxpayer Account Structure

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in

  @SUC:22-01  @Exemptions
  Scenario Outline: UAT_M7_01-03-Verify the Process of  Application Data Error
    Given User navigates to Exemptions Applications>>New Exemptions Applications
    When user inputs <Category> and <TIN> and <exemptionCode> to fill form
    When User adds Declaration Info <applicant> <designation> <supervisorTIN>
    Then Exemption error displayed

    Examples:
      | TIN      | Category | applicant | designation | supervisorTIN | exemptionCode |
      | C0021695  | Gift Tax | tripsuser | supervisor  | P0019254      | EXD1          |

  @SUC:22-01  @Exemptions
  Scenario Outline: UAT_M7_01-01-Verify the fields in Submit Exemption Application
    Given User navigates to Exemptions Applications>>New Exemptions Applications
    When user inputs <Category> and <TIN> and <exemptionCode> to fill form
    And user Clicks on Add under ButtonExemption Qualification Attribute
    Then Exemption Application Qualification Attributes pop up should be displayed
    When user  Click on Add under Attachment Schedule
    Then Exemption Application Attachment pop up should be displayed
    When attachment added "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    Then attachment popup closed
    Examples:
      | TIN      | Category | exemptionCode |
      | C0021695  | Gift Tax | EXD1          |

  @SUC:22-01 @Exemptions--
  Scenario Outline: UAT_M7_01-02-Verify the Process of Submit Exemption Application
    Given User navigates to Exemptions Applications>>New Exemptions Applications
    When user inputs <Category> and <TIN> and <exemptionCode> to fill form
    And user Clicks on Add under ButtonExemption Qualification Attribute
    Then Exemption Application Qualification Attributes pop up should be displayed
    When user  Click on Add under Attachment Schedule
    Then Exemption Application Attachment pop up should be displayed
    When attachment added "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    Then attachment popup closed
    When User adds Declaration Info <applicant> <designation> <supervisorTIN>
    Then Exemption created successfully

    Examples:
      | TIN      | Category | applicant | designation | supervisorTIN | exemptionCode |
      | C0021695  | Gift Tax | tripsuser | supervisor  | P0019254      | 0005          |

