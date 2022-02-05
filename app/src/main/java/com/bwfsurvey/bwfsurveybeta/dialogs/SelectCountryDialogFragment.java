package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class SelectCountryDialogFragment extends DialogFragment {
    private ArrayList<String> countries;
    SelectCountryDialogFragment.SelectCountryDialogFragmentListener listener;


    public SelectCountryDialogFragment() {
    }

    public static SelectCountryDialogFragment newInstance(ArrayList<String> countries) {
        Bundle args = new Bundle();
        args.putStringArrayList("countries", countries);

        SelectCountryDialogFragment f = new SelectCountryDialogFragment();
        f.setArguments(args);
        return f;
    }

    public interface SelectCountryDialogFragmentListener {
        public void onSelectedCountry(String countryName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (SelectCountryDialogFragment.SelectCountryDialogFragmentListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement SelectCountryDialogFragmentListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        countries = getArguments().getStringArrayList("countries");

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
