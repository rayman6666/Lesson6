package ru.mirea.komaristyi.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText groupNumberEditText;
    private EditText listNumberEditText;
    private EditText favoriteMovieEditText;
    private Button saveButton;
    private Boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupNumberEditText = findViewById(R.id.editText1);
        listNumberEditText = findViewById(R.id.editText2);
        favoriteMovieEditText = findViewById(R.id.editText3);
        saveButton = findViewById(R.id.button);

        if (first != true){
            loadSharedPreferences();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first = false;
                saveSharedPreferences();
            }
        });
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String groupNumber = sharedPreferences.getString("groupNumber", "");
        String listNumber = sharedPreferences.getString("listNumber", "");
        String favoriteMovie = sharedPreferences.getString("favoriteMovie", "");

        groupNumberEditText.setText(groupNumber);
        listNumberEditText.setText(listNumber);
        favoriteMovieEditText.setText(favoriteMovie);
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("groupNumber", groupNumberEditText.getText().toString());
        myEdit.putString("listNumber", listNumberEditText.getText().toString());
        myEdit.putString("favoriteMovie", favoriteMovieEditText.getText().toString());
        myEdit.apply();
    }
}