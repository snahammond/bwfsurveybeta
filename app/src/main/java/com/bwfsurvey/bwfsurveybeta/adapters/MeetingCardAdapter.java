package com.bwfsurvey.bwfsurveybeta.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Meeting;
import com.amplifyframework.datastore.generated.model.Volunteer;
import com.bwfsurvey.bwfsurveybeta.activities.select.MeetingCardSelectActivity;
import com.example.bwfsurveybeta.R;

import java.util.ArrayList;

public class MeetingCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Meeting> listOfMeetings;
    private String namebwe;
    private String countrybwe;
    private String surveyType;
    private String operation;
    private Context context;

    public MeetingCardAdapter(MeetingCardSelectActivity meetingCardSelectActivity, ArrayList<Meeting> listOfMeetings, String namebwe, String countrybwe, String surveyType, String operation) {
        this.listOfMeetings = listOfMeetings;
        this.context = meetingCardSelectActivity;
        this.namebwe = namebwe;
        this.countrybwe = countrybwe;
        this.surveyType = surveyType;
        this.operation = operation;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_card, parent, false);
        return new MeetingCardAdapter.MeetingCardViewHolder(view);
    }

    private class MeetingCardViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMeetingDate;
        private TextView txtCommunityName;

        public MeetingCardViewHolder(View view) {
            super(view);
            txtMeetingDate = (TextView) itemView.findViewById(R.id.txtMeetingDate);
            txtCommunityName = (TextView) itemView.findViewById(R.id.txtCommunity);
            CardView meetingCard = (CardView) itemView.findViewById(R.id.meetingCard); // creating a CardView and assigning a value.

            meetingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Meeting name: " + txtMeetingDate.getText());
                }
            });
        }

        void setMeetingCardDetails(Meeting meeting, int position) {
            txtMeetingDate.setText(meeting.getDate().toString());
            txtCommunityName.setText(meeting.getCommunity());
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MeetingCardAdapter.MeetingCardViewHolder) holder).setMeetingCardDetails(listOfMeetings.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listOfMeetings.size();
    }
}
