package com.sanjay.androidamcservice.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.repository.dto.contact.ContactItem;

import java.net.URLEncoder;
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
        holder.number.setText(contact.getPhoneNumber());
        holder.contactitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWhatsup(contact.getPhoneNumber());
            }
        });
        if (contact.getPhotoUrl() != null) {
            holder.contactimage.setImageURI(Uri.parse(contact.getPhotoUrl()));
        } else {
            holder.contactimage.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_));
        }
    }

    public void shareWhatsup(String phone) {


//        String smsNumber =  phone; // E164 format without '+' sign
        String smsNumber = "91+" + phone; // E164 format without '+' sign

        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone=" + smsNumber + "&text=" + URLEncoder.encode("welcome to amc", "UTF-8");
            intent.setPackage("com.whatsapp");
            intent.setData(Uri.parse(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.startActivity(intent);


    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, number;
        ImageView contactimage;
        RelativeLayout contactitem;

        public MyViewHolder(View itemView) {
            super(itemView);
            contactimage = itemView.findViewById(R.id.IVContactImage);
            name = (TextView) itemView.findViewById(R.id.TVcontactname);
            number = (TextView) itemView.findViewById(R.id.TVnumber);
            contactitem = itemView.findViewById(R.id.RLContactItem);
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
