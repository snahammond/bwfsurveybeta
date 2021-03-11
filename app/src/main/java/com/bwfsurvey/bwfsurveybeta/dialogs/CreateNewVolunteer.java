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

import com.amplifyframework.datastore.generated.model.Volunteer;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNewVolunteer extends DialogFragment{
    private String namebwe;

    public CreateNewVolunteer(String namebwe) {
        this.namebwe = namebwe;
    }

    public interface CreateNewVolunteerListener {
        public void onDialogPositiveClick(DialogFragment dialog, Volunteer newVolunteer);
        public void onDialogNegativeClick(DialogFragment dialog);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_create_volunteer, null);



        builder.setView(layoutView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText nameNewVolunteerEditText = (EditText)layoutView.findViewById(R.id.nameNewVolunteer);

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String date_s = dateFormat.format(calendar.getTime());

                        Volunteer newVolunteer = Volunteer.builder()
                                .namebwe(namebwe)
                                .namevol(nameNewVolunteerEditText.getText().toString())
                                .build();
                        listener.onDialogPositiveClick(CreateNewVolunteer.this,newVolunteer);
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
