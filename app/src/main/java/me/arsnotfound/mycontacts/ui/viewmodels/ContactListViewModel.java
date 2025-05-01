package me.arsnotfound.mycontacts.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.data.ContactRepository;

@HiltViewModel
public class ContactListViewModel extends ViewModel {

    private final ContactRepository repository;

    public @Inject ContactListViewModel(ContactRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Contact>> getContactList() {
        return repository.selectAll();
    }
}
