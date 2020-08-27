package Steps;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.Authenticator;
import io.cucumber.java.After;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class steps extends BaseClass {

    @Before(order = 0)
    public void method1() throws Exception {
        // this.S=S;
        Pro = new Properties();
        FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\Objects\\object.properties");
        Pro.load(fip);
    }

    @Given("^Open trips URL$")
    public void loadTripsLink() throws Throwable {
        driver = BaseClass.getDriver();
        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
        driver.manage().window().maximize();
    }

    @Given("^Open portal URL$")
    public void loadPortalLink() throws Throwable {
        driver = BaseClass.getDriver();
        driver.get(Pro.getProperty("PORTAL_URL"));
        driver.manage().window().maximize();
    }

    @Then("^Login as Revenue Officer$")
    public void login_as_revenue_officer(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("BackOffice_UserName_ID")))).clear();
        driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(obj.get(0).get(0));
        driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
    }

    //login to taxpayer portal
    @Then("^Login to portal$")
    public void portalLogin(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("USERNAME_ID")))).sendKeys(obj.get(0).get(0));
        driver.findElement(By.id(Pro.getProperty("PASSWORD_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("LOGIN_ID"))).click();
    }

    @And("^Click returns filing and processing > adjust return$")
    public void OpenAdjustReturnsLink() {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]/a")).click();
    }

    @And("^Click on return filing and processing > File return$")
    public void OpenFileReturnsLink() {
        BaseClass.waitForPageToLoad();
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[3]")).click();
    }

    @And("^Click returns filing and processing > cancel return$")
    public void OpenCancelReturnsLink() {
        BaseClass.waitForPageToLoad();
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]/a")).click();
    }

    @And("^Click on return filing and processing > Lodge return$")
    public void OpenLodgeReturnsLink() {
        BaseClass.waitForPageToLoad();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[2]")).click();
    }

    @And("^Click returns filing and processing > paye credit$")
    public void OpenPayeCreditsLink() {
        BaseClass.waitForPageToLoad();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[6]/a")).click();
    }

    @And("^Click reporting > reports$")
    public void OpenReportsLink() {
        BaseClass.waitForPageToLoad();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    //Verify precense of elements using id
    @Then("^Verify fields dropdown \"([^\"]*)\" and next button \"([^\"]*)\" and cancel button \"([^\"]*)\"$")
    public void verifyFieldsInReturnDocumentsSelection(String Dropdown, String NextButton, String CancelButton) {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {Dropdown, NextButton, CancelButton};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.assertFalse("Field with ID '" + input_field + "' not found", false);
            }
        }
    }

    @Then("^Select return document as \"([^\"]*)\"$")
    public void select_return_document(String returnDocument) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + returnDocument + "')]")).click();
    }

    @Then("^Click next \"([^\"]*)\"$")
    public void clickNext(String NextButtonId) {
        driver.findElement(By.id(NextButtonId)).click();
    }

    @Then("^Verify presence of fields tin \"([^\"]*)\" and period number \"([^\"]*)\" and period year \"([^\"]*)\" and search button \"([^\"]*)\" and cancel button \"([^\"]*)\" and continue button \"([^\"]*)\" in find return page$")
    public void verify_presence_of_fields_in_find_returns_page(String Tin, String periodNumber, String periodYear, String search, String cancel, String Continue) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:periodnumber"))).isDisplayed();
        String Input_fields[] = {periodNumber, Tin, periodYear, Continue, search, cancel};
        for (int i = 0; i < Input_fields.length; i++) {
            WebElement form_element = driver.findElement(By.id(Input_fields[i]));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
    }

    @Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_table_columns_in_find_returns_page(String tin, String taxPayerName, String returnType, String period, String documentReference, String submissionDate) throws Throwable {
        String columns[] = {tin, taxPayerName, returnType, period, documentReference, submissionDate};
        BaseClass.waitForPageToLoad();
        //check if columns exist
        for (String column : columns) {
            String TableRowXpath = "//span[contains(text(),'" + column + "')]";
            WebElement Table_Column = driver.findElement(By.xpath(TableRowXpath));
            if (Table_Column.isDisplayed()) {
                System.out.println("Column : " + column + " found ");
                Assert.assertTrue(true);
                Thread.sleep(2000);
            } else {
                Assert.assertFalse("Column : " + column + " not found", false);
                System.out.println("Column : " + column + " not found ");
            }
        }
    }

    @Then("^Enter tin as \"([^\"]*)\" and period number as \"([^\"]*)\" and year as \"([^\"]*)\"$")
    public void enter_tin_and_period_number_and_year(String Tin, String Number, String Year) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:tin"))).sendKeys(Tin);
        driver.findElement(By.id("SearchForm:periodnumber")).sendKeys(Number);
        driver.findElement(By.id("SearchForm:periodyear")).sendKeys(Year);
    }

    @Then("^Click search$")
    public void click_search() {
        driver.findElement(By.id("SearchForm:j_idt42")).click();
    }

    @Then("^Click table column \"([^\"]*)\"$")
    public void click_table_column(String ColumnXpath) throws Throwable {
        Thread.sleep(6000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ColumnXpath))).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
    }

    @Then("^Click continue \"([^\"]*)\"$")
    public void click_continue(String continueID) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(continueID)).click();
    }

    //verify if fields are present and readonly
    @Then("^Verify readonly fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_readonly_fields(String tinField, String taxPayerNameField, String tradingNameField, String postalAddressField, String emailAddressField, String mobileNumberField, String periodFromField, String periodToField) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:TIN"))).isDisplayed();
        String[] Input_fields = {tinField, taxPayerNameField, tradingNameField, postalAddressField, emailAddressField, mobileNumberField, periodFromField, periodToField};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
                if (form_element.isEnabled()) {
                    Assert.assertFalse("'" + input_field + "' is not read only", false);
                }
            } else {
                Assert.assertFalse("'" + input_field + "' is not displayed", false);
            }
        }
    }

    @Then("^Verify existence of buttons \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in paye tax return$")
    public void verify_existence_of_buttons_in_paye_tax_return(String add, String update, String view, String remove, String upload, String downloadTemplate) throws Throwable {
        BaseClass.waitForPageToLoad();
        String[] Input_fields = {add, update, view, remove, upload, downloadTemplate};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Button '" + input_field + "' not found");
            }
        }
    }

    @Then("^Verify error message \"([^\"]*)\"$")
    public void verify_error_message(String error) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + error + "')]")));
        if (errorMessage.isDisplayed()) {
            System.out.println("Error message ('" + error + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Verify no data is found in table$")
    public void verify_no_data_is_found_in_table() throws Throwable {
        WebElement noDataXpath = driver.findElement(By.xpath("//td[contains(text(),'No records found.')]"));
        if (noDataXpath.isDisplayed()) {
            Assert.assertTrue("No data found in table", true);
        } else {

            Assert.assertFalse("Data found in table", false);
        }
    }

    //--------------------Portal-----------------------------------------------------------------------------------------------------------------//

    @Then("^Click on my tax$")
    public void click_my_tax_button() throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("My_Tax_Button_ID")))).click();
    }

    @Then("^Click submit returns$")
    public void click_submit_returns() throws Throwable {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("SubmitReturn_ID")))).click();
    }

    @Then("^Select taxtype \"([^\"]*)\"$")
    public void selectTaxType(String taxTypeXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(taxTypeXpath))).click();
    }

    @Then("^Select period \"([^\"]*)\"$")
    public void selectPeriod(String periodXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(periodXpath))).click();
    }

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in vat return page$")
    public void verify_input_fields_in_vat_return_page(String period, String valueOfSales, String vatOnImportedServices, String vatOnDisposalOfCapitalItems, String valueOfExportSupplies, String valueOfZeroRatedSupplies, String valueOfExemptSupplies, String totalVat, String cancel) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_period"))).isDisplayed();
        String Input_fields[] = {period, valueOfSales, vatOnImportedServices, vatOnDisposalOfCapitalItems, valueOfExemptSupplies, valueOfExportSupplies, valueOfZeroRatedSupplies, totalVat, cancel};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue("Element with id '" + input_field + "' has been found", true);
            } else {
                Assert.fail();
            }
        }
    }

    @Then("^Fill in fields under VAT Returns with the double \"([^\"]*)\"$")
    public void fill_in_fields_under_vat_returns_with_the_double(String Amount) throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement valueOneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_value1")));
        if (valueOneField.isEnabled()) {
            valueOneField.sendKeys(Amount);
            driver.findElement(By.id("id_vat2")).sendKeys(Amount);
            driver.findElement(By.id("id_vat3")).sendKeys(Amount);
            driver.findElement(By.id("id_value4")).sendKeys(Amount);
            driver.findElement(By.id("id_value5")).sendKeys(Amount);
            driver.findElement(By.id("id_value6")).sendKeys(Amount);
        }
    }

    @Then("^Set returns to nill$")
    public void set_returns_to_nill() throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement valueOneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_value1")));
        if (valueOneField.isEnabled()) {
            driver.findElement(By.xpath("//*[@id=\"id_outputVATForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]")).click();
        }
    }

    @Then("^Confirm and certify information$")
    public void confirm_and_certify_information() throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_vatReturnSummaryForm\"]/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"))).click();
    }

    @Then("^Click next button \"([^\"]*)\"$")
    public void click_next_button(String nextXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextXpath))).click();
    }

    @Then("^Verify download and upload buttons with id \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_download_and_upload_buttons_with_id(String download, String upload) throws Throwable {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {download, upload};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue("Button with id '" + input_field + "' has been found", true);

            } else {
                Assert.assertFalse("Button with id '" + input_field + "' has not been found", false);
            }
        }
    }

    @Then("^Click Cancel \"([^\"]*)\"$")
    public void click_cancel(String cancelID) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cancelID))).click();
    }

    @Then("^Click ok in confirm dialogue$")
    public void click_ok_in_confirm_dialogue() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/trips-app/p-confirmdialog/div/div[3]/button[1]"))).click();
        Thread.sleep(3000);
    }

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in paye$")
    public void verify_input_fields_in_paye(String period, String nillCheckbox, String totalGrossIncome, String totalPaye, String printButton, String canceButton, String submitButton) {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {period, nillCheckbox, totalGrossIncome, totalPaye, printButton, canceButton, submitButton};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue("Element with id '" + input_field + "' has been found", true);

            } else {
                Assert.fail();
            }
        }
    }

    @Then("^fill in VAT return fields with value \"([^\"]*)\"$")
    public void fill_in_vat_return_fields_with_value(String amount) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_value8"))).sendKeys(amount);
        driver.findElement(By.id("id_value9")).sendKeys(amount);
        driver.findElement(By.id("id_value10")).sendKeys(amount);
        driver.findElement(By.id("id_vat11")).sendKeys(amount);
        driver.findElement(By.id("id_vat13")).sendKeys(amount);
        driver.findElement(By.id("id_value14")).sendKeys(amount);
        driver.findElement(By.id("id_value15")).sendKeys(amount);
        driver.findElement(By.id("id_value18")).sendKeys(amount);
        driver.findElement(By.id("id_vat18")).sendKeys(amount);
    }

    @Then("^Select nill return \"([^\"]*)\"$")
    public void select_nill_return(String nillXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nillXpath))).click();
    }

    @Then("^Confirm checkbox \"([^\"]*)\"$")
    public void confirm_checkbox(String checkboxXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkboxXpath))).click();
    }

    @And("^Click Submit: xpath \"([^\"]*)\"$")
    public void click_submit_xpath(String submitXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(submitXpath))).click();
    }

    @And("^Click Save as draft: xpath \"([^\"]*)\"$")
    public void click_save_as_draft_xpath(String draftXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(draftXpath))).click();
    }

    @Then("^Click Save with errors$")
    public void saveWithErrors() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:SAVE_WITH_ERROR"))).click();
    }

    @Then("^Modify PAYE calculations if not nil$")
    public void modifyTaxCalculations() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 60);
        String totalReturns = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:TotalPaye_input"))).getText();

        if (totalReturns != "0.00") {
            //change to nill
            driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:NilReturn\"]/div[2]/span")).click();
        }
    }

    @Then("^Verify save with errors button is displayed$")
    public void verifySaveWithErrorsButton() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement saveWithErrorsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:SAVE_WITH_ERROR")));

        if (saveWithErrorsButton.isDisplayed()) {
            System.out.println("Save with errors button displayed");
        } else {
            Assert.assertFalse("Save with errors button not visible", false);
        }
    }

    @Then("^Verify success message \"([^\"]*)\"$")
    public void verify_status_change(String Status) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + Status + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Status + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();

        }
    }

    @And("^Verify save button is disabled \"([^\"]*)\"$")
    public void verify_save_button_is_disabled(String saveButtonID) throws Throwable {
        Thread.sleep(2000);
        WebElement saveButton = driver.findElement(By.id(saveButtonID));
        if (saveButton.isEnabled()) {
            Assert.fail();
        } else {
            Assert.assertTrue("Save button is disabled", true);
        }
    }

    //------------------------------------------------Process tax return------------------------------------------------//

    @Then("^Fill in declaration fields name as \"([^\"]*)\", designation as \"([^\"]*)\", declaration date as \"([^\"]*)\"$")
    public void fill_in_declaration_fields(String name, String designation, String date) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:DeclarantName"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:DeclarantName"))).sendKeys(name);
        driver.findElement(By.id("FlexibleFormEntity:DeclarantPosition")).clear();
        driver.findElement(By.id("FlexibleFormEntity:DeclarantPosition")).sendKeys(designation);

        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + Pro.getProperty("DeclarationDate_Input_ID") + "').setAttribute('value', '" + date + "')");
    }

    @Then("^Fill in declaration fields \"([^\"]*)\"$")
    public void fill_in_declaration_fields(String returnDocument) throws Throwable {

        String declarantNameLocator = "";
        String declarantPositionLocator = "";
        String declarationDateLocator = "";

        if (returnDocument.equals("Capital Gain Tax(CGT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("Company Income Tax(CIT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("Dividend Tax Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("Domestic Excise Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("Domestic VAT Return")) {
            declarantNameLocator = "FlexibleFormEntity:DeclarantName";
            declarantPositionLocator = "FlexibleFormEntity:DeclarantPosition";
            declarationDateLocator = "FlexibleFormEntity:DeclarationDate_input";
        }
        if (returnDocument.equals("Fringe Benefit Tax Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("Non Resident Tax(NRT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("PAYE Tax Return")) {
            declarantNameLocator = "FlexibleFormEntity:DeclarantName";
            declarantPositionLocator = "FlexibleFormEntity:DeclarantPosition";
            declarationDateLocator = "FlexibleFormEntity:DeclarationDate_input";
        }
        if (returnDocument.equals("Personal Income Tax(PIT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:DeclarantName";
            declarantPositionLocator = "FlexibleFormEntity:DeclarantPosition";
            declarationDateLocator = "FlexibleFormEntity:DeclarationDate_input";
        }
        if (returnDocument.equals("Provisional Tax(CIT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if (returnDocument.equals("Provisional Tax(PIT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:Declarant_Name";
            declarantPositionLocator = "FlexibleFormEntity:Declarant_Designation";
            declarationDateLocator = "FlexibleFormEntity:Declaration_Date_input";
        }
        if (returnDocument.equals("Turnover Tax(TOT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:DeclarationName";
            declarantPositionLocator = "FlexibleFormEntity:DeclaratDesignation";
            declarationDateLocator = "FlexibleFormEntity:DeclarationDate_input";
        }
        if (returnDocument.equals("Withholding Tax(WHT) Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarantDate_input";
        }

        WebDriverWait wait = new WebDriverWait(driver, 40);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).sendKeys("DR MARGIE WAMBUI");
        driver.findElement(By.id(declarantPositionLocator)).clear();
        driver.findElement(By.id(declarantPositionLocator)).sendKeys("Software developer");

        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + declarationDateLocator + "').setAttribute('value', '01/01/2018')");

    }

    @Then("^Verify save success message \"([^\"]*)\"$")
    public void verify_success_message(String Message) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + Message + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    //------------------------------------------------Lodge paper return---------------------------------------------------------//

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in lodge return page$")
    public void verify_input_fields_in_lodge_return_page(String tin, String taxpayername, String tradingname, String returndocument, String period, String lodgementdate, String savebutton, String cancelbutton) throws Throwable {
        BaseClass.waitForPageToLoad();
        String[] Input_fields = {tin, taxpayername, tradingname, returndocument, period, lodgementdate, savebutton, cancelbutton};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail("Element with ID '" + input_field + "' not found");
            }
        }
    }

    @Then("^Click Return document search button$")
    public void click_return_document_search_button() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnsLodgement:searchId"))).click();
    }

    @Then("^Switch to frame$")
    public void shift_focus_to_frame() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement Iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Iframe);
    }

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in document search frame$")
    public void verify_input_fields_in_document_search_frame(String tindocumentsearch, String periodnumber, String periodyear) throws Throwable {
        Thread.sleep(2000);
        String[] Input_fields = {tindocumentsearch, periodnumber, periodyear};

        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail("Element with ID '" + input_field + "' not found");
            }
        }
    }

    @Then("^Verify searchform buttons \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_searchform_buttons(String search, String cancel, String Continue) {
        String[] Input_fields = {search, cancel, Continue};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail("Button with ID '" + input_field + "' not found");
            }
        }
    }

    @Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in search document frame$")
    public void verify_table_columns_in_search_document_frame(String tincolumn, String taxpayernamecolumn, String documenttypecolumn, String periodnumbercolumn, String periodyearcolumn) throws Throwable {
        String columns[] = {tincolumn, taxpayernamecolumn, documenttypecolumn, periodnumbercolumn, periodyearcolumn};

        //check if columns exist
        for (String column : columns) {
            String TableRowXpath = "//span[contains(text(),'" + column + "')]";
            WebElement Table_Column = driver.findElement(By.xpath(TableRowXpath));
            if (Table_Column.isDisplayed()) {

                System.out.println("Column : " + column + " found ");
                Assert.assertTrue(true);
                Thread.sleep(2000);
            } else {
                Assert.assertFalse("Column : " + column + " not found", false);
                System.out.println("Column : " + column + " not found ");
            }
        }
    }

    @Then("^Search for document with by filling Tin as \"([^\"]*)\" Return document as \"([^\"]*)\" Period number as \"([^\"]*)\" and Period year as \"([^\"]*)\"$")
    public void search_for_return_document(String tin, String returnDocument, String periodNumber, String periodYear) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:ReturnType_label\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + returnDocument + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:TIN")).sendKeys(tin);
        driver.findElement(By.id("SearchForm:PeriodNumber")).sendKeys(periodNumber);
        driver.findElement(By.id("SearchForm:PeriodYear")).sendKeys(periodYear);
    }

    @Then("^Search for document with only Tin as \"([^\"]*)\"$")
    public void search_for_document_with_only_tin(String tin) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:TIN")).sendKeys(tin);
    }

    @Then("^Click search button \"([^\"]*)\"$")
    public void click_search_button(String searchID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(searchID))).click();
    }

    @Then("^Enter liability as \"([^\"]*)\" and date of lodgement as \"([^\"]*)\"$")
    public void enter_liability_and_date_of_lodgement(String liability, String date) throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.id("ReturnsLodgement:id_Liability_input")).sendKeys(liability);

        if (date.length() > 1) {
            driver.findElement(By.id("ReturnsLodgement:id_LodgementDate_input")).clear();
            driver.findElement(By.id("ReturnsLodgement:id_LodgementDate_input")).sendKeys(date);
            Thread.sleep(2000);
        }
    }

    @Then("^Close print modal$")
    public void close_print_preview() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt16:Cancel"))).click();
    }

    //-------------------------------------------------Process Paper Form--------------------------------------------------------------//

    @Then("^Click add \"([^\"]*)\"$")
    public void click_add(String addID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addID))).click();
    }

    @Then("^Find entity by Tin \"([^\"]*)\"$")
    public void find_entity_by_tin(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:tin"))).sendKeys(tin);
    }

    @Then("^Enter designation as \"([^\"]*)\" and basic salary as \"([^\"]*)\"$")
    public void enter_designation_and_basic_salary(String designation, String salary) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id("TestFlexibleForm:Designation")).sendKeys(designation);
        driver.findElement(By.id("TestFlexibleForm:BasicSalaryAndWages_input")).sendKeys(salary);
    }

    @Then("^Switch to default$")
    public void switch_to_default() throws Throwable {
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
    }

    @Then("^Switch to frame 2$")
    public void shift_focus_to_second_frame() throws Throwable {
        Thread.sleep(2000);
        driver.switchTo().frame(1);
    }

    @And("^Click ok: xpath \"([^\"]*)\"$")
    public void click_ok_xpath(String okXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(okXpath))).click();
    }

    @And("^Click ok")
    public void click_ok() throws Throwable {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='TestFlexibleForm:Save']")));
        actions.doubleClick(okButton).perform();

    }

    @Then("^Fill in Transaction details$")
    public void fillVatTransactionDetails() throws Throwable {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement totalValueSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:tdValue1_input")));
        if(totalValueSelector.isEnabled()){
            totalValueSelector.sendKeys("1000000");
        }
    }

    @Then("^Enter transaction information for local purchases: date \"([^\"]*)\", inv number \"([^\"]*)\", VAT \"([^\"]*)\"$")
    public void enter_transaction_information_for_local_purchases(String date, String invoiceNumber, String vat) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("TestFlexibleForm:DateOfInvoice_input")).sendKeys(date);
        driver.findElement(By.id("TestFlexibleForm:InvoiceNumber")).sendKeys(invoiceNumber);
        driver.findElement(By.id("TestFlexibleForm:VatOnSupplies_input")).sendKeys(vat);
    }

    @Then("^Verify numeric fields are readonly and total is zero$")
    public void verify_numeric_fields_are_readonly_and_total_is_zero() throws Throwable {
        Thread.sleep(2000);
        WebElement salesVat = driver.findElement(By.id("FlexibleFormEntity:tdVat17"));
        String total = driver.findElement(By.id("FlexibleFormEntity:TotalVatLocalPurch_input")).getText();
        if (salesVat.isEnabled()) {
            Assert.fail("Field is not read only");
        } else {
            if (total.equals("0.00")) {
                Assert.assertTrue("Total VAT is 0.00", true);
            } else {
                Assert.assertFalse("Total VAT is not 0.00", false);
            }
        }
    }

    //.....................................................amend tax returns.........................................................................//
    @Then("^Select reason for amendment as \"([^\"]*)\"$")
    public void select_reason_for_amendment(String amendmentReason) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + amendmentReason + "')]")).click();
    }

    @Then("^Click reset$")
    public void click_reset() throws Throwable {
        driver.findElement(By.id("FlexibleFormEntity:reset")).click();
    }

    @Then("^Verify name field has been reset \"([^\"]*)\"$")
    public void verify_name_field_has_been_reset(String name) throws Throwable {
        Thread.sleep(3000);
        String declarantName = driver.findElement(By.id("FlexibleFormEntity:DeclarantName")).getText();
        if (declarantName == name) {
            Assert.assertTrue("Fields reset", true);
            System.out.println("Name fields reset");
        } else {
            Assert.assertFalse("Fields not reset", false);
        }
    }

    //.......................................................cancel return.............................................................................//
    @Then("^Select reason for cancellation as \"([^\"]*)\" \"([^\"]*)\"$")
    public void select_reason_for_cancellation(String cancellationReason, String returnType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        String dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";

        if (returnType.equals("PAYE Tax Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:ReasonForCancellation\"]/div[3]";
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
    }

    @Then("^Click cancel return$")
    public void click_cancel_return() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:cancel"))).click();
    }

    @Then("^Click yes$")
    public void click_yes() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:j_idt32"))).click();
    }

    @Then("^Click no$")
    public void click_no() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:j_idt33"))).click();
    }

    @Then("^Verify switch to page with url \"([^\"]*)\"$")
    public void verify_switch_to_page_with_url(String url) throws Throwable {
        Thread.sleep(2000);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, url);
    }

    //...............................................................PAYE CREDIT...............................................//
    @Then("^Click find to search for employee$")
    public void click_find_to_search_for_employee() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FormPayeCredit:FindTin"))).click();
    }

    @Then("^Search for employee using tin \"([^\"]*)\"$")
    public void search_for_employee_using_tin_something(String tin) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
    }

    @Then("^Search for employer$")
    public void search_for_employer() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FormPayeCredit:search"))).click();
    }

    @Then("^Verify paye$")
    public void verify_paye() throws Throwable {
        Thread.sleep(4000);
        String totalPaye = driver.findElement(By.id("FormPayeCredit:totalPaye")).getText();

        if (totalPaye.equals("0.00")) {
            Assert.assertFalse("Could not search paye credit", false);
        }
    }

    //...............................................................print return reports....................................................................................................//
    @Then("^Select report to print \"([^\"]*)\"$")
    public void select_report_to_print(String reportType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + reportType + "']"))).click();

    }

    @Then("^Select tax office \"([^\"]*)\"$")
    public void select_tax_office(String taxOffice) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select business sector$")
    public void select_business_sector() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select return type$")
    public void select_return_type() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select outcome status$")
    public void select_outcome_status() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:OUTCOME_STATUS\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select taxpayer category")
    public void select_taxpayer_category() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select taxpayer type")
    public void select_taxpayer_type() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:TAXPAYER_TYPE\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select submission method")
    public void select_submission_method() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:SUBMISSION_METHOD\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Click run report \"([^\"]*)\"$")
    public void click_run_report(String buttonID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(buttonID))).click();
    }

    @Then("^Enter start date as \"([^\"]*)\"$")
    public void enter_start_date(String startDate) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmReportDetails:StartDate_input"))).sendKeys(startDate);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Then("^Verify file \"([^\"]*)\" has been downloaded in downloads directory \"([^\"]*)\"$")
    public void verify_file_has_been_downloaded_in_downloads_directory(String fileName, String downloadPath) throws Throwable {
        Thread.sleep(5000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @Then("^Enter employer tin \"([^\"]*)\"$")
    public void enter_employer_tin(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmReportDetails:TaxpayerTIN"))).sendKeys(tin);
    }

    @Then("^Enter tin nimber \"([^\"]*)\"$")
    public void enter_tin_number(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmReportDetails:TIN_NUMBER"))).sendKeys(tin);
    }



    //................portal..........................................................................//

    //Verify precense of navigation links in home page
    @Then("^Verify Home Screen Buttons$")
    public void verify_home_screen_buttons(DataTable data) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Taxpayer Notification')]")));
        List<List<String>> obj = data.asLists();
        String buttons[] = {
                obj.get(0).get(0),
                obj.get(1).get(0),
                obj.get(2).get(0),
                obj.get(3).get(0)
        };

        for (int i = 0; i < buttons.length; i++) {
            WebElement Button = driver.findElement(By.xpath("//button[contains(text(),'" + buttons[i] + "')]"));
            if (Button.isDisplayed()) {
                Assert.assertTrue("Button '" + buttons[i] + "' found", true);
            } else {
                Assert.fail("Button '" + buttons[i] + "' not found");
            }

        }


    }

    //Verify accounts table columns
    @Then("^Verify Tax Accounts Table$")
    public void verify_tax_accounts_table(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        String columns[] = {obj.get(0).get(0), obj.get(1).get(0)};

        //check if columns exist
        for (String column : columns) {
            WebElement Table_Column = driver.findElement(By.xpath("//th[contains(text(),'" + column + "')]"));


            if (Table_Column.isDisplayed()) {

                System.out.println("Column : " + column + " found ");
                Assert.assertTrue(true);
                Thread.sleep(2000);
            } else {

                Assert.fail();
                System.out.println("Column : " + column + " not found ");

            }
        }
    }

    @Then("^Click statement requests under tasks$")
    public void click_statement_requests_under_tasks() throws Throwable{
        Thread.sleep(15000);
        driver.findElement(By.id(Pro.getProperty("Statement_Request_ID"))).click();
    }

    @Then("^Click tax type dropdown and select tax type that has transactions$")
    public void click_tax_type_dropdown_and_select_tax_type_that_has_transactions() throws Throwable {
        Thread.sleep(7000);
        driver.findElement(By.xpath(Pro.getProperty("TaxTypeDropdownXPATH"))).click();

        String transactionsXpath = "//span[contains(text(),'Suspense')]";
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(transactionsXpath))).click();
    }

    @Then("^Verify statement request input fields$")
    public void verify_statement_request_input_fields(DataTable data) throws Throwable {

        Thread.sleep(3000);

        List<List<String>> obj = data.asLists();
        String[] elements = {obj.get(0).get(0), obj.get(1).get(0), obj.get(2).get(0), obj.get(3).get(0), obj.get(4).get(0)};

        for (String element : elements) {
            WebElement InputElement = driver.findElement(By.xpath(Pro.getProperty(element)));
            if (InputElement.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Element '" + element + "' not found");
            }

        }
    }

    @Then("^Click cancel button$")
    public void click_cancel_button() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Cancel_XPATH")))).click();

    }

    @Then("^Select month \"([^\"]*)\" and \"([^\"]*)\"$")
    public void select_month_and_year(String month, String year) throws Throwable {

        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("MonthDropdownXPATH"))).click();
        String monthXPATH = "//span[contains(text(),'" + month + "')]";
        driver.findElement(By.xpath(monthXPATH)).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("YearDropdownXPATH"))).click();
        String yearXPATH = "//span[contains(text(),'" + year + "')]";
        driver.findElement(By.xpath(yearXPATH)).click();

    }

    @Then("^Click submit : portal$")
    public void click_submit_portal() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Submit_XPATH"))).click();
    }

    @Then("^Click download and verify download$")
    public void click_download_and_verify_download() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,120);
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Statement generated successfully')]")));
        if (success.isDisplayed()) {
            Thread.sleep(3000);
            if (driver.findElement(By.xpath(Pro.getProperty("Download_XPATH"))).isEnabled()) {
                Assert.assertTrue("Download button is enabled", true);
            }
            else {
                Assert.fail("Download button not enabled");
            }
        } else {
            Assert.fail("Download button not enabled");
        }

    }

    @Then("^Select report file type \"([^\"]*)\"$")
    public void select_report_file_type(String reportFormat) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat\"]/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + reportFormat + "')]")).click();
    }

    @Given("^User navigates to the login page$")
    public void user_navigates_to_the_login_page() throws Throwable {
//    	driver = BaseClass.getDriver();
//    	Intergration
//    	driver.get("http://18.202.88.7:8001/trips-ui/faces/login/tripsLogin.xhtml");

//        SIT
        driver.get("https://backoffice.mra.mw:8443/trips-ui/faces/login/tripsLogin.xhtml");

    }
    @When("^Enter the username \"(.*)\" and password \"(.*)\"$")
    public void enter_the_Username_and_Password(String username,String password) throws Throwable
    {
        driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(username);
        driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(password);
    }


    @Then("^click on login$")
    public void	click_on_login() throws Throwable
    {
        driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
        //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Thread.sleep(5000);

    }

    @When("^I Fill the Individual Taxpayer Registration form$")
    public void  I_Fill_the_Individual_Taxpayer_Registration_form() throws Throwable
    {

        WebDriverWait wait=new WebDriverWait(driver,70);
        // driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH"))).click();
        Actions action = new Actions(driver);
        WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")))).click();
        WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
        action.click(Taxpayer).build().perform();
        // 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterIndividual_LINK_XPATH")))).click();
        WebElement Individual=driver.findElement(By.xpath(Pro.getProperty("RegisterIndividual_LINK_XPATH")));
        action.click(Individual).build().perform();
        Thread.sleep(2000);
    }

    @When("^I enter valid data on the Individualpage and Submit$")
    public void i_enter_valid_data_on_the_Individualpage_and_Submit(DataTable table) throws Throwable {

        //Initialize data table
        List<List<String>> data =table.asLists();

        driver.findElement(By.id(Pro.getProperty("FirstName_ID"))).sendKeys(data.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("LastName_ID"))).sendKeys(data.get(1).get(1));
        Thread.sleep(2000);
        Actions action = new Actions(driver);

        WebElement title=driver.findElement(By.xpath(Pro.getProperty("Title_LINK_XPATH")));
        action.click(title).build().perform();
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("TITLE_LIST_ITEMS_XPATH")));
        for(WebElement option : list)
        {
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(3).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();
            }
        }


        Thread.sleep(7000);
        driver.findElement(By.xpath(Pro.getProperty("Categoryofindividual_XPATH"))).click();

        List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("Categoryofindividual_LIST_ITEMS_XPATH")));
        for(WebElement option1 : CatValue)
        {
            String text= option1.getText();
            System.out.println(text);
            if(text.equalsIgnoreCase(data.get(2).get(1)))
            {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
                //  builder1.perform();

            }
        }
        Thread.sleep(4000);
        List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
        for (WebElement ele : element)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);
            //ele.click();
        }
        Thread.sleep(2000);


        Thread.sleep(2000);
        WebElement Gender=driver.findElement(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
        action.click(Gender).build().perform();
        Thread.sleep(3000);
        List<WebElement> Gen = driver.findElements(By.xpath

                (Pro.getProperty("GenderItems_LINK_XPATH")));
        for(WebElement option : Gen)
        {
            String text= option.getText();
            if(text.equalsIgnoreCase(data.get(4).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();
            }
        }
        Thread.sleep(3000);
    }


    @When("^Enter Date Of Birth in additional info tab\"([^\"]*)\"$")
    public void enter_Date_Of_Birth_in_additional_info_tab(String DOB, DataTable AddTable) throws Throwable {
        List<List<String>> data =AddTable.asLists();
        Actions action = new Actions(driver);
        Thread.sleep(6000);

        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).sendKeys(DOB);

        Thread.sleep(4000);
        WebElement Meritalstatus=driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:MaritalStatus\"]/div[3]"));
        Meritalstatus.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+data.get(0).get(1)+"']")).click();


        Thread.sleep(3000);
        driver.findElement(By.id(Pro.getProperty("PLACEOFBIRTH_LINK_ID"))).sendKeys(data.get(1).get(1));
        Thread.sleep(7000);
        List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("PLACEOFBIRTH_LINK_ID")));
        for (WebElement ele : element2)
        {
            JavascriptExecutor js1 = (JavascriptExecutor) driver;js1.executeScript("arguments [0].scrollIntoView(true);",ele);

        }
        Thread.sleep(5000);


        WebDriverWait wait1=new WebDriverWait(driver,60);
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("CountyOfResidency_LINK_XPATH")))).click();
        List<WebElement> CountryValue = driver.findElements(By.xpath(Pro.getProperty("CountyOfResidency_ITEM_LINK_XPATH")));
        for(WebElement option : CountryValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                option.click();
                break;
            }
        }

        Thread.sleep(5000);
        WebDriverWait wait=new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReasonForTINChange_CLICK_LINK_XPATH")))).click();
        List<WebElement> RFTValue = driver.findElements(By.xpath(Pro.getProperty("ReasonForTINChange_ITEM_LINK_XPATH")));
        for(WebElement option : RFTValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(3).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        WebDriverWait Nwait=new WebDriverWait(driver,60);
        Nwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NATIONALITY_CLICK_LINK_XPATH")))).click();
        List<WebElement> NValue = driver.findElements(By.xpath(Pro.getProperty("NationalityItem_LINK_XATH")));
        for(WebElement option : NValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(4).get(1)))
            {
                option.click();
                break;
            }
        }

    }



    @When("^Enter identification Date of Issue \"([^\"]*)\"$")
    public void enter_identification_Date_of_Issue(String DOI,DataTable DateTable) throws Throwable {

        List<List<String>> data =DateTable.asLists();
        Actions action = new Actions(driver);
        List<WebElement> ScrollIdent=driver.findElements(By.id(Pro.getProperty("ScrollTO-Identification_LINK_XPATH")));
        for (WebElement ele : ScrollIdent)
        {
            JavascriptExecutor js1 = (JavascriptExecutor) driver;js1.executeScript("arguments [0].scrollIntoView(true);",ele);

        }

        List<WebElement> Reg = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : Reg){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
//            List<WebElement> IdAdd=driver.findElements(By.id(Pro.getProperty("Identification_Add_ID")));
//            for (WebElement ele : IdAdd)
//            {
//
//                JavascriptExecutor js1 = (JavascriptExecutor) driver;
//                js1.executeScript("arguments[0].scrollIntoView(true);",ele);
//
//            }
        WebDriverWait wait=new WebDriverWait(driver,50);
        WebElement Identification=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Identification_Add_ID"))));

        Identification.click();
        Thread.sleep(7000);
        WebElement  iframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        Thread.sleep(2000);
        WebElement identificationNumber=driver.findElement(By.id(Pro.getProperty("Identification_Number_ID")));
        identificationNumber.sendKeys(data.get(2).get(1));

        WebDriverWait Iwait=new WebDriverWait(driver,60);
        Iwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("IdenificationType_CLICK_XPATH")))).click();
        Thread.sleep(2000);
        List<WebElement> IdentificationType = driver.findElements(By.xpath(Pro.getProperty("IdenificationType_ITEM__XPATH")));
        for(WebElement option : IdentificationType){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(1).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(5000);

        driver.findElement(By.id("Identification:CountryOfIssue_label")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+data.get(3).get(1)+"')]")).click();

        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Identification_ePermit_Num_ID"))).sendKeys(data.get(4).get(1));
//            driver.findElement(By.id(Pro.getProperty("Identification_ePermit_Num_ID"))).sendKeys(Keys.TAB);

        WebDriverWait epermit=new WebDriverWait(driver,60);
        epermit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Identification_epermit_Type_XPATH")))).click();

        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

        Thread.sleep(3000);
        JavascriptExecutor js3 = (JavascriptExecutor)driver;
        js3.executeScript("document.getElementById('"+Pro.getProperty("DateOfIssue_ID")+"').setAttribute('value', '"+DOI+"')");

    }

    @When("^Enter identification Expiry Date \"([^\"]*)\"$")
    public void enter_identification_Expiry_Date(String IED, DataTable Idtable) throws Throwable {
        Thread.sleep(2000);
        List<List<String>> data =Idtable.asLists();
        Actions action = new Actions(driver);

        JavascriptExecutor js3 = (JavascriptExecutor)driver;
        js3.executeScript("document.getElementById('"+Pro.getProperty("Identification_Expiry_Date_ID")+"').setAttribute('value', '"+IED+"')");


        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Identification_Expiry_Date_ID"))).sendKeys(Keys.TAB);
        List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("Identification_Frame_OK_ID")));
        for (WebElement ele : element2)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Identification_Frame_OK_ID"))).click();

//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            driver.switchTo().defaultContent();

//            Thread.sleep(2000);
//            List<WebElement> recidentadd=driver.findElements(By.id(Pro.getProperty("Identification_Add_ID")));
//            for (WebElement ele : recidentadd)
//            {
//
//                JavascriptExecutor js1 = (JavascriptExecutor) driver;
//                js1.executeScript("arguments[0].scrollIntoView(true);",ele);
//
//            }
        Thread.sleep(5000);
        WebElement Identification1=driver.findElement(By.id(Pro.getProperty("Identification_Add_ID")));
        Identification1.click();
        Thread.sleep(5000);
        WebElement  idfnframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(idfnframe);

        Thread.sleep(6000);
        driver.findElement(By.id(Pro.getProperty("Identification_Number_ID"))).sendKeys(data.get(1).get(1));
        Thread.sleep(2000);

        WebDriverWait Irecidentwait=new WebDriverWait(driver,60);
        Irecidentwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("IdenificationType_CLICK_XPATH")))).click();
        List<WebElement> IdntRecidenrType = driver.findElements(By.xpath(Pro.getProperty("IdenificationType_ITEM__XPATH")));
        for(WebElement option : IdntRecidenrType){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                option.click();
                break;
            }
        }

        Thread.sleep(4000);
        driver.findElement(By.id(Pro.getProperty("Identification_Frame_OK_ID"))).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();




        WebDriverWait IRecordwait=new WebDriverWait(driver,50);
        IRecordwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Identification_RecordAdded_XPATH"))));
        List<WebElement> RegInd = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : RegInd){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                option.click();
                break;
            }
        }
        driver.findElement(By.xpath(Pro.getProperty("Employment_details_Add_XPATH"))).click();
        Thread.sleep(4000);
        WebElement  Eiframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Eiframe);
        Thread.sleep(5000);
        driver.findElement(By.id(Pro.getProperty("Employment_details_Position_ID"))).sendKeys(data.get(3).get(1));
        driver.findElement(By.id(Pro.getProperty("Employment_Details_Employer's_Name_ID"))).sendKeys(data.get(4).get(1));
        driver.findElement(By.id(Pro.getProperty("Employment_Details_Employer's_Name_ID"))).sendKeys(Keys.TAB);

    }

    @Then("^wait for webpage to load$")
    public void wait_for_webpage_to_load() throws Throwable {
        Thread.sleep(30000);

    }

    @And("^enters attachment details \"([^\"]*)\"  with number \"([^\"]*)\" and path \"([^\"]*)\"$")
    public void enters_attachment_details_something_with_number_something_and_path_something(String strArg1, String strArg2, String strArg3) throws Throwable {
        WebElement attachTab=driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[10]"));
        attachTab.click();
        Thread.sleep(2000);

        WebElement Identification1=driver.findElement(By.id("RegisterIndividual:individualAccordion:attachmentTableHandler:AddAttachment"));
        Identification1.click();
        Thread.sleep(8000);

        WebElement idfnframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(idfnframe);

        WebElement docTypeDropdown=driver.findElement(By.xpath("//*[@id=\"AttachmentDetails:DocType\"]/div[3]"));
        docTypeDropdown.click();
        Thread.sleep(2000);

        //selects passport instead of diplomatic passport which has matching selector
        List <WebElement> attachments=driver.findElements(By.xpath("//li[contains(text(),'"+strArg1+"')]"));
        if (attachments.size()==1){
            attachments.get(0).click();
        }
        else{
            WebElement passport = attachments.get(1);
            passport.click();
        }

        WebElement docNumber=driver.findElement(By.id("AttachmentDetails:Reference"));
        docNumber.sendKeys(strArg2);

        Thread.sleep(2000);
        driver.findElement(By.id("AttachmentDetails:AttachmentPath_input")).sendKeys(strArg3);

        Thread.sleep(2000);
        WebElement verifiedCheckbox=driver.findElement(By.xpath("//*[@id='AttachmentDetails:Verified']/div[2]/span"));
        verifiedCheckbox.click();

        Thread.sleep(2000);
        driver.findElement(By.id("AttachmentDetails:Ok")).click();
        Thread.sleep(5000);
    }


    @When("^Enter Employee details \"([^\"]*)\"$")
    public void enter_Employee_details(String ESD , DataTable Employetable) throws Throwable {
        Thread.sleep(3000);

        List<List<String>> data =Employetable.asLists();
        Actions action = new Actions(driver);
	/*WebElement DateES=driver.findElement(By.xpath(Pro.getProperty("Employment_details_Employment_StartDate_XPATH")));
		action.sendKeys(DateES, ESD).build().perform();*/
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('"+Pro.getProperty("Employment_details_Employment_StartDate_ID")+"').setAttribute('value', '"+ESD+"')");
        Thread.sleep(2000);

        WebDriverWait Empwait=new WebDriverWait(driver,60);
        Empwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Employment_details_OK_ID")))).click();
        driver.switchTo().defaultContent();
        WebDriverWait Recordwait=new WebDriverWait(driver,50);
        Recordwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Employment_details_RecordAdded_XPATH"))));
        List<WebElement> Occupation = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : Occupation){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
        WebDriverWait Occupationwait=new WebDriverWait(driver,100);
        Occupationwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Occupation/Business_occupationStatus_XPATH")))).click();
        List<WebElement> Employed = driver.findElements(By.xpath(Pro.getProperty("Occupation/Business_occupationStatus_ITEM_XPATH")));
        for(WebElement option : Employed){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(1).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).click(option);
                builder.perform();
                //  option.click();
                break;
            }
        }
        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Occupation/Business_MainCategory_XPATH"))).click();
        List<WebElement> OccupationValue = driver.findElements(By.xpath(Pro.getProperty("Occupation/Business_MainCategory_ITEM_XPATH")));
        for(WebElement option : OccupationValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
        WebElement preciseDropdown=driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:PreciseCategory\"]/div[3]"));
        preciseDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+data.get(3).get(1)+"']")).click();

        Thread.sleep(9000);
        List<WebElement> Addresses = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : Addresses){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(4).get(1)))
            {
                option.click();
                break;
            }
        }
        WebElement Addressadd=driver.findElement(By.id(Pro.getProperty("Addresses_ADD_ID")));
        action.click(Addressadd).build().perform();
        Thread.sleep(7000);
        WebElement  Addressframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Addressframe);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait AddressType=new WebDriverWait(driver,50);
        AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
        List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
        for(WebElement option : AddressValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(5).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        WebElement SName=driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
        action.sendKeys(SName,data.get(6).get(1)).build().perform();
        WebElement CName=driver.findElement(By.xpath(Pro.getProperty("Addresses_Town/City_XPATH")));
        action.sendKeys(CName,data.get(7).get(1)).build().perform();

        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Addresses_region_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+data.get(10).get(1)+"')]")).click();

        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Addresses_District_XPATH"))).click();

        List<WebElement> Dvalue = driver.findElements(By.xpath(Pro.getProperty("Addresses_District_ITEM_XPATH")));
        for(WebElement option : Dvalue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(9).get(1)))
            {
                option.click();
                break;
            }
        }
        // Thread.sleep(2000);

        List<WebElement> AddressOK=driver.findElements(By.id(Pro.getProperty("Address_Scroll_View_XPATH")));
        for (WebElement ele : AddressOK)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        WebElement AddOK=driver.findElement(By.id(Pro.getProperty("Addresses_OK_ID")));
        action.doubleClick(AddOK).build().perform();
        AddOK.click();
        driver.switchTo().defaultContent();
        WebDriverWait AddressRecord=new WebDriverWait(driver,50);
        AddressRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_RecordAdded_XPATH"))));
        List<WebElement> ContactReg = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : ContactReg){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(11).get(1)))
            {
                option.click();
                break;
            }
        }
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ADD_XPATH"))).click();
        Thread.sleep(5000);
        WebElement  ContMethodframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(ContMethodframe);
        WebDriverWait Purpose=new WebDriverWait(driver,50);
        Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
        List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
        for(WebElement option : PurposeValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(12).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+data.get(13).get(1)+"')]")).click();

//            JavascriptExecutor jsp = (JavascriptExecutor)driver;
//            jsp.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")))));

//            WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")));
//            action.click(PrimInd).build().perform();

        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("ContactMethods_ContactMethodDetails_ID"))).sendKeys(data.get(14).get(1));
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
    }

    @Then("^Click On Individual Page Save Button$")
    public void click_On_Individual_PageSave_Button() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("Save_DATA_XPATH"))).click();

    }

    @Then("^Click On Individual Page Submit Button$")
    public void click_On_Individual_PageSubmit_Button() throws Throwable {
        WebDriverWait Submit=new WebDriverWait(driver,60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Individual_Submit_Data_ID")))).click();
        Thread.sleep(7000);

    }
    @Then("^ARN number will generate$")
    public void	ARN_number_will_generate(DataTable table) throws Throwable
    {
        List<List<String>> data =table.asLists();
        WebDriverWait Submit=new WebDriverWait(driver,60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Individual_Submit_Data_ID")))).click();
        Thread.sleep(7000);
        WebDriverWait RefNumber=new WebDriverWait(driver,60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();

        System.out.println(text);
        System.out.println("substring is "+ text.substring(42));
        String A_BackOffice_ARN=text.substring(42);

        sharedatastep.A_CRMARN="*"+A_BackOffice_ARN;
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);

        if(text.contains(data.get(0).get(1)))
        {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        }
        else
        {
            System.out.println("Unsuccessful Registartion");
        }

        Thread.sleep(27000);
    }

    @Then("^Verify the ARN number \"([^\"]*)\"$")
    public void verify_the_ARN_number_ARN(String ARN) throws Throwable {

        WebDriverWait RefNumber=new WebDriverWait(driver,150);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();

        System.out.println(text);
        System.out.println("substring is "+ text.substring(42));
        String A_BackOffice_ARN=text.substring(42);

        sharedatastep.A_CRMARN=A_BackOffice_ARN;
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);

        if(text.contains(ARN))
        {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        }
        else
        {
            System.out.println("Text Not Verified and failed");
        }

        Thread.sleep(25000);
    }

    @Given("^Open CRM URL Module$")
    public void open_CRM_URL_Module() throws Throwable {
//        driver = BaseClass.getDriver();
        driver.get(Pro.getProperty("MRA_crm_url_Registration"));
    }

    @When("^Close Popup Window$")
    public void close_Popup_Window() throws Throwable {

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow=new WebDriverWait(driver,60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @And("^Click on Case management dropdown$")
    public void click_on_case_management_dropdown() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
    }

    @And("^click on Registration application$")
    public void click_on_revenue_collection_application() throws Throwable {
        driver.findElement(By.id("tbg_registrationapplication")).click();
    }

    @Then("^switch to frame$")
    public void switch_to_frame() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

    }

    @When("^enters reference number in search results$")
    public void enters_in_search_results() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchTextBox")));
        search.sendKeys(sharedatastep.A_CRMARN);
//    	search.sendKeys("ARN/00020759/2020");
        search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }

    @Then("^Click selected Reference Number$")
    public void click_selected_Reference_Number() throws Throwable {
        WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();

        driver.switchTo().defaultContent();
        Thread.sleep(4000);
    }

    //--------------------approve crm-------------------------------------//
    @And("^Click start search$")
    public void click_start_search() throws Throwable {
        driver.findElement(By.id("TabSearch")).click();
    }

    @And("^Pick registration case$")
    public void pick_registration_case() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List <WebElement> cases=driver.findElements(By.xpath("//div[@tabindex='0']"));
        cases.get(1).click();
    }

    @Then("^Goto view AttachmentDetails screen$")
    public void goto_view_AttachmentDetails_screen() throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);
        List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
        for (WebElement ele : element)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        Thread.sleep(2000);

    }

    @Then("^Download the Attachment$")
    public void download_the_Attachment() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,100);

        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Download']")));
        downloadAttach.click();
        Thread.sleep(9000);
        String downloadpath="C:\\Users\\v-bakam\\Downloads";
        boolean isPresent = false;
        File dir = new File(downloadpath);
        File[] dir_contents = dir.listFiles();

        for (File dir_content : dir_contents) {
            if (dir_content.getName().equals("id_doc.png"))
                isPresent = true;
            dir_content.delete();
            System.out.print("0000000000000000000000000000000000000");
        }
        Thread.sleep(4000);
        Assert.assertTrue(isPresent);

    }
    @Then("^Select Identification Outcome dropdown value for Individual Taxpayer Approval$")
    public void select_Identification_Outcome_dropdown_value_for_Individual_Taxpayer_Approval() throws Throwable {
        driver.findElement(By.xpath("//span[text()='click to enter']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Approval outcome dropdown value to Approve\"([^\"]*)\"$")
    public void select_Approval_outcome_dropdown_value_to_Approve(String Approve) throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);

        WebDriverWait wait=new WebDriverWait(driver,100);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Download']")));
        Assert.assertTrue(downloadAttach.isDisplayed());

        driver.switchTo().defaultContent();
        WebDriverWait wait1=new WebDriverWait(driver, 30);
        WebElement specificframe = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

        driver.findElement(By.id("header_process_tbg_approvaloutcome_c")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Click on NextStage button two times$")
    public void click_on_NextStage_button_two_times() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(11000);
        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
    }
    @Then("^wait for duplicate check$")
    public void wait_for_duplicate_check() throws Throwable {
//        driver.switchTo().defaultContent();
        WebDriverWait wait=new WebDriverWait(driver,160);

        WebElement validation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stageAdvanceActionContainer")));
        validation.click();
    }

    @Then("^Click on NextStage button$")
    public void click_on_NextStage_button() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(8000);
        WebElement  specificframe2= (driver.findElement(By.id(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_ID"))));
        driver.switchTo().frame(specificframe2);

    }

    @Then("^Click on Save button$")
    public void click_on_Save_button() throws Throwable {
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
//            WebElement  specificframe3= (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
//            driver.switchTo().frame(specificframe3);
        driver.findElement(By.id("tbg_registrationapplication|NoRelationship|Form|Mscrm.Form.tbg_registrationapplication.Save")).click();


    }

    @Then("^Verify the String \"([^\"]*)\"$")
    public void verify_the_String(String Status) throws Throwable {
        driver.switchTo().frame("contentIFrame0");
        WebDriverWait wait = new WebDriverWait(driver,100);
        Thread.sleep(3000);
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Status_label']"))).getText();
        if(text.contains(Status))
        {

            System.out.println("Text Verified and"+Status);
        }
        else
        {
            System.out.println("Text Not Verfied and failed");
        }
        Thread.sleep(2000);

    }

    @When("^Enter Attachment Tab details$")
    public void enter_Attachment_Tab_details(DataTable Attchtable) throws Throwable {

        List<List<String>> data =Attchtable.asLists();
        //Actions action=new Actions(driver);


        WebDriverWait ContactRecord=new WebDriverWait(driver,50);
        ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_ContactMethods_RecordAdded_XPATH"))));
        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> OrgAttachment = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for(WebElement option : OrgAttachment)
        {
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement  Attachmentframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Attachmentframe);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for(WebElement option : AttPassport){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
        Thread.sleep(2000);
        WebElement uploadBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        // action.click(uploadBtn).build().perform();
        uploadBtn.click();
        //put path to your image in a clipboard
        StringSelection ss = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(600);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);

        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();

        WebElement dateRecieved=driver.findElement(By.id("AttachmentDetails:DateReceived_input"));
        dateRecieved.click();
        dateRecieved.sendKeys("10/12/2005");
        Thread.sleep(4000);

        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord=new WebDriverWait(driver,50);
        AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
//Attachment-----	Approval Letter from Line Ministry


        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();

        WebElement  AttLetterframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AttLetterframe);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttLettert = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for(WebElement option : AttLettert){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(6).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(5).get(1));
        Thread.sleep(2000);

        WebElement dateRecieved3=driver.findElement(By.id("AttachmentDetails:DateReceived_input"));
        dateRecieved3.click();
        dateRecieved3.sendKeys("10/12/2005");
        dateRecieved3.sendKeys(Keys.TAB);
        Thread.sleep(4000);


        WebElement LetterBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
// action.click(LetterBtn).build().perform();
        LetterBtn.click();
//put path to your image in a clipboard
        StringSelection ss1 = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot1 = new Robot();
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.keyRelease(KeyEvent.VK_ENTER);
        robot1.keyPress(KeyEvent.VK_CONTROL);
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_V);
        robot1.keyRelease(KeyEvent.VK_V);
        robot1.keyRelease(KeyEvent.VK_CONTROL);
        robot1.delay(600);
        Thread.sleep(5000);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.delay(300);
        robot1.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();



        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord1=new WebDriverWait(driver,50);
        AttRecord1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
//End....
// Certificate of Incorporation
        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement  AttCertiframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AttCertiframe);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttCertificate = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for(WebElement option : AttCertificate){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(7).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(9).get(1));
        Thread.sleep(2000);
        WebElement CertiBtn=driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        CertiBtn.click();
// action.click(CertiBtn).build().perform();
//put path to your image in a clipboard
        StringSelection ss2 = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot2 = new Robot();
        robot2.delay(300);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
        robot2.keyPress(KeyEvent.VK_CONTROL);
        robot2.keyPress(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_CONTROL);
        robot2.delay(600);
        Thread.sleep(5000);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.delay(300);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.delay(300);
        robot2.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();

        WebElement dateRecieved2=driver.findElement(By.id("AttachmentDetails:DateReceived_input"));
        dateRecieved2.click();
        dateRecieved2.sendKeys("10/12/2005");
        Thread.sleep(4000);

        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord2=new WebDriverWait(driver,50);
        AttRecord2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
    }

    @When("^I Fill the Organization Taxpayer Registration form$")
    public void	I_Fill_the_Organization_Taxpayer_Registration_form() throws Throwable
    {
        WebDriverWait wait=new WebDriverWait(driver,70);
        // driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH"))).click();
        Actions action = new Actions(driver);
        WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")))).click();
        WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
        action.click(Taxpayer).build().perform();
        WebElement Individual=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxpayer_RegisterOrganisation_XPATh")));
        action.click(Individual).build().perform();
        Thread.sleep(2000);



    }

    // Register Taxpayer Organization Scenario submit
    @And("^I enter valid data on the pages of Organization$")
    public void I_enter_valid_data_on_the_pages_of_Organization(DataTable table )throws Throwable
    {

        //Initialize data table
        List<List<String>> data =table.asLists();
        Actions action = new Actions(driver);
        driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
        Thread.sleep(2000);
        WebDriverWait Category=new WebDriverWait(driver,60);
        Category.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Organization_OrganisationCategory_XPATH")))).click();
        List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("HeaderDetails_OrganisationCategory_ITEM_XPATH")));
        for(WebElement option : CatValue)
        {
            String text2= option.getText();

            // System.out.println(text2);
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                option.click();
                break;
            }
        }

        Thread.sleep(2000);

        // driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
        List<WebElement> element2=driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
        for (WebElement ele : element2)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        Thread.sleep(5000);
        driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_RGDNO_ID"))).sendKeys(data.get(2).get(1));
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Thread.sleep(7000);
        // Boolean status=driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).isEnabled();
        WebElement Date=driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID")));
        if(Date.isEnabled())
        {
            System.out.println("enabled");
        }
        else
        {
            System.out.println("Disabled");
        }



        // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).sendKeys(data.get(3).get(1));

        // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID"))).sendKeys(data.get(4).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Organisation_SourceOfCapital_Xapth"))).sendKeys(data.get(5).get(1));
        Thread.sleep(2000);
        // driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH"))).sendKeys(data.get(6).get(1));

        WebDriverWait Place=new WebDriverWait(driver,60);
        Place.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH")))).click();
        List<WebElement> PlaceValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_Values_XPATH")));
        for(WebElement option : PlaceValue)
        {
            String text2= option.getText();

            // System.out.println(text2);
            if(text2.equalsIgnoreCase(data.get(6).get(1)))
            {
                option.click();
                break;
            }
        }




        List<WebElement> BSDetails=driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
        for (WebElement ele : BSDetails)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_XPATH"))).click();

        List<WebElement> ReasonFTIN = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_ITEM_XPATH")));
        for(WebElement option1 : ReasonFTIN)
        {
            String text= option1.getText();
            // System.out.println(text);
            if(text.equalsIgnoreCase(data.get(7).get(1)))
            {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
            }}
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
        List<WebElement> EndYearMonth = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_Value_XPATH")));
        for(WebElement option1 : EndYearMonth)
        {
            String text= option1.getText();
            // System.out.println(text);
            if(text.equalsIgnoreCase(data.get(19).get(1)))
            {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
                break;
            }}

        Thread.sleep(4000);
        WebElement yearDay=driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_XPATH")));
        yearDay.click();
        List<WebElement> EndYearDay = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_Value_XPATH")));
        for(WebElement option1 : EndYearDay)
        {
            String text= option1.getText();
            // System.out.println(text);
            if(text.equalsIgnoreCase(data.get(20).get(1)))
            {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
                break;
            }}

        Thread.sleep(4000);

	 /*driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
	 action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 Thread.sleep(2000);*/
        List<WebElement> element=driver.findElements(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID")));
        for (WebElement ele : element)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID"))).click();
        WebElement  Occupationframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Occupationframe);
        WebDriverWait OccWait=new WebDriverWait(driver,50);
        OccWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Occupation/Business_ADD_WAIT_ID"))));
        driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_Primaryindicator_XPATH"))).click();

        WebElement BSadd=driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_XPATH")));
        action.click(BSadd).build().perform();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        List<WebElement> BSecValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_ITEM_XPATH")));
        for(WebElement option : BSecValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(8).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(5000);

        driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_OK_ID"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait BSecrecord=new WebDriverWait(driver,50);
        BSecrecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("AdditionalDetails_RecordAdded_XPATH"))));
        Thread.sleep(2000);
        List<WebElement> ScrollAddress=driver.findElements(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID")));
        for (WebElement ele : ScrollAddress)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }

        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for(WebElement option : SummarytabValue)
        {
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(9).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement Addressadd=driver.findElement(By.id(Pro.getProperty("Organization_Addresses_Add_ID")));
        action.click(Addressadd).build().perform();
        Thread.sleep(7000);
        WebElement  Addressframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Addressframe);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait AddressType=new WebDriverWait(driver,50);
        AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
        List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
        for(WebElement option : AddressValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(10).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);

        WebElement SName=driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
        action.sendKeys(SName,data.get(11).get(1)).build().perform();
        WebElement CName=driver.findElement(By.xpath(Pro.getProperty("Addresses_Town/City_XPATH")));
        action.sendKeys(CName,data.get(12).get(1)).build().perform();

        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Addresses_region_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+data.get(14).get(1)+"')]")).click();

        Thread.sleep(2000);
        WebDriverWait District=new WebDriverWait(driver,100);
        District.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_District_XPATH")))).click();

        List<WebElement> Dvalue = driver.findElements(By.xpath(Pro.getProperty("Addresses_District_ITEM_XPATH")));
        for(WebElement option : Dvalue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(13).get(1)))
            {
                option.click();
                break;
            }
        }

        List<WebElement> AddressOK=driver.findElements(By.id(Pro.getProperty("Addresses_OK_ID")));
        for (WebElement ele : AddressOK)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        WebElement AddOK=driver.findElement(By.id(Pro.getProperty("Addresses_OK_ID")));
        action.click(AddOK).build().perform();
        driver.switchTo().defaultContent();
        WebDriverWait AddressRecord=new WebDriverWait(driver,100);
        AddressRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_Addresses_RecordAdded_XPATH"))));
        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> SummarytabValue2 = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for(WebElement option : SummarytabValue2)
        {
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(15).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("ContactMethods_ADD_ID"))).click();
        Thread.sleep(9000);
        WebElement  ContMethodframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(ContMethodframe);driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait Purpose=new WebDriverWait(driver,50);
        Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
        List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
        for(WebElement option : PurposeValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(16).get(1)))
            {

                option.click();
                break;
            }
        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Thread.sleep(2000);
//            WebDriverWait ContactType=new WebDriverWait(driver,50);
//            ContactType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH")))).click();
//            List<WebElement> ContactTypeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Type_ITEM_XPATH")));
//            for(WebElement option : ContactTypeValue){
//                String text2= option.getText();
//                if(text2.equalsIgnoreCase(data.get(17).get(1)))
//                {
//
//                    Actions builder = new Actions(driver);
//                    builder.moveToElement(option).click(option);
//                    builder.perform();
//                    // option.click();
//                    break;
//                }
//            }
//            JavascriptExecutor js = (JavascriptExecutor)driver;
//            js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")))));
//
//            WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")));
//            PrimInd.click();
        Thread.sleep(3000);
        WebDriverWait ContctDet=new WebDriverWait(driver,50);
        ContctDet.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH"))));
        Thread.sleep(1000);
        WebElement Contdetails=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH")));
        action.sendKeys(Contdetails, data.get(18).get(1)).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();


    }

    @And("^enters director \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enters_director_something_and_something(String strArg1, String strArg2) throws Throwable {
        WebElement directorsPath=driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[14]"));
        directorsPath.click();
        Thread.sleep(4000);

        WebElement addSummary=driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:directorsTableHandler:AddDirectors"));
        addSummary.click();
        Thread.sleep(2000);

        //Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);

        driver.findElement(By.id("DirectorsDetails:FindTin")).click();
        Thread.sleep(3000);


        driver.switchTo().defaultContent();
//        WebElement  Tinframe= driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(1);
        Thread.sleep(3000);

        WebElement tinInput=driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.sendKeys(strArg1);

        WebElement searchBtn=driver.findElement(By.id("SearchForm:j_idt21"));
        searchBtn.click();
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
//        WebElement  Tinframe= driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(0);
        Thread.sleep(7000);

        WebDriverWait wait=new WebDriverWait(driver,50);
        WebElement startDate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DirectorsDetails:PositionHeldSince_input")));

        startDate.click();
        startDate.sendKeys(Keys.ENTER);

        driver.findElement(By.id("DirectorsDetails:rdOk")).click();
        Thread.sleep(4000);

    }

    @Then("^Click On Organization Page Submit Button$")
    public void click_On_Organization_Page_Submit_Button() throws Throwable {
        WebDriverWait Submit=new WebDriverWait(driver,60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("RegisterOrganisation_SUBMIT_XPATH")))).click();
        Thread.sleep(7000);
    }

    @When("^I enter valid data on the TaxType Individual page (.+)$")
    public void i_enter_valid_data_on_the_taxtype_individual_page(String taxtype,DataTable arg1) throws Throwable {
        {
            //Initialize data table
            List<List<String>> data =arg1.asLists();
            Actions action = new Actions(driver);
            WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
            action.doubleClick(Reg).build().perform();
            Reg.click();
            WebElement manage=driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
            action.doubleClick(manage).build().perform();
            WebDriverWait Taxtype=new WebDriverWait(driver,60);
            Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_RegisterTaxType_XPATH")))).click();

            Thread.sleep(4000);
            driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();;
            List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
            for(WebElement option : list)
            {
                String text2= option.getText();
                System.out.println(text2);


                if(text2.equalsIgnoreCase(data.get(0).get(1)))
                {

                    option.click();
                    break;
                }
            }
            driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));


            WebDriverWait Searchwait=new WebDriverWait(driver,100);
            Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Individual_Search_ID")))).click();

            Thread.sleep(2000);
            List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("SrollToView_Title_ID")));
            for (WebElement ele : element2)
            {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);",ele);

            }
            WebDriverWait wait=new WebDriverWait(driver,100);
            WebElement RegTaxType=wait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_RegisterTaxTypeButton_ID"))));

            action.click(RegTaxType).build().perform();
            WebDriverWait iframeWait=new WebDriverWait(driver,60);
            WebElement  Taxtypeframe= iframeWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
            driver.switchTo().frame(Taxtypeframe);
            WebDriverWait selectTaxtype=new WebDriverWait(driver,60);
            selectTaxtype.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_TaxType_ID")))).click();
            List<WebElement> TaxTypeValue = driver.findElements(By.xpath(Pro.getProperty("Registration_RegisterTaxType_TaxType_ITEM_XPATH")));
            for(WebElement option : TaxTypeValue)
            {
                String text2= option.getText();



                if(text2.equalsIgnoreCase(taxtype))
                {

                    option.click();
                    break;
                }
            }
            Thread.sleep(2000);
            //driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_EDR_ID"))).sendKeys(data.get(3).get(1));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("document.getElementById('"+Pro.getProperty("Registration_RegisterTaxType_EDR_ID")+"').setAttribute('value', '"+data.get(3).get(1)+"')");

            WebElement turnoverInput=driver.findElement(By.id("RevenueTypeDetails:TaxableTurnover_input"));
            turnoverInput.sendKeys(data.get(2).get(1));

            WebElement attachDropdown=driver.findElement(By.xpath("//*[@id=\"RevenueTypeDetails:DocType\"]/div[3]"));
            attachDropdown.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[text()='"+data.get(6).get(1)+"']")).click();

            Thread.sleep(2000);
            WebElement docNumber= driver.findElement(By.id("RevenueTypeDetails:Reference"));
            docNumber.sendKeys(data.get(7).get(1));

            Thread.sleep(2000);
            WebElement attachment=driver.findElement(By.id("RevenueTypeDetails:AttachmentPath_input"));
            Thread.sleep(2000);
            attachment.sendKeys(data.get(5).get(1));


            driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_OK_ID"))).click();
            driver.switchTo().defaultContent();
//                String Recordwait=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxType_RecordAdded_XPATH"))).getText();
//                System.out.println(Recordwait);
//                if(Recordwait.contains(data.get(4).get(1)))
//                {
//
//                    System.out.println("Text Verified and passed");
//                }
//                else
//                {
//                    System.out.println("Text Not Verfied and failed");
//                }
//
//                Thread.sleep(1000);
        }
    }

    @Then("^TaxType ARN number will generate$")
    public void taxtype_ARN_number_will_generate(DataTable TaxType) throws Throwable {

        List<List<String>> data =TaxType.asLists();
        WebDriverWait Submit=new WebDriverWait(driver,60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("TaxType_Submit_ID")))).click();
        Thread.sleep(7000);
        WebDriverWait RefNumber=new WebDriverWait(driver,60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text  =driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
        System.out.println(text);

        System.out.println("substring is "+ text.substring(42));
        String A_BackOffice_ARN=text.substring(42);

        sharedatastep.A_CRMARN="*"+A_BackOffice_ARN;

        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);



        if(text.contains(data.get(0).get(1)))
        {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        }
        else
        {
            System.out.println("Text Not Verfied and failed");
        }


    }

    @And("^clicks Approve from the taxtypedropdown$")
    public void clicks_Approve_from_the_taxtypedropdown() throws Throwable {
//    	driver.switchTo().frame("contentIFrame0");
//    	Thread.sleep(9000);
//    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id("header_process_tbg_approvaloutcome4"));
        WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if(hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }

    }






}