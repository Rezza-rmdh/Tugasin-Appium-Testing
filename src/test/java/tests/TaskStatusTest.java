package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddTaskPage;
import pages.HomePage;

import java.time.Duration;

public class TaskStatusTest extends BaseTest {

    private HomePage homePage;
    private AddTaskPage addTaskPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        addTaskPage = new AddTaskPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tugas 1: Daily → LOW
        homePage.clickAddTask();
        addTaskPage.addTaskComplete("Tugas Harian", "desc", "daily");

        // Tugas 2: Major → HIGH
        homePage.clickAddTask();
        addTaskPage.addTaskComplete("Tugas UAS", "desc", "major");
    }

    private void clickFilterElement(String resourceId) {
        WebElement element = driver.findElement(By.xpath("//android.view.View[@resource-id=\"" + resourceId + "\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Test
    public void testMarkTaskAsCompleted() {
        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas 1 seharusnya ada");
        homePage.clickCheckboxTask(1);

        homePage.clickFilter();
        clickFilterElement("filter_status_completed");
        clickFilterElement("btn_apply_filter");

        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas 1 seharusnya muncul di filter completed");
    }

    @Test
    public void testMarkTaskAsActive() {
        homePage.clickCheckboxTask(1);
        homePage.clickCheckboxTask(1);

        homePage.clickFilter();
        clickFilterElement("filter_status_active");
        clickFilterElement("btn_apply_filter");

        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas 1 seharusnya muncul di filter active");
    }

    @Test
    public void testCompletedTaskAppearInFilter() {
        homePage.clickCheckboxTask(1);

        homePage.clickFilter();
        clickFilterElement("filter_status_completed");
        clickFilterElement("btn_apply_filter");

        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas 1 seharusnya muncul di completed");
        Assert.assertFalse(homePage.isTaskVisible(2), "Tugas 2 seharusnya tidak muncul di completed");
    }
}