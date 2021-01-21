package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class EditContact extends AppCompatActivity {

    Intent intent;
    ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        this.intent = getIntent();
        messages = new ArrayList<>();

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
            SaveData(contact);
        });
    }

    private void initalizeActivity(String name, String birthdate)
    {
        TextView NameTextView = (TextView) findViewById(R.id.ContactName_Tv);
        NameTextView.setText(name);

        TextView BirthdateTextView = (TextView) findViewById(R.id.Birthdate_Tv);
        BirthdateTextView.setText(birthdate);
    }

    public void SaveData(Contact contact)
    {
        TextInputLayout CustomText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
        contact.setCustomText(CustomText.getEditText().getText().toString().trim());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference messagesRef = database.getReference().child("Messages").child(contact.getName());

        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                dataSnapshot.getRef().child("message").setValue(contact.getCustomText());
            }

            @Override
            public void onCancelled(DatabaseError error)
            {

            }
        });
    }
}