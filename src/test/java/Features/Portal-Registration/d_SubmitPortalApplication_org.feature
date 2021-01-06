Feature: [SUC:01-12] Submit Portal Application(Organisation)

  Background:
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bab05@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to organisation portal

  @SUC:01-12 @UAT_TCS-03.04.2
  Scenario: UAT_TCS-03.04.2 To Verify all Fields in Update Portal Application Screens
    Given user clicks organization details
    And user enters organisation details
      | category         | Association    |
      | OrgName          | Windows 10          |
      | Preferred Office | Balaka         |
      | TINreason        | Am an employer |
      | DOI              | 01/01/2007     |
      | place            | malawi         |
      | capital source   | Research       |
    Given user clicks next for more details
    And clicks new on business sector
    And Fill business sector details and click next
      | BusinessSector | 011 - Growing of non-perennial crops |
    And click next on business sector
    And Fill in contact details and click next
      | ContactPurpose | Business                |
      | ContactDetail  | baze@codeisystems.co.ke |
    And Add address details and click next
      | AddressType | Local Business Address |
      | Town        | Lilongwe               |
      | Region      | Central Region         |
      | District    | Lilongwe               |
    And Fill directors details and click next
      | DirectorsTin  | P0020448 |
      | DirectorsName | Baze     |
    And Fill in attachment details and click next
      | DocumentType | Constitution  |
      | DocNumber    | 3235524766      |
      | Attachment   | C:\id_doc.png |
    And Click Submit: xpath "//*[@id='id_OrgForm']/form-wizard/div/div/div[2]/div[3]/button"
    Then Success message is displayed "Your application has been submitted successfully."