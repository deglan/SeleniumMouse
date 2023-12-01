import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DragAndDropPage {

    private WebDriver driver;

    @FindBy(css = ".dropZone>.ditem")
    private List<WebElement> sourceItems;

    @FindBy(xpath = "//p[contains(text(), 'drop')]/parent::div")
    private WebElement target;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void moveToTarget(Actions actions) {

        for (WebElement item : sourceItems) {
            move(item, target, actions);
        }
    }

    public void move(WebElement element, WebElement dropZone, Actions actions) {

        actions.clickAndHold(element)
                .moveByOffset(10, 0)
                .moveToElement(dropZone)
                .release()
                .build()
                .perform();
    }

    public boolean areItemsInTarget() {
        List<WebElement> itemsInTarget = target.findElements(By.className("ditem"));
        return itemsInTarget.size() >= 3;
    }

    public void takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot/" + fileName + ".png");

        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

