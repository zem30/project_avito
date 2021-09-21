package com.amr.project.util;

import java.util.Base64;

public class ImageConverter {
    public static String convertreArrayToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
