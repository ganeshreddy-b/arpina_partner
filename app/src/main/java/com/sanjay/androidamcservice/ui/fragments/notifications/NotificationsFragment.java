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
import com.sanjay.androidamcservice.ui.adapter.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment implements ContactListAdapter.ContactsAdapterListener {

    FragmentNotificationsBinding binding;
    private List<ContactItem> contactList = new ArrayList<>();
    private ContactListAdapter mAdapter;
    private String TAG = NotificationsFragment.class.getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        View root = binding.getRoot();
        contactList = getReadContacts();
        mAdapter = new ContactListAdapter(getContext(), contactList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.RVContactList.setLayoutManager(mLayoutManager);
        binding.RVContactList.setItemAnimator(new DefaultItemAnimator());
        binding.RVContactList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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
        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        while (phones.moveToNext())
        {
            ContactItem contactItem = new ContactItem();
            String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String image_uri = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
//            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
//            Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);


            contactItem.setDisplayName(name);
            contactItem.setPhotoUrl(image_uri);
            contactItem.setPhoneNumber(phoneNumber);
            contactList.add(contactItem);

//            ContactModel contactModel = new ContactModel();
//            contactModel.setName(name);
//            contactModel.setNumber(phoneNumber);
//            contactModelArrayList.add(contactModel);
//            Log.d("name>>",name+"  "+phoneNumber);
        }
        phones.close();



//        ContentResolver cr = getActivity().getContentResolver();
//        Cursor mainCursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//        if (mainCursor != null) {
//            while (mainCursor.moveToNext()) {
//                ContactItem contactItem = new ContactItem();
//                String id = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts._ID));
//                String displayName = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                String phone = mainCursor.getString(mainCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//                Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
//                Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
//
//                //ADD NAME AND CONTACT PHOTO DATA...
//                contactItem.setDisplayName(displayName);
//                contactItem.setPhotoUrl(displayPhotoUri.toString());
//                contactItem.setPhoneNumber(phone);
//                contactList.add(contactItem);
//            }
//        }
//        if (mainCursor != null) {
//            mainCursor.close();
//        }
        Log.d(TAG, "getReadContacts: " + contactList.size());
        return contactList;
    }

    @Override
    public void onContactSelected(ContactItem contact) {
        Toast.makeText(getContext(), "Selected: " + contact.getPhotoUrl() + ", " + contact.getDisplayName(), Toast.LENGTH_LONG).show();
    }

}