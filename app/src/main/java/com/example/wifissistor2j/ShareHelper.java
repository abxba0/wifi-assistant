package com.example.wifissistor2j;

import android.content.Context;
import android.content.Intent;

/**
 * Helper class for sharing Wi-Fi test results in a user-friendly format.
 */
public class ShareHelper {

    /**
     * Creates a shareable text summary of the Wi-Fi test results.
     *
     * @param healthLabel   Health label (Excellent/Good/Fair/Poor)
     * @param healthScore   Health score 0-100
     * @param downloadMbps  Download speed in Mbps
     * @param uploadMbps    Upload speed in Mbps
     * @param signalQuality Signal quality label
     * @param isSecure      Whether the network is secure
     * @return Formatted shareable text
     */
    public static String createShareableText(
            String healthLabel,
            int healthScore,
            double downloadMbps,
            double uploadMbps,
            String signalQuality,
            boolean isSecure) {

        StringBuilder sb = new StringBuilder();
        sb.append("📶 My Wi-Fi Results\n");
        sb.append("─────────────────\n");
        sb.append(String.format("Health: %s (%d/100)\n", healthLabel, healthScore));
        sb.append(String.format("Download: %.1f Mbps\n", downloadMbps));
        sb.append(String.format("Upload: %.1f Mbps\n", uploadMbps));
        sb.append(String.format("Signal: %s\n", signalQuality));
        sb.append(String.format("Security: %s\n", isSecure ? "🔒 Secure" : "⚠️ Open"));
        sb.append("─────────────────\n");
        sb.append("Tested with Wi-Fi Assistant");

        return sb.toString();
    }

    /**
     * Creates a simple one-line shareable text.
     *
     * @param healthLabel  Health label
     * @param downloadMbps Download speed in Mbps
     * @return Simple shareable text
     */
    public static String createSimpleShareableText(String healthLabel, double downloadMbps) {
        return String.format("My Wi-Fi: %s (%.0f Mbps) 📶", healthLabel, downloadMbps);
    }

    /**
     * Creates a share intent with the provided text.
     *
     * @param text The text to share
     * @return Share intent
     */
    public static Intent createShareIntent(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        return shareIntent;
    }

    /**
     * Launches the share dialog with the test results.
     *
     * @param context       Android context
     * @param healthLabel   Health label
     * @param healthScore   Health score
     * @param downloadMbps  Download speed
     * @param uploadMbps    Upload speed
     * @param signalQuality Signal quality
     * @param isSecure      Security status
     */
    public static void shareResults(
            Context context,
            String healthLabel,
            int healthScore,
            double downloadMbps,
            double uploadMbps,
            String signalQuality,
            boolean isSecure) {

        String shareText = createShareableText(
                healthLabel, healthScore, downloadMbps, uploadMbps, signalQuality, isSecure);

        Intent shareIntent = createShareIntent(shareText);
        context.startActivity(Intent.createChooser(shareIntent, "Share your Wi-Fi results"));
    }

    /**
     * Launches the share dialog with a simple one-line result.
     *
     * @param context      Android context
     * @param healthLabel  Health label
     * @param downloadMbps Download speed
     */
    public static void shareSimpleResult(Context context, String healthLabel, double downloadMbps) {
        String shareText = createSimpleShareableText(healthLabel, downloadMbps);
        Intent shareIntent = createShareIntent(shareText);
        context.startActivity(Intent.createChooser(shareIntent, "Share your Wi-Fi results"));
    }
}
