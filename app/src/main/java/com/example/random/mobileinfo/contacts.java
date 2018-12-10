package com.example.random.mobileinfo;

import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class contacts extends AppCompatActivity {

    ListView contactListView;
    TextView lastDialedTextView;
    List<String> contactList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        contactListView = (ListView) findViewById(R.id.listViewContacts);
        lastDialedTextView = (TextView) findViewById(R.id.textViewLastDialedNumber);

        contactListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getContactList()));
        lastDialedTextView.setText(getLastDialedNumber());
    }

    private List<String> getContactList() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            // Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();

            contactList.add(name + " : " + phoneNumber);
        }
        phones.close();

        return contactList;
    }

    private String getLastDialedNumber() {
        return CallLog.Calls.getLastOutgoingCall(getApplicationContext());
    }
}
