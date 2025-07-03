package taskmanager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import java.io.File;

public class TaskManagerTest {

    @Test
    public void testAddTask() {
        TaskManager manager = new TaskManager("test-tasks.txt");
        manager.addTask("Test task");
        assertEquals(1, manager.getTasks().size());
        assertEquals("[ ] Test task", manager.getTasks().get(0).toString());
    }

    @Test
    public void testMarkTaskDone() {
        TaskManager manager = new TaskManager("test-tasks.txt");
        manager.addTask("Finish homework");
        manager.markTaskDone(0);
        assertTrue(manager.getTasks().get(0).toString().contains("✓"));
    }

    @AfterEach
    public void cleanup() {
        new File("test-tasks.txt").delete();
    }
}

