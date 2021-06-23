Feature: [SUC:01-01] Submit Registration Application	Organisation - Register Taxpayer
#  Modify email and Paths  on line 36 attachments and 61 only
  @main @Org @MRA @MRA-ORG @MRA-ORG-REG
  Scenario Outline:UAT_TCS 01.02.4	To verify the process of Registering an Organisation successfully with mandatory fields
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Organization Taxpayer Registration form
    And I enter valid data on the pages of Organization
      | CategoryValue          | Co-operative Society (Other) | 0     |
      | Organization Name      | A&A                          | 1     |
      | RGD Number             | kuji                         | 2     |
      | DOE                    | 12092018                     | 3     |
      | DOC                    | 12092020                     | 4     |
      | Source of Capital      | Home Loan                    | 5     |
      | Place Of Incorporation | ALBANIA                      | 6     |
      | ReasonForTin Value     | Exporting goods              | 7     |
      | Business Sector Value  | 0112 - Growing of rice       | 8     |
      | Address Submodule      | Addresses                    | 9     |
      | AddressValue           | Local Postal Address         | 10    |
      | SName                  | United States                | 11    |
      | City                   | United States                | 12    |
      | ProvisionValue         | Karonga                      | 30 13 |
      | ReogonValue            | Northern Region              | 31 14 |
      | Contact Method         | Contact Methods              | 15    |
      | Purpose Value          | Business                     | 16    |
      | ContactTypeValue       | Email                        | 17    |
      | ContactDetails         | margiewambui11@gmail.com     | 18    |
      | EndYearMonth           | February                     | 19    |
      | EndYeadDay             | 01                           | 20    |
    And Enter Attachment Tab details
      | Attachments                 | Attachments                       | 19 0 |
      | Attachment Date             | 21082016                          | 20 1 |
      | Attachment Pasport          | Business Registration Certificate | 21 2 |
      | Reference number            | ug                                | 22 3 |
      | File upload                 | C:\id_doc.png                     | 23 4 |
      | Attachments                 | Doccument                         | 24 5 |
      | Attachments                 | Certificate of Incorporation      | 25 6 |
      | Attachments                 | Letter Of Authorization           | 26 7 |
      | Attachment Reference number | 78a                               | 27 8 |
    And enters director "P0017167" and "startDate"
    Then Click On Organization Page Submit Button
    And  Verify the ARN number "<ARN>"
    Given Open CRM URL Module
    And Close Popup Window
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame1
    And Click on NextStage button
    Then switch to frame1
    Then wait for duplicate check <Approve>
    Then switch to frame1
    And Click on NextStage button
    Then switch to frame1
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\Users\maxma\Downloads"
    Then switch to frame1
    And Select Approval outcome Org dropdown value to Approve
    Then Click on Save button
    Then switch to frame1
    And Verify the String "<Read>"
    And Clicks on Taxpayer name CRM
    And refresh page
    Then switch to frame0
    Then Taxpayer Tin is displayed

    Examples:
      | Approve           | Read     | ARN                                           |
      | Organisation Name | Approved | Processing Completed - Reference Number - ARN |
