package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddTask() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_add_task\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickFilter() {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_filter\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickEditTask(int taskId) {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_edit_task_" + taskId + "\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickDeleteTask(int taskId) {
        WebElement btn = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_delete_task_" + taskId + "\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

    public void clickCheckboxTask(int taskId) {
        WebElement checkbox = driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"checkbox_task_" + taskId + "\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        checkbox.click();
    }

    public String getTaskTitle(int taskId) {
        WebElement title = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"task_title_" + taskId + "\"]"));
        wait.until(ExpectedConditions.visibilityOf(title));
        return title.getText();
    }

    public String getTaskPriority(int taskId) {
        WebElement priority = driver.findElement(By.xpath("//android.view.View[@resource-id=\"task_priority_" + taskId + "\"]"));
        wait.until(ExpectedConditions.visibilityOf(priority));
        return priority.getText();
    }

    public boolean isEmptyStateVisible() {
        try {
            WebElement empty = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"txt_empty_state\"]"));
            wait.until(ExpectedConditions.visibilityOf(empty));
            return empty.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTaskVisible(int taskId) {
        try {
            WebElement task = driver.findElement(By.xpath("//android.view.View[@resource-id=\"task_item_" + taskId + "\"]"));
            return task.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}