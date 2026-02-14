package bll;

import dal.IFacadeDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class importCommand implements ICommand {
    private IFacadeDAO db;
    private File file;
    private String fileName;
    private boolean success;

    public importCommand(IFacadeDAO db, File file, String fileName) {
        this.db = db;
        this.file = file;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            success = db.createFileInDB(fileName, fileContent.toString());
        } catch (Exception e) {
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }
}
