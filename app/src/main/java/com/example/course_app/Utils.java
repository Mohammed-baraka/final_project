package com.example.course_app;


import android.text.InputType;
import android.widget.EditText;

public class Utils {

    public static boolean showPassword(boolean isPasswordVisible , EditText text){
        if (isPasswordVisible){
            text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordVisible = false;
        }else{
            text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isPasswordVisible = true;
        }
        text.setSelection(text.getText().length());

        return isPasswordVisible;
    }

}
