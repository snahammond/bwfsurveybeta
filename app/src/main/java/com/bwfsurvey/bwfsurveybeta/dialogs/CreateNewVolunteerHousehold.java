package com.bwfsurvey.bwfsurveybeta.dialogs;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.bwfsurvey.bwfsurveybeta.activities.select.MeetingCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.VolunteerHouseholdCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateNewVolunteerHousehold extends DialogFragment {

    private ArrayList<String> communities;
    private String selectedCommunity;
    private String country;
    private String namebwe;

    public CreateNewVolunteerHousehold() {
    }

    public static CreateNewVolunteerHousehold newInstance(ArrayList<String> communities,String country,String namebwe) {
        Bundle args = new Bundle();
        args.putStringArrayList("communities", communities);
        args.putString("namebwe", namebwe);
        args.putString("country", country);

        CreateNewVolunteerHousehold f = new CreateNewVolunteerHousehold();
        f.setArguments(args);
        return f;
    }

    public interface CreateNewVolunteerHouseholdListener {
        public void onDialogPositiveClick(DialogFragment dialog, VolunteerHousehold newVolunteerHousehold);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    CreateNewVolunteerHousehold.CreateNewVolunteerHouseholdListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CreateNewVolunteerHousehold.CreateNewVolunteerHouseholdListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement CreateNewVolunteerHouseholdListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        communities = getArguments().getStringArrayList("communities");
        namebwe = getArguments().getString("namebwe");
        country = getArguments().getString("country");

        final VolunteerHouseholdCardSelectActivity activity = (VolunteerHouseholdCardSelectActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_create_volunteer_household, null);

        Spinner spinCommunities;
        spinCommunities = (Spinner)layoutView.findViewById(R.id.spinnerCommunity);
        ArrayList<String> communities_ = new ArrayList<>();
        communities_.add("Select the community...");
        //get communities inside a arraylist
        for(String communityName : communities) {
            communities_.add(communityName);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, communities_){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCommunities.setAdapter(dataAdapter);
        spinCommunities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCommunity_ = (String)parent.getItemAtPosition(position);
                if(position > 0){
                    selectedCommunity = selectedCommunity_;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(layoutView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText nameNewHouseholdEditText = (EditText)layoutView.findViewById(R.id.nameNewHousehold);
                        String nameNewHouseholdEditTextStr =nameNewHouseholdEditText.getText().toString();
                        EditText locationNewHouseholdEditText = (EditText)layoutView.findViewById(R.id.locationNewHousehold);
                        String locationNewHouseholdEditTextStr =locationNewHouseholdEditText.getText().toString();
                        if(locationNewHouseholdEditTextStr!=null && !locationNewHouseholdEditTextStr.isEmpty() &&
                                nameNewHouseholdEditTextStr!=null && !nameNewHouseholdEditTextStr.isEmpty())
                        {
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String date_s = dateFormat.format(calendar.getTime());

                            VolunteerHousehold newVolunteerHousehold = VolunteerHousehold.builder()
                                    .namebwe(namebwe)
                                    .namevol("")
                                    .country(country)
                                    .community(selectedCommunity)
                                    .headHouseholdName(nameNewHouseholdEditText.getText().toString())
                                    .householdLocation(locationNewHouseholdEditText.getText().toString())
                                    .completed(1)
                                    .lat("")
                                    .lng("")
                                    .date(new Temporal.Date(date_s))
                                    .build();
                            listener.onDialogPositiveClick(CreateNewVolunteerHousehold.this,newVolunteerHousehold);
                        }else{
                            //fire error
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    new androidx.appcompat.app.AlertDialog.Builder(activity)
                                            .setTitle("Invalid Volunteer household creation")
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
