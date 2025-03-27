package me.arsnotfound.mycontacts.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.time.LocalDate;
import java.util.Locale;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.databinding.FragmentContactListBinding;
import me.arsnotfound.mycontacts.ui.adapter.ContactAdapter;

public class ContactListFragment extends Fragment {
    private FragmentContactListBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);

        Contact[] contacts = new Contact[100];
        for (int i = 0; i < contacts.length; i++)
            contacts[i] = new Contact(
                    "Иван " + i,
                    "Сухарев",
                    "Александрович",
                    "+7(999)99999" + String.format(Locale.getDefault(), "%02d", i),
                    LocalDate.of(1999, 12, i % 31 + 1)
            );

        ContactAdapter adapter = new ContactAdapter(getContext(), contacts);
        binding.contactList.setAdapter(adapter);

        binding.contactList.setOnItemClickListener((parent, view, pos, id) -> {
            Contact contact = (Contact) binding.contactList.getAdapter().getItem(pos);
            ContactListFragmentDirections.NavigateToContactInfoFragment directions =
                    ContactListFragmentDirections.navigateToContactInfoFragment(contact);
            NavHostFragment.findNavController(this).navigate(directions);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
