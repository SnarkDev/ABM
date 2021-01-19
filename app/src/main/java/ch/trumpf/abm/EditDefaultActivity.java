package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;

public class EditDefaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_default);


        Button clear = (Button) findViewById(R.id.Clear_Btn);

        clear.setOnClickListener(v ->
        {
            TextInputLayout DefaultText = (TextInputLayout) findViewById(R.id.DefaultText_TextInputLayout);
            DefaultText.getEditText().setText("");
        });
    }
}