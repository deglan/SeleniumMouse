import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DragAndDropTest extends DriverSetUp {

    @Test
    public void testDragAndDrop() {

        dragAndDropPage.moveToTarget(actions);
        assertTrue(dragAndDropPage.areItemsInTarget());
        dragAndDropPage.takeScreenshot(driver, "screenshot");

    }
}
