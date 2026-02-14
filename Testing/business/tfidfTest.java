// test written by M Abu Huraira
package business;

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
	    public void testTFIDFMathTrapNegativeScore() {
	        TFIDFCalculator calculator = new TFIDFCalculator();
	        // This triggers the log(N/df) trap where N=1, df=1
	        calculator.addDocumentToCorpus("تفاح"); 
	        double score = calculator.calculateDocumentTfIdf("تفاح");
	        assertTrue(score >= 0, "FAILED: TF-IDF calculation produced a negative score: " + score);
	    }

	    @Test
	    public void testTrapEmptyCorpusError() {
	        TFIDFCalculator calculator = new TFIDFCalculator();
	        // Negative case: Calculating with 0 documents
	        double score = calculator.calculateDocumentTfIdf("تفاح");
	        assertTrue(Double.isFinite(score), "FAILED: Calculation resulted in non-finite value (NaN/Infinity) for empty corpus.");
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