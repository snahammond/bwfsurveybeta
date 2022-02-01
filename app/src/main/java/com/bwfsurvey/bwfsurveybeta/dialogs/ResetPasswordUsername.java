package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.bwfsurveybeta.R;

public class ResetPasswordUsername extends DialogFragment {

    public EditText editText_emailAddress;

    public interface ResetPasswordUsernameListener {
        public void onResetPasswordUsernameDialogPositiveClick(DialogFragment dialog,String email);
        public void onResetPasswordUsernameDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    ResetPasswordUsername.ResetPasswordUsernameListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (ResetPasswordUsername.ResetPasswordUsernameListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("AuthenticationActivity must implement ResetPasswordUsernameListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_reset_password_username, null);
        builder.setView(layoutView)
                .setPositiveButton(R.string.send_password_reset_code, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editText_emailAddress = (EditText)layoutView.findViewById(R.id.resetPasswordUsername);
                        // Send the positive button event back to the host activity
                        listener.onResetPasswordUsernameDialogPositiveClick(ResetPasswordUsername.this,editText_emailAddress.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        listener.onResetPasswordUsernameDialogNegativeClick(ResetPasswordUsername.this);
                    }
                });;
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
