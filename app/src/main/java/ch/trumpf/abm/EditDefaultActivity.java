package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        Button save = (Button) findViewById(R.id.EditDefaultSave_Btn);

        save.setOnClickListener(v ->
        {
            TextInputLayout CustomText = (TextInputLayout) findViewById(R.id.ContactText_TextInput);
            String customText = CustomText.getEditText().getText().toString().trim();
            Contact defaultContact = new Contact(100000, "Default", "", null);
            defaultContact.setCustomText(customText);
            SaveData(defaultContact);
        });
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