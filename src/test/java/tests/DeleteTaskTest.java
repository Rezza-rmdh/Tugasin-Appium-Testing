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

public class DeleteTaskTest extends BaseTest {

    private HomePage homePage;
    private AddTaskPage addTaskPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        addTaskPage = new AddTaskPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Setup: tambah 1 task
        homePage.clickAddTask();
        addTaskPage.addTaskComplete("Tugas Untuk Dihapus", "Deskripsi", "daily");
    }

    @Test
    public void testDeleteTaskConfirm() {
        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas seharusnya ada sebelum dihapus");

        homePage.clickDeleteTask(1);

        WebElement btnConfirm = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_confirm_delete\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
        btnConfirm.click();

        Assert.assertTrue(homePage.isEmptyStateVisible(), "Tugas seharusnya sudah terhapus");
    }

    @Test
    public void testDeleteTaskCancel() {
        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas seharusnya ada");

        homePage.clickDeleteTask(1);

        WebElement btnCancel = driver.findElement(By.xpath("//android.view.View[@resource-id=\"btn_cancel_delete\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(btnCancel));
        btnCancel.click();

        Assert.assertTrue(homePage.isTaskVisible(1), "Tugas seharusnya masih ada setelah batal");
    }
}