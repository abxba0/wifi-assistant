package com.example.wifissistor2j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Provides actionable Wi-Fi improvement tips and daily suggestions.
 * Tips are written in plain, non-technical language.
 */
public class WifiTips {

    /**
     * Represents a single Wi-Fi improvement tip.
     */
    public static class Tip {
        private final String title;
        private final String description;
        private final TipCategory category;

        public Tip(String title, String description, TipCategory category) {
            this.title = title;
            this.description = description;
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public TipCategory getCategory() {
            return category;
        }
    }

    /**
     * Categories for organizing tips.
     */
    public enum TipCategory {
        LOCATION,
        ROUTER,
        DEVICES,
        SECURITY,
        GENERAL
    }

    private static final List<Tip> ALL_TIPS = createTipsList();
    private static final Random random = new Random();

    /**
     * Gets all available improvement tips.
     *
     * @return List of all tips
     */
    public static List<Tip> getAllTips() {
        return new ArrayList<>(ALL_TIPS);
    }

    /**
     * Gets tips relevant to weak signal issues.
     *
     * @return List of location/signal improvement tips
     */
    public static List<Tip> getSignalImprovementTips() {
        List<Tip> tips = new ArrayList<>();
        for (Tip tip : ALL_TIPS) {
            if (tip.getCategory() == TipCategory.LOCATION) {
                tips.add(tip);
            }
        }
        return tips;
    }

    /**
     * Gets tips relevant to slow speed issues.
     *
     * @return List of speed improvement tips
     */
    public static List<Tip> getSpeedImprovementTips() {
        List<Tip> tips = new ArrayList<>();
        for (Tip tip : ALL_TIPS) {
            if (tip.getCategory() == TipCategory.DEVICES || tip.getCategory() == TipCategory.ROUTER) {
                tips.add(tip);
            }
        }
        return tips;
    }

    /**
     * Gets a random daily tip.
     *
     * @return A random tip for daily display
     */
    public static Tip getDailyTip() {
        return ALL_TIPS.get(random.nextInt(ALL_TIPS.size()));
    }

    /**
     * Gets a random tip from a specific category.
     *
     * @param category The category to get a tip from
     * @return A random tip from the category
     */
    public static Tip getRandomTipByCategory(TipCategory category) {
        List<Tip> categoryTips = new ArrayList<>();
        for (Tip tip : ALL_TIPS) {
            if (tip.getCategory() == category) {
                categoryTips.add(tip);
            }
        }
        if (categoryTips.isEmpty()) {
            return getDailyTip();
        }
        return categoryTips.get(random.nextInt(categoryTips.size()));
    }

    /**
     * Gets the improvement checklist - actionable steps to improve Wi-Fi.
     *
     * @return List of checklist items
     */
    public static List<String> getImprovementChecklist() {
        List<String> checklist = new ArrayList<>();
        checklist.add("Move closer to your Wi-Fi router");
        checklist.add("Restart your router (unplug for 30 seconds)");
        checklist.add("Reduce walls between you and the router");
        checklist.add("Disconnect unused devices from Wi-Fi");
        checklist.add("Close apps using the internet in the background");
        checklist.add("Position router in a central, elevated location");
        checklist.add("Keep router away from microwaves and cordless phones");
        checklist.add("Check if your router needs a firmware update");
        return checklist;
    }

    private static List<Tip> createTipsList() {
        List<Tip> tips = new ArrayList<>();

        // Location tips
        tips.add(new Tip(
                "Move Closer",
                "The closer you are to your router, the stronger your Wi-Fi signal. Try moving to the same room as your router.",
                TipCategory.LOCATION
        ));
        tips.add(new Tip(
                "Reduce Obstacles",
                "Walls, floors, and furniture can block Wi-Fi signals. Try to have a clear path to your router.",
                TipCategory.LOCATION
        ));
        tips.add(new Tip(
                "Elevate Your Router",
                "Place your router on a high shelf. Wi-Fi signals spread outward and downward.",
                TipCategory.LOCATION
        ));
        tips.add(new Tip(
                "Central Location",
                "Put your router in the center of your home so the signal reaches all rooms evenly.",
                TipCategory.LOCATION
        ));
        tips.add(new Tip(
                "Avoid Interference",
                "Keep your router away from microwaves, baby monitors, and cordless phones - they can interfere with Wi-Fi.",
                TipCategory.LOCATION
        ));

        // Router tips
        tips.add(new Tip(
                "Restart Your Router",
                "Turn off your router, wait 30 seconds, then turn it back on. This can fix many connection problems.",
                TipCategory.ROUTER
        ));
        tips.add(new Tip(
                "Update Router Firmware",
                "Check if your router has updates available. Updates often improve performance and security.",
                TipCategory.ROUTER
        ));
        tips.add(new Tip(
                "Router Age Check",
                "If your router is more than 4-5 years old, consider upgrading to a newer model for better speeds.",
                TipCategory.ROUTER
        ));

        // Device tips
        tips.add(new Tip(
                "Disconnect Unused Devices",
                "Other devices on your network share the same bandwidth. Disconnect devices you're not using.",
                TipCategory.DEVICES
        ));
        tips.add(new Tip(
                "Close Background Apps",
                "Apps updating or syncing in the background can slow down your connection.",
                TipCategory.DEVICES
        ));
        tips.add(new Tip(
                "Limit Streaming",
                "Multiple people streaming video at once can slow down everyone's connection.",
                TipCategory.DEVICES
        ));

        // Security tips
        tips.add(new Tip(
                "Use a Strong Password",
                "A strong Wi-Fi password prevents neighbors from using your internet and slowing you down.",
                TipCategory.SECURITY
        ));
        tips.add(new Tip(
                "Check Unknown Devices",
                "Look for devices you don't recognize on your network - they might be using your bandwidth.",
                TipCategory.SECURITY
        ));
        tips.add(new Tip(
                "Avoid Open Networks",
                "Open public Wi-Fi isn't secure. Avoid accessing banking or personal accounts on open networks.",
                TipCategory.SECURITY
        ));

        // General tips
        tips.add(new Tip(
                "Peak Hours",
                "Internet can be slower in the evening when everyone is online. Try important tasks during off-peak hours.",
                TipCategory.GENERAL
        ));
        tips.add(new Tip(
                "Contact Your Provider",
                "If problems persist, your internet provider can check if there are issues in your area.",
                TipCategory.GENERAL
        ));
        tips.add(new Tip(
                "Use Ethernet When Possible",
                "For the fastest, most reliable connection, use a cable instead of Wi-Fi when you can.",
                TipCategory.GENERAL
        ));

        return tips;
    }
}
