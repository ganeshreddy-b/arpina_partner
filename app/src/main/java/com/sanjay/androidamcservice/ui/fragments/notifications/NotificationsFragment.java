package com.sanjay.androidamcservice.ui.fragments.notifications;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.databinding.FragmentNotificationsBinding;
import com.sanjay.androidamcservice.repository.dto.contact.ContactItem;
import com.sanjay.androidamcservice.repository.dto.contact.EmailContact;
import com.sanjay.androidamcservice.repository.dto.contact.PhoneContact;
import com.sanjay.androidamcservice.repository.dto.contact.PostalAddress;
import com.sanjay.androidamcservice.ui.adapter.ContactListAdapter;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment implements ContactListAdapter.ContactsAdapterListener {

    FragmentNotificationsBinding binding;
    private List<ContactItem> contactList=new ArrayList<>();
    private ContactListAdapter mAdapter;
    private String TAG=NotificationsFragment.class.getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        View root = binding.getRoot();
        contactList=getReadContacts();
        mAdapter=new ContactListAdapter(getContext(),contactList,this);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.RVContactList.setLayoutManager(mLayoutManager);
        binding.RVContactList.setItemAnimator(new DefaultItemAnimator());
//        binding.RVContactList.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        binding.RVContactList.setAdapter(mAdapter);
        binding.SVContactsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return root;
    }

    public ArrayList<ContactItem> getReadContacts() {
        ArrayList<ContactItem> contactList = new ArrayList<>();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor mainCursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (mainCursor != null) {
            while (mainCursor.moveToNext()) {
                ContactItem contactItem = new ContactItem();
                String id = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts._ID));
                String displayName = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
                Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);

                //ADD NAME AND CONTACT PHOTO DATA...
                contactItem.setDisplayName(displayName);
                contactItem.setPhotoUrl(displayPhotoUri.toString());

                if (Integer.parseInt(mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //ADD PHONE DATA...
                    ArrayList<PhoneContact> arrayListPhone = new ArrayList<>();
                    Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{
                            id
                    }, null);
                    if (phoneCursor != null) {
                        while (phoneCursor.moveToNext()) {
                            PhoneContact phoneContact = new PhoneContact();
                            String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            phoneContact.setPhone(phone);
                            arrayListPhone.add(phoneContact);
                        }
                    }
                    if (phoneCursor != null) {
                        phoneCursor.close();
                    }
                    contactItem.setArrayListPhone(arrayListPhone);


                    //ADD E-MAIL DATA...
                    ArrayList<EmailContact> arrayListEmail = new ArrayList<>();
                    Cursor emailCursor = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{
                            id
                    }, null);
                    if (emailCursor != null) {
                        while (emailCursor.moveToNext()) {
                            EmailContact emailContact = new EmailContact();
                            String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                            emailContact.setEmail(email);
                            arrayListEmail.add(emailContact);
                        }
                    }
                    if (emailCursor != null) {
                        emailCursor.close();
                    }
                    contactItem.setArrayListEmail(arrayListEmail);

                    //ADD ADDRESS DATA...
                    ArrayList<PostalAddress> arrayListAddress = new ArrayList<>();
                    Cursor addrCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?", new String[]{
                            id
                    }, null);
                    if (addrCursor != null) {
                        while (addrCursor.moveToNext()) {
                            PostalAddress postalAddress = new PostalAddress();
                            String city = addrCursor.getString(addrCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                            String state = addrCursor.getString(addrCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                            String country = addrCursor.getString(addrCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                            postalAddress.setCity(city);
                            postalAddress.setState(state);
                            postalAddress.setCountry(country);
                            arrayListAddress.add(postalAddress);
                        }
                    }
                    if (addrCursor != null) {
                        addrCursor.close();
                    }
                    contactItem.setArrayListAddress(arrayListAddress);
                }
                contactList.add(contactItem);
            }
        }
        if (mainCursor != null) {
            mainCursor.close();
        }
        Log.d(TAG, "getReadContacts: "+contactList.size());
        return contactList;
    }

    @Override
    public void onContactSelected(ContactItem contact) {
        Toast.makeText(getContext(), "Selected: " + contact.getPhotoUrl() + ", " + contact.getDisplayName(), Toast.LENGTH_LONG).show();
    }

}