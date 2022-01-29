package com.bwfsurvey.bwfsurveybeta.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Meeting;
import com.bwfsurvey.bwfsurveybeta.activities.select.HouseholdAttendingMeetingCardSelectActivity;
import com.bwfsurvey.bwfsurveybeta.activities.select.MeetingCardSelectActivity;
import com.example.bwfsurveybeta.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        private TextView txtLessonsTaught;
        private TextView textVolunteerAssistingDesc;
        private TextView textVolunteerAssisting;
        private String uuidMeeting;

        public MeetingCardViewHolder(View view) {
            super(view);
            txtMeetingDate = (TextView) itemView.findViewById(R.id.txtMeetingDate);
            txtCommunityName = (TextView) itemView.findViewById(R.id.txtCommunity);
            txtLessonsTaught = (TextView) itemView.findViewById(R.id.txtLessonsTaught);
            textVolunteerAssistingDesc = (TextView) itemView.findViewById(R.id.textVolunteerAssistingDesc);
            textVolunteerAssistingDesc.setVisibility(View.GONE);
            textVolunteerAssisting = (TextView) itemView.findViewById(R.id.textVolunteerAssisting);
            textVolunteerAssisting.setVisibility(View.GONE);

            CardView meetingCard = (CardView) itemView.findViewById(R.id.meetingCard); // creating a CardView and assigning a value.

            meetingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                    Log.i("Tutorials", "Meeting name: " + txtMeetingDate.getText());
                    if(operation.contentEquals("CREATE")){
                        startHouseholdAttendingActivity(uuidMeeting);
                    }
                }
            });
        }

        void setMeetingCardDetails(Meeting meeting, int position) {
            String ansToWrite = "";
            String dateStr =  meeting.getDate().toString();
            if(dateStr.indexOf("{")>0&&dateStr.indexOf("}")>0){
                dateStr = dateStr.substring(dateStr.indexOf("{") + 1);
                dateStr = dateStr.substring(0, dateStr.indexOf("}"));
                dateStr = dateStr.split(",")[0].split("=")[1];
                dateStr = dateStr.substring( 1, dateStr.length() - 1 );
                Log.i("Tutorial", "index dateStr: "+dateStr);

                Date date_ = null;
                try {
                    date_ = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    String dateToShow = new SimpleDateFormat("dd/MM/yyyy").format(date_);

                    if(!dateToShow.contentEquals("01/01/1900"))
                        ansToWrite = dateToShow;
                    else
                        ansToWrite = "";

                } catch (Exception e) {
                    ansToWrite = "";
                }

            }else{
                Log.i("Tutorial", "out index dateStr: "+dateStr);
                ansToWrite = "";
            }

            txtMeetingDate.setText(ansToWrite);
            txtCommunityName.setText(meeting.getCommunity());
            txtLessonsTaught.setText(meeting.getDiscussionsTaught());

            if(meeting.getNamevol()!=null && !meeting.getNamevol().isEmpty()){
                textVolunteerAssistingDesc.setVisibility(View.VISIBLE);
                textVolunteerAssisting.setVisibility(View.VISIBLE);
                textVolunteerAssisting.setText(meeting.getNamevol());
            }
            uuidMeeting = meeting.getId();
        }

    }

    private void startHouseholdAttendingActivity(String uuidMeeting) {
        Intent i = new Intent(context, HouseholdAttendingMeetingCardSelectActivity.class);
        i.putExtra("UUID", uuidMeeting);
        i.putExtra("OPERATION", operation);
        context.startActivity(i);
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
