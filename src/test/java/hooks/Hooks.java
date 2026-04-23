package hooks;

import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setupBrowser() {
        
        String env = ConfigReader.getProperty("env");
        String applicationUrl = ConfigReader.getProperty("appUrl");

        if (applicationUrl == null || applicationUrl.isBlank()) {
            applicationUrl = ConfigReader.getProperty(env + ".url");
        }

        BrowserFactory.startBrowser(
                ConfigReader.getProperty("browser"),
                applicationUrl
        );
    }

    @After
    public void tearDown() {
        if (BrowserFactory.getDriverInstance() != null) {
            BrowserFactory.getDriverInstance().quit();
            BrowserFactory.removeDriver();
        }
    }
}
