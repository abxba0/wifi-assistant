package com.example.wifissistor2j;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for GlossaryItem
 */
public class GlossaryItemTest {

    @Test
    public void constructor_setsTermAndExplanation() {
        GlossaryItem item = new GlossaryItem("Test Term", "Test Explanation");
        assertEquals("Test Term", item.getTerm());
        assertEquals("Test Explanation", item.getExplanation());
    }

    @Test
    public void toString_formatsCorrectly() {
        GlossaryItem item = new GlossaryItem("Speed", "How fast data moves");
        assertEquals("Speed: How fast data moves", item.toString());
    }

    @Test
    public void getDefaultGlossary_notEmpty() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        assertFalse(glossary.isEmpty());
    }

    @Test
    public void getDefaultGlossary_containsSpeedTerms() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        boolean hasDownloadSpeed = false;
        boolean hasUploadSpeed = false;
        boolean hasMbps = false;

        for (GlossaryItem item : glossary) {
            if (item.getTerm().equals("Download Speed")) hasDownloadSpeed = true;
            if (item.getTerm().equals("Upload Speed")) hasUploadSpeed = true;
            if (item.getTerm().equals("Mbps")) hasMbps = true;
        }

        assertTrue("Should contain Download Speed", hasDownloadSpeed);
        assertTrue("Should contain Upload Speed", hasUploadSpeed);
        assertTrue("Should contain Mbps", hasMbps);
    }

    @Test
    public void getDefaultGlossary_containsSecurityTerms() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        boolean hasSecureNetwork = false;
        boolean hasOpenNetwork = false;

        for (GlossaryItem item : glossary) {
            if (item.getTerm().equals("Secure Network")) hasSecureNetwork = true;
            if (item.getTerm().equals("Open Network")) hasOpenNetwork = true;
        }

        assertTrue("Should contain Secure Network", hasSecureNetwork);
        assertTrue("Should contain Open Network", hasOpenNetwork);
    }

    @Test
    public void getDefaultGlossary_containsQualityLabels() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        boolean hasExcellent = false;
        boolean hasGood = false;
        boolean hasFair = false;
        boolean hasPoor = false;

        for (GlossaryItem item : glossary) {
            if (item.getTerm().equals("Excellent")) hasExcellent = true;
            if (item.getTerm().equals("Good")) hasGood = true;
            if (item.getTerm().equals("Fair")) hasFair = true;
            if (item.getTerm().equals("Poor")) hasPoor = true;
        }

        assertTrue("Should contain Excellent", hasExcellent);
        assertTrue("Should contain Good", hasGood);
        assertTrue("Should contain Fair", hasFair);
        assertTrue("Should contain Poor", hasPoor);
    }

    @Test
    public void getDefaultGlossary_explanationsAreNotEmpty() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        for (GlossaryItem item : glossary) {
            assertFalse("Explanation should not be empty for: " + item.getTerm(), 
                    item.getExplanation().isEmpty());
        }
    }

    @Test
    public void getDefaultGlossary_termsAreNotEmpty() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        for (GlossaryItem item : glossary) {
            assertFalse("Term should not be empty", item.getTerm().isEmpty());
        }
    }

    @Test
    public void getDefaultGlossary_usesPlainLanguage() {
        List<GlossaryItem> glossary = GlossaryItem.getDefaultGlossary();
        
        // Check that explanations don't use technical jargon
        for (GlossaryItem item : glossary) {
            String explanation = item.getExplanation().toLowerCase();
            // These are common technical terms that should be avoided or explained
            assertFalse("Should use plain language: " + item.getTerm(),
                    explanation.contains("tcp") || 
                    explanation.contains("udp") ||
                    explanation.contains("protocol") ||
                    explanation.contains("packet") ||
                    explanation.contains("bandwidth") ||
                    explanation.contains("throughput"));
        }
    }
}
