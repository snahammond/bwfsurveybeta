package com.bwfsurvey.bwfsurveybeta.dialogs;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Meeting;
import com.amplifyframework.datastore.generated.model.Volunteer;
import com.amplifyframework.datastore.generated.model.VolunteerHousehold;
import com.bwfsurvey.bwfsurveybeta.activities.collect.InitialSurveyActivity;
import com.bwfsurvey.bwfsurveybeta.types.Community;
import com.example.bwfsurveybeta.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateNewMeeting extends DialogFragment {

    private ArrayList<Community> communities;
    private String selectedCommunity = null;
    private Temporal.Date selectedDate = null;
    private ArrayList<Volunteer> volunteers;
    private String selectedVolunteer = null;
    private String discussionsTaught = null;
    private String country;
    private String namebwe;

    private Context context;
    TextView textViewMeetingDate;
    final Calendar myCalendar = Calendar.getInstance();

    public CreateNewMeeting(Activity activity,ArrayList<Community> communities, ArrayList<Volunteer> volunteers, String namebwe, String country) {
        this.communities = communities;
        this.volunteers = volunteers;
        this.country = country;
        this.namebwe = namebwe;
        this.context = activity;
    }

    public interface CreateNewMeetingListener {
        public void onDialogPositiveClick(DialogFragment dialog, Meeting newMeeting);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    CreateNewMeeting.CreateNewMeetingListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CreateNewMeeting.CreateNewMeetingListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement CreateNewMeetingListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View layoutView = inflater.inflate(R.layout.dialog_create_meeting, null);

        Spinner spinCommunities;
        spinCommunities = (Spinner)layoutView.findViewById(R.id.spinnerCommunity);
        ArrayList<String> communities_ = new ArrayList<>();
        communities_.add("Select the community...");
        //get communities inside a arraylist
        for(Community community : communities) {
            communities_.add(community.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, communities_){
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


        textViewMeetingDate = (TextView)layoutView.findViewById(R.id.meetingDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        textViewMeetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Spinner spinVolunteers;
        spinVolunteers = (Spinner)layoutView.findViewById(R.id.spinnerVolunteer);
        ArrayList<String> volunteers_ = new ArrayList<>();
        volunteers_.add("Select the volunteer...");
        //get communities inside a arraylist
        for(Volunteer vol : volunteers) {
            volunteers_.add(vol.getNamevol());
        }
        ArrayAdapter<String> dataAdapterVols = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, volunteers_){
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
                                        ViewGroup parent) {
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
        dataAdapterVols.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinVolunteers.setAdapter(dataAdapterVols);
        spinVolunteers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedVolunteer_ = (String)parent.getItemAtPosition(position);
                if(position > 0){
                    selectedVolunteer = selectedVolunteer_;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CheckBox checkbox_1;
        checkbox_1 = (CheckBox)layoutView.findViewById(R.id.checkbox_1);
        checkbox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnsValueOnCheckboxClick(v);
            }
        });
        checkbox_1.setVisibility(View.VISIBLE);
        checkbox_1.setText("Lesson 1");
        checkbox_1.setHint("1");

        CheckBox checkbox_2;
        checkbox_2 = (CheckBox)layoutView.findViewById(R.id.checkbox_2);
        checkbox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnsValueOnCheckboxClick(v);
            }
        });
        checkbox_2.setVisibility(View.VISIBLE);
        checkbox_2.setText("Lesson 2");
        checkbox_2.setHint("2");

        CheckBox checkbox_3;
        checkbox_3 = (CheckBox)layoutView.findViewById(R.id.checkbox_3);
        checkbox_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnsValueOnCheckboxClick(v);
            }
        });
        checkbox_3.setVisibility(View.VISIBLE);
        checkbox_3.setText("Lesson 3");
        checkbox_3.setHint("3");

        CheckBox checkbox_4;
        checkbox_4 = (CheckBox)layoutView.findViewById(R.id.checkbox_4);
        checkbox_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnsValueOnCheckboxClick(v);
            }
        });
        checkbox_4.setVisibility(View.VISIBLE);
        checkbox_4.setText("Lesson 4");
        checkbox_4.setHint("4");

        builder.setView(layoutView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(selectedCommunity!=null && selectedDate != null && discussionsTaught!=null && !discussionsTaught.isEmpty() && !discussionsTaught.contentEquals(",")){
                            if(selectedVolunteer==null)
                                selectedVolunteer = "";
                            Meeting newMeeting = Meeting.builder()
                                    .country(country)
                                    .community(selectedCommunity)
                                    .namebwe(namebwe)
                                    .namevol(selectedVolunteer)
                                    .discussionsTaught(discussionsTaught)
                                    .date(selectedDate)
                                    .build();
                            listener.onDialogPositiveClick(CreateNewMeeting.this,newMeeting);
                        }else{
                            //fire error
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    new androidx.appcompat.app.AlertDialog.Builder(context)
                                            .setTitle("Invalid Meeting creation")
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
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        textViewMeetingDate.setText(sdf.format(myCalendar.getTime()));
        selectedDate = new Temporal.Date((Date)myCalendar.getTime());
    }

    void setAnsValueOnCheckboxClick(View v){
        String selectedEnumMulStrValue = ((CheckBox)v).getHint().toString();
        String ansAlreadyPresent = (String) discussionsTaught;
        boolean checked = ((CheckBox) v).isChecked();
        if(checked){
            if(ansAlreadyPresent!=null){
                if(ansAlreadyPresent.contains(","))
                    ansAlreadyPresent = ansAlreadyPresent + selectedEnumMulStrValue + ",";
                else
                    ansAlreadyPresent = ansAlreadyPresent + "," + selectedEnumMulStrValue + ",";
            }else{
                ansAlreadyPresent = selectedEnumMulStrValue + ",";
            }
            discussionsTaught = ansAlreadyPresent;
            Log.i("Tutorial", "ansAlreadyPresent: "+ ansAlreadyPresent  );
        }else{
            String[]  ansAlreadyPresentStrArr = ansAlreadyPresent.split(",");
            String ansToWrite = "";
            for(String anAnswer : ansAlreadyPresentStrArr){
                if(!anAnswer.contentEquals(selectedEnumMulStrValue)){
                    ansToWrite = ansToWrite + anAnswer + ",";
                }
            }
            discussionsTaught = ansToWrite;
            Log.i("Tutorial", "ansToWrite: "+ ansToWrite  );
        }
    }
}
