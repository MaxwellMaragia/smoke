Feature: [SUC:01-11] Create Applicant Portal Credential

  Background:
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And clicks register now
    And create portal credential page is displayed

  @SUC:01-11
  Scenario:UAT_TCS 03.02.2 To Verify the process of Validating Mandatory Details
    Given user clicks applicant submit button with fields blank
    Then submit button should not be clickable

  @SUC:01-11 @UAT_TCS-03.02.4
  Scenario Outline:UAT_TCS 03.02.4 To verify the process of successfully creating portal credentials
    Given user inputs identification details
      | FirstName     | Bakam      |
      | LastName      | Mende      |
      | DOB           | 01/01/1990 |
      | POB           | Lilongwe   |
      | Id            | 6111111    |
      | Date of issue | 01/01/1998 |
      | Expiry Date   | 01/01/2030 |
      | country       | malawi     |
    When user selects correct <Taxpayer Registration Type> and <identification type>
    And clicks create portal credential validate id button
    Then successful validation message appears
    Given user enters valid <email> and <password>
    And attaches id document <path>
    And clicks the submit button
    Then registration is successful
    Examples:
    #    change email after each run
      | Taxpayer Registration Type | identification type | email                | password     | path                                  |
      | Organisation               | NID Card            | bab02@mailinator.com | Passw0rd@123 | C:\id_doc.png |