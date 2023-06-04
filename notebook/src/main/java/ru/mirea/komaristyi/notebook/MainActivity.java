package ru.mirea.komaristyi.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText fileNameEditText;
    private EditText quoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameEditText = findViewById(R.id.fileNameEditText);
        quoteEditText = findViewById(R.id.quoteEditText);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void saveData() {
        String fileName = fileNameEditText.getText().toString();
        String quote = quoteEditText.getText().toString();

        File file = new File(getExternalFilesDir(null), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(quote.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        String fileName = fileNameEditText.getText().toString();

        File file = new File(getExternalFilesDir(null), fileName);
        int length = (int) file.length();
        byte[] bytes = new byte[length];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
            String quote = new String(bytes);
            quoteEditText.setText(quote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}