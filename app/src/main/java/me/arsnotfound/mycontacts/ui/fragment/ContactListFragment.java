package me.arsnotfound.mycontacts.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import me.arsnotfound.mycontacts.MyContactsApplication;
import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.databinding.FragmentContactListBinding;
import me.arsnotfound.mycontacts.repo.ContactsDB;
import me.arsnotfound.mycontacts.ui.adapter.ContactAdapter;

public class ContactListFragment extends Fragment {
    private FragmentContactListBinding binding = null;

    private ContactsDB db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        db = ((MyContactsApplication)getActivity().getApplication()).getContactsDB();

        List<Contact> contacts = db.selectAll();

        ContactAdapter adapter = new ContactAdapter(getContext(), contacts);
        binding.contactList.setAdapter(adapter);

        binding.contactList.setOnItemClickListener((parent, view, pos, id) -> {
            Contact contact = (Contact) binding.contactList.getAdapter().getItem(pos);
            ContactListFragmentDirections.NavigateToContactInfoFragment directions =
                    ContactListFragmentDirections.navigateToContactInfoFragment(contact.getID());
            NavHostFragment.findNavController(this).navigate(directions);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        db = null;
    }
}
