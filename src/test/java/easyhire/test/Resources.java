package easyhire.test;

import easyhire.page.EasyHire;
import utils.PropertyLoader;

/**
 * Created by dmitry on 30.5.15.
 */
public abstract class Resources {
    public static final String MAIN_PAGE_URL = PropertyLoader.getProperty("config.properties", "MAIN_PAGE_URL");
    public static final EasyHire.DriverType DRIVER_TYPE = EasyHire.DriverType.HTML;
    //login easyhire; password - easyhire123
    public static final String MAILINATOR_TOKEN = PropertyLoader.getProperty("config.properties", "MAILINATOR_TOKEN");
    public static final String TS_005_UNIQUE_USERID = PropertyLoader.getProperty("config.properties", "USERID_MASK")+Math.random()*1000000;
}
