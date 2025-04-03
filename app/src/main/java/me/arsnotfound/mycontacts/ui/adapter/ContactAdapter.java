package me.arsnotfound.mycontacts.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.databinding.ItemListContactBinding;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Contact> contactList;
    private OnItemClickListener onItemClickListener;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.inflater = LayoutInflater.from(context);
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListContactBinding binding = ItemListContactBinding.inflate(inflater);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.binding.title.setText(contact.getDisplayName());
        holder.binding.phoneNumber.setText(contact.getPhoneNumber());
        holder.binding.getRoot().setOnClickListener((view) -> {
            if (this.onItemClickListener != null)
                this.onItemClickListener.onItemClick(view, contact);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemListContactBinding binding;

        public ViewHolder(ItemListContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Contact contact);
    }
}
