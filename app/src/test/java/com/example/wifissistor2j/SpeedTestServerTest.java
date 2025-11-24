package com.example.wifissistor2j;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for SpeedTestServer
 */
public class SpeedTestServerTest {

    @Test
    public void constructor_createsValidServer() {
        SpeedTestServer server = new SpeedTestServer(
            "Test Server",
            "http://test.com/download",
            "http://test.com/upload"
        );
        assertNotNull(server);
    }

    @Test
    public void getName_returnsCorrectName() {
        SpeedTestServer server = new SpeedTestServer(
            "Test Server",
            "http://test.com/download",
            "http://test.com/upload"
        );
        assertEquals("Test Server", server.getName());
    }

    @Test
    public void getDownloadUrl_returnsCorrectUrl() {
        SpeedTestServer server = new SpeedTestServer(
            "Test Server",
            "http://test.com/download",
            "http://test.com/upload"
        );
        assertEquals("http://test.com/download", server.getDownloadUrl());
    }

    @Test
    public void getUploadUrl_returnsCorrectUrl() {
        SpeedTestServer server = new SpeedTestServer(
            "Test Server",
            "http://test.com/download",
            "http://test.com/upload"
        );
        assertEquals("http://test.com/upload", server.getUploadUrl());
    }

    @Test
    public void constructor_withEmptyStrings() {
        SpeedTestServer server = new SpeedTestServer("", "", "");
        assertEquals("", server.getName());
        assertEquals("", server.getDownloadUrl());
        assertEquals("", server.getUploadUrl());
    }

    @Test
    public void constructor_withHttpsUrls() {
        SpeedTestServer server = new SpeedTestServer(
            "Secure Server",
            "https://secure.test.com/download",
            "https://secure.test.com/upload"
        );
        assertEquals("Secure Server", server.getName());
        assertEquals("https://secure.test.com/download", server.getDownloadUrl());
        assertEquals("https://secure.test.com/upload", server.getUploadUrl());
    }

    @Test
    public void constructor_withLongValues() {
        String longName = "Very Long Server Name That Someone Decided To Use For Some Reason";
        String longUrl = "http://very.long.domain.name.that.someone.registered.for.some.reason.com/very/long/path/to/file";
        
        SpeedTestServer server = new SpeedTestServer(longName, longUrl, longUrl);
        assertEquals(longName, server.getName());
        assertEquals(longUrl, server.getDownloadUrl());
        assertEquals(longUrl, server.getUploadUrl());
    }

    @Test
    public void constructor_withSpecialCharacters() {
        SpeedTestServer server = new SpeedTestServer(
            "Test-Server_1.0",
            "http://test.com/download?param=value&other=123",
            "http://test.com/upload?param=value&other=123"
        );
        assertEquals("Test-Server_1.0", server.getName());
        assertTrue(server.getDownloadUrl().contains("?param=value"));
        assertTrue(server.getUploadUrl().contains("?param=value"));
    }

    @Test
    public void multipleServers_independentValues() {
        SpeedTestServer server1 = new SpeedTestServer("Server1", "http://s1.com/dl", "http://s1.com/ul");
        SpeedTestServer server2 = new SpeedTestServer("Server2", "http://s2.com/dl", "http://s2.com/ul");
        
        assertEquals("Server1", server1.getName());
        assertEquals("Server2", server2.getName());
        assertNotEquals(server1.getName(), server2.getName());
        assertNotEquals(server1.getDownloadUrl(), server2.getDownloadUrl());
    }
}
