package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddTaskPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public AddTaskPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void inputTitle(String title) {
        WebElement input = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"input_task_title\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(title);
    }

    public void inputDescription(String description) {
        WebElement input = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"input_task_description\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(description);
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

        WebElement btnConfirm = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_confirm_date\"]/android.widget.Button"));
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
        btnConfirm.click();
    }

    public void clickSave() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_save_task\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickBack() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_back\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public String getCalculatedPriority() {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@resource-id='badge_calculated_priority']")));
        WebElement text = badge.findElement(By.className("android.widget.TextView"));

        return text.getText();
    }

    public boolean isTitleErrorVisible() {
        try {
            WebElement error = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"txt_title_error\"]"));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addTaskComplete(String title, String description, String category) {
        inputTitle(title);
        inputDescription(description);
        selectCategory(category);
        pickDeadline();
        clickSave();
    }
}