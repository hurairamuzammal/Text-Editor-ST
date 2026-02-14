package bll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportCommand implements ICommand {
    private String content;
    private File targetFile;
    private boolean success;

    public ExportCommand(String content, File targetFile) {
        this.content = content;
        this.targetFile = targetFile;
    }

    @Override
    public void execute() {
        try (FileWriter writer = new FileWriter(targetFile)) {
            writer.write(content);
            success = true;
        } catch (IOException e) {
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }
}
