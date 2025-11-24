package com.example.wifissistor2j;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for GenericSpeedTestReport
 */
public class GenericSpeedTestReportTest {

    @Test
    public void constructor_createsValidReport() {
        double transferRate = 100000000.0; // 100 Mbps in bits
        GenericSpeedTestReport report = new GenericSpeedTestReport(transferRate);
        assertNotNull(report);
        assertEquals(transferRate, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void getTransferRateBit_returnsCorrectValue() {
        double transferRate = 50000000.0; // 50 Mbps in bits
        GenericSpeedTestReport report = new GenericSpeedTestReport(transferRate);
        assertEquals(50000000.0, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void constructor_withZeroTransferRate() {
        GenericSpeedTestReport report = new GenericSpeedTestReport(0.0);
        assertEquals(0.0, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void constructor_withSmallTransferRate() {
        double transferRate = 1000.0; // 1 Kbps
        GenericSpeedTestReport report = new GenericSpeedTestReport(transferRate);
        assertEquals(1000.0, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void constructor_withLargeTransferRate() {
        double transferRate = 1000000000.0; // 1 Gbps in bits
        GenericSpeedTestReport report = new GenericSpeedTestReport(transferRate);
        assertEquals(1000000000.0, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void constructor_withDecimalTransferRate() {
        double transferRate = 12345678.9;
        GenericSpeedTestReport report = new GenericSpeedTestReport(transferRate);
        assertEquals(12345678.9, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void constructor_withNegativeTransferRate() {
        // While this shouldn't happen in normal operation, the class should handle it
        double transferRate = -1000.0;
        GenericSpeedTestReport report = new GenericSpeedTestReport(transferRate);
        assertEquals(-1000.0, report.getTransferRateBit(), 0.001);
    }

    @Test
    public void multipleReports_independentValues() {
        GenericSpeedTestReport report1 = new GenericSpeedTestReport(100000000.0);
        GenericSpeedTestReport report2 = new GenericSpeedTestReport(200000000.0);
        
        assertEquals(100000000.0, report1.getTransferRateBit(), 0.001);
        assertEquals(200000000.0, report2.getTransferRateBit(), 0.001);
        
        // Ensure they are independent
        assertNotEquals(report1.getTransferRateBit(), report2.getTransferRateBit(), 0.001);
    }
}
