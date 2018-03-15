package com.example.guillermo.persistencia_archivos_ejemplo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    static final int READ_BLOCK_SIZE = 100;

    boolean Dissd;
    boolean AcEscsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox=(EditText) findViewById(R.id.editText);

        String status = Environment.getExternalStorageState();

        if(status.equals(Environment.MEDIA_MOUNTED)){
            Dissd=true;
            AcEscsd=true;
        }else if(status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            Dissd=true;
            AcEscsd=false;
        }else{
            Dissd=false;
            AcEscsd=false;
        }
    }
    public void onClickSave(View view) {
        String str = textBox.getText().toString();
        try{
            File ruta = Environment.getExternalStorageDirectory();
            File file = new File(ruta.getAbsolutePath(),"ejemplo_sd.txt");


            OutputStreamWriter data = new OutputStreamWriter(new FileOutputStream(file));

            data.write(str);
            data.close();

            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getBaseContext(), "File saved ERROR!", Toast.LENGTH_SHORT).show();
        }
        /*try {
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            //---write the string to the file---
            try {
                osw.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            osw.flush();
            osw.close();
            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
            textBox.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
    }
    public void onClickLoad(View view) {
        try{
            File ruta = Environment.getExternalStorageDirectory();
            File file = new File(ruta.getAbsolutePath(),"ejemplo_sd.txt");

            BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String data = buf.readLine();
            textBox.setText(data);
            buf.close();
            Toast.makeText(getBaseContext(), "File loaded successfully!",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getBaseContext(), "File loaded ERROR!", Toast.LENGTH_SHORT).show();
        }
        /*try {
            FileInputStream fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0,
                                charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            textBox.setText(s);
            Toast.makeText(getBaseContext(), "File loaded successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
    }
}
