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

public class DecryptActivity3 extends AppCompatActivity {

    String key;
    String message;

    Button btn;
    Button copyButton;
    EditText keyEditText;
    EditText messageEditText;
    TextView decryptedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        define();
        giveResponsibility();
    }

    public void define(){
        btn = findViewById(R.id.decryptButton);
        copyButton = findViewById(R.id.copy1);
        keyEditText = findViewById(R.id.key);
        messageEditText = findViewById(R.id.encryptedMessage);
        decryptedMessage = findViewById(R.id.decryptedText);
    }
    public void giveResponsibility(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = keyEditText.getText().toString();
                message = messageEditText.getText().toString();
                decryptedMessage.setText(decrypt());
            }
        });
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",decryptedMessage.getText());
                clipboard.setPrimaryClip(clip);
            }
        });
    }
    public String decrypt(){
        int encryptedLength  = message.toCharArray().length;
        int keywordLength = key.toCharArray().length;
        char [] decrypted = new char[encryptedLength];

        char [] keyword = new char[keywordLength];
        keyword = key.toUpperCase().toCharArray();
        char [] encrypted = message.toCharArray() ;

        //the loop is used to shift each letter
        for(int i=0;i<encryptedLength;i++){
            // 26 added for the cases negative numbers occurs
            if((encrypted[i] >= 65 && encrypted[i]<=90) && (keyword[i%(keywordLength)] >= 65 && keyword[i%(keywordLength)]<=90)){
                decrypted[i]= (char) (65+(encrypted[i]-keyword[i%(keywordLength)]+26)%26);
            }
            else if((encrypted[i] < 65 || encrypted[i]>90) && (keyword[i%(keywordLength)] < 65 || keyword[i%(keywordLength)]>90)){
                decrypted[i]= (char) (65+(encrypted[i]-32-keyword[i%(keywordLength)]-32+26)%26);
            }
            else
                decrypted[i]= (char) (65+(encrypted[i]-keyword[i%(keywordLength)]-32+26)%26);
        }

        System.out.println(decrypted);//lxfopvefrnhr
        return new String(decrypted);
    }
}
