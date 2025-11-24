package com.example.wifissistor2j;

/**
 * Calculates a network health score (0-100) based on signal strength and speed.
 * Provides user-friendly labels: Excellent, Good, Fair, Poor.
 */
public class NetworkHealthCalculator {

    /**
     * Health score result containing the numeric score and label.
     */
    public static class HealthScore {
        private final int score;
        private final String label;
        private final String tip;

        public HealthScore(int score, String label, String tip) {
            this.score = score;
            this.label = label;
            this.tip = tip;
        }

        public int getScore() {
            return score;
        }

        public String getLabel() {
            return label;
        }

        public String getTip() {
            return tip;
        }
    }

    /**
     * Calculates network health score from signal strength (RSSI in dBm) and speed (Mbps).
     *
     * @param rssi      Signal strength in dBm (typically -30 to -90)
     * @param speedMbps Download speed in Mbps
     * @return HealthScore with score 0-100, label, and optional tip
     */
    public static HealthScore calculate(int rssi, double speedMbps) {
        // Calculate signal score (0-50 points)
        int signalScore = calculateSignalScore(rssi);

        // Calculate speed score (0-50 points)
        int speedScore = calculateSpeedScore(speedMbps);

        // Total score
        int totalScore = signalScore + speedScore;

        // Get label and tip based on total score
        String label = getLabel(totalScore);
        String tip = getTip(totalScore, rssi, speedMbps);

        return new HealthScore(totalScore, label, tip);
    }

    /**
     * Calculates signal component of health score (0-50 points).
     * 
     * @param rssi Signal strength in dBm
     * @return Score from 0-50
     */
    static int calculateSignalScore(int rssi) {
        // RSSI typically ranges from -30 (excellent) to -90 (very weak)
        // Map to 0-50 score
        if (rssi >= -50) {
            return 50; // Excellent signal
        } else if (rssi >= -60) {
            return 40; // Good signal
        } else if (rssi >= -70) {
            return 30; // Fair signal
        } else if (rssi >= -80) {
            return 20; // Weak signal
        } else {
            return 10; // Very weak signal
        }
    }

    /**
     * Calculates speed component of health score (0-50 points).
     *
     * @param speedMbps Download speed in Mbps
     * @return Score from 0-50
     */
    static int calculateSpeedScore(double speedMbps) {
        // Map speed to 0-50 score
        if (speedMbps >= 100) {
            return 50; // Excellent speed
        } else if (speedMbps >= 50) {
            return 40; // Very good speed
        } else if (speedMbps >= 25) {
            return 30; // Good speed
        } else if (speedMbps >= 10) {
            return 20; // Fair speed
        } else if (speedMbps >= 5) {
            return 15; // Slow speed
        } else {
            return 10; // Very slow speed
        }
    }

    /**
     * Returns a user-friendly label for the health score.
     *
     * @param score Health score 0-100
     * @return Label: Excellent, Good, Fair, or Poor
     */
    public static String getLabel(int score) {
        if (score >= 80) {
            return "Excellent";
        } else if (score >= 60) {
            return "Good";
        } else if (score >= 40) {
            return "Fair";
        } else {
            return "Poor";
        }
    }

    /**
     * Returns a helpful tip based on the current network conditions.
     *
     * @param score     Total health score
     * @param rssi      Signal strength in dBm
     * @param speedMbps Download speed in Mbps
     * @return A helpful tip for the user, or empty string if no tip needed
     */
    static String getTip(int score, int rssi, double speedMbps) {
        if (score >= 80) {
            return ""; // No tip needed, everything is great
        }

        // Prioritize tips based on the weaker component
        int signalScore = calculateSignalScore(rssi);
        int speedScore = calculateSpeedScore(speedMbps);

        if (signalScore < speedScore) {
            // Signal is the main issue
            if (rssi < -80) {
                return "Try moving closer to your Wi-Fi router.";
            } else if (rssi < -70) {
                return "Your signal is weak. Move to a spot with better coverage.";
            } else {
                return "Signal could be better. Try repositioning your device.";
            }
        } else {
            // Speed is the main issue
            if (speedMbps < 5) {
                return "Your connection is very slow. Check if others are using the network.";
            } else if (speedMbps < 10) {
                return "Speed is below average. Try reducing network activity.";
            } else {
                return "Your speed could be better. Consider checking your router settings.";
            }
        }
    }

    /**
     * Returns the number of signal bars (1-5) based on RSSI value.
     *
     * @param rssi Signal strength in dBm
     * @return Number of bars 1-5
     */
    public static int getSignalBars(int rssi) {
        if (rssi >= -50) {
            return 5;
        } else if (rssi >= -60) {
            return 4;
        } else if (rssi >= -70) {
            return 3;
        } else if (rssi >= -80) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * Returns a color hint for the signal bars based on RSSI.
     * Colors: green (excellent), light green (good), yellow (fair), orange (weak), red (very weak)
     *
     * @param rssi Signal strength in dBm
     * @return Color name as string
     */
    public static String getSignalBarColor(int rssi) {
        if (rssi >= -50) {
            return "green";
        } else if (rssi >= -60) {
            return "light_green";
        } else if (rssi >= -70) {
            return "yellow";
        } else if (rssi >= -80) {
            return "orange";
        } else {
            return "red";
        }
    }
}
