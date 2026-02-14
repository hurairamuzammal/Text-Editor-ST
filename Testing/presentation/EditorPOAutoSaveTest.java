package presentation;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.EditorPO;
import bll.IEditorBO;

class EditorPOAutoSaveTest {

    private IEditorBO mockBO;
    private EditorPO editor;

    @BeforeEach
    public void setup() {
        // Mock the business object
        mockBO = mock(IEditorBO.class);

        editor = new EditorPO(mockBO);
        editor.currentFileId = 1;
        editor.currentFileName = "TestFile";

        if (editor.getContentTextArea() == null) {
            editor.getContentTextArea(); 
        }
    }

    @Test
    void skipAutoSaveIfUnder500Words() throws Exception {
        editor.getContentTextArea().setText(repeatWord("word", 499)); // 499 words
        editor.autoSaveFile();
        verify(mockBO, never()).updateFile(anyInt(), anyString(), anyInt(), anyString());
    }

    @Test
    void runAutoSaveIfOver500Words() throws Exception {
        editor.getContentTextArea().setText(repeatWord("word", 501));
        when(mockBO.updateFile(anyInt(), anyString(), anyInt(), anyString())).thenReturn(true);
        editor.autoSaveFile();
        verify(mockBO, times(1)).updateFile(anyInt(), anyString(), anyInt(), anyString());
    }

    @Test
    void skipAutoSaveIfEmpty() throws Exception {
        editor.getContentTextArea().setText("");
        editor.autoSaveFile();
        verify(mockBO, never()).updateFile(anyInt(), anyString(), anyInt(), anyString());
    }

    @Test
    void skipAutoSaveIfExactly500Words() throws Exception {
        editor.getContentTextArea().setText(repeatWord("word", 500));
        editor.autoSaveFile();
        verify(mockBO, never()).updateFile(anyInt(), anyString(), anyInt(), anyString());
    }

    @Test
    void testAutoSaveBelowLimit() throws Exception {
        editor.currentFileId = 1;
        editor.currentFileName = "Test";

        editor.getContentTextArea().setText("small text");

        editor.autoSaveFile();

        verify(mockBO, never()).updateFile(anyInt(), anyString(), anyInt(), anyString());
    }

    private String repeatWord(String word, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }
}
