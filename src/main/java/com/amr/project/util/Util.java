package com.amr.project.util;

import java.util.UUID;

public class Util {

    public static String getGuid(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
