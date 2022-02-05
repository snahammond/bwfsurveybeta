package com.bwfsurvey.bwfsurveybeta.dialogs;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.amplifyframework.datastore.generated.model.Volunteer;
import com.bwfsurvey.bwfsurveybeta.activities.select.MeetingCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.VolunteerCardSelectActivity;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNewVolunteer extends DialogFragment{
    private String namebwe;

    public CreateNewVolunteer() {
    }

    public static CreateNewVolunteer newInstance(String namebwe) {
        Bundle args = new Bundle();
        args.putString("namebwe", namebwe);
        CreateNewVolunteer f = new CreateNewVolunteer();
        f.setArguments(args);
        return f;
    }

    public interface CreateNewVolunteerListener {
        void onDialogPositiveClick(DialogFragment dialog, Volunteer newVolunteer);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    CreateNewVolunteer.CreateNewVolunteerListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CreateNewVolunteer.CreateNewVolunteerListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement CreateNewVolunteerListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        namebwe = getArguments().getString("namebwe");

        final VolunteerCardSelectActivity activity = (VolunteerCardSelectActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_create_volunteer, null);

        builder.setView(layoutView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText nameNewVolunteerEditText = (EditText)layoutView.findViewById(R.id.nameNewVolunteer);
                        String nameNewVolunteerEditTextStr =nameNewVolunteerEditText.getText().toString();
                        if(nameNewVolunteerEditTextStr!=null && !nameNewVolunteerEditTextStr.isEmpty()){

                            Volunteer newVolunteer = Volunteer.builder()
                                    .namebwe(namebwe)
                                    .namevol(nameNewVolunteerEditTextStr)
                                    .build();
                            listener.onDialogPositiveClick(CreateNewVolunteer.this,newVolunteer);
                        }else{
                            //fire error
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    new androidx.appcompat.app.AlertDialog.Builder(activity)
                                            .setTitle("Invalid Volunteer creation")
                                            .setMessage("Please fill out all fields on the form.\n" )
                                            // A null listener allows the button to dismiss the dialog and take no further action.
                                            .setNegativeButton(android.R.string.ok, null)
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .show()
                                            .setCanceledOnTouchOutside(false);
                                }
                            });
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });;
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
