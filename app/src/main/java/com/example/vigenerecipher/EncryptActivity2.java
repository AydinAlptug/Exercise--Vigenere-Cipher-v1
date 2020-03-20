package com.example.vigenerecipher;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EncryptActivity2 extends AppCompatActivity {

    String key;
    String message;

    Button btn;
    Button copyButton;
    EditText keyEditText;
    EditText messageEditText;
    TextView encryptedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        define();
        giveResponsibilityToButton();
    }
    public void define(){
        btn = findViewById(R.id.encryptingButton);
        copyButton = findViewById(R.id.copy2);
        keyEditText = findViewById(R.id.key);
        messageEditText = findViewById(R.id.message);
        encryptedText = findViewById(R.id.encryptedText) ;
    }
    public void giveResponsibilityToButton(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = keyEditText.getText().toString();
                message = messageEditText.getText().toString();
                encryptedText.setText(encrypt());
            }
        });
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",encryptedText.getText());
                clipboard.setPrimaryClip(clip);
            }
        });
    }
    public String encrypt(){
        int plaintextLength = message.toCharArray().length;
        int keywordLength = key.toCharArray().length;
        char [] plaintext = new char[plaintextLength];
        plaintext = message.toCharArray();
        char [] keyword = new char[keywordLength];
        keyword = key.toCharArray();
        char [] encrypted = new char[plaintextLength];

        //the loop is used to shift each letter
        for(int i=0;i<plaintextLength;i++){
            //subtracting -32 (difference between 'A' and 'a') when necessary, provides case insensitivity.
            if((plaintext[i] >= 65 && plaintext[i]<=90) && (keyword[i%(keywordLength)] >= 65 && keyword[i%(keywordLength)]<=90)){
                encrypted[i]= (char) (65+(plaintext[i]+keyword[i%(keywordLength)])%26);
            }
            else if((plaintext[i] < 65 || plaintext[i]>90) && (keyword[i%(keywordLength)] < 65 || keyword[i%(keywordLength)]>90)){
                encrypted[i]= (char) (65+(plaintext[i]-32+keyword[i%(keywordLength)]-32)%26);
            }
            else
                encrypted[i]= (char) (65+(plaintext[i]+keyword[i%(keywordLength)]-32)%26);
        }

        return new String(encrypted);
    }
}
