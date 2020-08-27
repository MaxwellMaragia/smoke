Feature: [SUC:22-10] Print Exemption Documents

  @[finder1]
  Scenario Outline: UAT_M7_10-01-UAT_M7_10-02-Verify the Process of Print Taxpayer Documents
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>New Exemptions Applications
    When user inputs <Category> and <TIN> and <exemptionCode> to fill form
    And user Clicks on Add under ButtonExemption Qualification Attribute
    Then Exemption Application Qualification Attributes pop up should be displayed
    When user  Click on Add under Attachment Schedule
    Then Exemption Application Attachment pop up should be displayed
    When attachment added "C:\\test.png"
    Then attachment popup closed
    When User adds Declaration Info <applicant> <designation> <supervisorTIN>
    Then Exemption created successfully

    Examples:
      | TIN      | Category | applicant | designation | supervisorTIN | exemptionCode |
      | P0018531 | Gift Tax | tripsuser | supervisor  | P0019254      | 0005          |

  @[finder1]
  Scenario: UAT_M7_10-02-CRM approve the Exemption Application(CRM)
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    Then Home Page Should be Displayed with User's Queue
    When Click on Case management dropdown
    And click on Exemption application
    Then switch to frame
    And enters Exemption reference number in search results
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on exemption
    Then Application Account Adjustment status should be "Approved"

  @[finder2]
  Scenario Outline: UAT_M7_10-02-Apply for Exemption(Trips+)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>New Exemptions Applications
    When user inputs <Category> and <TIN> and <exemptionCode> to fill form
    And user Clicks on Add under ButtonExemption Qualification Attribute
    Then Exemption Application Qualification Attributes pop up should be displayed
    When user  Click on Add under Attachment Schedule
    Then Exemption Application Attachment pop up should be displayed
    When attachment added "C:\\test.png"
    Then attachment popup closed
    When User adds Declaration Info <applicant> <designation> <supervisorTIN>
    Then Exemption created successfully

    Examples:
      | TIN      | Category | applicant | designation | supervisorTIN | exemptionCode |
      | P0018531 | Gift Tax | tripsuser | supervisor  | P0019254      | 0005          |

  @[finder2]
  Scenario Outline: UAT_M7_10-02-CRM Reject the Exemption Application(CRM)
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    Then Home Page Should be Displayed with User's Queue
    When Click on Case management dropdown
    And click on Exemption application
    Then switch to frame
    And enters Exemption reference number in search results
    When Click selected Reference Number
    And clicks Decline from the dropdown
    Then Enter Outcome Notes "<Notes>"
    And Enter Outcome Reason for Taxpayer accounting
    And click save on exemption
    Then Application Account Adjustment status should be "Rejected"

    Examples:
      | Notes                 | Reason               |
      | Invalid Documentation | EXEMPTION_APP_REJECT |

  @[finder1]
  Scenario Outline: UAT_M7_10-02Apply for Exemption Cancellation(Trips+)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>Cancel Exemptions
    When user fills <TIN> on Find Exemption Application
    Then Exemptions Applications window displayed with <TIN> and <exemptionCategory>and <ECRType>and <applicationStatus> in read only mode
    When User enters <reason> and <notes> on Exemption Application under Cancellation Details section
    Then The System saves the Exemption application


    Examples:
      | TIN | exemptionCategory | ECRType | applicationStatus | reason             | notes           |
      | %   | Exemption         | %       | Active            | No Longer Eligible | invalid details |

  @[finder1]
  Scenario: UAT_M7_10-02-CRM Approve the Exemption Cancellation(Trips+)
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    Then Home Page Should be Displayed with User's Queue
    When Click on Case management dropdown
    And click on Exemption application
    Then switch to frame
    And enters Exemption reference number in search results
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on exemption
    Then Application Account Adjustment status should be "Approved"

  @[finder1]
  Scenario Outline: UAT_M7_10-02-Apply for Exemption Suspension(Trips+)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>suspend Exemptions
    When user fills <TIN> on Find Exemption Application
    Then Exemptions Applications window displayed with <TIN> and <exemptionCategory>and <ECRType>and <applicationStatus> in read only mode
    When User enters <reason> and <submissionType> data
    And clicks submit
    Then The System saves the Exemption application

    Examples:
      | TIN | exemptionCategory | ECRType | applicationStatus | reason              | submissionType |
      | %   | Exemption         | %       | Active            | Under Investigation | SuspendReason  |

  @[finder1]
  Scenario Outline: UAT_M7_10-02-Apply for Exemption Reactivation(Trips+)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>Reactivate Exemptions
    When user fills <TIN> on Find Exemption Application
    Then Exemptions Applications window displayed with <TIN> and <exemptionCategory>and <ECRType>and <applicationStatus> in read only mode
    When User enters <reason> and <submissionType> data
    And clicks submit
    Then The System saves the Exemption application

    Examples:
      | TIN | exemptionCategory | ECRType | applicationStatus | reason                 | submissionType   |
      | %   | Exemption         | %       | Active            | Investigation Complete | reactivateReason |

  @[finder1]-
  Scenario Outline: UAT_M7_10-03-Verify the Process of Service Call Fails
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>New Exemptions Applications
    When user inputs <Category> and <TIN> and <exemptionCode> to fill form
    And user Clicks on Add under ButtonExemption Qualification Attribute
    Then Exemption Application Qualification Attributes pop up should be displayed
    When user  Click on Add under Attachment Schedule
    Then Exemption Application Attachment pop up should be displayed
    When attachment added "C:\\test.png"
    Then attachment popup closed
    When User adds Declaration Info <applicant> <designation> <supervisorTIN>
    Then Exemption created successfully

    Examples:
      | TIN      | Category | applicant | designation | supervisorTIN | exemptionCode |
      | P0018531 | Gift Tax | tripsuser | supervisor  | P0019254      | 0005          |

  @[finder1]
  Scenario: UAT_M7_10-03-CRM approve the Exemption Application(CRM)
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    Then Home Page Should be Displayed with User's Queue
    When Click on Case management dropdown
    And click on Exemption application
    Then switch to frame
    And enters Exemption reference number in search results
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on exemption
    Then Application Account Adjustment status should be "Approved"

  @[finder1-]
  Scenario Outline: UAT_M7_10-03-Apply for Exemption Cancellation(Trips+)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given User navigates to Exemptions Applications>>Cancel Exemptions
    When user fills <TIN> on Find Exemption Application
    Then Exemptions Applications window displayed with <TIN> and <exemptionCategory>and <ECRType>and <applicationStatus> in read only mode
    When User enters <reason> and <notes> on Exemption Application under Cancellation Details section
    Then The System saves the Exemption application


    Examples:
      | TIN | exemptionCategory | ECRType | applicationStatus | reason             | notes           |
      | %   | Exemption         | %       | Active            | No Longer Eligible | invalid details |

  @[finder1]
  Scenario: UAT_M7_10-03-CRM Approve the Exemption Cancellation(CRM)
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    Then Home Page Should be Displayed with User's Queue
    When Click on Case management dropdown
    And click on Exemption application
    Then switch to frame
    And enters Exemption reference number in search results
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on exemption
    Then Application Account Adjustment status should be "Approved"
 