package util;

import java.io.IOException;
import java.util.Date;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

public class Screen {

    private String browserName;
    protected String executionMode;


    public Screen takeScreen () throws IOException {
        executionMode = PropertiesFileReader.getProperty(PropertyKey.EXECUTIONMODE);
        browserName = PropertiesFileReader.getProperty(PropertyKey.BROWSERNAME);

        Date currentdate = new Date ();
        String screenshotFileName = currentdate.toString().replace(" ", "-").replace(":", "-");

        if (executionMode.equals("local")) {
            Shutterbug.shootPage(WebDriverSingletonLocal.initialize(browserName), Capture.FULL, true).save(".//target//artifacts//screenshot//"+screenshotFileName+".png");
        } else if (executionMode.equals("remote")) {
            Shutterbug.shootPage(WebDriverSingletonRemote.openDriverInSauceLabs(browserName), Capture.FULL, true).save(".//target//artifacts//screenshot//"+screenshotFileName+".png");

        }

        return this;
    }
}
