package com.example.wifissistor2j;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a friendly glossary of Wi-Fi terms with plain language explanations.
 */
public class GlossaryItem {
    private final String term;
    private final String explanation;

    public GlossaryItem(String term, String explanation) {
        this.term = term;
        this.explanation = explanation;
    }

    public String getTerm() {
        return term;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return term + ": " + explanation;
    }

    /**
     * Returns a list of common Wi-Fi terms with plain language explanations.
     *
     * @return List of GlossaryItem objects
     */
    public static List<GlossaryItem> getDefaultGlossary() {
        List<GlossaryItem> glossary = new ArrayList<>();

        // Speed related
        glossary.add(new GlossaryItem(
                "Download Speed",
                "How fast you can get things from the internet. Higher is better for streaming videos or downloading files."
        ));
        glossary.add(new GlossaryItem(
                "Upload Speed",
                "How fast you can send things to the internet. Important for video calls and sharing photos."
        ));
        glossary.add(new GlossaryItem(
                "Mbps",
                "Megabits per second - a measure of internet speed. Think of it like the 'speed limit' for your data."
        ));

        // Signal related
        glossary.add(new GlossaryItem(
                "Signal Strength",
                "How well your device can hear the Wi-Fi router. Stronger signals mean faster, more reliable connections."
        ));
        glossary.add(new GlossaryItem(
                "Wi-Fi Range",
                "How far the Wi-Fi signal reaches. Walls and floors can block or weaken the signal."
        ));

        // Stability related
        glossary.add(new GlossaryItem(
                "Stability",
                "Whether your connection stays consistent. Good stability means video calls won't freeze."
        ));
        glossary.add(new GlossaryItem(
                "Latency",
                "How quickly your device communicates with the internet. Lower is better for gaming and video calls."
        ));

        // Security related
        glossary.add(new GlossaryItem(
                "Secure Network",
                "A network protected with a password. Your data is encrypted and safe from others."
        ));
        glossary.add(new GlossaryItem(
                "Open Network",
                "A network without a password. Anyone nearby can join. Be careful with sensitive information."
        ));
        glossary.add(new GlossaryItem(
                "WPA2/WPA3",
                "Modern security types that protect your Wi-Fi with strong encryption. WPA3 is the newest and safest."
        ));

        // Network related
        glossary.add(new GlossaryItem(
                "Router",
                "The device that creates your Wi-Fi network and connects you to the internet."
        ));
        glossary.add(new GlossaryItem(
                "Network Name (SSID)",
                "The name you see when looking for Wi-Fi networks on your device."
        ));
        glossary.add(new GlossaryItem(
                "IP Address",
                "A unique number that identifies your device on the network, like a street address for data."
        ));

        // Quality labels
        glossary.add(new GlossaryItem(
                "Excellent",
                "Your Wi-Fi is working great! Perfect for streaming, gaming, and video calls."
        ));
        glossary.add(new GlossaryItem(
                "Good",
                "Your Wi-Fi works well for most activities including video streaming."
        ));
        glossary.add(new GlossaryItem(
                "Fair",
                "Your Wi-Fi is okay but might struggle with HD video or multiple devices."
        ));
        glossary.add(new GlossaryItem(
                "Poor",
                "Your Wi-Fi needs attention. Try moving closer to your router."
        ));

        return glossary;
    }
}
