package easyhire.test;

import easyhire.page.EasyHire;

/**
 * Created by dmitry on 30.5.15.
 */
public abstract class Resources {

    public static final String MAIN_PAGE_URL = "https://54.191.172.71/";
    public static final EasyHire.DriverType DRIVER_TYPE = EasyHire.DriverType.HTML;
    //login easyhire; password - easyhire123
    public static final String MAILINATOR_TOKEN = "cfe6a7f4d45d4c1c9083dd1f9af75f35";
    public static final String TS_005_UNIQUE_USERID = "test"+Math.random()*1000000;
}
