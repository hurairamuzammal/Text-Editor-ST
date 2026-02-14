// test written by M Abu Huraira
package data;

import dal.TFIDFCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

class tfidfTest{
	
	@Test 
	public void testTfidfPositivePath(){
		
	 TFIDFCalculator calculator = new TFIDFCalculator();
	 
	 calculator.addDocumentToCorpus("تفاح تفاح"); 
     calculator.addDocumentToCorpus("موز"); 
	 calculator.addDocumentToCorpus("عنب");  
	 
//	 idf should be this much based on formulae
	 double expected=0.4054;
	 double actual= calculator.calculateDocumentTfIdf("تفاح");
	 assertEquals(expected, actual, 0.01);
		
	}
	 @Test
	    public void testTFIDFNegativePathEmpty() {
	        TFIDFCalculator calculator = new TFIDFCalculator();
	        calculator.addDocumentToCorpus("تفاح");
	        
	        // Should handle empty string without crashing
	        double result = calculator.calculateDocumentTfIdf("");
	        assertTrue(result >= 0, "Graceful exit for empty document");
	    }

	    @Test
	    public void testTFIDFNegativePathSpecialChars() {
	        TFIDFCalculator calculator = new TFIDFCalculator();
	        calculator.addDocumentToCorpus("تفاح");
	        
	        // Preprocessor removes non-Arabic characters, handle empty result
	        double result = calculator.calculateDocumentTfIdf("!@#$%^");
	        assertTrue(result >= 0, "Graceful exit for special characters");
	    }
}