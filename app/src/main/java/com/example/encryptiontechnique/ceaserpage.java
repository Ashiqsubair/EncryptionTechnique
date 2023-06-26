package com.example.encryptiontechnique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ceaserpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceaserpage);
    }
    public void encrypt(View v){
        try {
            EditText txt_plain,txt_key,txt_cipher;
            txt_plain=(EditText) findViewById(R.id.txt_plain);
            txt_key=(EditText) findViewById(R.id.txt_key);
            txt_cipher=(EditText) findViewById(R.id.txt_cipher);
            String str_plain=txt_plain.getText().toString().toLowerCase();

            String str_key=txt_key.getText().toString();
            String alpha="abcdefghijklmnopqrstuvwxyz";
            String newCipherText="";
            for(int i=0;i < str_plain.length();i++){
                for (int j=0;j<=25;j++){
                    if ((str_plain.charAt(i))==alpha.charAt(j)){
                        int calculate_Cipher=(j+Integer.parseInt(str_key.toString())%26);
                        newCipherText+=alpha.charAt(calculate_Cipher);
                        break;
                    }
                    if(str_plain.charAt(i)==' '){
                        newCipherText+=" ";
                        break;
                    }

                }
            }
            txt_cipher.setText(String.valueOf(newCipherText));
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    public void decrypt(View v){
        try{
            EditText txt_plain,txt_key,txt_cipher;
            txt_plain=(EditText) findViewById(R.id.txt_plain);
            txt_key=(EditText) findViewById(R.id.txt_key);
            txt_cipher=(EditText) findViewById(R.id.txt_cipher);
            String str_cipher=txt_cipher.getText().toString();
            String str_key=txt_key.getText().toString();
            String alpha="abcdefghijklmnopqrstuvwxyz";
            String newPlainText="";
            for(int i=0;i < str_cipher.length();i++){
                for (int j=0;j<=25;j++){
                    if (str_cipher.charAt(i)==alpha.charAt(j)){
                        int calculate_plain=(j-Integer.parseInt(str_key.toString())%26);
                        newPlainText+=alpha.charAt(calculate_plain);
                    }
                    if(str_cipher.charAt(i)==' '){
                        newPlainText+=" ";
                        break;
                    }
                }
            }
            txt_plain.setText(String.valueOf(newPlainText));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void back(View v){
        Intent intent=new Intent(ceaserpage.this,MainActivity.class);
        startActivity(intent);
    }

}