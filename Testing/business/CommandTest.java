package business;

import bll.*;
import dal.IFacadeDAO;
import dto.Documents;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class CommandTest {

    @TempDir
    Path tempDir;

    @Test
    public void testExportCommand() {
        File file = tempDir.resolve("export_test.txt").toFile();
        String content = "Test content for export";
        
        ExportCommand cmd = new ExportCommand(content, file);
        cmd.execute();
        
        assertTrue(cmd.isSuccess());
        assertTrue(file.exists());
    }

    @Test
    public void testImportCommandFailureWithNull() {
  
        importCommand cmd = new importCommand(null, new File("nonexistent.txt"), "test.txt");
        cmd.execute();
        assertFalse(cmd.isSuccess(), "Should fail gracefully when dependencies are missing");
    }

    @Test
    public void testTransliterateCommandStructure() {
        // Testing that the command pattern is correctly implemented using the interface
        ICommand cmd = new TransliterateCommand(null, 1, "test"); 
        assertNotNull(cmd);
        // We ensure it can be assigned to ICommand (Swappable requirement)
    }
}
