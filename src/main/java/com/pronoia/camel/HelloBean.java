package com.pronoia.camel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A bean which we use in the route
 */
public class HelloBean implements Hello {

    private String say = "Hello World";

    public String hello() {
        return say;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
