Feature: [SUC:01-01] Submit Registration Application	Individual - Register Taxpayer@[SUC:01-01]

  @[SUC:01-01] @Test3
  Scenario Outline: UAT_TCS 01.01.4: To verify the process of Registering an individual successfully with mandatory fields
    Given User navigates to the login page
    When Enter the username "<username>" and password "<password>"
    Then click on login
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max      | 0 |
      | Last Name         | Testdata | 1 |
      | CategoryValue     | Employee | 2 |
      | Title Value       | MR       | 3 |
      | Gender            | M        | 4 |
      | MothersMaidenName | hjuytgh  | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 0001s13                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhkdg                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | acciao68             | 1 |
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
      | ContactDetails           | margiewambui11@gmail.com                                                   | 35 14 |
    And enters attachment details "Passport"  with number "0000001468" and path "C:\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
#  Then wait for webpage to load

#  Change names and atttachment numbers after each run

    Examples:
      | username  | password | DOB      | DOI        | IED        | ESD        | ARN                                           |
      | tripsuser | Passw0rd | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN |

  @[SUC:01-01] @Test3
  Scenario Outline: Individual - [SUC:01-02] Approve Taxpayer [SUC:01-02] Approve Taxpayer (UAT_TCS 02.01.1)To verify the process of Approving Taxpayer Registration
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Approve    | Read     |
      | First Name | Approved |
       
    