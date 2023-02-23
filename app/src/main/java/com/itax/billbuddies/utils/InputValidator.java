package com.itax.billbuddies.utils;

import android.widget.EditText;

import java.util.ArrayList;

public class InputValidator {

    public static void validateInput(EditText editText){
        editText.setError("Field should not be empty");
        editText.requestFocus();
    }

    public static void validateInput(EditText editText,String errorMsg){
        editText.setError(errorMsg);
        editText.requestFocus();
    }

    public static boolean validateInput(ArrayList<EditText>editTextList){
        for (int i=0; i<editTextList.size(); i++){
            if (editTextList.get(i).getText().toString().isEmpty()){
                editTextList.get(i).setError("Field should not be empty");
                editTextList.get(i).requestFocus();
                return false;
            }
        }
        return true;
    }
}
