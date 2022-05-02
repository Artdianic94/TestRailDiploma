package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.GenerateFakeMessage;

public class NewProjectPage extends BasePage {
    private final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private final By NAME_OF_PROJECT = By.id("name");
    private final By ADD_PROJECT_ACCEPT_BUTTON = By.id("accept");
    String newProjectName = GenerateFakeMessage.getProjectName();
    String projectNameXpath = "//div[@class='content-header-title page_title display-inline-block' and contains(.,'%s')]";
    private final By CREATED_PROJECTS = By.xpath(String.format(projectNameXpath, newProjectName));

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill out the form and click to add project")
    public String createProject() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        driver.findElement(NAME_OF_PROJECT).sendKeys(newProjectName);
        driver.findElement(ADD_PROJECT_ACCEPT_BUTTON).click();
        return newProjectName;
    }

    @Step("Get name of the project")
    public String getNameOfProjectOnMainPage() {
        String containerOfName = driver.findElement(CREATED_PROJECTS).getText();
        if (containerOfName.equals(newProjectName)) {
            return containerOfName;
        } else {
            return "There is no project name";
        }
    }
}
