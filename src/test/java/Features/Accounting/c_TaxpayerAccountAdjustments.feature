Feature: [SUC:03-06] Perform Taxpayer Account Adjustments

  @Accounting
  Scenario Outline: UAT_M3_06-03-verify the process of Creating Adjustments by Revenue Officer and  Approving
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Given user navigates to Taxpayer Accounting
    When click Taxpayer Account Adjustment
    And user Clicks on Add button
    When User Clicks on Find Button
    Then Taxpayer Account Adjustment Details Search Screen should be displayed
    And enter Tin number <TIN> and click search
    And select charge type <chargetype>
    And select adjustment type <adjtype>
    Then give reason value <reason>
    Then enter revenue ledger code <code> and amount <amount>
    And click on submit button
    Then Credit reference number will generate <RefNo>
    Given Open CRM URL for Accounting Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Accounting application
    Then switch to frame
    When enters reference number in search results
    Then switch to frame
    When Click selected Reference Number
    And clicks Approve from the dropdown
    And click save on accounting
    Then Application Account Adjustment status should be "Approved"
    Examples:
      | TIN      | chargetype | adjtype | reason                            | code      | amount   | RefNo                                   |
      | V0021665 | Liability  | Credit  | MISCELLANEOUS ADJUSTMENT - CREDIT | 111110001 | 67587678 | Processing Completed - Reference Number |

