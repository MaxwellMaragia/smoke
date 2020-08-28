package Steps;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public static sharedatastep sharedata;

    public steps (sharedatastep sharedata) throws IOException {
        steps.sharedata=sharedata;
        driver = BaseClass.getDriver();
    }

    @Before(order = 0)
    public void method1() throws Exception {
        // this.S=S;
        Pro = new Properties();
        FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\Objects\\object.properties");
        Pro.load(fip);
    }

    @Given("^Open trips URL$")
    public void loadTripsLink() throws Throwable {
        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
        driver.manage().window().maximize();
    }

    @Given("^Open portal URL$")
    public void loadPortalLink() throws Throwable {
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


//    @When("^enters reference number in search results$")
//    public void enters_in_search_results() throws Throwable {
//        WebDriverWait wait=new WebDriverWait(driver, 30);
//        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchTextBox")));
//        search.sendKeys(sharedatastep.A_CRMARN);
////    	search.sendKeys("ARN/00020759/2020");
//        search.sendKeys(Keys.ENTER);
//
//        Thread.sleep(2000);
//    }


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

    @When("^Enters the username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_username_something_and_password_something_to_login(String strArg1, String strArg2) throws Throwable {
        driver.findElement(By.id("loginForm:username")).sendKeys(strArg1);
        driver.findElement(By.id("loginForm:password")).sendKeys(strArg2);
        driver.findElement(By.xpath("//*[@id=\"loginForm:j_idt19\"]/span")).click();
    }

    @Then("^User should be logged in$")
    public void user_should_be_logged_in() throws Throwable {
        String URL = driver.getCurrentUrl();

//    	Assert.assertEquals(URL, "http://18.202.88.7:8001/trips-ui/faces/login/Welcome.xhtml" );
        Assert.assertEquals(URL, "https://backoffice.mra.mw:8443/trips-ui/faces/login/Welcome.xhtml" );
    }

    ////----------------------------------------[SUC:05-15] Open a Cash Office---------------------------------------------------------------------------------------------///
    @Given("^navigate to Revenue Collection>>Cash Office Daily Control$")
    public void navigate_to_revenue_collectioncash_office_daily_control() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_CashOfficeDailyControl_XPATH"))).click();
        Thread.sleep(9000);
    }

    @When("^the User clicks Cash Office Name$")
    public void the_user_clicks_cash_office_name() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("cashOffice_NameDropdown_Xpath"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("CashOfficeDailyControlform:CashOfficeName_8")).click();

        Thread.sleep(4000);

    }

    @And("^clicks Open Cash Office$")
    public void clicks_open_cash_office() throws Throwable {
        // checks if office is already open and reconciles to enable it to be opened again
        Boolean openCashofficeButton = driver.findElement(By.xpath("//*[@id='CashOfficeDailyControlform:btnOpenCashOffice']")).isEnabled();

        if (openCashofficeButton) {
            driver.findElement(By.xpath("//*[@id='CashOfficeDailyControlform:btnOpenCashOffice']")).click();
        }
        Thread.sleep(5000);
        driver.findElement(By.id("CashOfficeDailyControlform:btnReconcileCashOffice")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("CashOfficeDailyControlform:btnOpenCashOffice")).click();
    }

    @Then("^System opens the Cash Office$")
    public void system_opens_the_cash_office() throws Throwable {
        Thread.sleep(4000);
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-messages-info-summary")));
        Assert.assertEquals(success.getText(), "Cash Office Opened Successfully");

    }

    @Then("^System reconciles the Cash Office$")
    public void system_reconciles_the_cash_office() throws Throwable {
        Thread.sleep(4000);
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-messages-info-summary")));
        Assert.assertEquals(success.getText(), "Cash Office Reconciled Successfully");
    }

///--------------------------UAT_M7_02-02-Verify the Process of Allocate Cash Till---------------------------------------------------------------------////

    @Given("^navigate to Revenue Collection>>Cash Till Maintenance$")
    public void navigate_to_revenue_collectioncash_till_maintenance() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("revenueCollection_Xpath"))).click();
        driver.findElement(By.xpath(Pro.getProperty("cashtillMaintenance_Xpath"))).click();
        Thread.sleep(4000);
    }

    @When("^clicks on Request Cash Till button$")
    public void clicks_on_request_cash_till_button() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("requestCashTill_id"))).click();
    }

    @Then("^success message displayed$")
    public void success_message_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Processing Completed')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
        String Text=Message.getText();
        String ARN = Text.substring(Text.lastIndexOf(" ")+1);
        sharedatastep.P_CRMARN=ARN;
        System.out.print(sharedatastep.P_CRMARN);
        System.out.print("Reference Number is - " + sharedatastep.P_CRMARN);
    }

    @Given("^Open CRM URL Module$")
    public void open_CRM_URL_Module() throws Throwable {
        driver = BaseClass.getDriver();
        driver.get(Pro.getProperty("MRA_crm_url_Registration"));
    }

    @When("^Close Popup Window$")
    public void close_Popup_Window() throws Throwable {

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow=new WebDriverWait(driver,100);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @And("^Click on Case management dropdown$")
    public void click_on_case_management_dropdown() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
    }

    @And("^click on Revenue Collection application$")
    public void click_on_revenue_collection_application() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"tbg_revenuecollectionapplication\"]")).click();
    }

    @Then("^switch to frame$")
    public void switch_to_frame() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait=new WebDriverWait(driver, 100);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

    }

    @When("^enters reference number in search results$")
    public void enters_in_search_results() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 100);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));
        search.sendKeys(sharedatastep.P_CRMARN);
//    	search.sendKeys("CT00001741");
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

    @And("^clicks Approve from the dropdown$")
    public void clicks_Approve_from_the_dropdown() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
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

    @And("^click save on Payments$")
    public void click_save_on_Payments() throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_revenuecollectionapplication|NoRelationship|Form|Mscrm.Form.tbg_revenuecollectionapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Then("^Application Account Adjustment status should be \"([^\"]*)\"$")
    public void application_account_adjustment_status_should_be_something(String Status) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver,30);
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

    @When("^enters approved ref number$")
    public void enters_approved_ref_number() throws Throwable {

        WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
        cashTillReferenceDropdown.click();
        Thread.sleep(2000);

        //click on the ref number of the specific cash till on the dropdown
        WebElement refNumber =driver.findElement(By.xpath("//li[@data-label='"+sharedatastep.P_CRMARN+"']"));

//        WebElement refNumber =driver.findElement(By.xpath("//li[@data-label='CT00001801']"));
        Thread.sleep(2000);
        refNumber.click();
    }

    @When("^selects ref number$")
    public void selects_ref_number() throws Throwable {
        WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
        cashTillReferenceDropdown.click();
        Thread.sleep(2000);

        //click on the ref number of the specific cash till on the dropdown
        WebElement refNumber =driver.findElement(By.xpath("CashTillMaintenance:TillReference_1"));
        Thread.sleep(2000);
        refNumber.click();

    }

    @And("^enters float amount (.+)$")
    public void enters_float_amount(String amount) throws Throwable {
        Thread.sleep(4000);
        WebElement floatAmount = driver.findElement(By.id("CashTillMaintenance:FloatAmount_input"));
        floatAmount.sendKeys(amount);
        Thread.sleep(4000);
    }

    @And("^clicks Save on cash till mainenance$")
    public void clicks_Save_on_cash_till_mainenance() throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.id("CashTillMaintenance:save")).click();
    }

    @Then("^Cash Till is now open$")
    public void cash_till_is_now_open() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Cash Till is now open')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
    }

    @Then("^Suspend CashTill$")
    public void suspend_cashtill() throws Throwable {
        Thread.sleep(5000);
        WebElement suspendCashTill=driver.findElement(By.id("CashTillMaintenance:btnSuspendCashtill"));
        suspendCashTill.click();

        Thread.sleep(4000);
    }

    @Then("^Cash Till is now suspended$")
    public void cash_till_is_now_suspended() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Cash Till suspended.')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
    }

    @And("^clicks Decline from the dropdown$")
    public void clicks_Decline_from_the_dropdown() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(10000);

        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if(hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @Then("^Enter Outcome Notes (.+)$")
    public void enter_outcome_notes(String Notes) throws Throwable {
        Thread.sleep(3000);
        Actions action1 = new Actions(driver);
        WebElement element1=driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
        action1.sendKeys(element1, Notes).build().perform();
        Thread.sleep(5000);
    }

    @Then("^Enter Outcome Reason for Taxpayer accounting$")
    public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
        WebElement specificframe=driver.findElement(By.id("WebResource_RevenueCollectionRejectionDataWebResource"));
        driver.switchTo().frame(specificframe);
        WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"viewoption\"]"));

        dropDown.click();

        driver.findElement(By.xpath("//option[@value='2']")).click();

    }

    @Then("^cashTill status should be (.+)$")
    public void cashtill_status_should_be(String status) throws Throwable {
        Thread.sleep(4000);
        WebElement cashTillStatus = driver.findElement(By.id("CashTillMaintenance:TillStatus_label"));

        Assert.assertEquals(status, cashTillStatus.getText());
    }

    @Then("^error message displayed$")
    public void error_message_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Float Amount - A value is required')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed",true);
        }else {
            Assert.fail("No Error message displayed");
        }
    }

    @When("^selects the (.+)$")
    public void selects_the(String refnumber) throws Throwable {
        WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
        cashTillReferenceDropdown.click();
        Thread.sleep(2000);
        sharedatastep.P_CRMARN=refnumber;
        //click on the ref number of the specific cash till on the dropdown
        WebElement refrenceNumber =driver.findElement(By.xpath("//li[@data-label='"+refnumber+"']"));

        Thread.sleep(2000);
        refrenceNumber.click();
//        driver.findElement(By.xpath("//html")).click();
        Thread.sleep(5000);
    }

    @And("^clicks on Open Cash Till button$")
    public void clicks_on_open_cash_till_button() throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.id(Pro.getProperty("openCashTill_id"))).click();
    }
    @Then("^successfuly awaits approval$")
    public void successfuly_awaits_approval() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Request has been sent successfully for approval')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
    }

    @And("^enters payment collection (.+)$")
    public void enters_payment_collection(String payment) throws Throwable {
        WebElement paymentCollectionInput = driver.findElement(By.id("CashTillMaintenance:PaymentCollected_input"));
//        paymentCollectionInput.clear();
        paymentCollectionInput.sendKeys(payment);
    }

    @And("^clicks on close Cash Till button$")
    public void clicks_on_close_cash_till_button() throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.id(Pro.getProperty("closeCashTill_id"))).click();
        Thread.sleep(3000);
    }

    @Then("^prompt to click save appears$")
    public void prompt_to_click_save_appears() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'To close the cash till, use')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
    }

    @Then("^unreconciled error message displayed$")
    public void unreconciled_error_message_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Reconciliation failed')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed",true);
        }else {
            Assert.fail("No Error message displayed");
        }
    }

    @When("^selects Cash Office Name (.+)$")
    public void selects_cash_office_name(String cfn) throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("cashOffice_NameDropdown_Xpath"))).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//li[@data-label='"+cfn+"']")).click();
        Thread.sleep(4000);
    }

    @Given("^navigate to  Revenue Collection>>Receive Payment$")
    public void navigate_to_revenue_collectionreceive_payment() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_recieve_Payment_XPATH"))).click();
        Thread.sleep(4000);

    }

    @When("^click on Find Button$")
    public void click_on_find_button() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("PaymentSummary:FindTin"))).click();
    }

    @Then("^Find Entity pop up window should be displayed$")
    public void find_entity_pop_up_window_should_be_displayed() throws Throwable {
        Thread.sleep(7000);
        //Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);

        String modalHeader = driver.findElement(By.id("SearchForm:directorHeader")).getText();
        Assert.assertEquals(modalHeader, "Trips - Find Entity");
    }

    @When("^User enters (.+) and (.+)$")
    public void user_enters_and(String taxpayerclassificationtype, String tin) throws Throwable {
        WebElement taxpayerClassificationdropDown=driver.findElement(By.xpath("//*[@id=\"SearchForm:DTYPE\"]/div[3]"));
        taxpayerClassificationdropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+taxpayerclassificationtype+"']")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebElement tinInput = driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.sendKeys(tin);
    }

    ////---------------------------------------------View Payment--------------------------------------------------------------------------------------------//
    @Given("^navigate to  Revenue Collection>>View Payment$")
    public void navigate_to_revenue_collectionview_payment() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_view_Payment_XPATH"))).click();
        Thread.sleep(3000);
    }

    @When("^From Find Payment window enters (.+) and (.+)$")
    public void from_find_payment_window_enters_and(String tin, String paymentmethod) throws Throwable {
        WebElement tinInput = driver.findElement(By.id("SearchForm:TIN"));
        tinInput.sendKeys(tin);

        WebElement paymentMethoddropDown=driver.findElement(By.xpath("//*[@id=\"SearchForm:PaymentMethod\"]/div[3]"));
        paymentMethoddropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+paymentmethod+"']")).click();

    }

    @And("^clicks search$")
    public void clicks_search() throws Throwable {

        WebElement searchButton = driver.findElement(By.id("SearchForm:j_idt42"));
        searchButton.click();
    }

    ////---------------------------------------------------------------Bulk Upload - Payments--------------------------------------------------------------------------///

    @Given("^navigate Revenue Collection>>Bulk Payment$")
    public void navigate_revenue_collectionbulk_payment() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("BulkPayment_Xpath"))).click();
        Thread.sleep(3000);
    }

    @When("^user clicks on browse button$")
    public void user_clicks_on_browse_button() throws Throwable {
        WebElement browseBtn=driver.findElement(By.xpath("//*[@id='BulkPayment:UploadFile']/span[1]"));
        Assert.assertTrue(browseBtn.isDisplayed());
    }

    @And("^Selects the file to be uploaded (.+)$")
    public void selects_the_file_to_be_uploaded(String path) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("BulkPayment:UploadFile_input")).sendKeys(path);
    }

    @And("^Then click Save Button on upload$")
    public void then_click_save_button_on_upload() throws Throwable {
        WebElement saveUploadButton = driver.findElement(By.id("BulkPayment:save"));
        saveUploadButton.click();
        Thread.sleep(5000);
    }

    @Then("^System displays message Records Not Found$")
    public void system_displays_message_records_not_found() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String emptyDatatable = driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr/td")).getText();
        Assert.assertEquals(emptyDatatable, "No records found.");
    }

    @And("^clicks search button$")
    public void clicks_search_button() throws Throwable {
        WebElement searchButton = driver.findElement(By.id("SearchForm:j_idt21"));
        searchButton.click();
    }

    @Then("^Payment Summary window displayed (.+)$")
    public void payment_summary_window_displayed(String tin) throws Throwable {
//    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(8000);
        String paymentSummaryLabel = driver.findElement(By.id("PaymentSummary:TaxpayerHeader")).getText();
        Assert.assertEquals(paymentSummaryLabel, "Taxpayer Account");

//        WebElement TIN = driver.findElement(By.id("PaymentSummary:TIN"));
//        Thread.sleep(2000);
//        Assert.assertEquals(tin, TIN.getAttribute("value"));

    }

    @And("^clicks yes on payment confirmation popup$")
    public void clicks_yes_on_payment_confirmation_popup() throws Throwable {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(4000);
        driver.findElement(By.id("PaymentDetails:j_idt46")).click();
    }

    @When("^From Payment Summary window enters (.+)$")
    public void from_payment_summary_window_enters(String nameofpersonpaying) throws Throwable {
        WebElement personPayingInput = driver.findElement(By.id("PaymentSummary:personPaying_id"));
        personPayingInput.sendKeys(nameofpersonpaying);
        Thread.sleep(4000);
    }

    @And("^clicks add button$")
    public void clicks_add_button() throws Throwable {
        WebElement paymentSummaryAddBtn = driver.findElement(By.id("PaymentSummary:PaymentListDataTable:AddBtn"));

        Actions actions = new Actions(driver);
        actions.doubleClick(paymentSummaryAddBtn).perform();
        Thread.sleep(9000);
    }

    @Then("^Payment Details should be displayed$")
    public void payment_details_should_be_displayed() throws Throwable {
        Thread.sleep(10000);
        String paymentDetailsHeader = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion\"]/ul/li")).getText();
        Assert.assertEquals(paymentDetailsHeader, "Payment Details");

    }

    @When("^user fills Payment Amount as (.+)$")
    public void user_fills_payment_amount_as(String amount) throws Throwable {
        WebElement paymentAmountInput = driver.findElement(By.id("PaymentDetails:paymentAccordion:paymentAmount_id_input"));
        paymentAmountInput.sendKeys(amount);
    }

    @And("^clicks Next button$")
    public void clicks_next_button() throws Throwable {
        WebElement nextBtn = driver.findElement(By.id("PaymentDetails:paymentAccordion:Next"));
        nextBtn.click();
        Thread.sleep(4000);
    }

    @Then("^Payment Allocation Summary tab should be displayed$")
    public void payment_allocation_summary_tab_should_be_displayed() throws Throwable {
        WebElement paymentAllocationSummaryTab = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion\"]/ul/li[2]"));
        Assert.assertTrue(paymentAllocationSummaryTab.isDisplayed());
    }

    @When("^clicks on On Account Button$")
    public void clicks_on_on_account_button() throws Throwable {
        WebElement onAccountBtn = driver.findElement(By.id("PaymentDetails:paymentAccordion:PaymentSpread:OnAccount"));
        onAccountBtn.click();
        Thread.sleep(4000);
    }

    @Then("^Account Payment Details pop up window should be displayed$")
    public void account_payment_details_pop_up_window_should_be_displayed() throws Throwable {
        Thread.sleep(7000);
        WebElement paymentonAccountHeader = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion:PaymentSpread:OnAccount_dlg\"]/div[1]"));
        Assert.assertTrue(paymentonAccountHeader.isDisplayed());
    }

    @Then("^Find document Details pop up window should be displayed$")
    public void find_document_details_pop_up_window_should_be_displayed() throws Throwable {
        Thread.sleep(7000);
        WebElement paymentonAccountHeader = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion:PaymentSpread:DocumentAllocation_dlg\"]/div[1]"));
        Assert.assertTrue(paymentonAccountHeader.isDisplayed());
    }

    @When("^On Account Payment Details enters (.+) and (.+)$")
    public void on_account_payment_details_enters_and(String taxtype, String amountallocated) throws Throwable {
        //Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);

        //Enter tax type
        WebElement taxTypedropDown = driver.findElement(By.xpath("//*[@id=\"OnAccountPayment:RegimeType\"]/div[3]"));
        taxTypedropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+taxtype+"']")).click();
        Thread.sleep(7000);
        //Enter returnType
//        WebElement returnTypeDropdown = driver.findElement(By.xpath("//*[@id=\"OnAccountPayment:ReturnType\"]/div[3]"));
//        returnTypeDropdown.click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("OnAccountPayment:ReturnType_1")).click();

        //Enter amount
        Thread.sleep(2000);
        driver.findElement(By.id("OnAccountPayment:AmountAllocated_input")).sendKeys(amountallocated);
    }

    @And("^clicks ok$")
    public void clicks_ok() throws Throwable {

        WebElement okButton = driver.findElement(By.id("OnAccountPayment:Ok"));
        okButton.click();
        Thread.sleep(4000);
    }

    @And("^clicks ok on empty fields$")
    public void clicks_ok_on_empty_fields() throws Throwable {
//    	//Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);

        WebElement okButton = driver.findElement(By.id("OnAccountPayment:Ok"));
        okButton.click();
        Thread.sleep(4000);
    }

    @Given("^From Payment Details window click on Save Button$")
    public void from_payment_details_window_click_on_save_button() throws Throwable {
        WebElement paymentAllocationBtn = driver.findElement(By.id("PaymentDetails:Ok"));
        paymentAllocationBtn.click();
        Thread.sleep(4000);
    }

    @Given("^Payment Summary window click on Save Button$")
    public void payment_summary_window_click_on_save_button() throws Throwable {
        WebElement paymentSummarySaveBtn = driver.findElement(By.id("PaymentSummary:Save"));
        paymentSummarySaveBtn.click();
        Thread.sleep(4000);
    }

    @And("^check non registered taxpayer$")
    public void check_non_registered_taxpayer() throws Throwable {
        WebElement nonRegisteredTaxCheckBox = driver.findElement(By.xpath("//*[@id=\"PaymentSummary:taxpayerType\"]/div[2]"));
        nonRegisteredTaxCheckBox.click();
    }

    @And("^enters Non Registered Taxpayer Details (.+) and (.+) and (.+) and (.+)$")
    public void enters_non_registered_taxpayer_details_and_and_and(String firstname, String lastname, String nationalidno, String address) throws Throwable {
        Thread.sleep(4000);
        WebElement firstNameInput = driver.findElement(By.id("PaymentSummary:FirstName"));
        firstNameInput.sendKeys(firstname);

        WebElement lastNameInput = driver.findElement(By.id("PaymentSummary:LastName"));
        lastNameInput.sendKeys(lastname);

        WebElement nationalidnoInput = driver.findElement(By.id("PaymentSummary:NationalId"));
        nationalidnoInput.sendKeys(nationalidno);

        WebElement addressInput = driver.findElement(By.id("PaymentSummary:Address"));
        addressInput.sendKeys(address);
    }

    @And("^clicks proceed on the popup$")
    public void clicks_proceed_on_the_popup() throws Throwable {
        Thread.sleep(4000);
        //Switch to iframe to allow interaction with modal
//        WebElement frame = driver.findElement(By.tagName("iframe"));
//        driver.switchTo().frame(frame);
//        Thread.sleep(2000);

        WebElement confirmationYesButton = driver.findElement(By.id("PaymentDetails:j_idt46"));
        confirmationYesButton.click();
    }

    @And("^Payment list click on Save Button$")
    public void payment_list_click_on_save_button() throws Throwable {
        Thread.sleep(4000);
        WebElement saveBtn=driver.findElement(By.id("PaymentSummary:Save"));
        saveBtn.click();
    }

    @Then("^account payment error message is displayed \"([^\"]*)\"$")
    public void account_payment_error_message_is_displayed_something(String strArg1) throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'"+strArg1+"')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed",true);
        }else {
            Assert.fail("No Error message displayed");
        }
    }

    @And("^enters designation (.+)$")
    public void enters_designation(String designation) throws Throwable {
        WebElement designationInput = driver.findElement(By.id("PaymentSummary:designation_id"));
        designationInput.sendKeys(designation);
    }

    @Then("^Reciept generated successfully$")
    public void reciept_generated_successfully() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'PaymentReceipt.pdf')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
    }

    @Then("^message is displayed \"([^\"]*)\"$")
    public void message_is_displayed_something(String strArg1) throws Throwable {
//    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver,10);

        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+strArg1+"')]")));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed",true);
        }else {
            Assert.fail("No Error message displayed");
        }
    }
    @When("^clicks on Document Allocation Button$")
    public void clicks_on_document_allocation_button() throws Throwable {
        WebElement docAllocationBtn = driver.findElement(By.id("PaymentDetails:paymentAccordion:PaymentSpread:DocumentAllocation"));
        docAllocationBtn.click();
        Thread.sleep(4000);
    }

    //--------------------------------------Exemptions--------------------------------------------------\\\\\\\\

    @Given("^User navigates to Exemptions Applications>>New Exemptions Applications$")
    public void user_navigates_to_exemptions_applicationsnew_exemptions_applications() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Exemptions_Applications_Button"))).click();
        driver.findElement(By.xpath(Pro.getProperty("New_Exemption_Button"))).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @When("^user inputs (.+) and (.+) and (.+) to fill form$")
    public void user_inputs_and_and_to_fill_form(String category, String tin, String exemptioncode) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("ExemptionApplicationFormsearch"))).click();
        Thread.sleep(3000);
        WebElement frame = driver.findElement(By.tagName("iframe"));

        //Switch to iframe to allow interaction with modal
        driver.switchTo().frame(frame);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id(Pro.getProperty("SearchFormTIN"))).sendKeys(tin);
        driver.findElement(By.id(Pro.getProperty("SearchFormbutton"))).click();

        WebDriverWait wait=new WebDriverWait(driver, 100);
        Boolean TIN = wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("ExemptionApplicationForm:employerTIN"), tin));
        Assert.assertTrue(TIN);

        System.out.print(category);
        driver.findElement(By.id("ExemptionApplicationForm:exemptionType_label")).click();
        String StatusXpath = "//li[@data-label='"+category+"']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(3000);
//   	 Actions action = new Actions(driver);
        driver.findElement(By.xpath("//*[@id='ExemptionApplicationForm:exemptionCode']/div[3]")).click();
        driver.findElement(By.xpath("//li[@data-label='"+exemptioncode+"']")).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("taxType"))).click();

        driver.findElement(By.id(Pro.getProperty("taxType1"))).click();

        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("returnType"))).click();

        driver.findElement(By.id(Pro.getProperty("returnType1"))).click();

        WebElement Date =driver.findElement(By.id(Pro.getProperty("dateSelector")));
        Date.clear();
        Date.sendKeys(Keys.ENTER);

    }

    @And("^user Clicks on Add under ButtonExemption Qualification Attribute$")
    public void user_clicks_on_add_under_buttonexemption_qualification_attribute() throws Throwable {

        driver.findElement(By.id(Pro.getProperty("Exemption_Qualification_Add"))).click();
        Thread.sleep(5000);
    }

    @Then("^Exemption Application Qualification Attributes pop up should be displayed$")
    public void exemption_application_qualification_attributes_pop_up_should_be_displayed() throws Throwable {
        WebElement frame = driver.findElement(By.tagName("iframe"));
        Assert.assertEquals(true, frame.isDisplayed());
        //Switch to iframe to allow interaction with modal
//    	String exemptionPopup = driver.findElement(By.xpath(Pro.getProperty("ExemptionQualificationPopup"))).getText();
//    	Assert.assertEquals(exemptionPopup, "Exemption Application - Qualification Attributes");
        driver.switchTo().frame(frame);
        Thread.sleep(3000);

        String attributeName = driver.findElement(By.id(Pro.getProperty("attributeName"))).getText();
        Assert.assertEquals(attributeName, "Attribute Name*");

        //Add a qualification attribute if it exists if not cancel dropdown
        WebElement attributesDropdown=driver.findElement(By.xpath("//*[@id=\"ExemptionAppQualificationAttributeForm:attributeName\"]/div[3]"));
        attributesDropdown.click();

        Thread.sleep(3000);
        boolean isPresent=driver.findElements(By.id("ExemptionAppQualificationAttributeForm:attributeName_1")).size() >0;
        if(isPresent){
            driver.findElement(By.id("ExemptionAppQualificationAttributeForm:attributeName_1")).click();
            Thread.sleep(2000);
            driver.findElement(By.id(Pro.getProperty("openQualificationAttribute"))).click();

        }else{
            driver.findElement(By.id("ExemptionAppQualificationAttributeForm:attributeName_0")).click();
            Thread.sleep(2000);
            driver.findElement(By.id(Pro.getProperty("closeQualificationAttribute"))).click();
        }
        Thread.sleep(3000);
    }

    @When("^user  Click on Add under Attachment Schedule$")
    public void user_click_on_add_under_attachment_schedule() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("addAttchSchedule"))).click();
    }

    @Then("^Exemption Application Attachment pop up should be displayed$")
    public void exemption_application_attachment_pop_up_should_be_displayed() throws Throwable {
        WebElement frame = driver.findElement(By.tagName("iframe"));
        //Switch to iframe to allow interaction with modal
        driver.switchTo().frame(frame);
        Thread.sleep(3000);

        String attributeName = driver.findElement(By.id(Pro.getProperty("attachmentName"))).getText();
        Assert.assertEquals(attributeName, "Attachment Name*");


    }

    @When("^attachment added \"([^\"]*)\"$")
    public void attachment_added_something(String strArg1) throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("attachmentSchedule"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("attachmentType1"))).click();

        Thread.sleep(2000);
        WebElement attachment = driver.findElement(By.id(Pro.getProperty("documentAttachment")));
        Thread.sleep(2000);
        attachment.sendKeys(strArg1);

        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("enterAttachment"))).click();
        Thread.sleep(3000);
    }

    @Then("^attachment popup closed$")
    public void attachment_popup_closed() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 20);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ExemptionApplicationForm")));
        Assert.assertTrue(search.isDisplayed());
    }

    @When("^User adds Declaration Info (.+) (.+) (.+)$")
    public void user_adds_declaration_info(String applicant, String designation, String supervisortin) throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.id(Pro.getProperty("applicantName"))).sendKeys(applicant);

        driver.findElement(By.id(Pro.getProperty("designation"))).sendKeys(designation);

        driver.findElement(By.id(Pro.getProperty("supervisorTin"))).sendKeys(supervisortin);

        driver.findElement(By.xpath(Pro.getProperty("checkbox"))).click();

        driver.findElement(By.id(Pro.getProperty("submitExemption"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("submitExemption"))).click();


    }

    @Then("^Exemption created successfully$")
    public void exemption_created_successfully() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Processing Completed')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
        String Text=Message.getText();
        String ARN = Text.substring(Text.lastIndexOf(" ")+1);
        sharedatastep.E_CRMARN=ARN;
        System.out.print("Reference Number is -" + sharedatastep.E_CRMARN);
    }

    @Then("^Exemption error displayed$")
    public void exemption_error_displayed() throws Throwable {
        Thread.sleep(4000);

        WebElement Table_Column = driver.findElement(By.xpath("//span[contains(text(),'Exemption Attachments Value is Required')]"));
        if(Table_Column.isDisplayed()) {
            Assert.assertTrue("error message displayed",true);
        }else {
            Assert.fail("No error message displayed");
        }
    }

    @Then("^Home Page Should be Displayed with User's Queue$")
    public void home_page_should_be_displayed_with_users_queue() throws Throwable {
        Thread.sleep(4000);
        String caseManagement = driver.findElement(By.xpath(Pro.getProperty("caseManagement"))).getText();
        Assert.assertEquals(caseManagement, "Case Management");

        String dashboards = driver.findElement(By.xpath(Pro.getProperty("dashboards"))).getText();
        Assert.assertEquals(dashboards, "Dashboards");
    }

    @And("^click on Exemption application$")
    public void click_on_exemption_application() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("crmExemption"))).click();

    }

    @Then("^Information window should be displayed$")
    public void information_window_should_be_displayed() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String caseType = driver.findElement(By.xpath(Pro.getProperty("caseType"))).getText();
        Assert.assertEquals(caseType, "Case Type");

        String caseTypeValue = driver.findElement(By.xpath(Pro.getProperty("caseTypeValue"))).getText();
        Assert.assertEquals(caseTypeValue, "Exemption");

        String createdOn = driver.findElement(By.xpath(Pro.getProperty("createdOn"))).getText();
        Assert.assertEquals(createdOn, "Created On");

//        WebElement exemptionType = driver.findElement(By.xpath(Pro.getProperty("exemptionType")));
//        Assert.assertFalse(exemptionType.isEnabled());

    }

    @And("^enters Exemption reference number in search results$")
    public void enters_exemption_reference_number_in_search_results() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 20);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));
        search.sendKeys(sharedatastep.E_CRMARN);
//    	search.sendKeys("EA00001045");
        Thread.sleep(2000);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }
    @Given("^Open CRM URL for Accounting Module$")
    public void open_CRM_URL_for_Accounting_Module() throws Throwable {
        driver = BaseClass.getDriver();
        driver.get(Pro.getProperty("MRA_crm_url_Registration"));
    }

    @And("^click save on exemption$")
    public void click_save_on_exemption() throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_exemptionapplication|NoRelationship|Form|Mscrm.Form.tbg_exemptionapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    ////////////////////////////////////////////////----------------cancel-exemption----------------------------------------------------//

    @Given("^User navigates to Exemptions Applications>>Cancel Exemptions$")
    public void user_navigates_to_exemptions_applicationscancel_exemptions() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Exemptions_Applications_Button"))).click();
        driver.findElement(By.xpath(Pro.getProperty("cancel_Exemption_Button"))).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^user fills (.+) on Find Exemption Application$")
    public void user_fills_on_find_exemption_application(String tin) throws Throwable {

        driver.findElement(By.id("SearchForm:tin")).sendKeys(tin);

        Thread.sleep(3000);
        driver.findElement(By.id("SearchForm:j_idt42")).click();

        //If multiple exemptions exists it selects the first
        List<WebElement> column = driver.findElements(By.xpath("//tr[@data-ri=\"0\"]"));

        if(!column.isEmpty()) {
            column.get(0).click();
            driver.findElement(By.id("SearchForm:j_id22")).click();
        }
        Thread.sleep(2000);

    }

    @Then("^Exemptions Applications window displayed with (.+) and (.+)and (.+)and (.+) in read only mode$")
    public void exemptions_applications_window_displayed_with_and_and_and_in_read_only_mode(String tin, String exemptioncategory, String ecrtype, String applicationstatus) throws Throwable {
        WebElement referenceNumber = driver.findElement(By.id("ExemptionApplicationForm:referenceNumber"));
        Assert.assertFalse(referenceNumber.isEnabled());

        WebElement TIN = driver.findElement(By.id("ExemptionApplicationForm:employerTIN"));
        Assert.assertFalse(TIN.isEnabled());
//        Assert.assertEquals(TIN.getAttribute("value"), tin);

        WebElement application = driver.findElement(By.id("ExemptionApplicationForm:exemptionType_label"));
//        Assert.assertEquals(application.getText(), ecrtype);

        WebElement taxpayerName = driver.findElement(By.id("ExemptionApplicationForm:taxpayerName"));
        Assert.assertFalse(taxpayerName.isEnabled());

        WebElement email = driver.findElement(By.id("ExemptionApplicationForm:email"));
        Assert.assertFalse(email.isEnabled());

        WebElement description = driver.findElement(By.id("ExemptionApplicationForm:description"));
        Assert.assertFalse(description.isEnabled());

        WebElement searchButton = driver.findElement(By.id("ExemptionApplicationForm:searchId"));
        Assert.assertFalse(searchButton.isEnabled());

    }

    @Then("^The System saves the Exemption application$")
    public void the_system_saves_the_exemption_application() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Processing Completed')]"));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Success message displayed",true);
        }else {
            Assert.fail("No Success message displayed");
        }
        String Text=Message.getText();
        String arr[] = Text.split(" ", 9);
        String ARN = arr[6];
        sharedatastep.E_CRMARN=ARN;
        System.out.print("Reference Number is - " + sharedatastep.E_CRMARN);
    }

    @Given("^User navigates to Exemptions Applications>>suspend Exemptions$")
    public void user_navigates_to_exemptions_applicationssuspend_exemptions() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Exemptions_Applications_Button"))).click();
        driver.findElement(By.xpath(Pro.getProperty("suspend_Exemption_Button"))).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("^clicks submit$")
    public void clicks_submit() throws Throwable {
        WebElement submitButton = driver.findElement(By.id("ExemptionApplicationForm:idSave"));
        submitButton.click();
    }

    @Given("^User navigates to Exemptions Applications>>Reactivate Exemptions$")
    public void user_navigates_to_exemptions_applicationsreactivate_exemptions() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Exemptions_Applications_Button"))).click();
        driver.findElement(By.xpath(Pro.getProperty("reactivate_Exemption_Button"))).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //--------------------------------------Taxtype registration -Portal----------------------------------------------------------------------------------------------//
    @Given("^User navigates to the Portal login page$")
    public void user_navigates_to_the_portal_login_page() throws Throwable {
        driver = BaseClass.getDriver();
        driver.get(Pro.getProperty("PortalURL"));
    }

    @And("^Enters the Portal username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_portal_username_something_and_password_something_to_login(String strArg1, String strArg2) throws Throwable {
        Thread.sleep(5000);
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"id_userName\"]"));
        usernameInput.sendKeys(strArg1);

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"id_password\"]"));
        passwordInput.sendKeys(strArg2);

        WebElement loginBtn = driver.findElement(By.id("btnSubmit"));
        loginBtn.click();
    }

    @When("^User clicks login as Taxpayer$")
    public void user_clicks_login_as_taxpayer() throws Throwable {
        Thread.sleep(5000);
        WebElement taxPayer = driver.findElement(By.xpath("/html/body/trips-app/div/app-portal-home/div/div/div[1]/div[3]/div[1]/p/a"));
        taxPayer.click();
    }

    @Then("^is logged in to portal$")
    public void is_logged_in_to_portal() throws Throwable {
        Thread.sleep(5000);
        WebElement welcomeImage=driver.findElement(By.id("id_btnMyTaxToggle"));
        Assert.assertTrue(welcomeImage.isDisplayed());
    }
    @Then("^is logged in to taxpayer portal$")
    public void is_logged_in_to_taxpayer_portal() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,30);
        WebElement welcomeImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_btnMyTaxToggle")));
        Assert.assertTrue(welcomeImage.isDisplayed());
    }


    @Given("^user navigates to my tax>>taxtype request$")
    public void user_navigates_to_my_taxtaxtype_request() throws Throwable {
        WebElement myTaxDropdown=driver.findElement(By.xpath("//*[@id=\"id_btnMyTaxToggle\"]/span"));
        myTaxDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_linkTaxTypeRequest")).click();
    }

    @Given("^user navigates to my tax>>request suspension$")
    public void user_navigates_to_my_taxrequest_suspension() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement myTaxDropdown=driver.findElement(By.xpath("//*[@id=\"id_btnMyTaxToggle\"]/span"));
        myTaxDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_linkRequestSuspension")).click();
    }

    @And("^enters taxtype as (.+)$")
    public void enters_taxtype_as(String taxtype) throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,120);
        WebElement taxTypeDropdown=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_taxTypes\"]/div/div[2]/p-dropdown/div/div[3]")));
        taxTypeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='"+taxtype+"']")).click();
    }

    @And("^enters an effective date (.+)$")
    public void enters_an_effective_date(String date) throws Throwable {
        WebElement effectiveDateInput=driver.findElement(By.id("id_edr"));
        effectiveDateInput.click();
        Thread.sleep(2000);
        effectiveDateInput.sendKeys(date);
        Thread.sleep(2000);
    }

    @And("^enters taxtype taxable turnover (.+)$")
    public void enters_taxtype_taxable_turnover(String amount) throws Throwable {
        WebElement taxableTurnoverInput=driver.findElement(By.id("id_taxableTurnover"));
        taxableTurnoverInput.sendKeys(amount);
    }

    @And("^clicks taxtype registration Save Button$")
    public void clicks_taxtype_registration_save_button() throws Throwable {
        WebElement saveBtn=driver.findElement(By.id("btnSave"));
        saveBtn.click();
    }

    @Then("^Portal message is displayed \"([^\"]*)\"$")
    public void portal_message_is_displayed_something(String strArg1) throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,50);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '"+strArg1+"')]")));
        Assert.assertTrue(message.isDisplayed());
    }

    @Then("^SUSPEND DORMANT TAX TYPE screen displayed$")
    public void suspend_dormant_tax_type_screen_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement taxTypeDropdown = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        Assert.assertTrue(taxTypeDropdown.isEnabled());

        WebElement dormantCheckbox = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"));
        Assert.assertTrue(dormantCheckbox.isEnabled());

        WebElement inputBox = driver.findElement(By.id("id_notes"));
        Assert.assertTrue(inputBox.isEnabled());

        WebElement submitBtn = driver.findElement(By.id("btnSubmit"));
        Assert.assertTrue(submitBtn.isEnabled());

        WebElement cancelBtn = driver.findElement(By.id("btnCancel"));
        Assert.assertTrue(cancelBtn.isEnabled());

    }
    @And("^enters suspension start date$")
    public void enters_suspension_start_date() throws Throwable {
        WebElement suspensionDateInput=driver.findElement(By.id("id_suspensionStartDate"));
        suspensionDateInput.sendKeys(todaysDate());
    }

    @And("^enters suspension end date$")
    public void enters_suspension_end_date() throws Throwable {
        WebElement endSuspensionDateInput=driver.findElement(By.id("id_suspensionEndDate"));
        endSuspensionDateInput.sendKeys(tomorrowsDate());
    }

    @And("^enters reason for suspension$")
    public void enters_reason_for_suspension() throws Throwable {
        WebElement reasonDropdown=driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        reasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]"));
    }

    @And("^clicks Suspension Save Button$")
    public void clicks_suspension_save_button() throws Throwable {
        WebElement saveBtn=driver.findElement(By.id("btnSubmit"));
        saveBtn.click();
    }

    @And("^checks dormant account$")
    public void checks_dormant_account() throws Throwable {
        WebElement dormantCheckbox = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"));
        dormantCheckbox.click();
    }

    @And("^enters dormant start date$")
    public void enters_dormant_start_date() throws Throwable {
        WebElement effectiveDateInput=driver.findElement(By.id("id_dormantStartDate"));
        effectiveDateInput.sendKeys(todaysDate());

    }
    @Given("^user navigates to my tax>>request reactivation$")
    public void user_navigates_to_my_taxrequest_reactivation() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement myTaxDropdown=driver.findElement(By.xpath("//*[@id=\"id_btnMyTaxToggle\"]/span"));
        myTaxDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_linkRequestReActivation")).click();
    }

    @Then("^REACTIVATE TAX TYPE screen displayed$")
    public void reactivate_tax_type_screen_displayed() throws Throwable {
        WebElement taxTypeDropdown = driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/label"));
        Assert.assertTrue(taxTypeDropdown.isEnabled());

        WebElement reactivationDate = driver.findElement(By.id("id_reactivationDate"));
        Assert.assertTrue(reactivationDate.isEnabled());

        WebElement reasonDropdown = driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/label"));
        Assert.assertTrue(reasonDropdown.isEnabled());

        WebElement notesField = driver.findElement(By.id("id_notes"));
        Assert.assertTrue(notesField.isEnabled());

    }

    @And("^enters reactivation date$")
    public void enters_reactivation_date() throws Throwable {
        WebElement reactivationDateInput=driver.findElement(By.id("id_reactivationDate"));
        reactivationDateInput.sendKeys(todaysDate());
    }

    @And("^enters reason for reactivation$")
    public void enters_reason_for_reactivation() throws Throwable {
        WebElement reasonDropdown=driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        reasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]"));
    }

    @And("^clicks reactivation Save Button$")
    public void clicks_reactivation_save_button() throws Throwable {
        WebElement saveBtn=driver.findElement(By.id("btnSubmit"));
        saveBtn.click();
    }

    @And("^enters number of employees as \"([^\"]*)\"$")
    public void enters_number_of_employees_as_something(String strArg1) throws Throwable {
        Thread.sleep(3000);
        WebElement employeeNo=driver.findElement(By.id("id_noOfEmployees"));
        employeeNo.sendKeys(strArg1);
    }

    //--------------------------geeta code----------------------------------------------------------------///
    ///////--------------------------geeta code----------------------------------------------------------------///

    @When("^User clicks login as Applicant$")
    public void user_clicks_login_as_applicant() throws Throwable {
        Thread.sleep(5000);
        WebElement taxPayer = driver.findElement(By.xpath("/html/body/trips-app/div/app-portal-home/div/div/div[1]/div[3]/div[2]/p/a"));
        taxPayer.click();
    }

    @And("^clicks register now$")
    public void clicks_register_now() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.id("id_linkRegisterNow")).click();
    }

    @Then("^create portal credential page is displayed$")
    public void create_portal_credential_page_is_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement header=driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/ng-component/div"));
        Assert.assertTrue(header.isDisplayed());
    }

    @Then("^successfully logged in to appplicant portal$")
    public void successfully_logged_in_to_appplicant_portal() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,60);
        WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[1]/ul/li[1]")));
        Assert.assertTrue(nav.isDisplayed());
        Thread.sleep(4000);
    }

    @Then("^successfully logged in to organisation portal$")
    public void successfully_logged_in_to_organisation_portal() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,30);
        WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[1]/ul/li[1]")));
        Assert.assertTrue(nav.isDisplayed());
        Thread.sleep(4000);
    }

    @Given("^user clicks applicant submit button with fields blank$")
    public void user_clicks_applicant_submit_button_with_fields_blank() throws Throwable {
        WebElement submitBtn=driver.findElement(By.id("id_btnSubmit"));
        Assert.assertTrue(submitBtn.isDisplayed());
    }

    @Then("^submit button should not be clickable$")
    public void submit_button_should_not_be_clickable() throws Throwable {
        WebElement submitBtn=driver.findElement(By.id("id_btnSubmit"));
        Assert.assertFalse(submitBtn.isEnabled());
    }

    @Given("^user inputs identification details$")
    public void user_inputs_identification_details(DataTable table) throws Throwable {
        List<List<String>> data =table.asLists();

        WebElement firstNameInput=driver.findElement(By.id("id_firstName"));
        firstNameInput.sendKeys(data.get(0).get(1));

        WebElement lastNameInput=driver.findElement(By.id("id_lastName"));
        lastNameInput.sendKeys(data.get(1).get(1));

        WebElement genderDropDown=driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/div[3]"));
        genderDropDown.click();
        Thread.sleep(2000);
        // clicks male from dropdown
        driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[3]")).click();

        WebElement DOBInput=driver.findElement(By.id("id_dateOfBirth"));
        DOBInput.sendKeys(data.get(2).get(1));

        WebElement POBInput=driver.findElement(By.id("id_placeOfBirth"));
        POBInput.sendKeys(data.get(3).get(1));

        WebElement IdInput=driver.findElement(By.id("id_idenNumber"));
        IdInput.sendKeys(data.get(4).get(1));

        WebElement issueDate=driver.findElement(By.id("id_IssueDate"));
        issueDate.sendKeys(data.get(5).get(1));

        WebElement expiryDate=driver.findElement(By.id("id_expiryDate"));
        expiryDate.sendKeys(data.get(6).get(1));

        // clicks on country drop down then enters value in datatable
        WebElement countryDropdown=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        countryDropdown.click();
        Thread.sleep(2000);
        WebElement countryInput=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        countryInput.sendKeys(data.get(7).get(1));

        //clicks on the search result in drop down after entering country
        WebElement firstEntry=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        firstEntry.click();

    }

    @When("^user selects correct (.+) and (.+)$")
    public void user_selects_correct_and(String taxpayerregistrationtype, String identificationtype) throws Throwable {
        WebElement registrationType=driver.findElement(By.xpath("//span[contains(text(),'"+taxpayerregistrationtype+"')]"));
        registrationType.click();

        WebElement identificationDropdown=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]/span"));
        identificationDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+identificationtype+"')]")).click();

        // clicks on nationality down then enters value in datatable
        try {
            WebElement nationalityDropdown=driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
            nationalityDropdown.click();
            Thread.sleep(2000);
            WebElement nationalityInput = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
            nationalityInput.sendKeys("malawi");

            //clicks on the search result in drop down after entering country
            WebElement myNationality = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
            myNationality.click();
        } catch(NoSuchElementException | StaleElementReferenceException e) {

        }

    }

    @And("^clicks create portal credential validate id button$")
    public void clicks_create_portal_credential_validate_id_button() throws Throwable {
        WebElement validateId=driver.findElement(By.id("id_btnValidate"));
        validateId.click();
    }

    @Then("^successful validation message appears$")
    public void successful_validation_message_appears() throws Throwable {
        Thread.sleep(5000);
        WebElement validateId=driver.findElement(By.id("id_btnValidate"));
        Assert.assertFalse(validateId.isEnabled());
    }

    @Given("^user clicks contacts$")
    public void user_clicks_contacts() throws Throwable {
//        WebElement contacts=driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[1]/ul/li[2]"));
//        if(contacts.isEnabled()){
//            contacts.click();
//        }else {
        driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[2]/div[3]/button")).click();
//        }
//        Thread.sleep(3000);
    }

    @Given("^user clicks Personal Details$")
    public void user_clicks_personal_details() throws Throwable {
        WebElement personalDetails=driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[1]/ul/li[1]"));
        personalDetails.click();
    }

    @Given("^user enters valid (.+) and (.+)$")
    public void user_enters_valid_and(String email, String password) throws Throwable {
        WebElement emailInput=driver.findElement(By.id("id_email"));
        emailInput.sendKeys(email);

        WebElement confirmEmailInput=driver.findElement(By.id("id_confirmEmail"));
        confirmEmailInput.sendKeys(email);

        WebElement passwordInput=driver.findElement(By.id("id_password"));
        passwordInput.sendKeys(password);

        WebElement confirmPasswordInput=driver.findElement(By.id("id_confirmPassword"));
        confirmPasswordInput.sendKeys(password);
    }

    @And("^attaches id document (.+)$")
    public void attaches_id_document(String path) throws Throwable {
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"id_fileChoose\"]/div/div[2]/div/div/div[1]/span")).click();
        driver.switchTo()
                .activeElement()
                .sendKeys(
                        path);
        driver.switchTo();
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ESCAPE).perform();
    }

    @And("^checks captcha$")
    public void checks_captcha() throws Throwable {
        Thread.sleep(2000);
        WebElement captcha=driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]"));
        captcha.click();
    }


    @And("^clicks the submit button$")
    public void clicks_the_submit_button() throws Throwable {
        WebElement submitBtn=driver.findElement(By.id("id_btnSubmit"));
        submitBtn.click();
    }

    @And("^enters Portal email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void enters_portal_email_something_and_password_something(String strArg1, String strArg2) throws Throwable {
        Thread.sleep(5000);
        WebElement emailInput=driver.findElement(By.id("id_userName"));
        emailInput.sendKeys(strArg1);

        WebElement passwordInput=driver.findElement(By.id("id_password"));
        passwordInput.sendKeys(strArg2);

        WebElement loginBtn=driver.findElement(By.id("btnSubmit"));
        loginBtn.click();

    }

    @And("^user enters Applicant Individual details$")
    public void user_enters_applicant_individual_details(DataTable table) throws Throwable {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        List<List<String>> data =table.asLists();

        WebElement firstNameInput=driver.findElement(By.id("id_firstName"));
        firstNameInput.clear();
        firstNameInput.sendKeys(data.get(0).get(1));

        WebElement lastNameInput=driver.findElement(By.id("id_lastName"));
        lastNameInput.clear();
        lastNameInput.sendKeys(data.get(1).get(1));

        WebElement dob=driver.findElement(By.id("id_dateOfBirth"));
        dob.clear();
        dob.sendKeys(data.get(2).get(1));
        Thread.sleep(2000);
//        driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[2]/td[7]/a")).click();


        Thread.sleep(2000);

//        driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]/a")).click();

        WebElement pobInput=driver.findElement(By.id("id_placeOfBirth"));
        pobInput.clear();
        pobInput.sendKeys(data.get(3).get(1));

        WebElement categoryDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        categoryDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(4).get(1)+"')]")).click();

        WebElement titleDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        titleDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(5).get(1)+"')]")).click();

        WebElement genderDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[4]/div/div[2]/p-dropdown/div/div[3]"));
        genderDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(6).get(1)+"')]")).click();

        WebElement maritalDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[5]/div/div[2]/p-dropdown/div/div[3]"));
        maritalDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(7).get(1)+"')]")).click();

        WebElement nationalityDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[6]/div/div[2]/p-dropdown/div/div[3]"));
        nationalityDropdown.click();
        Thread.sleep(2000);
        WebElement nationalityInput=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[6]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        nationalityInput.sendKeys(data.get(8).get(1));

        //clicks on the search result in drop down after entering country
        WebElement oneEntry=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[6]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        oneEntry.click();

        // clicks on country drop down then enters value in datatable
        WebElement countryDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[7]/div/div[2]/p-dropdown/div/div[3]"));
        countryDropdown.click();
        Thread.sleep(2000);
        WebElement countryInput=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[7]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        countryInput.sendKeys(data.get(8).get(1));

        //clicks on the search result in drop down after entering country
        WebElement firstEntry=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[7]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        firstEntry.click();

        WebElement taxOfficeDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[8]/div/div[2]/p-dropdown/div/div[3]"));
        taxOfficeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(9).get(1)+"')]")).click();

        WebElement reasonTinDropdown=driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown-with-othertext/div/div[2]/p-dropdown/div/div[3]"));
        reasonTinDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(10).get(1)+"')]")).click();
    }

    @And("^clicks save as draft$")
    public void clicks_save_as_draft() throws Throwable {
        WebElement saveDraftBtn=driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[5]/div/div[2]/button"));
        saveDraftBtn.click();
    }

    @Then("^success message and exit confirmation dislayed$")
    public void success_message_and_exit_confirmation_dislayed() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,20);
        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Exit Confirmation')]")));
        if(Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed",true);
        }else {
            Assert.fail("No Error message displayed");
        }

        //  click yes to exit confirmation modal
        driver.findElement(By.xpath("/html/body/trips-app/p-confirmdialog/div/div[3]/button[1]")).click();
    }

    @Given("^user clicks next for more details$")
    public void user_clicks_next_for_more_details() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement nextBtn=driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[2]/div[3]/button"));
        nextBtn.click();
        Thread.sleep(5000);
    }
    @And("^clicks new$")
    public void clicks_new() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Thread.sleep(4000);
        WebElement newBtn=driver.findElement(By.id("btnAdd"));
        newBtn.click();
    }

    @And("^inputs contact purpose as (.+) and contact detail as (.+)$")
    public void inputs_contact_purpose_as_and_contact_detail_as(String purpose, String detail) throws Throwable {
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement purposeDropdown=driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        purposeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='"+purpose+"']")).click();

        WebElement contactDetailInput=driver.findElement(By.id("id_contactDetail"));
        contactDetailInput.sendKeys(detail);
    }

    @And("^clicks update contact button$")
    public void clicks_update_contact_button() throws Throwable {
        WebElement updateBtn=driver.findElement(By.id("btnSave"));
        updateBtn.click();
    }

    @Then("^contact successfully saved$")
    public void contact_successfully_saved() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement contactsRow=driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div[1]/tb-dropdown/div/div[3]"));
        Assert.assertTrue(contactsRow.isDisplayed());
    }

    @Then("^identification successfully saved$")
    public void identification_successfully_saved() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement contactsRow=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div[1]"));
        Assert.assertTrue(contactsRow.isDisplayed());
    }

    @And("^inputs identification type as (.+) and identification number (.+)$")
    public void inputs_identification_type_as_and_identification_number(String identification, String number) throws Throwable {
        WebElement identificationDropdown=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div[2]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        identificationDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+identification+"')]")).click();

        WebElement identifyNumberInput=driver.findElement(By.id("id_idenNumber"));
        identifyNumberInput.sendKeys(number);

    }
    @And("^inputs issue date (.+) and expiry date (.+)$")
    public void inputs_issue_date_and_expiry_date(String issue, String expiry) throws Throwable {
        WebElement issueDateInput=driver.findElement(By.id("id_IssueDate"));
        issueDateInput.sendKeys(issue);

        WebElement expiryDateInput=driver.findElement(By.id("id_expiryDate"));
        expiryDateInput.sendKeys(expiry);
    }

    @And("^clicks identification new button$")
    public void clicks_identification_new_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("newBtnIdentification"))).click();
//        driver.findElement(By.xpath("//button[contains(text(),'New')]"))
    }

    @And("^clicks update identification button$")
    public void clicks_update_identification_button() throws Throwable {
        Thread.sleep(4000);

//        WebElement updateBtn=driver.findElement(By.id("btnSave"));
        WebElement updateBtn=driver.findElement(By.xpath(Pro.getProperty("addIdentificationBtn")));
        updateBtn.click();
    }

    @And("^inputs occupation status (.+) and main category (.+) and precise category (.+)$")
    public void inputs_occupation_status_and_main_category_and_precise_category(String occupation, String category, String precise) throws Throwable {
        WebElement occupationDropdown=driver.findElement(By.xpath("//*[@id=\"id_occupationForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        occupationDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+occupation+"')]")).click();

        WebElement categoryDropdown=driver.findElement(By.xpath("//*[@id=\"id_occupationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        categoryDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+category+"')]")).click();

        WebElement preciseDropdown=driver.findElement(By.xpath("//*[@id=\"id_occupationForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[3]"));
        preciseDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+precise+"')]")).click();

    }

    @Then("^registration is successful$")
    public void registration_is_successful() throws Throwable {
        Thread.sleep(3000);
        WebElement success=driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/ng-component/div/div"));
        Assert.assertTrue(success.isDisplayed());
    }

    @And("^clicks address new button$")
    public void clicks_address_new_button() throws Throwable {
        driver.findElement(By.id("id_newAddress")).click();
    }
    @And("^inputs Addres type(.+) and house number (.+) and street (.+) and town (.+)$")
    public void inputs_addres_type_and_house_number_and_street_and_town(String address, String number, String street, String town) throws Throwable {
        WebElement addressDropdown=driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        addressDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+address+"')]")).click();

        driver.findElement(By.id("id_houseNumber")).sendKeys(number);

        driver.findElement(By.id("id_streetName")).sendKeys(street);

        driver.findElement(By.id("id_townCity")).sendKeys(town);


        // clicks on country drop down then enters value in datatable
        WebElement countryDropdown=driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        countryDropdown.click();
        Thread.sleep(2000);
        WebElement countryInput=driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        countryInput.sendKeys("kenya");

        //clicks on the search result in drop down after entering country
        WebElement firstEntry=driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        firstEntry.click();

    }

    @And("^clicks update address button$")
    public void clicks_update_address_button() throws Throwable {
        Thread.sleep(3000);
        WebElement updateBtn=driver.findElement(By.xpath(Pro.getProperty("addAddressBtn")));
        Assert.assertTrue(updateBtn.isEnabled());
        updateBtn.click();
    }

    @Given("^user clicks organization details$")
    public void user_clicks_organization_details() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[1]/ul/li[1]")).click();
    }
    @And("^user enters organisation details$")
    public void user_enters_organisation_details(DataTable table) throws Throwable {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        List<List<String>> data =table.asLists();

        WebElement categoryDropdown=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        categoryDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(0).get(1)+"')]")).click();

        WebElement orgNameInput=driver.findElement(By.id("id_orgName"));
        orgNameInput.clear();
        orgNameInput.sendKeys(data.get(1).get(1));

        WebElement prefOfficeDropdown=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        prefOfficeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(2).get(1)+"')]")).click();

        WebElement tinReasonDropdown=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown-with-othertext/div/div[2]/p-dropdown/div/div[3]"));
        tinReasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(3).get(1)+"')]")).click();

        WebElement DOIInput=driver.findElement(By.id("id_dateOfIncorporation"));
        DOIInput.clear();
        DOIInput.sendKeys(data.get(4).get(1));

        WebElement placeDropdown=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[3]"));
        placeDropdown.click();
        Thread.sleep(2000);
        WebElement placeInput=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        placeInput.sendKeys(data.get(5).get(1));
        driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li")).click();

        WebElement endDayDropdown=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[4]/div/div[2]/p-dropdown/div/div[3]"));
        endDayDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[4]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]")).click();

        WebElement endmonthDropdown=driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[5]/div/div[2]/p-dropdown/div/div[3]"));
        endmonthDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[5]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]")).click();

        WebElement capitalSourceInput=driver.findElement(By.id("id_sourceOfCapital"));
        capitalSourceInput.sendKeys(data.get(6).get(1));

    }

    //.....................Verify fields......................................//
    @And("^clicks new on business sector$")
    public void clicks_new_on_business_sector() throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[2]/div/tb-business-sector-list/div/div/form/div[1]/div/div/button[1]")).click();
    }

    @And("^Fill business sector details and click next$")
    public void fill_business_sector_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data =table.asLists();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[2]/div/tb-business-sector-list/div/div/form/div[3]/div/business-sector/div/form/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"id_businessSectorForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]")).click();

        Thread.sleep(1000);
//        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(0).get(1)+"')]")).click();
        driver.findElement(By.xpath("//*[@id='id_businessSectorForm']/div/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[2]/div/tb-business-sector-list/div/div/form/div[4]/div[2]/div/button[1]")).click();

    }

    @And("^click next on business sector$")
    public void click_next_on_business_sector() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    @And("^Fill in contact details and click next$")
    public void fill_in_contact_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data =table.asLists();
//        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[3]/div/tb-contact-list/div/div/form/div[1]/div/div/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(0).get(1)+"')]")).click();
        driver.findElement(By.id("id_contactDetail")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[3]/div/tb-contact-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }
    @And("^Add address details and click next$")
    public void add_address_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data =table.asLists();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[6]/div/tb-address-list/div/div/form/div[1]/div/div/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-checkbox/div/div[2]/p-checkbox")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(0).get(1)+"')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_townCity")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(2).get(1)+"')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(3).get(1)+"')]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[6]/div/tb-address-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }
    @And("^Fill directors details and click next$")
    public void fill_directors_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data =table.asLists();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[7]/div/tb-director-list/div/div/form/div[1]/div/div/button[1]")).click();
        driver.findElement(By.id("id_relationTin")).sendKeys(data.get(0).get(1));
        driver.findElement(By.id("id_relationName")).sendKeys(data.get(1).get(1));
        driver.findElement(By.id("id_startDate")).sendKeys("05/08/2020");
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[7]/div/tb-director-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }
    @And("^Fill in attachment details and click next$")
    public void fill_in_attachment_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data =table.asLists();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[8]/div/tb-attachment-list/div/div/form/div[1]/div/div/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_attachmentForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(0).get(1)+"')]")).click();
        driver.findElement(By.id("id_reference")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("//*[@id=\"id_fileChoose\"]/div/div[2]/div/div/div[1]/span/input")).sendKeys(data.get(2).get(1));
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[8]/div/tb-attachment-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    @Then("^Success message is displayed \"([^\"]*)\"$")
    public void verify_success(String Message) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'" + Message + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }
}