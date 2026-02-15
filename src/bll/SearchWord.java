package bll;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dto.Documents;
import dto.Pages;
import pl.EditorPO;

public class SearchWord {
	public static List<String> searchKeyword(String keyword, List<Documents> docs) {
		List<String> results = new ArrayList<>();
		if (keyword == null || keyword.isEmpty()) {
			throw new IllegalArgumentException("Could not Search, Please Enter a keyword to search");
		}

		for (Documents doc : docs) {
			StringBuilder fullText = new StringBuilder();
			for (Pages p : doc.getPages()) {
				if (p.getPageContent() != null) {
					fullText.append(p.getPageContent());
				}
			}
			
			String[] words = fullText.toString().split("\\s+");
			for (int i = 0; i < words.length; i++) {
				String cleanWord = words[i].replaceAll("[\\p{Punct}،؛؟]", "");
				if (cleanWord.equalsIgnoreCase(keyword)) {
					String prefix = (i > 0) ? words[i - 1] : "";
					results.add(doc.getName() + " - " + prefix + " " + keyword + "...");
				}
			}
		}
		return results;
	}

}
