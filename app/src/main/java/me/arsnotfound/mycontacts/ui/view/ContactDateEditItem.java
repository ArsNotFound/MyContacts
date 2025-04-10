package me.arsnotfound.mycontacts.ui.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import me.arsnotfound.mycontacts.R;
import me.arsnotfound.mycontacts.databinding.ItemDateEditContactBinding;

public class ContactDateEditItem extends ConstraintLayout {
    private String title;
    private String hint;
    private LocalDate date = LocalDate.now();

    private final ItemDateEditContactBinding binding;

    public ContactDateEditItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = ItemDateEditContactBinding.inflate(LayoutInflater.from(context), this, false);
        addView(binding.getRoot());

        try (TypedArray a = context
                .getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ContactDateEditItem, 0, 0)
        ) {
            title = a.getString(R.styleable.ContactDateEditItem_title);
            hint = a.getString(R.styleable.ContactDateEditItem_hint);
        }

        binding.label.setText(title);
        binding.value.setHint(hint);

        binding.value.setOnClickListener(v -> {
            new DatePickerDialog(
                    getContext(),
                    this::setDate,
                    date.getYear(),
                    date.getMonthValue(),
                    date.getDayOfMonth()
            );
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        binding.label.setText(title);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        binding.value.setHint(hint);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        binding.value.setText(date.format(DateTimeFormatter.ISO_DATE));
    }

    protected void setDate(View view, int year, int month, int dayOfMonth) {
        setDate(LocalDate.of(year, month, dayOfMonth));
    }
}
