package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditTaskPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public EditTaskPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitleValue() {
        WebElement input = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"input_task_title\"]"));
        wait.until(ExpectedConditions.visibilityOf(input));
        return input.getText();
    }

    public void clearAndInputTitle(String title) {
        WebElement input = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"input_task_title\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(title);
    }

    public void clearTitle() {
        WebElement input = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"input_task_title\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
    }

    public void selectCategory(String categoryName) {
        WebElement radio = driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"radio_category_" + categoryName.toLowerCase() + "\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(radio));
        radio.click();
    }

    public void pickDeadline() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_pick_deadline\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();

        WebElement btnConfirm = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_confirm_date\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
        btnConfirm.click();
    }

    public void clickUpdate() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_update_task\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickBack() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_back\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public String getCalculatedPriority() {
        WebElement badge = driver.findElement(By.xpath("//android.view.View[@resource-id=\"badge_calculated_priority\"]"));
        wait.until(ExpectedConditions.visibilityOf(badge));
        return badge.getText();
    }

    public boolean isTitleErrorVisible() {
        try {
            WebElement error = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"txt_title_error\"]"));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}