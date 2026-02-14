package bll;

import dal.IFacadeDAO;

public class TransliterateCommand implements ICommand {
    private IFacadeDAO db;
    private int pageId;
    private String text;
    private String result;

    public TransliterateCommand(IFacadeDAO db, int pageId, String text) {
        this.db = db;
        this.pageId = pageId;
        this.text = text;
    }

    @Override
    public void execute() {
        result = db.transliterateInDB(pageId, text);
    }

    public String getResult() {
        return result;
    }
}
