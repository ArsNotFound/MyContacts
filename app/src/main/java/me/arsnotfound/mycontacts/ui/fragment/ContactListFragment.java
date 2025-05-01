package me.arsnotfound.mycontacts.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Collections;

import dagger.hilt.android.AndroidEntryPoint;
import me.arsnotfound.mycontacts.databinding.FragmentContactListBinding;
import me.arsnotfound.mycontacts.ui.adapter.ContactAdapter;
import me.arsnotfound.mycontacts.ui.viewmodels.ContactListViewModel;

@AndroidEntryPoint
public class ContactListFragment extends Fragment {
    private FragmentContactListBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        ContactListViewModel viewModel = new ViewModelProvider(this).get(ContactListViewModel.class);

        ContactAdapter adapter = new ContactAdapter(getContext(), Collections.emptyList());
        adapter.setOnItemClickListener((view, contact) -> {
            ContactListFragmentDirections.NavigateToContactInfoFragment directions =
                    ContactListFragmentDirections.navigateToContactInfoFragment(contact.getID());
            NavHostFragment.findNavController(this).navigate(directions);
        });
        adapter.setOnItemLongClickListener(((view, contact) -> {
            ContactListFragmentDirections.NavigateToContactEditFragment directions =
                    ContactListFragmentDirections.navigateToContactEditFragment(contact.getID());
            NavHostFragment.findNavController(this).navigate(directions);
        }));
        binding.contactList.setAdapter(adapter);

        viewModel.getContactList().observe(getViewLifecycleOwner(), adapter::updateList);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
