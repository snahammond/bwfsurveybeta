package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the HealthCheckSurvey type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "HealthCheckSurveys", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class HealthCheckSurvey implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField SURVEY_ID = field("SurveyId");
  public static final QueryField DATE = field("date");
  public static final QueryField HEAD_HOUSEHOLD_NAME = field("HeadHouseholdName");
  public static final QueryField PERSON_BEING_INTERVIEWED = field("PersonBeingInterviewed");
  public static final QueryField WASTE_DISPOSAL_YOUNGEST_CHILD = field("WasteDisposalYoungestChild");
  public static final QueryField WASHED_HANDS_IN24_HOURS = field("WashedHandsIn24Hours");
  public static final QueryField WHEN_WASHED_HANDS_IN24_HOURS = field("WhenWashedHandsIn24Hours");
  public static final QueryField WHAT_USED_TO_WASH_YOUR_HANDS = field("WhatUsedToWashYourHands");
  public static final QueryField NO_CHILDREN_WITH_VOMITING_OR_DIARRHEA_IN14DAYS = field("NoChildrenWithVomitingOrDiarrheaIn14days");
  public static final QueryField NO_TOTAL_SCHOOL_DAYS_MISSED_BY_SCHOOL_AGE_CHILDREN_IN2_LAST_WEEK = field("NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek");
  public static final QueryField NO_CHILDREN_WITH_VOMITING_OR_DIARRHEA_IN7DAYS = field("NoChildrenWithVomitingOrDiarrheaIn7days");
  public static final QueryField DID_SICK_CHILDREN_GO_TO_HOSPITAL = field("DidSickChildrenGoToHospital");
  public static final QueryField DID_SICK_CHILDREN_GO_TO_HOSPITAL_YES = field("DidSickChildrenGoToHospitalYes");
  public static final QueryField SICK_CHILDREN_BREASTFEEDING = field("SickChildrenBreastfeeding");
  public static final QueryField OUTCOME_MOST_RECENT_VOMITING_DIARRHEA_AT_HOSPITAL = field("OutcomeMostRecentVomiting_DiarrheaAtHospital");
  public static final QueryField NO_DAYS_NO_WORK_BECAUSE_OF_OWN_ILLNESS = field("NoDaysNoWorkBecauseOfOwnIllness");
  public static final QueryField NO_DAYS_NO_WORK_BECAUSE_OF_ILLNESS_FAMILY_MEMBERS = field("NoDaysNoWorkBecauseOfIllnessFamilyMembers");
  public static final QueryField MONEY_SPENT_MEDICAL_TREATMENT_LAST4WEEKS = field("MoneySpentMedicalTreatmentLast4weeks");
  public static final QueryField WATER_TREATMENT24_HOURS = field("WaterTreatment24Hours");
  public static final QueryField MAIN_REASON_NO_WATER_TREATMENT24_HOUR = field("MainReasonNoWaterTreatment24Hour");
  public static final QueryField WATER_TREATMENT24_HOUR_METHOD = field("WaterTreatment24HourMethod");
  public static final QueryField COMPLETED = field("Completed");
  public static final QueryField LAT = field("Lat");
  public static final QueryField LNG = field("Lng");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="Int", isRequired = true) Integer SurveyId;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdName;
  private final @ModelField(targetType="String", isRequired = true) String PersonBeingInterviewed;
  private final @ModelField(targetType="String", isRequired = true) String WasteDisposalYoungestChild;
  private final @ModelField(targetType="String", isRequired = true) String WashedHandsIn24Hours;
  private final @ModelField(targetType="String", isRequired = true) String WhenWashedHandsIn24Hours;
  private final @ModelField(targetType="String", isRequired = true) String WhatUsedToWashYourHands;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoChildrenWithVomitingOrDiarrheaIn14days;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoChildrenWithVomitingOrDiarrheaIn7days;
  private final @ModelField(targetType="String", isRequired = true) String DidSickChildrenGoToHospital;
  private final @ModelField(targetType="String", isRequired = true) String DidSickChildrenGoToHospitalYes;
  private final @ModelField(targetType="String", isRequired = true) String SickChildrenBreastfeeding;
  private final @ModelField(targetType="String", isRequired = true) String OutcomeMostRecentVomiting_DiarrheaAtHospital;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoDaysNoWorkBecauseOfOwnIllness;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers;
  private final @ModelField(targetType="Int", isRequired = true) Integer MoneySpentMedicalTreatmentLast4weeks;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatment24Hours;
  private final @ModelField(targetType="String", isRequired = true) String MainReasonNoWaterTreatment24Hour;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatment24HourMethod;
  private final @ModelField(targetType="Int", isRequired = true) Integer Completed;
  private final @ModelField(targetType="String", isRequired = true) String Lat;
  private final @ModelField(targetType="String", isRequired = true) String Lng;
  public String getId() {
      return id;
  }
  
  public String getNamebwe() {
      return Namebwe;
  }
  
  public String getCountry() {
      return Country;
  }
  
  public String getCommunity() {
      return Community;
  }
  
  public Integer getSurveyId() {
      return SurveyId;
  }
  
  public Temporal.Date getDate() {
      return date;
  }
  
  public String getHeadHouseholdName() {
      return HeadHouseholdName;
  }
  
  public String getPersonBeingInterviewed() {
      return PersonBeingInterviewed;
  }
  
  public String getWasteDisposalYoungestChild() {
      return WasteDisposalYoungestChild;
  }
  
  public String getWashedHandsIn24Hours() {
      return WashedHandsIn24Hours;
  }
  
  public String getWhenWashedHandsIn24Hours() {
      return WhenWashedHandsIn24Hours;
  }
  
  public String getWhatUsedToWashYourHands() {
      return WhatUsedToWashYourHands;
  }
  
  public Integer getNoChildrenWithVomitingOrDiarrheaIn14days() {
      return NoChildrenWithVomitingOrDiarrheaIn14days;
  }
  
  public Integer getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek() {
      return NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek;
  }
  
  public Integer getNoChildrenWithVomitingOrDiarrheaIn7days() {
      return NoChildrenWithVomitingOrDiarrheaIn7days;
  }
  
  public String getDidSickChildrenGoToHospital() {
      return DidSickChildrenGoToHospital;
  }
  
  public String getDidSickChildrenGoToHospitalYes() {
      return DidSickChildrenGoToHospitalYes;
  }
  
  public String getSickChildrenBreastfeeding() {
      return SickChildrenBreastfeeding;
  }
  
  public String getOutcomeMostRecentVomitingDiarrheaAtHospital() {
      return OutcomeMostRecentVomiting_DiarrheaAtHospital;
  }
  
  public Integer getNoDaysNoWorkBecauseOfOwnIllness() {
      return NoDaysNoWorkBecauseOfOwnIllness;
  }
  
  public Integer getNoDaysNoWorkBecauseOfIllnessFamilyMembers() {
      return NoDaysNoWorkBecauseOfIllnessFamilyMembers;
  }
  
  public Integer getMoneySpentMedicalTreatmentLast4weeks() {
      return MoneySpentMedicalTreatmentLast4weeks;
  }
  
  public String getWaterTreatment24Hours() {
      return WaterTreatment24Hours;
  }
  
  public String getMainReasonNoWaterTreatment24Hour() {
      return MainReasonNoWaterTreatment24Hour;
  }
  
  public String getWaterTreatment24HourMethod() {
      return WaterTreatment24HourMethod;
  }
  
  public Integer getCompleted() {
      return Completed;
  }
  
  public String getLat() {
      return Lat;
  }
  
  public String getLng() {
      return Lng;
  }
  
  private HealthCheckSurvey(String id, String Namebwe, String Country, String Community, Integer SurveyId, Temporal.Date date, String HeadHouseholdName, String PersonBeingInterviewed, String WasteDisposalYoungestChild, String WashedHandsIn24Hours, String WhenWashedHandsIn24Hours, String WhatUsedToWashYourHands, Integer NoChildrenWithVomitingOrDiarrheaIn14days, Integer NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek, Integer NoChildrenWithVomitingOrDiarrheaIn7days, String DidSickChildrenGoToHospital, String DidSickChildrenGoToHospitalYes, String SickChildrenBreastfeeding, String OutcomeMostRecentVomiting_DiarrheaAtHospital, Integer NoDaysNoWorkBecauseOfOwnIllness, Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers, Integer MoneySpentMedicalTreatmentLast4weeks, String WaterTreatment24Hours, String MainReasonNoWaterTreatment24Hour, String WaterTreatment24HourMethod, Integer Completed, String Lat, String Lng) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.Country = Country;
    this.Community = Community;
    this.SurveyId = SurveyId;
    this.date = date;
    this.HeadHouseholdName = HeadHouseholdName;
    this.PersonBeingInterviewed = PersonBeingInterviewed;
    this.WasteDisposalYoungestChild = WasteDisposalYoungestChild;
    this.WashedHandsIn24Hours = WashedHandsIn24Hours;
    this.WhenWashedHandsIn24Hours = WhenWashedHandsIn24Hours;
    this.WhatUsedToWashYourHands = WhatUsedToWashYourHands;
    this.NoChildrenWithVomitingOrDiarrheaIn14days = NoChildrenWithVomitingOrDiarrheaIn14days;
    this.NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek = NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek;
    this.NoChildrenWithVomitingOrDiarrheaIn7days = NoChildrenWithVomitingOrDiarrheaIn7days;
    this.DidSickChildrenGoToHospital = DidSickChildrenGoToHospital;
    this.DidSickChildrenGoToHospitalYes = DidSickChildrenGoToHospitalYes;
    this.SickChildrenBreastfeeding = SickChildrenBreastfeeding;
    this.OutcomeMostRecentVomiting_DiarrheaAtHospital = OutcomeMostRecentVomiting_DiarrheaAtHospital;
    this.NoDaysNoWorkBecauseOfOwnIllness = NoDaysNoWorkBecauseOfOwnIllness;
    this.NoDaysNoWorkBecauseOfIllnessFamilyMembers = NoDaysNoWorkBecauseOfIllnessFamilyMembers;
    this.MoneySpentMedicalTreatmentLast4weeks = MoneySpentMedicalTreatmentLast4weeks;
    this.WaterTreatment24Hours = WaterTreatment24Hours;
    this.MainReasonNoWaterTreatment24Hour = MainReasonNoWaterTreatment24Hour;
    this.WaterTreatment24HourMethod = WaterTreatment24HourMethod;
    this.Completed = Completed;
    this.Lat = Lat;
    this.Lng = Lng;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      HealthCheckSurvey healthCheckSurvey = (HealthCheckSurvey) obj;
      return ObjectsCompat.equals(getId(), healthCheckSurvey.getId()) &&
              ObjectsCompat.equals(getNamebwe(), healthCheckSurvey.getNamebwe()) &&
              ObjectsCompat.equals(getCountry(), healthCheckSurvey.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), healthCheckSurvey.getCommunity()) &&
              ObjectsCompat.equals(getSurveyId(), healthCheckSurvey.getSurveyId()) &&
              ObjectsCompat.equals(getDate(), healthCheckSurvey.getDate()) &&
              ObjectsCompat.equals(getHeadHouseholdName(), healthCheckSurvey.getHeadHouseholdName()) &&
              ObjectsCompat.equals(getPersonBeingInterviewed(), healthCheckSurvey.getPersonBeingInterviewed()) &&
              ObjectsCompat.equals(getWasteDisposalYoungestChild(), healthCheckSurvey.getWasteDisposalYoungestChild()) &&
              ObjectsCompat.equals(getWashedHandsIn24Hours(), healthCheckSurvey.getWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhenWashedHandsIn24Hours(), healthCheckSurvey.getWhenWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhatUsedToWashYourHands(), healthCheckSurvey.getWhatUsedToWashYourHands()) &&
              ObjectsCompat.equals(getNoChildrenWithVomitingOrDiarrheaIn14days(), healthCheckSurvey.getNoChildrenWithVomitingOrDiarrheaIn14days()) &&
              ObjectsCompat.equals(getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(), healthCheckSurvey.getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek()) &&
              ObjectsCompat.equals(getNoChildrenWithVomitingOrDiarrheaIn7days(), healthCheckSurvey.getNoChildrenWithVomitingOrDiarrheaIn7days()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospital(), healthCheckSurvey.getDidSickChildrenGoToHospital()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospitalYes(), healthCheckSurvey.getDidSickChildrenGoToHospitalYes()) &&
              ObjectsCompat.equals(getSickChildrenBreastfeeding(), healthCheckSurvey.getSickChildrenBreastfeeding()) &&
              ObjectsCompat.equals(getOutcomeMostRecentVomitingDiarrheaAtHospital(), healthCheckSurvey.getOutcomeMostRecentVomitingDiarrheaAtHospital()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfOwnIllness(), healthCheckSurvey.getNoDaysNoWorkBecauseOfOwnIllness()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfIllnessFamilyMembers(), healthCheckSurvey.getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) &&
              ObjectsCompat.equals(getMoneySpentMedicalTreatmentLast4weeks(), healthCheckSurvey.getMoneySpentMedicalTreatmentLast4weeks()) &&
              ObjectsCompat.equals(getWaterTreatment24Hours(), healthCheckSurvey.getWaterTreatment24Hours()) &&
              ObjectsCompat.equals(getMainReasonNoWaterTreatment24Hour(), healthCheckSurvey.getMainReasonNoWaterTreatment24Hour()) &&
              ObjectsCompat.equals(getWaterTreatment24HourMethod(), healthCheckSurvey.getWaterTreatment24HourMethod()) &&
              ObjectsCompat.equals(getCompleted(), healthCheckSurvey.getCompleted()) &&
              ObjectsCompat.equals(getLat(), healthCheckSurvey.getLat()) &&
              ObjectsCompat.equals(getLng(), healthCheckSurvey.getLng());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getNamebwe())
      .append(getCountry())
      .append(getCommunity())
      .append(getSurveyId())
      .append(getDate())
      .append(getHeadHouseholdName())
      .append(getPersonBeingInterviewed())
      .append(getWasteDisposalYoungestChild())
      .append(getWashedHandsIn24Hours())
      .append(getWhenWashedHandsIn24Hours())
      .append(getWhatUsedToWashYourHands())
      .append(getNoChildrenWithVomitingOrDiarrheaIn14days())
      .append(getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek())
      .append(getNoChildrenWithVomitingOrDiarrheaIn7days())
      .append(getDidSickChildrenGoToHospital())
      .append(getDidSickChildrenGoToHospitalYes())
      .append(getSickChildrenBreastfeeding())
      .append(getOutcomeMostRecentVomitingDiarrheaAtHospital())
      .append(getNoDaysNoWorkBecauseOfOwnIllness())
      .append(getNoDaysNoWorkBecauseOfIllnessFamilyMembers())
      .append(getMoneySpentMedicalTreatmentLast4weeks())
      .append(getWaterTreatment24Hours())
      .append(getMainReasonNoWaterTreatment24Hour())
      .append(getWaterTreatment24HourMethod())
      .append(getCompleted())
      .append(getLat())
      .append(getLng())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("HealthCheckSurvey {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("SurveyId=" + String.valueOf(getSurveyId()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("HeadHouseholdName=" + String.valueOf(getHeadHouseholdName()) + ", ")
      .append("PersonBeingInterviewed=" + String.valueOf(getPersonBeingInterviewed()) + ", ")
      .append("WasteDisposalYoungestChild=" + String.valueOf(getWasteDisposalYoungestChild()) + ", ")
      .append("WashedHandsIn24Hours=" + String.valueOf(getWashedHandsIn24Hours()) + ", ")
      .append("WhenWashedHandsIn24Hours=" + String.valueOf(getWhenWashedHandsIn24Hours()) + ", ")
      .append("WhatUsedToWashYourHands=" + String.valueOf(getWhatUsedToWashYourHands()) + ", ")
      .append("NoChildrenWithVomitingOrDiarrheaIn14days=" + String.valueOf(getNoChildrenWithVomitingOrDiarrheaIn14days()) + ", ")
      .append("NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek=" + String.valueOf(getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek()) + ", ")
      .append("NoChildrenWithVomitingOrDiarrheaIn7days=" + String.valueOf(getNoChildrenWithVomitingOrDiarrheaIn7days()) + ", ")
      .append("DidSickChildrenGoToHospital=" + String.valueOf(getDidSickChildrenGoToHospital()) + ", ")
      .append("DidSickChildrenGoToHospitalYes=" + String.valueOf(getDidSickChildrenGoToHospitalYes()) + ", ")
      .append("SickChildrenBreastfeeding=" + String.valueOf(getSickChildrenBreastfeeding()) + ", ")
      .append("OutcomeMostRecentVomiting_DiarrheaAtHospital=" + String.valueOf(getOutcomeMostRecentVomitingDiarrheaAtHospital()) + ", ")
      .append("NoDaysNoWorkBecauseOfOwnIllness=" + String.valueOf(getNoDaysNoWorkBecauseOfOwnIllness()) + ", ")
      .append("NoDaysNoWorkBecauseOfIllnessFamilyMembers=" + String.valueOf(getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) + ", ")
      .append("MoneySpentMedicalTreatmentLast4weeks=" + String.valueOf(getMoneySpentMedicalTreatmentLast4weeks()) + ", ")
      .append("WaterTreatment24Hours=" + String.valueOf(getWaterTreatment24Hours()) + ", ")
      .append("MainReasonNoWaterTreatment24Hour=" + String.valueOf(getMainReasonNoWaterTreatment24Hour()) + ", ")
      .append("WaterTreatment24HourMethod=" + String.valueOf(getWaterTreatment24HourMethod()) + ", ")
      .append("Completed=" + String.valueOf(getCompleted()) + ", ")
      .append("Lat=" + String.valueOf(getLat()) + ", ")
      .append("Lng=" + String.valueOf(getLng()))
      .append("}")
      .toString();
  }
  
  public static NamebweStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static HealthCheckSurvey justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new HealthCheckSurvey(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Namebwe,
      Country,
      Community,
      SurveyId,
      date,
      HeadHouseholdName,
      PersonBeingInterviewed,
      WasteDisposalYoungestChild,
      WashedHandsIn24Hours,
      WhenWashedHandsIn24Hours,
      WhatUsedToWashYourHands,
      NoChildrenWithVomitingOrDiarrheaIn14days,
      NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek,
      NoChildrenWithVomitingOrDiarrheaIn7days,
      DidSickChildrenGoToHospital,
      DidSickChildrenGoToHospitalYes,
      SickChildrenBreastfeeding,
      OutcomeMostRecentVomiting_DiarrheaAtHospital,
      NoDaysNoWorkBecauseOfOwnIllness,
      NoDaysNoWorkBecauseOfIllnessFamilyMembers,
      MoneySpentMedicalTreatmentLast4weeks,
      WaterTreatment24Hours,
      MainReasonNoWaterTreatment24Hour,
      WaterTreatment24HourMethod,
      Completed,
      Lat,
      Lng);
  }
  public interface NamebweStep {
    CountryStep namebwe(String namebwe);
  }
  

  public interface CountryStep {
    CommunityStep country(String country);
  }
  

  public interface CommunityStep {
    SurveyIdStep community(String community);
  }
  

  public interface SurveyIdStep {
    HeadHouseholdNameStep surveyId(Integer surveyId);
  }
  

  public interface HeadHouseholdNameStep {
    PersonBeingInterviewedStep headHouseholdName(String headHouseholdName);
  }
  

  public interface PersonBeingInterviewedStep {
    WasteDisposalYoungestChildStep personBeingInterviewed(String personBeingInterviewed);
  }
  

  public interface WasteDisposalYoungestChildStep {
    WashedHandsIn24HoursStep wasteDisposalYoungestChild(String wasteDisposalYoungestChild);
  }
  

  public interface WashedHandsIn24HoursStep {
    WhenWashedHandsIn24HoursStep washedHandsIn24Hours(String washedHandsIn24Hours);
  }
  

  public interface WhenWashedHandsIn24HoursStep {
    WhatUsedToWashYourHandsStep whenWashedHandsIn24Hours(String whenWashedHandsIn24Hours);
  }
  

  public interface WhatUsedToWashYourHandsStep {
    NoChildrenWithVomitingOrDiarrheaIn14daysStep whatUsedToWashYourHands(String whatUsedToWashYourHands);
  }
  

  public interface NoChildrenWithVomitingOrDiarrheaIn14daysStep {
    NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeekStep noChildrenWithVomitingOrDiarrheaIn14days(Integer noChildrenWithVomitingOrDiarrheaIn14days);
  }
  

  public interface NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeekStep {
    NoChildrenWithVomitingOrDiarrheaIn7daysStep noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(Integer noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek);
  }
  

  public interface NoChildrenWithVomitingOrDiarrheaIn7daysStep {
    DidSickChildrenGoToHospitalStep noChildrenWithVomitingOrDiarrheaIn7days(Integer noChildrenWithVomitingOrDiarrheaIn7days);
  }
  

  public interface DidSickChildrenGoToHospitalStep {
    DidSickChildrenGoToHospitalYesStep didSickChildrenGoToHospital(String didSickChildrenGoToHospital);
  }
  

  public interface DidSickChildrenGoToHospitalYesStep {
    SickChildrenBreastfeedingStep didSickChildrenGoToHospitalYes(String didSickChildrenGoToHospitalYes);
  }
  

  public interface SickChildrenBreastfeedingStep {
    OutcomeMostRecentVomitingDiarrheaAtHospitalStep sickChildrenBreastfeeding(String sickChildrenBreastfeeding);
  }
  

  public interface OutcomeMostRecentVomitingDiarrheaAtHospitalStep {
    NoDaysNoWorkBecauseOfOwnIllnessStep outcomeMostRecentVomitingDiarrheaAtHospital(String outcomeMostRecentVomitingDiarrheaAtHospital);
  }
  

  public interface NoDaysNoWorkBecauseOfOwnIllnessStep {
    NoDaysNoWorkBecauseOfIllnessFamilyMembersStep noDaysNoWorkBecauseOfOwnIllness(Integer noDaysNoWorkBecauseOfOwnIllness);
  }
  

  public interface NoDaysNoWorkBecauseOfIllnessFamilyMembersStep {
    MoneySpentMedicalTreatmentLast4weeksStep noDaysNoWorkBecauseOfIllnessFamilyMembers(Integer noDaysNoWorkBecauseOfIllnessFamilyMembers);
  }
  

  public interface MoneySpentMedicalTreatmentLast4weeksStep {
    WaterTreatment24HoursStep moneySpentMedicalTreatmentLast4weeks(Integer moneySpentMedicalTreatmentLast4weeks);
  }
  

  public interface WaterTreatment24HoursStep {
    MainReasonNoWaterTreatment24HourStep waterTreatment24Hours(String waterTreatment24Hours);
  }
  

  public interface MainReasonNoWaterTreatment24HourStep {
    WaterTreatment24HourMethodStep mainReasonNoWaterTreatment24Hour(String mainReasonNoWaterTreatment24Hour);
  }
  

  public interface WaterTreatment24HourMethodStep {
    CompletedStep waterTreatment24HourMethod(String waterTreatment24HourMethod);
  }
  

  public interface CompletedStep {
    LatStep completed(Integer completed);
  }
  

  public interface LatStep {
    LngStep lat(String lat);
  }
  

  public interface LngStep {
    BuildStep lng(String lng);
  }
  

  public interface BuildStep {
    HealthCheckSurvey build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, CountryStep, CommunityStep, SurveyIdStep, HeadHouseholdNameStep, PersonBeingInterviewedStep, WasteDisposalYoungestChildStep, WashedHandsIn24HoursStep, WhenWashedHandsIn24HoursStep, WhatUsedToWashYourHandsStep, NoChildrenWithVomitingOrDiarrheaIn14daysStep, NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeekStep, NoChildrenWithVomitingOrDiarrheaIn7daysStep, DidSickChildrenGoToHospitalStep, DidSickChildrenGoToHospitalYesStep, SickChildrenBreastfeedingStep, OutcomeMostRecentVomitingDiarrheaAtHospitalStep, NoDaysNoWorkBecauseOfOwnIllnessStep, NoDaysNoWorkBecauseOfIllnessFamilyMembersStep, MoneySpentMedicalTreatmentLast4weeksStep, WaterTreatment24HoursStep, MainReasonNoWaterTreatment24HourStep, WaterTreatment24HourMethodStep, CompletedStep, LatStep, LngStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Country;
    private String Community;
    private Integer SurveyId;
    private String HeadHouseholdName;
    private String PersonBeingInterviewed;
    private String WasteDisposalYoungestChild;
    private String WashedHandsIn24Hours;
    private String WhenWashedHandsIn24Hours;
    private String WhatUsedToWashYourHands;
    private Integer NoChildrenWithVomitingOrDiarrheaIn14days;
    private Integer NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek;
    private Integer NoChildrenWithVomitingOrDiarrheaIn7days;
    private String DidSickChildrenGoToHospital;
    private String DidSickChildrenGoToHospitalYes;
    private String SickChildrenBreastfeeding;
    private String OutcomeMostRecentVomiting_DiarrheaAtHospital;
    private Integer NoDaysNoWorkBecauseOfOwnIllness;
    private Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers;
    private Integer MoneySpentMedicalTreatmentLast4weeks;
    private String WaterTreatment24Hours;
    private String MainReasonNoWaterTreatment24Hour;
    private String WaterTreatment24HourMethod;
    private Integer Completed;
    private String Lat;
    private String Lng;
    private Temporal.Date date;
    @Override
     public HealthCheckSurvey build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new HealthCheckSurvey(
          id,
          Namebwe,
          Country,
          Community,
          SurveyId,
          date,
          HeadHouseholdName,
          PersonBeingInterviewed,
          WasteDisposalYoungestChild,
          WashedHandsIn24Hours,
          WhenWashedHandsIn24Hours,
          WhatUsedToWashYourHands,
          NoChildrenWithVomitingOrDiarrheaIn14days,
          NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek,
          NoChildrenWithVomitingOrDiarrheaIn7days,
          DidSickChildrenGoToHospital,
          DidSickChildrenGoToHospitalYes,
          SickChildrenBreastfeeding,
          OutcomeMostRecentVomiting_DiarrheaAtHospital,
          NoDaysNoWorkBecauseOfOwnIllness,
          NoDaysNoWorkBecauseOfIllnessFamilyMembers,
          MoneySpentMedicalTreatmentLast4weeks,
          WaterTreatment24Hours,
          MainReasonNoWaterTreatment24Hour,
          WaterTreatment24HourMethod,
          Completed,
          Lat,
          Lng);
    }
    
    @Override
     public CountryStep namebwe(String namebwe) {
        Objects.requireNonNull(namebwe);
        this.Namebwe = namebwe;
        return this;
    }
    
    @Override
     public CommunityStep country(String country) {
        Objects.requireNonNull(country);
        this.Country = country;
        return this;
    }
    
    @Override
     public SurveyIdStep community(String community) {
        Objects.requireNonNull(community);
        this.Community = community;
        return this;
    }
    
    @Override
     public HeadHouseholdNameStep surveyId(Integer surveyId) {
        Objects.requireNonNull(surveyId);
        this.SurveyId = surveyId;
        return this;
    }
    
    @Override
     public PersonBeingInterviewedStep headHouseholdName(String headHouseholdName) {
        Objects.requireNonNull(headHouseholdName);
        this.HeadHouseholdName = headHouseholdName;
        return this;
    }
    
    @Override
     public WasteDisposalYoungestChildStep personBeingInterviewed(String personBeingInterviewed) {
        Objects.requireNonNull(personBeingInterviewed);
        this.PersonBeingInterviewed = personBeingInterviewed;
        return this;
    }
    
    @Override
     public WashedHandsIn24HoursStep wasteDisposalYoungestChild(String wasteDisposalYoungestChild) {
        Objects.requireNonNull(wasteDisposalYoungestChild);
        this.WasteDisposalYoungestChild = wasteDisposalYoungestChild;
        return this;
    }
    
    @Override
     public WhenWashedHandsIn24HoursStep washedHandsIn24Hours(String washedHandsIn24Hours) {
        Objects.requireNonNull(washedHandsIn24Hours);
        this.WashedHandsIn24Hours = washedHandsIn24Hours;
        return this;
    }
    
    @Override
     public WhatUsedToWashYourHandsStep whenWashedHandsIn24Hours(String whenWashedHandsIn24Hours) {
        Objects.requireNonNull(whenWashedHandsIn24Hours);
        this.WhenWashedHandsIn24Hours = whenWashedHandsIn24Hours;
        return this;
    }
    
    @Override
     public NoChildrenWithVomitingOrDiarrheaIn14daysStep whatUsedToWashYourHands(String whatUsedToWashYourHands) {
        Objects.requireNonNull(whatUsedToWashYourHands);
        this.WhatUsedToWashYourHands = whatUsedToWashYourHands;
        return this;
    }
    
    @Override
     public NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeekStep noChildrenWithVomitingOrDiarrheaIn14days(Integer noChildrenWithVomitingOrDiarrheaIn14days) {
        Objects.requireNonNull(noChildrenWithVomitingOrDiarrheaIn14days);
        this.NoChildrenWithVomitingOrDiarrheaIn14days = noChildrenWithVomitingOrDiarrheaIn14days;
        return this;
    }
    
    @Override
     public NoChildrenWithVomitingOrDiarrheaIn7daysStep noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(Integer noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek) {
        Objects.requireNonNull(noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek);
        this.NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek = noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek;
        return this;
    }
    
    @Override
     public DidSickChildrenGoToHospitalStep noChildrenWithVomitingOrDiarrheaIn7days(Integer noChildrenWithVomitingOrDiarrheaIn7days) {
        Objects.requireNonNull(noChildrenWithVomitingOrDiarrheaIn7days);
        this.NoChildrenWithVomitingOrDiarrheaIn7days = noChildrenWithVomitingOrDiarrheaIn7days;
        return this;
    }
    
    @Override
     public DidSickChildrenGoToHospitalYesStep didSickChildrenGoToHospital(String didSickChildrenGoToHospital) {
        Objects.requireNonNull(didSickChildrenGoToHospital);
        this.DidSickChildrenGoToHospital = didSickChildrenGoToHospital;
        return this;
    }
    
    @Override
     public SickChildrenBreastfeedingStep didSickChildrenGoToHospitalYes(String didSickChildrenGoToHospitalYes) {
        Objects.requireNonNull(didSickChildrenGoToHospitalYes);
        this.DidSickChildrenGoToHospitalYes = didSickChildrenGoToHospitalYes;
        return this;
    }
    
    @Override
     public OutcomeMostRecentVomitingDiarrheaAtHospitalStep sickChildrenBreastfeeding(String sickChildrenBreastfeeding) {
        Objects.requireNonNull(sickChildrenBreastfeeding);
        this.SickChildrenBreastfeeding = sickChildrenBreastfeeding;
        return this;
    }
    
    @Override
     public NoDaysNoWorkBecauseOfOwnIllnessStep outcomeMostRecentVomitingDiarrheaAtHospital(String outcomeMostRecentVomitingDiarrheaAtHospital) {
        Objects.requireNonNull(outcomeMostRecentVomitingDiarrheaAtHospital);
        this.OutcomeMostRecentVomiting_DiarrheaAtHospital = outcomeMostRecentVomitingDiarrheaAtHospital;
        return this;
    }
    
    @Override
     public NoDaysNoWorkBecauseOfIllnessFamilyMembersStep noDaysNoWorkBecauseOfOwnIllness(Integer noDaysNoWorkBecauseOfOwnIllness) {
        Objects.requireNonNull(noDaysNoWorkBecauseOfOwnIllness);
        this.NoDaysNoWorkBecauseOfOwnIllness = noDaysNoWorkBecauseOfOwnIllness;
        return this;
    }
    
    @Override
     public MoneySpentMedicalTreatmentLast4weeksStep noDaysNoWorkBecauseOfIllnessFamilyMembers(Integer noDaysNoWorkBecauseOfIllnessFamilyMembers) {
        Objects.requireNonNull(noDaysNoWorkBecauseOfIllnessFamilyMembers);
        this.NoDaysNoWorkBecauseOfIllnessFamilyMembers = noDaysNoWorkBecauseOfIllnessFamilyMembers;
        return this;
    }
    
    @Override
     public WaterTreatment24HoursStep moneySpentMedicalTreatmentLast4weeks(Integer moneySpentMedicalTreatmentLast4weeks) {
        Objects.requireNonNull(moneySpentMedicalTreatmentLast4weeks);
        this.MoneySpentMedicalTreatmentLast4weeks = moneySpentMedicalTreatmentLast4weeks;
        return this;
    }
    
    @Override
     public MainReasonNoWaterTreatment24HourStep waterTreatment24Hours(String waterTreatment24Hours) {
        Objects.requireNonNull(waterTreatment24Hours);
        this.WaterTreatment24Hours = waterTreatment24Hours;
        return this;
    }
    
    @Override
     public WaterTreatment24HourMethodStep mainReasonNoWaterTreatment24Hour(String mainReasonNoWaterTreatment24Hour) {
        Objects.requireNonNull(mainReasonNoWaterTreatment24Hour);
        this.MainReasonNoWaterTreatment24Hour = mainReasonNoWaterTreatment24Hour;
        return this;
    }
    
    @Override
     public CompletedStep waterTreatment24HourMethod(String waterTreatment24HourMethod) {
        Objects.requireNonNull(waterTreatment24HourMethod);
        this.WaterTreatment24HourMethod = waterTreatment24HourMethod;
        return this;
    }
    
    @Override
     public LatStep completed(Integer completed) {
        Objects.requireNonNull(completed);
        this.Completed = completed;
        return this;
    }
    
    @Override
     public LngStep lat(String lat) {
        Objects.requireNonNull(lat);
        this.Lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lng(String lng) {
        Objects.requireNonNull(lng);
        this.Lng = lng;
        return this;
    }
    
    @Override
     public BuildStep date(Temporal.Date date) {
        this.date = date;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String namebwe, String country, String community, Integer surveyId, Temporal.Date date, String headHouseholdName, String personBeingInterviewed, String wasteDisposalYoungestChild, String washedHandsIn24Hours, String whenWashedHandsIn24Hours, String whatUsedToWashYourHands, Integer noChildrenWithVomitingOrDiarrheaIn14days, Integer noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek, Integer noChildrenWithVomitingOrDiarrheaIn7days, String didSickChildrenGoToHospital, String didSickChildrenGoToHospitalYes, String sickChildrenBreastfeeding, String outcomeMostRecentVomitingDiarrheaAtHospital, Integer noDaysNoWorkBecauseOfOwnIllness, Integer noDaysNoWorkBecauseOfIllnessFamilyMembers, Integer moneySpentMedicalTreatmentLast4weeks, String waterTreatment24Hours, String mainReasonNoWaterTreatment24Hour, String waterTreatment24HourMethod, Integer completed, String lat, String lng) {
      super.id(id);
      super.namebwe(namebwe)
        .country(country)
        .community(community)
        .surveyId(surveyId)
        .headHouseholdName(headHouseholdName)
        .personBeingInterviewed(personBeingInterviewed)
        .wasteDisposalYoungestChild(wasteDisposalYoungestChild)
        .washedHandsIn24Hours(washedHandsIn24Hours)
        .whenWashedHandsIn24Hours(whenWashedHandsIn24Hours)
        .whatUsedToWashYourHands(whatUsedToWashYourHands)
        .noChildrenWithVomitingOrDiarrheaIn14days(noChildrenWithVomitingOrDiarrheaIn14days)
        .noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek)
        .noChildrenWithVomitingOrDiarrheaIn7days(noChildrenWithVomitingOrDiarrheaIn7days)
        .didSickChildrenGoToHospital(didSickChildrenGoToHospital)
        .didSickChildrenGoToHospitalYes(didSickChildrenGoToHospitalYes)
        .sickChildrenBreastfeeding(sickChildrenBreastfeeding)
        .outcomeMostRecentVomitingDiarrheaAtHospital(outcomeMostRecentVomitingDiarrheaAtHospital)
        .noDaysNoWorkBecauseOfOwnIllness(noDaysNoWorkBecauseOfOwnIllness)
        .noDaysNoWorkBecauseOfIllnessFamilyMembers(noDaysNoWorkBecauseOfIllnessFamilyMembers)
        .moneySpentMedicalTreatmentLast4weeks(moneySpentMedicalTreatmentLast4weeks)
        .waterTreatment24Hours(waterTreatment24Hours)
        .mainReasonNoWaterTreatment24Hour(mainReasonNoWaterTreatment24Hour)
        .waterTreatment24HourMethod(waterTreatment24HourMethod)
        .completed(completed)
        .lat(lat)
        .lng(lng)
        .date(date);
    }
    
    @Override
     public CopyOfBuilder namebwe(String namebwe) {
      return (CopyOfBuilder) super.namebwe(namebwe);
    }
    
    @Override
     public CopyOfBuilder country(String country) {
      return (CopyOfBuilder) super.country(country);
    }
    
    @Override
     public CopyOfBuilder community(String community) {
      return (CopyOfBuilder) super.community(community);
    }
    
    @Override
     public CopyOfBuilder surveyId(Integer surveyId) {
      return (CopyOfBuilder) super.surveyId(surveyId);
    }
    
    @Override
     public CopyOfBuilder headHouseholdName(String headHouseholdName) {
      return (CopyOfBuilder) super.headHouseholdName(headHouseholdName);
    }
    
    @Override
     public CopyOfBuilder personBeingInterviewed(String personBeingInterviewed) {
      return (CopyOfBuilder) super.personBeingInterviewed(personBeingInterviewed);
    }
    
    @Override
     public CopyOfBuilder wasteDisposalYoungestChild(String wasteDisposalYoungestChild) {
      return (CopyOfBuilder) super.wasteDisposalYoungestChild(wasteDisposalYoungestChild);
    }
    
    @Override
     public CopyOfBuilder washedHandsIn24Hours(String washedHandsIn24Hours) {
      return (CopyOfBuilder) super.washedHandsIn24Hours(washedHandsIn24Hours);
    }
    
    @Override
     public CopyOfBuilder whenWashedHandsIn24Hours(String whenWashedHandsIn24Hours) {
      return (CopyOfBuilder) super.whenWashedHandsIn24Hours(whenWashedHandsIn24Hours);
    }
    
    @Override
     public CopyOfBuilder whatUsedToWashYourHands(String whatUsedToWashYourHands) {
      return (CopyOfBuilder) super.whatUsedToWashYourHands(whatUsedToWashYourHands);
    }
    
    @Override
     public CopyOfBuilder noChildrenWithVomitingOrDiarrheaIn14days(Integer noChildrenWithVomitingOrDiarrheaIn14days) {
      return (CopyOfBuilder) super.noChildrenWithVomitingOrDiarrheaIn14days(noChildrenWithVomitingOrDiarrheaIn14days);
    }
    
    @Override
     public CopyOfBuilder noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(Integer noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek) {
      return (CopyOfBuilder) super.noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek);
    }
    
    @Override
     public CopyOfBuilder noChildrenWithVomitingOrDiarrheaIn7days(Integer noChildrenWithVomitingOrDiarrheaIn7days) {
      return (CopyOfBuilder) super.noChildrenWithVomitingOrDiarrheaIn7days(noChildrenWithVomitingOrDiarrheaIn7days);
    }
    
    @Override
     public CopyOfBuilder didSickChildrenGoToHospital(String didSickChildrenGoToHospital) {
      return (CopyOfBuilder) super.didSickChildrenGoToHospital(didSickChildrenGoToHospital);
    }
    
    @Override
     public CopyOfBuilder didSickChildrenGoToHospitalYes(String didSickChildrenGoToHospitalYes) {
      return (CopyOfBuilder) super.didSickChildrenGoToHospitalYes(didSickChildrenGoToHospitalYes);
    }
    
    @Override
     public CopyOfBuilder sickChildrenBreastfeeding(String sickChildrenBreastfeeding) {
      return (CopyOfBuilder) super.sickChildrenBreastfeeding(sickChildrenBreastfeeding);
    }
    
    @Override
     public CopyOfBuilder outcomeMostRecentVomitingDiarrheaAtHospital(String outcomeMostRecentVomitingDiarrheaAtHospital) {
      return (CopyOfBuilder) super.outcomeMostRecentVomitingDiarrheaAtHospital(outcomeMostRecentVomitingDiarrheaAtHospital);
    }
    
    @Override
     public CopyOfBuilder noDaysNoWorkBecauseOfOwnIllness(Integer noDaysNoWorkBecauseOfOwnIllness) {
      return (CopyOfBuilder) super.noDaysNoWorkBecauseOfOwnIllness(noDaysNoWorkBecauseOfOwnIllness);
    }
    
    @Override
     public CopyOfBuilder noDaysNoWorkBecauseOfIllnessFamilyMembers(Integer noDaysNoWorkBecauseOfIllnessFamilyMembers) {
      return (CopyOfBuilder) super.noDaysNoWorkBecauseOfIllnessFamilyMembers(noDaysNoWorkBecauseOfIllnessFamilyMembers);
    }
    
    @Override
     public CopyOfBuilder moneySpentMedicalTreatmentLast4weeks(Integer moneySpentMedicalTreatmentLast4weeks) {
      return (CopyOfBuilder) super.moneySpentMedicalTreatmentLast4weeks(moneySpentMedicalTreatmentLast4weeks);
    }
    
    @Override
     public CopyOfBuilder waterTreatment24Hours(String waterTreatment24Hours) {
      return (CopyOfBuilder) super.waterTreatment24Hours(waterTreatment24Hours);
    }
    
    @Override
     public CopyOfBuilder mainReasonNoWaterTreatment24Hour(String mainReasonNoWaterTreatment24Hour) {
      return (CopyOfBuilder) super.mainReasonNoWaterTreatment24Hour(mainReasonNoWaterTreatment24Hour);
    }
    
    @Override
     public CopyOfBuilder waterTreatment24HourMethod(String waterTreatment24HourMethod) {
      return (CopyOfBuilder) super.waterTreatment24HourMethod(waterTreatment24HourMethod);
    }
    
    @Override
     public CopyOfBuilder completed(Integer completed) {
      return (CopyOfBuilder) super.completed(completed);
    }
    
    @Override
     public CopyOfBuilder lat(String lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lng(String lng) {
      return (CopyOfBuilder) super.lng(lng);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}
