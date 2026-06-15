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

public class FilterTaskTest extends BaseTest {

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
        addTaskPage.addTaskComplete("Tugas Low", "desc", "daily");

        // Tugas 2: Quiz → MEDIUM
        homePage.clickAddTask();
        addTaskPage.addTaskComplete("Tugas Medium", "desc", "quiz");

        // Tugas 3: Major → HIGH
        homePage.clickAddTask();
        addTaskPage.addTaskComplete("Tugas High", "desc", "major");
    }

    private void openFilter() {
        homePage.clickFilter();
    }

    private void clickFilterElement(String resourceId) {
        WebElement element = driver.findElement(By.xpath("//android.view.View[@resource-id=\"" + resourceId + "\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private void applyFilter() {
        clickFilterElement("btn_apply_filter");
    }

    @Test
    public void testFilterByPriorityHigh() {
        openFilter();
        clickFilterElement("filter_priority_high");
        applyFilter();

        Assert.assertTrue(homePage.isTaskVisible(3), "Tugas HIGH seharusnya tampil");
        Assert.assertFalse(homePage.isTaskVisible(1), "Tugas LOW seharusnya tidak tampil");
        Assert.assertFalse(homePage.isTaskVisible(2), "Tugas MEDIUM seharusnya tidak tampil");
    }

    @Test
    public void testFilterByPriorityLow() {
        openFilter();
        clickFilterElement("filter_priority_low");
        applyFilter();

        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas LOW seharusnya tampil");
        Assert.assertFalse(homePage.isTaskVisible(2), "Tugas MEDIUM seharusnya tidak tampil");
        Assert.assertFalse(homePage.isTaskVisible(3), "Tugas HIGH seharusnya tidak tampil");
    }

    @Test
    public void testFilterByStatusActive() {
        openFilter();
        clickFilterElement("filter_status_active");
        applyFilter();

        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas 1 seharusnya tampil (active)");
        Assert.assertTrue(homePage.isTaskVisible(2), "Tugas 2 seharusnya tampil (active)");
        Assert.assertTrue(homePage.isTaskVisible(3), "Tugas 3 seharusnya tampil (active)");
    }

    @Test
    public void testFilterByStatusCompleted() {
        openFilter();
        clickFilterElement("filter_status_completed");
        applyFilter();

        Assert.assertTrue(homePage.isEmptyStateVisible(), "Tidak ada task completed, seharusnya empty state");
    }

    @Test
    public void testSortByPriority() {
        openFilter();

        WebElement switchSort = driver.findElement(By.xpath("//android.view.View[@resource-id=\"switch_sort_by_priority\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(switchSort));
        switchSort.click();

        applyFilter();

        Assert.assertEquals(homePage.getTaskPriority(3), "High", "Tugas pertama seharusnya HIGH");
        Assert.assertEquals(homePage.getTaskPriority(2), "Medium", "Tugas kedua seharusnya MEDIUM");
        Assert.assertEquals(homePage.getTaskPriority(1), "Low", "Tugas ketiga seharusnya LOW");
    }
}