package util;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingletonRemote {
    private static WebDriver driver;

    private static final String USERNAME_SL_KEY = "username";
    private static final String ACCESSKEY_SL_KEY = "accessKey";

    private static String userNameSLVal;
    private static String accessKeySLVal;
    private static String urlSL;

    private static WebDriverSingletonRemote instanceDriver  = null;


    private WebDriverSingletonRemote() {
    }

    public static WebDriver getDriver() {
        System.out.println("REMOTE!!!");
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        WebDriverSingletonRemote.driver = driver;
    }

    public static WebDriver openDriverInSauceLabs(String browserName) throws IOException {

        userNameSLVal = PropertiesFileReader.getProperty(PropertyKey.USERNAMESL);
        accessKeySLVal = PropertiesFileReader.getProperty(PropertyKey.ACCESSKEYSL);

        System.out.println("browser name is: " + browserName);

        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability(USERNAME_SL_KEY, PropertiesFileReader.getProperty(PropertyKey.USERNAMESL));
        sauceOpts.setCapability(ACCESSKEY_SL_KEY, PropertiesFileReader.getProperty(PropertyKey.ACCESSKEYSL));

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("sauce:options", sauceOpts);

        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.WIN10);
            cap.setVersion("latest");
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.WIN8_1);
            cap.setVersion("latest");
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            cap.setBrowserName("MicrosoftEdge");
            cap.setPlatform(Platform.WIN10);
            cap.setVersion("latest");
        }

        try {
            urlSL = PropertiesFileReader.getProperty(PropertyKey.URLSL);
            URL url = new URL (urlSL);
            return new RemoteWebDriver(url, cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Sauce Labs driver instance");
        }
    }
}


