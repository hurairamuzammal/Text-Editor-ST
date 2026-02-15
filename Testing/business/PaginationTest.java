package business;

import bll.SearchWord;
import dto.Documents;
import dto.Pages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PaginationTest {

    @Test
    public void testMultiMatch() {
        List<Pages> pages = new ArrayList<>();
        pages.add(new Pages(1, 1, 1, "apple apple"));
        Documents doc = new Documents(1, "doc", "h", "t", "t", pages);
        List<Documents> docs = new ArrayList<>();
        docs.add(doc);

        assertEquals(2, SearchWord.searchKeyword("apple", docs).size());
    }

    @Test
    public void testSplitWord() {
        List<Pages> pages = new ArrayList<>();
        pages.add(new Pages(1, 1, 1, "ap"));
        pages.add(new Pages(2, 1, 2, "ple"));
        Documents doc = new Documents(1, "doc", "h", "t", "t", pages);
        List<Documents> docs = new ArrayList<>();
        docs.add(doc);
        assertFalse(SearchWord.searchKeyword("apple", docs).isEmpty());
    }

    @Test
    public void testPrefixContextLoss() {
        List<Pages> pages = new ArrayList<>();
        pages.add(new Pages(1, 1, 1, "Best "));
        pages.add(new Pages(2, 1, 2, "apple"));
        Documents doc = new Documents(1, "doc", "h", "t", "t", pages);
        List<Documents> docs = new ArrayList<>();
        docs.add(doc);

        List<String> results = SearchWord.searchKeyword("apple", docs);
        assertTrue(results.get(0).contains("Best"), "Should capture prefix from previous page");
    }



    @Test
    public void testNullContent() {
        List<Pages> pages = new ArrayList<>();
        pages.add(new Pages(1, 1, 1, null));
        Documents doc = new Documents(1, "doc", "h", "t", "t", pages);
        List<Documents> docs = new ArrayList<>();
        docs.add(doc);

        assertDoesNotThrow(() -> SearchWord.searchKeyword("a", docs));
    }
}
