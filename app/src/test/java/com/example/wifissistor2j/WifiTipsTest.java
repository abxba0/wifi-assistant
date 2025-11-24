package com.example.wifissistor2j;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for WifiTips
 */
public class WifiTipsTest {

    @Test
    public void getAllTips_notEmpty() {
        List<WifiTips.Tip> tips = WifiTips.getAllTips();
        assertFalse(tips.isEmpty());
    }

    @Test
    public void getAllTips_hasAllCategories() {
        List<WifiTips.Tip> tips = WifiTips.getAllTips();
        
        boolean hasLocation = false;
        boolean hasRouter = false;
        boolean hasDevices = false;
        boolean hasSecurity = false;
        boolean hasGeneral = false;

        for (WifiTips.Tip tip : tips) {
            switch (tip.getCategory()) {
                case LOCATION: hasLocation = true; break;
                case ROUTER: hasRouter = true; break;
                case DEVICES: hasDevices = true; break;
                case SECURITY: hasSecurity = true; break;
                case GENERAL: hasGeneral = true; break;
            }
        }

        assertTrue("Should have LOCATION tips", hasLocation);
        assertTrue("Should have ROUTER tips", hasRouter);
        assertTrue("Should have DEVICES tips", hasDevices);
        assertTrue("Should have SECURITY tips", hasSecurity);
        assertTrue("Should have GENERAL tips", hasGeneral);
    }

    @Test
    public void getSignalImprovementTips_notEmpty() {
        List<WifiTips.Tip> tips = WifiTips.getSignalImprovementTips();
        assertFalse(tips.isEmpty());
    }

    @Test
    public void getSignalImprovementTips_onlyLocationCategory() {
        List<WifiTips.Tip> tips = WifiTips.getSignalImprovementTips();
        for (WifiTips.Tip tip : tips) {
            assertEquals(WifiTips.TipCategory.LOCATION, tip.getCategory());
        }
    }

    @Test
    public void getSpeedImprovementTips_notEmpty() {
        List<WifiTips.Tip> tips = WifiTips.getSpeedImprovementTips();
        assertFalse(tips.isEmpty());
    }

    @Test
    public void getSpeedImprovementTips_correctCategories() {
        List<WifiTips.Tip> tips = WifiTips.getSpeedImprovementTips();
        for (WifiTips.Tip tip : tips) {
            assertTrue(tip.getCategory() == WifiTips.TipCategory.DEVICES ||
                    tip.getCategory() == WifiTips.TipCategory.ROUTER);
        }
    }

    @Test
    public void getDailyTip_notNull() {
        WifiTips.Tip tip = WifiTips.getDailyTip();
        assertNotNull(tip);
        assertNotNull(tip.getTitle());
        assertNotNull(tip.getDescription());
        assertNotNull(tip.getCategory());
    }

    @Test
    public void getDailyTip_titleNotEmpty() {
        WifiTips.Tip tip = WifiTips.getDailyTip();
        assertFalse(tip.getTitle().isEmpty());
    }

    @Test
    public void getDailyTip_descriptionNotEmpty() {
        WifiTips.Tip tip = WifiTips.getDailyTip();
        assertFalse(tip.getDescription().isEmpty());
    }

    @Test
    public void getRandomTipByCategory_returnsCorrectCategory() {
        WifiTips.Tip locationTip = WifiTips.getRandomTipByCategory(WifiTips.TipCategory.LOCATION);
        assertEquals(WifiTips.TipCategory.LOCATION, locationTip.getCategory());

        WifiTips.Tip routerTip = WifiTips.getRandomTipByCategory(WifiTips.TipCategory.ROUTER);
        assertEquals(WifiTips.TipCategory.ROUTER, routerTip.getCategory());

        WifiTips.Tip securityTip = WifiTips.getRandomTipByCategory(WifiTips.TipCategory.SECURITY);
        assertEquals(WifiTips.TipCategory.SECURITY, securityTip.getCategory());
    }

    @Test
    public void getImprovementChecklist_notEmpty() {
        List<String> checklist = WifiTips.getImprovementChecklist();
        assertFalse(checklist.isEmpty());
    }

    @Test
    public void getImprovementChecklist_hasReasonableSize() {
        List<String> checklist = WifiTips.getImprovementChecklist();
        assertTrue("Checklist should have at least 5 items", checklist.size() >= 5);
        assertTrue("Checklist should not be too long", checklist.size() <= 15);
    }

    @Test
    public void getImprovementChecklist_itemsNotEmpty() {
        List<String> checklist = WifiTips.getImprovementChecklist();
        for (String item : checklist) {
            assertFalse("Checklist item should not be empty", item.isEmpty());
        }
    }

    @Test
    public void getImprovementChecklist_usesPlainLanguage() {
        List<String> checklist = WifiTips.getImprovementChecklist();
        for (String item : checklist) {
            // Should not contain technical jargon
            String lower = item.toLowerCase();
            assertFalse("Should use plain language: " + item,
                    lower.contains("bandwidth") ||
                    lower.contains("protocol") ||
                    lower.contains("tcp") ||
                    lower.contains("latency") ||
                    lower.contains("throughput"));
        }
    }

    @Test
    public void tip_hasAllFields() {
        List<WifiTips.Tip> tips = WifiTips.getAllTips();
        for (WifiTips.Tip tip : tips) {
            assertNotNull("Title should not be null", tip.getTitle());
            assertNotNull("Description should not be null", tip.getDescription());
            assertNotNull("Category should not be null", tip.getCategory());
            assertFalse("Title should not be empty", tip.getTitle().isEmpty());
            assertFalse("Description should not be empty", tip.getDescription().isEmpty());
        }
    }

    @Test
    public void tip_descriptionsArePlainLanguage() {
        List<WifiTips.Tip> tips = WifiTips.getAllTips();
        for (WifiTips.Tip tip : tips) {
            String desc = tip.getDescription().toLowerCase();
            // Check for technical jargon that should be avoided
            assertFalse("Description should be plain language: " + tip.getTitle(),
                    desc.contains("dbm") ||
                    desc.contains("megahertz") ||
                    desc.contains("5ghz") ||
                    desc.contains("2.4ghz"));
        }
    }
}
