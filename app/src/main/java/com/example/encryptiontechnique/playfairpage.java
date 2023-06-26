package com.example.encryptiontechnique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class playfairpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playfairpage);
    }
    public void encrypt_Playfair(View v){
        try {
            EditText txt_plain_p, txt_key_p, txt_cipher_p;
            txt_plain_p = (EditText) findViewById(R.id.txt_plain_p);
            txt_key_p = (EditText) findViewById(R.id.txt_key_p);
            txt_cipher_p = (EditText) findViewById(R.id.txt_cipher_p);
            String str_key = txt_key_p.getText().toString().toUpperCase();
            String filtered_key = "";

//            removing duplicates from key

            for (int x = 0; x < str_key.length(); x++) {
                int z;
                for (z = 0; z < x; z++) {
                    if (str_key.charAt(x) == str_key.charAt(z)) {
                        break;
                    }
                }
                if (z == x) {
                    filtered_key += str_key.charAt(x);
                }
            }
            String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char array[][] = new char[5][5];

            //txt_cipher_p.setText("i is"+String.valueOf(i)+" j is"+String.valueOf(j));
            int countforalpha;
            String charwokey = "";
            for (char c = 'A'; c <= 'Z'; c++) {
                int flag = 0;
                for (countforalpha = 0; countforalpha < filtered_key.length(); countforalpha++) {
                    if (c == filtered_key.charAt(countforalpha) || c == 'I') {
                        flag = flag + 1;
                    }
                }
                if (flag == 0) {
                    charwokey += c;
                }
            }
            int charcount = 0;
            int newcount = 0;
            String finalkey = filtered_key + charwokey;
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 4; j++) {
                    array[i][j] = finalkey.charAt(newcount);
                    newcount++;
                }
            }


            String str_plain = txt_plain_p.getText().toString().toUpperCase();
            String newtxt="";
            for(int y=0;y<str_plain.length();y++){
                if(str_plain.charAt(y)==' '){
                    continue;
                }
                else {
                    newtxt+=str_plain.charAt(y);
                }
            }
            str_plain=newtxt;
            int m = 5;
            int n = 5;
            int i, j, p = 0;
            StringBuilder sb = new StringBuilder(str_plain);
            int originalLength = sb.length();
            for (i = 0; i < originalLength; i = i + 2) {
                if (i + 1 >= sb.length()) {
                    break;
                }
                if (sb.charAt(i + 1) == '0') {
                    break;
                }
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    sb.insert(i + 1, 'X');
                    originalLength++;
                    if (sb.length() % 2 == 1) {
                        sb.insert(originalLength, '0');
                        p++;
                    }
                }
            }
            int len = sb.length() - p;
            sb.setLength(len);
            str_plain = String.valueOf(sb);
            if (str_plain.length() % 2 != 0) {
                str_plain += 'X';
            }
            System.out.println(str_plain + " length is " + str_plain.length());

            String encrypted = "";
            for (int c = 0; c < str_plain.length(); c = c + 2) {
                String substr = str_plain.substring(c, c + 2);
                int element1 = substr.charAt(0);
                int element2 = substr.charAt(1);
                int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
                System.out.println((char) element1 + " " + (char) element2);
                for (int t = 0; t < 5; t++) {
                    for (int k = 0; k < 5; k++) {
                        if (array[t][k] == element1) {
                            row1 = t;
                            col1 = k;
                        }
                    }
                }

                for (int q = 0; q < 5; q++) {
                    for (int w = 0; w < 5; w++) {
                        if (array[q][w] == element2) {
                            row2 = q;
                            col2 = w;
                        }
                    }
                }
                System.out.println(row1 + "," + col1 + "   " + row2 + "," + col2);
                if (row1 == row2) {
                    if (col1 == 4) {
                        encrypted += String.valueOf(array[row1][0]).toString();
                    }
                    else{
                        encrypted += String.valueOf(array[row1][col1 + 1]).toString();
                    }
                    if (col2 == 4) {
                        encrypted += String.valueOf(array[row2][0]).toString();
                    }
                    else{
                        encrypted += String.valueOf(array[row2][col2 + 1]).toString();
                    }
                }
                if (col1 == col2) {
                    if (row1 == 4) {
                        encrypted += String.valueOf(array[0][col1]).toString();
                    }
                    else{
                        encrypted += String.valueOf(array[row1 + 1][col1]).toString();
                    }
                    if (row2 == 4) {
                        encrypted += String.valueOf(array[0][col2]).toString();
                    }
                    else{
                        encrypted += String.valueOf(array[row2 + 1][col2]).toString();
                    }
                }
                if(row1!=row2 &&col1!=col2){
                    encrypted += String.valueOf(array[row1][col2]).toString();
                    encrypted += String.valueOf(array[row2][col1]).toString();
                }
                txt_cipher_p.setText(encrypted.toString());
            }
        }
        catch (Exception e){
            System.out.println("Exception "+e);
        }
    }
    public void back(View v){
        Intent intent=new Intent(playfairpage.this,MainActivity.class);
        startActivity(intent);
    }
    public void decrypt_Playfair(View v){
        try {
            EditText txt_plain_p,txt_key_p,txt_cipher_p;
            txt_plain_p=(EditText) findViewById(R.id.txt_plain_p);
            txt_key_p=(EditText) findViewById(R.id.txt_key_p);
            txt_cipher_p=(EditText) findViewById(R.id.txt_cipher_p);
            String str_key=txt_key_p.getText().toString().toUpperCase();
            String str_cipher=txt_cipher_p.getText().toString().toUpperCase();

            String filtered_key = "";
            for (int x = 0; x < str_key.length(); x++) {
                int z;
                for (z = 0; z < x; z++) {
                    if (str_key.charAt(x) == str_key.charAt(z)) {
                        break;
                    }
                }
                if (z == x) {
                    filtered_key += str_key.charAt(x);
                }
            }
            String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char array[][] = new char[5][5];

            int countforalpha;
            String charwokey = "";
            for (char c = 'A'; c <= 'Z'; c++) {
                int flag = 0;
                for (countforalpha = 0; countforalpha < filtered_key.length(); countforalpha++) {
                    if (c == filtered_key.charAt(countforalpha) || c == 'I') {
                        flag = flag + 1;
                    }
                }
                if (flag == 0) {
                    charwokey += c;
                }
            }
            int charcount = 0;
            int newcount = 0;
            String finalkey = filtered_key + charwokey;
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 4; j++) {
                    array[i][j] = finalkey.charAt(newcount);
                    newcount++;
                }
            }

            String decrypted = "";
            for (int c = 0; c < str_cipher.length(); c = c + 2) {
                String substr = str_cipher.substring(c, c + 2);
                int element1 = substr.charAt(0);
                int element2 = substr.charAt(1);
                int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
                System.out.println((char) element1 + " " + (char) element2);
                for (int t = 0; t < 5; t++) {
                    for (int k = 0; k < 5; k++) {
                        if (array[t][k] == element1) {
                            row1 = t;
                            col1 = k;
                        }
                    }
                }

                for (int q = 0; q < 5; q++) {
                    for (int w = 0; w < 5; w++) {
                        if (array[q][w] == element2) {
                            row2 = q;
                            col2 = w;
                        }
                    }
                }
                System.out.println(row1 + "," + col1 + "   " + row2 + "," + col2);
                if (row1 == row2) {
                    if (col1 == 0) {
                        decrypted += String.valueOf(array[row1][4]).toString();
                    }
                    else{
                        decrypted += String.valueOf(array[row1][col1 - 1]).toString();
                    }
                    if (col2 == 0) {
                        decrypted += String.valueOf(array[row2][4]).toString();
                    }
                    else{
                        decrypted += String.valueOf(array[row2][col2 - 1]).toString();
                    }
                }
                if (col1 == col2) {
                    if (row1 == 0) {
                        decrypted += String.valueOf(array[4][col1]).toString();
                    }
                    else{
                        decrypted += String.valueOf(array[row1 - 1][col1]).toString();
                    }
                    if (row2 == 0) {
                        decrypted += String.valueOf(array[4][col2]).toString();
                    }
                    else{
                        decrypted += String.valueOf(array[row2 - 1][col2]).toString();
                    }
                }
                if(row1!=row2 &&col1!=col2){
                    decrypted += String.valueOf(array[row1][col2]).toString();
                    decrypted += String.valueOf(array[row2][col1]).toString();
                }

                String newStr="";
                for(int i=0;i<decrypted.length();i=i+2){
                    String substr2=decrypted.substring(i, i+2);
                    if(substr2.charAt(1)=='X'){
                        if(substr2.charAt(0)=='A'){
                            newStr+=substr2;
                        }
                        else if(substr2.charAt(0)=='E'){
                            newStr+=substr2;
                        }
                        else if(substr2.charAt(0)=='I'){
                            newStr+=substr2;
                        }
                        else if(substr2.charAt(0)=='O'){
                            newStr+=substr2;
                        }
                        else if(substr2.charAt(0)=='U'){
                            newStr+=substr2;
                        }
                        else{
                            newStr+=substr2.charAt(0);
                        }
                    }
                    else{
                        newStr+=substr2;
                    }
                }
                txt_plain_p.setText(newStr.toLowerCase().toString());

            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}