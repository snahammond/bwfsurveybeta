package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.amplifyframework.datastore.generated.model.VolunteerHouseholdWaterTest;
import com.bwfsurvey.bwfsurveybeta.activities.AuthenticationActivity;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateNewVolunteerHousehold extends DialogFragment {

    private ArrayList<Community> communities;
    private String selectedCommunity;
    private String country;
    private String namebwe;

    public CreateNewVolunteerHousehold(ArrayList<Community> communities,String country,String namebwe) {
        this.communities = communities;
        this.country = country;
        this.namebwe = namebwe;
    }

    public interface CreateNewVolunteerHouseholdListener {
        public void onDialogPositiveClick(DialogFragment dialog,VolunteerHousehold newVolunteerHousehold);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    CreateNewVolunteerHousehold.CreateNewVolunteerHouseholdListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_create_volunteer_household, null);

        Spinner spinCommunities;
        spinCommunities = (Spinner)layoutView.findViewById(R.id.spinnerCommunity);
        ArrayList<String> communities_ = new ArrayList<>();
        //get communities inside a arraylist
        for(Community community : communities) {
            communities_.add(community.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, communities_);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCommunities.setAdapter(dataAdapter);
        spinCommunities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCommunity = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(layoutView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText nameNewHouseholdEditText = (EditText)layoutView.findViewById(R.id.nameNewHousehold);
                        EditText locationNewHouseholdEditText = (EditText)layoutView.findViewById(R.id.locationNewHousehold);

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
