package dal;

import java.util.ArrayList;
import java.util.List;

import dto.Pages;

public class PaginationDAO {

	
	static List<Pages> paginate(String fileContent) {
		int pageSize = 100;
		List<Pages> pages = new ArrayList<>();
		if (fileContent == null || fileContent.isEmpty()) {
			pages.add(new Pages(0, 0, 1, ""));
			return pages;
		}

		int start = 0;
		int pageNum = 1;
		while (start < fileContent.length()) {
			int end = Math.min(start + pageSize, fileContent.length());
			
			if (end < fileContent.length()) {
				int lastSpace = fileContent.lastIndexOf(' ', end);
				if (lastSpace > start) {
					end = lastSpace;
				}
			}
			
			pages.add(new Pages(0, 0, pageNum++, fileContent.substring(start, end).trim()));
			start = end;
			while (start < fileContent.length() && Character.isWhitespace(fileContent.charAt(start))) {
				start++;
			}
		}
		return pages;
	} 
}