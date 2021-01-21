package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class EditDefaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_default);


        Button clear = (Button) findViewById(R.id.EditDefaultClear_Btn);

        clear.setOnClickListener(v ->
        {
            TextInputLayout CustomText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
            CustomText.getEditText().setText("");
        });

        Button save = (Button) findViewById(R.id.Save_Btn);

        save.setOnClickListener(v ->
        {
            TextInputLayout CustomText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
            CustomText.getEditText();
        });


    }
}