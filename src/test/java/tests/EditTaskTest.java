package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddTaskPage;
import pages.EditTaskPage;
import pages.HomePage;

public class EditTaskTest extends BaseTest {

    private HomePage homePage;
    private AddTaskPage addTaskPage;
    private EditTaskPage editTaskPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        addTaskPage = new AddTaskPage(driver);
        editTaskPage = new EditTaskPage(driver);

        // Setup: tambah 1 tugas — Daily → LOW
        homePage.clickAddTask();
        addTaskPage.addTaskComplete("Tugas Awal", "Deskripsi Awal", "daily");
    }

    @Test
    public void testEditTaskSuccess() {
        homePage.clickEditTask(1);
        editTaskPage.clearAndInputTitle("Tugas Sudah Diedit");
        editTaskPage.selectCategory("quiz");
        editTaskPage.clickUpdate();

        Assert.assertEquals(homePage.getTaskTitle(1), "Tugas Sudah Diedit", "Judul task seharusnya berubah");
    }

    @Test
    public void testEditTaskClearTitle() {
        homePage.clickEditTask(1);
        editTaskPage.clearTitle();
        editTaskPage.clickUpdate();

        Assert.assertTrue(editTaskPage.isTitleErrorVisible(), "Error judul seharusnya muncul");
    }

    @Test
    public void testEditTaskPriorityRecalculated() {
        homePage.clickEditTask(1);
        editTaskPage.selectCategory("major");

        String priority = editTaskPage.getCalculatedPriority();
        Assert.assertEquals(priority, "High", "Priority seharusnya HIGH setelah diubah ke UAS");
    }
}