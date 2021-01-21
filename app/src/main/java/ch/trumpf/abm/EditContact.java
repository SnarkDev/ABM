package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

public class EditContact extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        this.intent = getIntent();

        Contact contact = (Contact) intent.getExtras().getSerializable("Contact");

        initalizeActivity(contact.getName(), contact.getBirthdate().toString());

        Button clear = (Button) findViewById(R.id.Clear_Btn);

        clear.setOnClickListener(v ->
        {
            TextInputLayout DefaultText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
            DefaultText.getEditText().setText("");
        });

        Button save = (Button) findViewById(R.id.Save_Btn);
        save.setOnClickListener(v ->
        {
            TextInputLayout CustomText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
            contact.setCustomText(CustomText.getEditText().getText().toString().trim());
        });
    }

    private void initalizeActivity(String name, String birthdate)
    {
        TextView NameTextView = (TextView) findViewById(R.id.ContactName_Tv);
        NameTextView.setText(name);

        TextView BirthdateTextView = (TextView) findViewById(R.id.Birthdate_Tv);
        BirthdateTextView.setText(birthdate);
    }
}