package util;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

import java.util.Date;
import java.util.Optional;

public class AllureWatcher implements TestWatcher {

    @Attachment (value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Timestamp log", type = "text/plain")
    public static String saveTimestampLog() {
        Date d = new Date();
        String[] dateTokens = d.toString().split(" ");

        String month = dateTokens[1];
        String day = dateTokens[2];
        String year = dateTokens[5];
        String time = dateTokens[3];

        return month + " " + day + ", " + year + " " + time;
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {

    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {

    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {

    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {

    }
}