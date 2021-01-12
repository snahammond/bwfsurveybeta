package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.bwfsurveybeta.R;


public class ConfirmSignUp extends DialogFragment {
    public EditText confirmation_code;

    public interface ConfirmSignUpListener {
        public void onDialogPositiveClick(DialogFragment dialog,String confirmationCode);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    ConfirmSignUpListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (ConfirmSignUpListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("AuthenticationActivity must implement ConfirmSignUpListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_confirm_signup, null);
        builder.setView(layoutView)
                .setPositiveButton(R.string.confirm_signup, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmation_code = (EditText)layoutView.findViewById(R.id.confirmation_code);
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(ConfirmSignUp.this,confirmation_code.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(ConfirmSignUp.this);
                    }
                });;
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
