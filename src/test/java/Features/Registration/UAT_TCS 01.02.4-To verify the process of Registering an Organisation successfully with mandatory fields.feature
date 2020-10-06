Feature: [SUC:01-01] Submit - Application	Organisation - Register Taxpayer

  @[SUC:01-01] @Test2
Scenario Outline:UAT_TCS 01.02.4-To verify the process of Registering an Organisation successfully with mandatory fields
  Given User navigates to the login page
  When Enter the username "<username>" and password "<password>"
  Then click on login
  When I Fill the Organization Taxpayer Registration form
  And I enter valid data on the pages of Organization
    | CategoryValue |Co-operative Society (Other)|0|
    | Organization Name |A&F|1|
    | RGD Number |kujP|2|
    | DOE | 12092018|3|
    | DOC|12092020|4|
    | Source of Capital |Home Loan|5|
    | Place Of Incorporation |ALBANIA|6|
    | ReasonForTin Value |Exporting goods|7|
    | Business Sector Value |0112 - Growing of rice|8|
    | Address Submodule|Addresses|9|
    | AddressValue|Local Postal Address|10|
    | SName|United States|11|
    | City|United States|12|
    | ProvisionValue|Karonga|30 13|
    | ReogonValue|Northern Region|31 14|
    | Contact Method|Contact Methods|15|
    |Purpose Value |Business|16|
    |ContactTypeValue |Email|17|
    |ContactDetails |v-bakam@microsoft.com|18|
    |EndYearMonth |February|19|
    |EndYeadDay |01|20|
  And Enter Attachment Tab details
    | Attachments|Attachments|19 0|
    | Attachment Date|21082016|20 1|
    |Attachment Pasport|Business Registration Certificate|21 2|
    | Reference number|uK|22 3|
    |File upload |C:\Users\barnaby.kamau\Desktop\id_doc.png|23 4|
    | Attachments|Doccument | 24 5|
    | Attachments|Certificate of Incorporation| 25 6|
    | Attachments|Letter Of Authorization| 26 7|
    | Attachment Reference number|80E| 27 8|
    | Attachment Reference number|01F| 28 9|
  And enters director "P0017167" and "startDate"
  Then Click On Organization Page Submit Button
And  Verify the ARN number "<ARN>"

  Examples:
    | username   |password |ARN|
    | tripsuser  |Passw0rd |Processing Completed - Reference Number - ARN|

@[SUC:01-01] @Test2
Scenario Outline: UAT_TCS 02.02.1-To verify the process of Approving Taxpayer Registration
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
#  Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
#  And Click on NextStage button
#  Then switch to frame
#  Then wait for duplicate check
#  And Click on NextStage button
#  Then switch to frame
  And Select Approval outcome dropdown value to Approve <Approve>
  Then Click on Save button
  And Verify the String "<Read>"

  Examples:
    | Approve |Read |
    |Approve|Approved|
    
