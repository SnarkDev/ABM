package ch.trumpf.abm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;

import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity  {

    public static final int REQUEST_READ_CONTACTS = 79;
    public ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermission();
        }

        contacts = getAllContacts();

        fillListView(contacts);

        Button refresh = (Button) findViewById(R.id.refresh_Btn);

        refresh.setOnClickListener(v ->
        {
            contacts.clear();
            contacts = getAllContacts();

            fillListView(contacts);
        });

        Button editDefault = (Button) findViewById(R.id.editDefault_Btn);

        editDefault.setOnClickListener(v ->
        {
            setContentView(R.layout.activity_edit_default);
        });

        contacts = sortContacts(contacts);
    }

    private void fillListView(ArrayList<Contact> contacts)
    {
        contacts = sortContacts(contacts);

        ArrayList<String> listViewData = new ArrayList<String>();

        for (Contact contact : contacts)
        {
            listViewData.add(contact.getM_Name());
        }

        ListView contactsListView = (ListView) findViewById(R.id.contacts_Lv);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listViewData );

        contactsListView.setAdapter(arrayAdapter);
    }

    private ArrayList<Contact> sortContacts(ArrayList<Contact> contacts)
    {
        Comparator<Contact> contactComparator = new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getM_Name().compareToIgnoreCase(c2.getM_Name());
            }
        };

        contacts.sort(contactComparator);
        return contacts;
    }



    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {

        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }
    }

    private ArrayList<Contact> getAllContacts()
    {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String columns[] =
        {
                ContactsContract.CommonDataKinds.Event.START_DATE,
                ContactsContract.CommonDataKinds.Event.TYPE,
                ContactsContract.CommonDataKinds.Event.MIMETYPE,
        };

        String selection = ContactsContract.CommonDataKinds.Event.TYPE + "=" + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY +
                " and " + ContactsContract.CommonDataKinds.Event.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE +
                "' and "+ ContactsContract.Data.CONTACT_ID + " = ";

        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext())
            {
                String contact_Id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String contact_Name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0)
                {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contact_Id}, null);

                    while (pCur.moveToNext())
                    {
                        String contact_PhoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Cursor birthdayCur = cr.query(ContactsContract.Data.CONTENT_URI, columns, selection + contact_Id, null, sortOrder);

                        if (birthdayCur.getCount() > 0)
                        {
                            while (birthdayCur.moveToNext()) {
                                String birthday = birthdayCur.getString(birthdayCur.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
                                Date contact_Birthdate;
                                try
                                {
                                    contact_Birthdate = Date.valueOf(birthday);
                                }
                                catch (Exception e)
                                {
                                    contact_Birthdate = null;
                                }

                                Contact contact = new Contact(Integer.parseInt(contact_Id) , contact_Name, contact_PhoneNumber, contact_Birthdate);
                                contacts.add(contact);
                            }
                        }
                        else
                        {
                            Contact contact = new Contact(Integer.parseInt(contact_Id), contact_Name, contact_PhoneNumber, null);
                            contacts.add(contact);
                        }
                        birthdayCur.close();
                    }
                    pCur.close();
                }
            }
        }

        if (cur != null) {
            cur.close();
        }
        return contacts;
    }
}
