package com.bwfsurvey.bwfsurveybeta.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.HouseholdAttendingMeeting;
import com.amplifyframework.datastore.generated.model.Meeting;
import com.bwfsurvey.bwfsurveybeta.activities.select.HouseholdAttendingMeetingCardSelectActivity;
import com.example.bwfsurveybeta.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HouseholdAttendingMeetingCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<HouseholdAttendingMeeting> listOfHouseholdAttendingMeeting;
    private String operation;
    private Context context;

    public HouseholdAttendingMeetingCardAdapter(HouseholdAttendingMeetingCardSelectActivity householdAttendingMeetingCardSelectActivity, ArrayList<HouseholdAttendingMeeting> listOfHouseholdAttendingMeeting, String operation) {
        this.listOfHouseholdAttendingMeeting = listOfHouseholdAttendingMeeting;
        this.context = householdAttendingMeetingCardSelectActivity;
        this.operation = operation;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.household_attending_meeting_card, parent, false);
        return new HouseholdAttendingMeetingCardAdapter.HouseholdAttendingMeetingCardViewHolder(view);
    }

    private class HouseholdAttendingMeetingCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtHeadHouseholdName;
        private TextView txtPhoneNumber;
        private TextView txtNumberOfAdultPresent;
        private TextView txtCommittedToUseAquaTabs;
        private TextView textNumberOfAquatabsReceived;
        private TextView txtHouseholdAttendingMeetingOnlineStatus;
        private String uuidHouseholdAttendingMeeting;

        public HouseholdAttendingMeetingCardViewHolder(View view) {
            super(view);
            txtHeadHouseholdName = (TextView) view.findViewById(R.id.txtHeadHouseholdName);
            txtPhoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumber);
            txtNumberOfAdultPresent = (TextView) view.findViewById(R.id.txtNumberOfAdultPresent);
            txtCommittedToUseAquaTabs = (TextView) view.findViewById(R.id.txtCommittedToUseAquaTabs);
            textNumberOfAquatabsReceived = (TextView) view.findViewById(R.id.textNumberOfAquatabsReceived);
            txtHouseholdAttendingMeetingOnlineStatus = (TextView) view.findViewById(R.id.txtHouseholdAttendingMeetingOnlineStatus);
            /*CardView householdAttendingMeetingCard = (CardView) itemView.findViewById(R.id.householdAttendingMeetingCard); // creating a CardView and assigning a value.

            meetingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Meeting name: " + txtMeetingDate.getText());
                    if(operation.contentEquals("CREATE")){
                       // startHouseholdAttendingActivity(uuidMeeting);
                    }
                }
            });*/
        }

        void setHouseholdAttendingMeetingCardDetails(HouseholdAttendingMeeting householdAttendingMeeting, int position) {
            txtHeadHouseholdName.setText(householdAttendingMeeting.getHeadHouseholdName());
            txtPhoneNumber.setText(householdAttendingMeeting.getHeadHouseholdPhoneNumber());
            txtNumberOfAdultPresent.setText(String.valueOf(householdAttendingMeeting.getNumberOfAdults()));
            txtCommittedToUseAquaTabs.setText(householdAttendingMeeting.getCommitedToUseAquatabs());
            textNumberOfAquatabsReceived.setText(String.valueOf(householdAttendingMeeting.getNumberOfAquaTabsReceived()));

            txtHouseholdAttendingMeetingOnlineStatus.setVisibility(View.INVISIBLE);
            uuidHouseholdAttendingMeeting = householdAttendingMeeting.getId();

            Amplify.API.query(
                    ModelQuery.get(HouseholdAttendingMeeting.class, uuidHouseholdAttendingMeeting),
                    response -> {
                        if(response.getData()!=null){
                            if(response.getData()!=null && (response.getData()).getId().contentEquals(uuidHouseholdAttendingMeeting)){
                                Activity activity = (Activity) context;
                                activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        txtHouseholdAttendingMeetingOnlineStatus.setVisibility(View.VISIBLE);
                                    }
                                });
                                Log.i("bwfsurveybeta", (response.getData()).getNamebwe() +"household attending meeting is on the cloud");
                            }
                        }
                    },
                    error -> {
                        Log.e("bwfsurveybeta", error.toString(), error);
                    }
            );
        }

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((HouseholdAttendingMeetingCardAdapter.HouseholdAttendingMeetingCardViewHolder) viewHolder).setHouseholdAttendingMeetingCardDetails(listOfHouseholdAttendingMeeting.get(i),i);
    }

    @Override
    public int getItemCount() {
        return listOfHouseholdAttendingMeeting.size();
    }
}
