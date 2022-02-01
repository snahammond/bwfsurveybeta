package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.bwfsurveybeta.R;

public class ResetPasswordConfirmation extends DialogFragment implements View.OnClickListener {
    public EditText editText_newPassword;
    public EditText editText_confirmation;
    public ImageView show_pass_btn_reset_dialog;

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.show_pass_btn_reset_dialog){

            if(editText_newPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_hide_password);

                //Show Password
                editText_newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_show_password);

                //Hide Password
                editText_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public interface ResetPasswordConfirmationListener {
        public void onResetPasswordConfirmationDialogPositiveClick(DialogFragment dialog,String newPassword,String confirmationCode);
        public void onResetPasswordConfirmationDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    ResetPasswordConfirmation.ResetPasswordConfirmationListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (ResetPasswordConfirmation.ResetPasswordConfirmationListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("AuthenticationActivity must implement ResetPasswordConfirmationListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_reset_password_confirmation, null);
        editText_newPassword = (EditText)layoutView.findViewById(R.id.editTextNewPassword);
        editText_confirmation = (EditText)layoutView.findViewById(R.id.resetPasswordConfirmationCode);
        show_pass_btn_reset_dialog = (ImageView)layoutView.findViewById(R.id.show_pass_btn_reset_dialog);
        show_pass_btn_reset_dialog.setOnClickListener(this);

        builder.setView(layoutView)
                .setPositiveButton(R.string.confirm_password_reset, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        listener.onResetPasswordConfirmationDialogPositiveClick(ResetPasswordConfirmation.this,editText_newPassword.getText().toString(),editText_confirmation.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        listener.onResetPasswordConfirmationDialogNegativeClick(ResetPasswordConfirmation.this);
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void ShowHidePassDialog(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(editText_newPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_hide_password);

                //Show Password
                editText_newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_show_password);

                //Hide Password
                editText_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}
