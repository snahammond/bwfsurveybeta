package com.bwfsurvey.bwfsurveybeta.dialogs;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.HouseholdAttendingMeeting;
import com.amplifyframework.datastore.generated.model.Meeting;
import com.amplifyframework.datastore.generated.model.Volunteer;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateNewHouseholdAttendingMeeting extends DialogFragment {

    private String householdAttendingMeetingName = null;
    private String phoneNumber = null;
    private int numberOfAdultAttendingMeeting = 0;
    private String committedToUseAquatabs = null;
    private int numberOfAquatabsReceived = 0;
    private String uuidMeeting;
    private String nameSWE;

    private Context context;

    public CreateNewHouseholdAttendingMeeting(Activity activity, String uuidMeeting, String nameSWE) {
        this.context = activity;
        this.uuidMeeting = uuidMeeting;
        this.nameSWE = nameSWE;
    }

    public interface CreateNewHouseholdAttendingMeetingListener {
        public void onDialogPositiveClick(DialogFragment dialog, HouseholdAttendingMeeting newHouseholdAttendingMeeting);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    CreateNewHouseholdAttendingMeeting.CreateNewHouseholdAttendingMeetingListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CreateNewHouseholdAttendingMeeting.CreateNewHouseholdAttendingMeetingListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement CreateNewHouseholdAttendingMeetingListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_create_household_attending_meeting, null);

        RadioGroup radioEnum = (RadioGroup)layoutView.findViewById(R.id.radioEnum);
        RadioButton radio_1 = (RadioButton)layoutView.findViewById(R.id.radio_1);
        radio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                committedToUseAquatabs = ((RadioButton)v).getHint().toString();
            }
        });
        radio_1.setVisibility(View.VISIBLE);
        radio_1.setChecked(false);
        radio_1.setText(Html.fromHtml("Yes"));
        radio_1.setHint("Yes");

        RadioButton radio_2 = (RadioButton)layoutView.findViewById(R.id.radio_2);
        radio_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                committedToUseAquatabs = ((RadioButton)v).getHint().toString();
            }
        });
        radio_2.setVisibility(View.VISIBLE);
        radio_2.setChecked(false);
        radio_2.setText(Html.fromHtml("No"));
        radio_2.setHint("No");

        builder.setView(layoutView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText nameNewHouseholdEditText = (EditText)layoutView.findViewById(R.id.nameNewHousehold);
                        EditText phoneNumberEditText = (EditText)layoutView.findViewById(R.id.phoneNumber);
                        EditText numberOfAdultsEditText = (EditText)layoutView.findViewById(R.id.numberOfAdults);
                        EditText numberOfAquatabsReceivedEditText = (EditText)layoutView.findViewById(R.id.numberOfAquatabsReceived);

                        householdAttendingMeetingName = nameNewHouseholdEditText.getText().toString();
                        phoneNumber = phoneNumberEditText.getText().toString();
                        try{
                            numberOfAdultAttendingMeeting = Integer.valueOf(numberOfAdultsEditText.getText().toString());
                        }catch (NumberFormatException x){
                            numberOfAdultAttendingMeeting = 0;
                        }

                        try{
                            numberOfAquatabsReceived = Integer.valueOf(numberOfAquatabsReceivedEditText.getText().toString());
                        }catch (NumberFormatException x){
                            numberOfAquatabsReceived = 0;
                        }

                        if(householdAttendingMeetingName!=null && !householdAttendingMeetingName.isEmpty() &&
                           phoneNumber!=null && !phoneNumber.isEmpty() &&
                           numberOfAdultAttendingMeeting != 0 &&
                           committedToUseAquatabs != null && !committedToUseAquatabs.isEmpty() &&
                           numberOfAquatabsReceived != 0){

                            HouseholdAttendingMeeting newHouseholdAttendingMeeting = HouseholdAttendingMeeting.builder()
                                    .namebwe(nameSWE)
                                    .headHouseholdName(householdAttendingMeetingName)
                                    .headHouseholdPhoneNumber(phoneNumber)
                                    .numberOfAdults(numberOfAdultAttendingMeeting)
                                    .commitedToUseAquatabs(committedToUseAquatabs)
                                    .numberOfAquaTabsReceived(numberOfAquatabsReceived)
                                    .meetingId(uuidMeeting)
                                    .build();
                            listener.onDialogPositiveClick(CreateNewHouseholdAttendingMeeting.this,newHouseholdAttendingMeeting);
                        }else{
                            //fire error
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    new androidx.appcompat.app.AlertDialog.Builder(context)
                                            .setTitle("Invalid Household creation")
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
