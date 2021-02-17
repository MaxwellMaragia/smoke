Feature: [SUC:03-07]-Perform Credit Allocation

 @Accountin
 Scenario Outline: UAT_M3_07-03-To verify the Credit Allocation Process (Suspense Account to Tax Type Account-Partially Cleared)
  Given User navigates to the login page
  When Enters the username "tripsuser" and password "Passw0rd" to login
  And Click on Taxpayer accounting > Manage Credit Allocation
  Then Click add button
  Then Click find button
  Then Shift focus to modal
  Then enter tin "<Tin>" and click search
  Then select tax type "<TaxType>"
  Then Click suspense radio button under unallocated credit balance
  Then Select transaction with document "<DocumentType>" under unallocated credit
  Then Click suspense radio button under outstanding liability
  Then Select transaction with document "<DocumentType>" under outstanding liability
  Then populate allocated amount field
  Then Click submit
  Then Confirm saved success message "<SuccessMessage>"
  Then Obtain reference number "<SuccessMessage>"
  Then Open CRM and close modal
  Then Click on accounting application link
  Then switch to frame
  Then search for reference number
  Then Click on reference number
  Then approve transaction
  Then Click save CRM
  Then Application Account Adjustment status should be "<Status>"
  Examples:
   | Tin      | TaxType            | DocumentType       | SuccessMessage       | Status   |
   | P0020797 | Fringe Benefit Tax | Account Adjustment | Processing Completed | Approved |

