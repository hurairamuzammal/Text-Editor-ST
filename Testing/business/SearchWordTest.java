package business;

import bll.SearchWord;
import dto.Documents;
import dto.Pages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SearchWordTest {

    @Test
    public void testSearchPunctuationGhosting() {
        List<Documents> docs = createMockDocs("سلام،");

        List<String> results = SearchWord.searchKeyword("سلام", docs);
        assertFalse(results.isEmpty(), "Fails to find 'سلام' because of the trailing comma '،'");
    }

    @Test
    public void testSearchShortWordShouldWork() {
    List<Documents> docs = createMockDocs("ذهب محمد في المدرسة");

    List<String> results = SearchWord.searchKeyword("في", docs);
    assertFalse(results.isEmpty(), "Search for 'في' should return results but it threw an error or was blocked!");
    }

    @Test
    public void testSearchLengthConstraint() {
        List<Documents> docs = createMockDocs("ذهب محمد في المدرسة");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SearchWord.searchKeyword("في", docs);
        });

        String expectedMessage = "Could not Search, Please Enter at least 3 letter to search";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Should enforce 3-letter minimum search length");
    }

    private List<Documents> createMockDocs(String content) {
        List<Documents> docs = new ArrayList<>();
        Documents doc = new Documents(0, "TestDoc", content, content, content, null);
        Pages page = new Pages(0, 0, 0, content);

        List<Pages> pages = new ArrayList<>();
        pages.add(page);
        doc.setPages(pages);
        docs.add(doc);
        return docs;
    }
}
