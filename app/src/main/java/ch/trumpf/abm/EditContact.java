package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class EditContact extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Button clear = (Button) findViewById(R.id.Clear_Btn);

        clear.setOnClickListener(v ->
        {
            TextInputLayout DefaultText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
            DefaultText.getEditText().setText("");
        });
    }
}