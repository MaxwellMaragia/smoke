Feature: [SUC:01-01] Submit Registration Application	Individual - Register Taxpayer
#  Modify email and Paths  on line 52 attachments and 74 only
  @[SUC:01-01] @UAT_TCS-01.01.4 @MRA @MRA-INDV
  Scenario Outline: UAT_TCS 01.01.4: To verify the process of Registering an individual successfully with mandatory fields
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Yes      | 0 |
      | Last Name         | Bana     | 1 |
      | CategoryValue     | Employee | 2 |
      | Title Value       | MR       | 3 |
      | Gender            | M        | 4 |
      | MothersMaidenName | Maria  | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 0000n                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhb                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | accoac             | 1 |
      | Register Ind        | Employment Details | 2 |
      | Employment Position | Senior Executive   | 3 |
      | Employer's Name     | SiddharthReddy     | 4 |
    And Enter Employee details "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | barnaby.kamau@technobraingroup.com                                      | 35 14 |

    And enters attachment details "Passport"  with number "00000007" and path "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"


    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN |

  @main @Indv @MRA @MRA-INDV
  Scenario Outline: Individual - [SUC:01-02] Approve Taxpayer [SUC:01-02] Approve Taxpayer (UAT_TCS 02.01.1)To verify the process of Approving Taxpayer Registration
    Given Open CRM URL Module
    And Close Popup Window
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame1
    And Click on NextStage button
    Then switch to frame1
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\Users\barnaby.kamau\Downloads"
    Then switch to frame1
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame1
    Then wait for duplicate check <Approve>
    Then switch to frame1
    And Click on NextStage button
    Then switch to frame1
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    Then switch to frame1
    And Verify the String "<Read>"
    And Clicks on Taxpayer name CRM
    And refresh page
    Then switch to frame1
    Then Taxpayer Tin is displayed

    Examples:
      | Approve    | Read     |
      | First Name | Approved |