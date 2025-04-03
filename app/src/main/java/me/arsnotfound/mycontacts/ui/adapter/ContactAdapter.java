package me.arsnotfound.mycontacts.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.R;
import me.arsnotfound.mycontacts.databinding.ItemListContactBinding;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, Contact[] array) {
        super(context, R.layout.item_list_contact, array);
    }

    public ContactAdapter(Context context, List<Contact> array) {
        super(context, R.layout.item_list_contact, array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);

        ItemListContactBinding binding;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = ItemListContactBinding.inflate(inflater, null, false);
        } else {
            binding = ItemListContactBinding.bind(convertView);
        }

        assert contact != null;

        binding.title.setText(contact.getDisplayName());
        binding.phoneNumber.setText(contact.getPhoneNumber());

        return binding.getRoot();
    }
}
