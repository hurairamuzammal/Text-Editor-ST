package data;

import dal.HashCalculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class hashingTest{
	
	@Test
	public void testHashingIntegrity() throws Exception {
        String originalContent = "This is the original content.";
        String editedContent = "This is the edited content.";

        // Here i will Calculate hash values
        String originalHash = HashCalculator.calculateHash(originalContent);
        String editedHash = HashCalculator.calculateHash(editedContent);

        // Verify hashes are consistent for same content
        assertEquals(originalHash, HashCalculator.calculateHash(originalContent),"Hash should be deterministic");
        assertNotEquals("Editing content should change the hash", originalHash, editedHash);
        
        // Verify format (MD5 is 32 hex chars)
        assertEquals(32, originalHash.length(),"MD5 hash should be 32 characters long");
    }
	
	@Test
	void testOriginalHashRetention() throws Exception {
		String originalContent = "Original";
	    String originalHash = HashCalculator.calculateHash(originalContent);
	 // simulate DB storing original hash
	    String storedImportHash = originalHash;

	    // simulate edit
	    String editedContent = "Edited";
	    String editedHash = HashCalculator.calculateHash(editedContent);

	    assertNotEquals(storedImportHash, editedHash);
	    assertEquals(originalHash, storedImportHash);
	}
}