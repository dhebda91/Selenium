package commons.utils;

import io.qameta.allure.Allure;

public class TextAttachment {
    public static String addTextAttachment(String text){
        Allure.addAttachment("Tekst", text);
        return text;
    }

    public static String addCustomTextAttachment(String attachmentName, String value){
        Allure.addAttachment(attachmentName, value);
        return value;
    }
}
