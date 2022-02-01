package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class SelectCountryDialogFragment extends DialogFragment {
    private ArrayList<String> countries;
    SelectCountryDialogFragment.SelectCountryDialogFragmentListener listener;

    public interface SelectCountryDialogFragmentListener {
        public void onSelectedCountry(String countryName);
    }

    public SelectCountryDialogFragment(ArrayList<String> countries,SelectCountryDialogFragment.SelectCountryDialogFragmentListener listener) {
        this.countries = countries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence[] csCountries = countries.toArray(new CharSequence[countries.size()]);
        builder.setTitle("Select Country")
                .setItems(csCountries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onSelectedCountry(countries.get(which));
                    }
                });
        return builder.create();
    }
}
