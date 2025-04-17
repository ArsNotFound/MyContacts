package me.arsnotfound.mycontacts.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.arsnotfound.mycontacts.MyContactsApplication;
import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.data.ContactRepository;
import me.arsnotfound.mycontacts.databinding.FragmentContactInfoBinding;
import me.arsnotfound.mycontacts.repo.room.dao.ContactDao;

public class ContactInfoFragment extends Fragment {
    private FragmentContactInfoBinding binding;

    private ContactRepository repo;

    private ContactInfoFragmentArgs args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = ContactInfoFragmentArgs.fromBundle(requireArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = FragmentContactInfoBinding.inflate(inflater, container, false);
       repo = ((MyContactsApplication)getActivity().getApplication()).getContactRepository();

       long contactId = args.getContactId();

       Contact contact = repo.select(contactId);

       binding.contactLastName.setValue(contact.getLastName());
       binding.contactFirstName.setValue(contact.getFirstName());
       binding.contactMiddleName.setValue(contact.getMiddleName());
       binding.contactPhoneNumber.setValue(contact.getPhoneNumber());
       if (contact.getDateOfBirth() != null)
           binding.contactDayOfBirth.setValue(contact.getDateOfBirth().toString());
       else
           binding.contactDayOfBirth.setValue("Не указано");

       return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        repo = null;
    }
}
