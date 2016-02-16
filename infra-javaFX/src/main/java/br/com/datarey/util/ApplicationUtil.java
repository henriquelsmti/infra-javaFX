package br.com.datarey.util;

import javafx.application.Application;

/**
 * Created by henrique.luiz on 22/01/2016.
 */
public class ApplicationUtil {

    private static Application application;

    private ApplicationUtil(){}

    public static Application getApplication() {
        return application;
    }

    public static void setApplication(Application application) {
        ApplicationUtil.application = application;
    }

}
