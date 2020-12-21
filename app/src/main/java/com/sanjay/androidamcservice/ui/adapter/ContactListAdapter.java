package com.sanjay.androidamcservice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.repository.dto.contact.ContactItem;
import com.sanjay.androidamcservice.ui.fragments.notifications.NotificationsFragment;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<ContactItem> contactList;
    private List<ContactItem> contactListFiltered;
    private ContactsAdapterListener listener;

    public ContactListAdapter(Context context, List<ContactItem> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ContactItem contact = contactListFiltered.get(position);
        holder.name.setText(contact.getDisplayName());
        holder.number.setText(contact.getArrayListPhone().get(0).getPhone());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,number;
        public MyViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.TVcontactname);
            number= (TextView) itemView.findViewById(R.id.TVnumber);

        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<ContactItem> filteredList = new ArrayList<>();
                    for (ContactItem row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
//                        need to check again
                        if (row.getDisplayName().toLowerCase().contains(charString.toLowerCase()) || row.getDisplayName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<ContactItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(ContactItem contact);
    }
}
