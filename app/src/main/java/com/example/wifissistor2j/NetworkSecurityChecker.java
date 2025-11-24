package com.example.wifissistor2j;

/**
 * Checks network security and provides user-friendly security information.
 * Determines if a network is secure (WPA2/WPA3) or unsecured (Open).
 */
public class NetworkSecurityChecker {

    /**
     * Security result containing status and user-friendly advice.
     */
    public static class SecurityResult {
        private final boolean isSecure;
        private final String label;
        private final String advice;
        private final String securityType;

        public SecurityResult(boolean isSecure, String label, String advice, String securityType) {
            this.isSecure = isSecure;
            this.label = label;
            this.advice = advice;
            this.securityType = securityType;
        }

        public boolean isSecure() {
            return isSecure;
        }

        public String getLabel() {
            return label;
        }

        public String getAdvice() {
            return advice;
        }

        public String getSecurityType() {
            return securityType;
        }
    }

    /**
     * Analyzes the security capabilities string from a Wi-Fi scan result.
     *
     * @param capabilities The capabilities string from ScanResult (e.g., "[WPA2-PSK-CCMP][ESS]")
     * @return SecurityResult with security status and advice
     */
    public static SecurityResult checkSecurity(String capabilities) {
        if (capabilities == null || capabilities.isEmpty()) {
            return new SecurityResult(
                    false,
                    "Unknown",
                    "Unable to determine network security. Be cautious.",
                    "Unknown"
            );
        }

        String capsUpper = capabilities.toUpperCase();

        // Check for WPA3 (most secure)
        if (capsUpper.contains("WPA3") || capsUpper.contains("SAE")) {
            return new SecurityResult(
                    true,
                    "Secure",
                    "This network uses WPA3 - the latest security standard.",
                    "WPA3"
            );
        }

        // Check for WPA2 (secure)
        if (capsUpper.contains("WPA2")) {
            return new SecurityResult(
                    true,
                    "Secure",
                    "This network is protected with a password.",
                    "WPA2"
            );
        }

        // Check for WPA (less secure but still protected)
        if (capsUpper.contains("WPA")) {
            return new SecurityResult(
                    true,
                    "Secure",
                    "This network is password protected, but uses older security.",
                    "WPA"
            );
        }

        // Check for WEP (outdated security)
        if (capsUpper.contains("WEP")) {
            return new SecurityResult(
                    false,
                    "Weak Security",
                    "This network uses outdated WEP security. Avoid sensitive activities.",
                    "WEP"
            );
        }

        // Check for open network (no security)
        if (capsUpper.contains("ESS") && !capsUpper.contains("WPA") && !capsUpper.contains("WEP")) {
            return new SecurityResult(
                    false,
                    "Unsecured",
                    "This is an open network. Anyone nearby can join. Avoid banking or passwords.",
                    "Open"
            );
        }

        // Default case - assume open/unsecured
        return new SecurityResult(
                false,
                "Unsecured",
                "This network doesn't appear to be password protected.",
                "Open"
        );
    }

    /**
     * Gets a simple secure/unsecure label for display.
     *
     * @param capabilities The capabilities string from ScanResult
     * @return "Secure" or "Unsecured"
     */
    public static String getSimpleSecurityLabel(String capabilities) {
        SecurityResult result = checkSecurity(capabilities);
        return result.isSecure() ? "Secure" : "Unsecured";
    }

    /**
     * Checks if a network is secure based on its capabilities string.
     *
     * @param capabilities The capabilities string from ScanResult
     * @return true if network uses WPA/WPA2/WPA3, false otherwise
     */
    public static boolean isNetworkSecure(String capabilities) {
        return checkSecurity(capabilities).isSecure();
    }
}
