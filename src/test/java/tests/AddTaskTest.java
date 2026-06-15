package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddTaskPage;
import pages.HomePage;

public class AddTaskTest extends BaseTest {

    private HomePage homePage;
    private AddTaskPage addTaskPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        addTaskPage = new AddTaskPage(driver);
        homePage.clickAddTask();
    }

    @Test
    public void testAddTaskSuccess() {
        addTaskPage.inputTitle("Belajar Appium");
        addTaskPage.inputDescription("Latihan automation testing");
        addTaskPage.selectCategory("quiz");
        addTaskPage.pickDeadline();
        addTaskPage.clickSave();

        Assert.assertTrue(homePage.isTaskVisible(1), "Task seharusnya muncul di Home");
        Assert.assertEquals(homePage.getTaskTitle(1), "Belajar Appium", "Judul task seharusnya sama");
    }

    @Test
    public void testAddTaskWithoutTitle() {
        addTaskPage.inputDescription("Deskripsi tanpa judul");
        addTaskPage.selectCategory("daily");
        addTaskPage.pickDeadline();
        addTaskPage.clickSave();

        Assert.assertTrue(addTaskPage.isTitleErrorVisible(), "Error judul seharusnya muncul");
    }

    @Test
    public void testPriorityAutoCalculated() {
        addTaskPage.selectCategory("major");

        String priority = addTaskPage.getCalculatedPriority();
        Assert.assertEquals(priority, "High", "Priority seharusnya HIGH untuk kategori UAS");
    }
}