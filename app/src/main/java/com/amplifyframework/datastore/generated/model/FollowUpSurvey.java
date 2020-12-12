package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the FollowUpSurvey type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FollowUpSurveys")
public final class FollowUpSurvey implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField SURVEY_ID = field("SurveyId");
  public static final QueryField DATE = field("date");
  public static final QueryField HEAD_HOUSEHOLD_NAME = field("HeadHouseholdName");
  public static final QueryField PERSON_BEING_INTERVIEWED = field("PersonBeingInterviewed");
  public static final QueryField WATER_TREATMENT_BEFORE_DRINKING = field("WaterTreatmentBeforeDrinking");
  public static final QueryField MAIN_REASON_NO_WATER_TREATMENT_BEFORE_DRINKING = field("MainReasonNoWaterTreatmentBeforeDrinking");
  public static final QueryField WATER_TREATMENT_METHOD = field("WaterTreatmentMethod");
  public static final QueryField HOW_LONG_USING_WATER_TREATMENT = field("HowLongUsingWaterTreatment");
  public static final QueryField FREQUENCY_WATER_TREATMENT = field("FrequencyWaterTreatment");
  public static final QueryField WATER_STORAGE_AT_HOME = field("WaterStorageAtHome");
  public static final QueryField TAKING_WATER_FROM_STORAGE = field("TakingWaterFromStorage");
  public static final QueryField WASHED_HANDS_IN24_HOURS = field("WashedHandsIn24Hours");
  public static final QueryField WHEN_WASHED_HANDS_IN24_HOURS = field("WhenWashedHandsIn24Hours");
  public static final QueryField WHAT_USED_TO_WASH_YOUR_HANDS = field("WhatUsedToWashYourHands");
  public static final QueryField NO_TOTAL_SCHOOL_DAYS_MISSED_BY_ALL_CHILDREN_IN2_LAST_WEEK = field("NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek");
  public static final QueryField COMMON_ILLNESS_AFFECTING_CHILDREN_UNDER5 = field("CommonIllnessAffectingChildrenUnder5");
  public static final QueryField NO_CHILDREN_WITH_VOMITING_OR_DIARRHEA_IN7DAYS = field("NoChildrenWithVomitingOrDiarrheaIn7days");
  public static final QueryField DID_SICK_CHILDREN_GO_TO_HOSPITAL = field("DidSickChildrenGoToHospital");
  public static final QueryField DID_SICK_CHILDREN_GO_TO_HOSPITAL_YES = field("DidSickChildrenGoToHospitalYes");
  public static final QueryField SICK_CHILDREN_BREASTFEEDING = field("SickChildrenBreastfeeding");
  public static final QueryField OUTCOME_MOST_RECENT_VOMITING_DIARRHEA_AT_HOSPITAL = field("OutcomeMostRecentVomiting_DiarrheaAtHospital");
  public static final QueryField NO_DAYS_NO_WORK_BECAUSE_OF_OWN_ILLNESS = field("NoDaysNoWorkBecauseOfOwnIllness");
  public static final QueryField NO_DAYS_NO_WORK_BECAUSE_OF_ILLNESS_FAMILY_MEMBERS = field("NoDaysNoWorkBecauseOfIllnessFamilyMembers");
  public static final QueryField MONEY_SPENT_MEDICAL_TREATMENT_LAST4WEEKS = field("MoneySpentMedicalTreatmentLast4weeks");
  public static final QueryField HEALTH_CHANGE_IN_A_YEAR = field("HealthChangeInAYear");
  public static final QueryField HEALTH_CHANGE_FAMILY_IN_A_YEAR = field("HealthChangeFamilyInAYear");
  public static final QueryField BENEFIT_SWP = field("BenefitSWP");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="Int", isRequired = true) Integer SurveyId;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdName;
  private final @ModelField(targetType="String", isRequired = true) String PersonBeingInterviewed;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatmentBeforeDrinking;
  private final @ModelField(targetType="String", isRequired = true) String MainReasonNoWaterTreatmentBeforeDrinking;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatmentMethod;
  private final @ModelField(targetType="String", isRequired = true) String HowLongUsingWaterTreatment;
  private final @ModelField(targetType="String", isRequired = true) String FrequencyWaterTreatment;
  private final @ModelField(targetType="String", isRequired = true) String WaterStorageAtHome;
  private final @ModelField(targetType="String", isRequired = true) String TakingWaterFromStorage;
  private final @ModelField(targetType="String", isRequired = true) String WashedHandsIn24Hours;
  private final @ModelField(targetType="String", isRequired = true) String WhenWashedHandsIn24Hours;
  private final @ModelField(targetType="String", isRequired = true) String WhatUsedToWashYourHands;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek;
  private final @ModelField(targetType="String", isRequired = true) String CommonIllnessAffectingChildrenUnder5;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoChildrenWithVomitingOrDiarrheaIn7days;
  private final @ModelField(targetType="String", isRequired = true) String DidSickChildrenGoToHospital;
  private final @ModelField(targetType="String", isRequired = true) String DidSickChildrenGoToHospitalYes;
  private final @ModelField(targetType="String", isRequired = true) String SickChildrenBreastfeeding;
  private final @ModelField(targetType="String", isRequired = true) String OutcomeMostRecentVomiting_DiarrheaAtHospital;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoDaysNoWorkBecauseOfOwnIllness;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers;
  private final @ModelField(targetType="Int", isRequired = true) Integer MoneySpentMedicalTreatmentLast4weeks;
  private final @ModelField(targetType="String", isRequired = true) String HealthChangeInAYear;
  private final @ModelField(targetType="String", isRequired = true) String HealthChangeFamilyInAYear;
  private final @ModelField(targetType="String", isRequired = true) String BenefitSWP;
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
  
  public String getWaterTreatmentBeforeDrinking() {
      return WaterTreatmentBeforeDrinking;
  }
  
  public String getMainReasonNoWaterTreatmentBeforeDrinking() {
      return MainReasonNoWaterTreatmentBeforeDrinking;
  }
  
  public String getWaterTreatmentMethod() {
      return WaterTreatmentMethod;
  }
  
  public String getHowLongUsingWaterTreatment() {
      return HowLongUsingWaterTreatment;
  }
  
  public String getFrequencyWaterTreatment() {
      return FrequencyWaterTreatment;
  }
  
  public String getWaterStorageAtHome() {
      return WaterStorageAtHome;
  }
  
  public String getTakingWaterFromStorage() {
      return TakingWaterFromStorage;
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
  
  public Integer getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek() {
      return NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek;
  }
  
  public String getCommonIllnessAffectingChildrenUnder5() {
      return CommonIllnessAffectingChildrenUnder5;
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
  
  public String getHealthChangeInAYear() {
      return HealthChangeInAYear;
  }
  
  public String getHealthChangeFamilyInAYear() {
      return HealthChangeFamilyInAYear;
  }
  
  public String getBenefitSwp() {
      return BenefitSWP;
  }
  
  private FollowUpSurvey(String id, String Namebwe, String Country, String Community, Integer SurveyId, Temporal.Date date, String HeadHouseholdName, String PersonBeingInterviewed, String WaterTreatmentBeforeDrinking, String MainReasonNoWaterTreatmentBeforeDrinking, String WaterTreatmentMethod, String HowLongUsingWaterTreatment, String FrequencyWaterTreatment, String WaterStorageAtHome, String TakingWaterFromStorage, String WashedHandsIn24Hours, String WhenWashedHandsIn24Hours, String WhatUsedToWashYourHands, Integer NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek, String CommonIllnessAffectingChildrenUnder5, Integer NoChildrenWithVomitingOrDiarrheaIn7days, String DidSickChildrenGoToHospital, String DidSickChildrenGoToHospitalYes, String SickChildrenBreastfeeding, String OutcomeMostRecentVomiting_DiarrheaAtHospital, Integer NoDaysNoWorkBecauseOfOwnIllness, Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers, Integer MoneySpentMedicalTreatmentLast4weeks, String HealthChangeInAYear, String HealthChangeFamilyInAYear, String BenefitSWP) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.Country = Country;
    this.Community = Community;
    this.SurveyId = SurveyId;
    this.date = date;
    this.HeadHouseholdName = HeadHouseholdName;
    this.PersonBeingInterviewed = PersonBeingInterviewed;
    this.WaterTreatmentBeforeDrinking = WaterTreatmentBeforeDrinking;
    this.MainReasonNoWaterTreatmentBeforeDrinking = MainReasonNoWaterTreatmentBeforeDrinking;
    this.WaterTreatmentMethod = WaterTreatmentMethod;
    this.HowLongUsingWaterTreatment = HowLongUsingWaterTreatment;
    this.FrequencyWaterTreatment = FrequencyWaterTreatment;
    this.WaterStorageAtHome = WaterStorageAtHome;
    this.TakingWaterFromStorage = TakingWaterFromStorage;
    this.WashedHandsIn24Hours = WashedHandsIn24Hours;
    this.WhenWashedHandsIn24Hours = WhenWashedHandsIn24Hours;
    this.WhatUsedToWashYourHands = WhatUsedToWashYourHands;
    this.NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek = NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek;
    this.CommonIllnessAffectingChildrenUnder5 = CommonIllnessAffectingChildrenUnder5;
    this.NoChildrenWithVomitingOrDiarrheaIn7days = NoChildrenWithVomitingOrDiarrheaIn7days;
    this.DidSickChildrenGoToHospital = DidSickChildrenGoToHospital;
    this.DidSickChildrenGoToHospitalYes = DidSickChildrenGoToHospitalYes;
    this.SickChildrenBreastfeeding = SickChildrenBreastfeeding;
    this.OutcomeMostRecentVomiting_DiarrheaAtHospital = OutcomeMostRecentVomiting_DiarrheaAtHospital;
    this.NoDaysNoWorkBecauseOfOwnIllness = NoDaysNoWorkBecauseOfOwnIllness;
    this.NoDaysNoWorkBecauseOfIllnessFamilyMembers = NoDaysNoWorkBecauseOfIllnessFamilyMembers;
    this.MoneySpentMedicalTreatmentLast4weeks = MoneySpentMedicalTreatmentLast4weeks;
    this.HealthChangeInAYear = HealthChangeInAYear;
    this.HealthChangeFamilyInAYear = HealthChangeFamilyInAYear;
    this.BenefitSWP = BenefitSWP;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FollowUpSurvey followUpSurvey = (FollowUpSurvey) obj;
      return ObjectsCompat.equals(getId(), followUpSurvey.getId()) &&
              ObjectsCompat.equals(getNamebwe(), followUpSurvey.getNamebwe()) &&
              ObjectsCompat.equals(getCountry(), followUpSurvey.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), followUpSurvey.getCommunity()) &&
              ObjectsCompat.equals(getSurveyId(), followUpSurvey.getSurveyId()) &&
              ObjectsCompat.equals(getDate(), followUpSurvey.getDate()) &&
              ObjectsCompat.equals(getHeadHouseholdName(), followUpSurvey.getHeadHouseholdName()) &&
              ObjectsCompat.equals(getPersonBeingInterviewed(), followUpSurvey.getPersonBeingInterviewed()) &&
              ObjectsCompat.equals(getWaterTreatmentBeforeDrinking(), followUpSurvey.getWaterTreatmentBeforeDrinking()) &&
              ObjectsCompat.equals(getMainReasonNoWaterTreatmentBeforeDrinking(), followUpSurvey.getMainReasonNoWaterTreatmentBeforeDrinking()) &&
              ObjectsCompat.equals(getWaterTreatmentMethod(), followUpSurvey.getWaterTreatmentMethod()) &&
              ObjectsCompat.equals(getHowLongUsingWaterTreatment(), followUpSurvey.getHowLongUsingWaterTreatment()) &&
              ObjectsCompat.equals(getFrequencyWaterTreatment(), followUpSurvey.getFrequencyWaterTreatment()) &&
              ObjectsCompat.equals(getWaterStorageAtHome(), followUpSurvey.getWaterStorageAtHome()) &&
              ObjectsCompat.equals(getTakingWaterFromStorage(), followUpSurvey.getTakingWaterFromStorage()) &&
              ObjectsCompat.equals(getWashedHandsIn24Hours(), followUpSurvey.getWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhenWashedHandsIn24Hours(), followUpSurvey.getWhenWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhatUsedToWashYourHands(), followUpSurvey.getWhatUsedToWashYourHands()) &&
              ObjectsCompat.equals(getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek(), followUpSurvey.getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek()) &&
              ObjectsCompat.equals(getCommonIllnessAffectingChildrenUnder5(), followUpSurvey.getCommonIllnessAffectingChildrenUnder5()) &&
              ObjectsCompat.equals(getNoChildrenWithVomitingOrDiarrheaIn7days(), followUpSurvey.getNoChildrenWithVomitingOrDiarrheaIn7days()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospital(), followUpSurvey.getDidSickChildrenGoToHospital()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospitalYes(), followUpSurvey.getDidSickChildrenGoToHospitalYes()) &&
              ObjectsCompat.equals(getSickChildrenBreastfeeding(), followUpSurvey.getSickChildrenBreastfeeding()) &&
              ObjectsCompat.equals(getOutcomeMostRecentVomitingDiarrheaAtHospital(), followUpSurvey.getOutcomeMostRecentVomitingDiarrheaAtHospital()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfOwnIllness(), followUpSurvey.getNoDaysNoWorkBecauseOfOwnIllness()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfIllnessFamilyMembers(), followUpSurvey.getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) &&
              ObjectsCompat.equals(getMoneySpentMedicalTreatmentLast4weeks(), followUpSurvey.getMoneySpentMedicalTreatmentLast4weeks()) &&
              ObjectsCompat.equals(getHealthChangeInAYear(), followUpSurvey.getHealthChangeInAYear()) &&
              ObjectsCompat.equals(getHealthChangeFamilyInAYear(), followUpSurvey.getHealthChangeFamilyInAYear()) &&
              ObjectsCompat.equals(getBenefitSwp(), followUpSurvey.getBenefitSwp());
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
      .append(getWaterTreatmentBeforeDrinking())
      .append(getMainReasonNoWaterTreatmentBeforeDrinking())
      .append(getWaterTreatmentMethod())
      .append(getHowLongUsingWaterTreatment())
      .append(getFrequencyWaterTreatment())
      .append(getWaterStorageAtHome())
      .append(getTakingWaterFromStorage())
      .append(getWashedHandsIn24Hours())
      .append(getWhenWashedHandsIn24Hours())
      .append(getWhatUsedToWashYourHands())
      .append(getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek())
      .append(getCommonIllnessAffectingChildrenUnder5())
      .append(getNoChildrenWithVomitingOrDiarrheaIn7days())
      .append(getDidSickChildrenGoToHospital())
      .append(getDidSickChildrenGoToHospitalYes())
      .append(getSickChildrenBreastfeeding())
      .append(getOutcomeMostRecentVomitingDiarrheaAtHospital())
      .append(getNoDaysNoWorkBecauseOfOwnIllness())
      .append(getNoDaysNoWorkBecauseOfIllnessFamilyMembers())
      .append(getMoneySpentMedicalTreatmentLast4weeks())
      .append(getHealthChangeInAYear())
      .append(getHealthChangeFamilyInAYear())
      .append(getBenefitSwp())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FollowUpSurvey {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("SurveyId=" + String.valueOf(getSurveyId()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("HeadHouseholdName=" + String.valueOf(getHeadHouseholdName()) + ", ")
      .append("PersonBeingInterviewed=" + String.valueOf(getPersonBeingInterviewed()) + ", ")
      .append("WaterTreatmentBeforeDrinking=" + String.valueOf(getWaterTreatmentBeforeDrinking()) + ", ")
      .append("MainReasonNoWaterTreatmentBeforeDrinking=" + String.valueOf(getMainReasonNoWaterTreatmentBeforeDrinking()) + ", ")
      .append("WaterTreatmentMethod=" + String.valueOf(getWaterTreatmentMethod()) + ", ")
      .append("HowLongUsingWaterTreatment=" + String.valueOf(getHowLongUsingWaterTreatment()) + ", ")
      .append("FrequencyWaterTreatment=" + String.valueOf(getFrequencyWaterTreatment()) + ", ")
      .append("WaterStorageAtHome=" + String.valueOf(getWaterStorageAtHome()) + ", ")
      .append("TakingWaterFromStorage=" + String.valueOf(getTakingWaterFromStorage()) + ", ")
      .append("WashedHandsIn24Hours=" + String.valueOf(getWashedHandsIn24Hours()) + ", ")
      .append("WhenWashedHandsIn24Hours=" + String.valueOf(getWhenWashedHandsIn24Hours()) + ", ")
      .append("WhatUsedToWashYourHands=" + String.valueOf(getWhatUsedToWashYourHands()) + ", ")
      .append("NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek=" + String.valueOf(getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek()) + ", ")
      .append("CommonIllnessAffectingChildrenUnder5=" + String.valueOf(getCommonIllnessAffectingChildrenUnder5()) + ", ")
      .append("NoChildrenWithVomitingOrDiarrheaIn7days=" + String.valueOf(getNoChildrenWithVomitingOrDiarrheaIn7days()) + ", ")
      .append("DidSickChildrenGoToHospital=" + String.valueOf(getDidSickChildrenGoToHospital()) + ", ")
      .append("DidSickChildrenGoToHospitalYes=" + String.valueOf(getDidSickChildrenGoToHospitalYes()) + ", ")
      .append("SickChildrenBreastfeeding=" + String.valueOf(getSickChildrenBreastfeeding()) + ", ")
      .append("OutcomeMostRecentVomiting_DiarrheaAtHospital=" + String.valueOf(getOutcomeMostRecentVomitingDiarrheaAtHospital()) + ", ")
      .append("NoDaysNoWorkBecauseOfOwnIllness=" + String.valueOf(getNoDaysNoWorkBecauseOfOwnIllness()) + ", ")
      .append("NoDaysNoWorkBecauseOfIllnessFamilyMembers=" + String.valueOf(getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) + ", ")
      .append("MoneySpentMedicalTreatmentLast4weeks=" + String.valueOf(getMoneySpentMedicalTreatmentLast4weeks()) + ", ")
      .append("HealthChangeInAYear=" + String.valueOf(getHealthChangeInAYear()) + ", ")
      .append("HealthChangeFamilyInAYear=" + String.valueOf(getHealthChangeFamilyInAYear()) + ", ")
      .append("BenefitSWP=" + String.valueOf(getBenefitSwp()))
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
  public static FollowUpSurvey justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new FollowUpSurvey(
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
      WaterTreatmentBeforeDrinking,
      MainReasonNoWaterTreatmentBeforeDrinking,
      WaterTreatmentMethod,
      HowLongUsingWaterTreatment,
      FrequencyWaterTreatment,
      WaterStorageAtHome,
      TakingWaterFromStorage,
      WashedHandsIn24Hours,
      WhenWashedHandsIn24Hours,
      WhatUsedToWashYourHands,
      NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek,
      CommonIllnessAffectingChildrenUnder5,
      NoChildrenWithVomitingOrDiarrheaIn7days,
      DidSickChildrenGoToHospital,
      DidSickChildrenGoToHospitalYes,
      SickChildrenBreastfeeding,
      OutcomeMostRecentVomiting_DiarrheaAtHospital,
      NoDaysNoWorkBecauseOfOwnIllness,
      NoDaysNoWorkBecauseOfIllnessFamilyMembers,
      MoneySpentMedicalTreatmentLast4weeks,
      HealthChangeInAYear,
      HealthChangeFamilyInAYear,
      BenefitSWP);
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
    WaterTreatmentBeforeDrinkingStep personBeingInterviewed(String personBeingInterviewed);
  }
  

  public interface WaterTreatmentBeforeDrinkingStep {
    MainReasonNoWaterTreatmentBeforeDrinkingStep waterTreatmentBeforeDrinking(String waterTreatmentBeforeDrinking);
  }
  

  public interface MainReasonNoWaterTreatmentBeforeDrinkingStep {
    WaterTreatmentMethodStep mainReasonNoWaterTreatmentBeforeDrinking(String mainReasonNoWaterTreatmentBeforeDrinking);
  }
  

  public interface WaterTreatmentMethodStep {
    HowLongUsingWaterTreatmentStep waterTreatmentMethod(String waterTreatmentMethod);
  }
  

  public interface HowLongUsingWaterTreatmentStep {
    FrequencyWaterTreatmentStep howLongUsingWaterTreatment(String howLongUsingWaterTreatment);
  }
  

  public interface FrequencyWaterTreatmentStep {
    WaterStorageAtHomeStep frequencyWaterTreatment(String frequencyWaterTreatment);
  }
  

  public interface WaterStorageAtHomeStep {
    TakingWaterFromStorageStep waterStorageAtHome(String waterStorageAtHome);
  }
  

  public interface TakingWaterFromStorageStep {
    WashedHandsIn24HoursStep takingWaterFromStorage(String takingWaterFromStorage);
  }
  

  public interface WashedHandsIn24HoursStep {
    WhenWashedHandsIn24HoursStep washedHandsIn24Hours(String washedHandsIn24Hours);
  }
  

  public interface WhenWashedHandsIn24HoursStep {
    WhatUsedToWashYourHandsStep whenWashedHandsIn24Hours(String whenWashedHandsIn24Hours);
  }
  

  public interface WhatUsedToWashYourHandsStep {
    NoTotalSchoolDaysMissedByAllChildrenIn2LastWeekStep whatUsedToWashYourHands(String whatUsedToWashYourHands);
  }
  

  public interface NoTotalSchoolDaysMissedByAllChildrenIn2LastWeekStep {
    CommonIllnessAffectingChildrenUnder5Step noTotalSchoolDaysMissedByAllChildrenIn2LastWeek(Integer noTotalSchoolDaysMissedByAllChildrenIn2LastWeek);
  }
  

  public interface CommonIllnessAffectingChildrenUnder5Step {
    NoChildrenWithVomitingOrDiarrheaIn7daysStep commonIllnessAffectingChildrenUnder5(String commonIllnessAffectingChildrenUnder5);
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
    HealthChangeInAYearStep moneySpentMedicalTreatmentLast4weeks(Integer moneySpentMedicalTreatmentLast4weeks);
  }
  

  public interface HealthChangeInAYearStep {
    HealthChangeFamilyInAYearStep healthChangeInAYear(String healthChangeInAYear);
  }
  

  public interface HealthChangeFamilyInAYearStep {
    BenefitSwpStep healthChangeFamilyInAYear(String healthChangeFamilyInAYear);
  }
  

  public interface BenefitSwpStep {
    BuildStep benefitSwp(String benefitSwp);
  }
  

  public interface BuildStep {
    FollowUpSurvey build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, CountryStep, CommunityStep, SurveyIdStep, HeadHouseholdNameStep, PersonBeingInterviewedStep, WaterTreatmentBeforeDrinkingStep, MainReasonNoWaterTreatmentBeforeDrinkingStep, WaterTreatmentMethodStep, HowLongUsingWaterTreatmentStep, FrequencyWaterTreatmentStep, WaterStorageAtHomeStep, TakingWaterFromStorageStep, WashedHandsIn24HoursStep, WhenWashedHandsIn24HoursStep, WhatUsedToWashYourHandsStep, NoTotalSchoolDaysMissedByAllChildrenIn2LastWeekStep, CommonIllnessAffectingChildrenUnder5Step, NoChildrenWithVomitingOrDiarrheaIn7daysStep, DidSickChildrenGoToHospitalStep, DidSickChildrenGoToHospitalYesStep, SickChildrenBreastfeedingStep, OutcomeMostRecentVomitingDiarrheaAtHospitalStep, NoDaysNoWorkBecauseOfOwnIllnessStep, NoDaysNoWorkBecauseOfIllnessFamilyMembersStep, MoneySpentMedicalTreatmentLast4weeksStep, HealthChangeInAYearStep, HealthChangeFamilyInAYearStep, BenefitSwpStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Country;
    private String Community;
    private Integer SurveyId;
    private String HeadHouseholdName;
    private String PersonBeingInterviewed;
    private String WaterTreatmentBeforeDrinking;
    private String MainReasonNoWaterTreatmentBeforeDrinking;
    private String WaterTreatmentMethod;
    private String HowLongUsingWaterTreatment;
    private String FrequencyWaterTreatment;
    private String WaterStorageAtHome;
    private String TakingWaterFromStorage;
    private String WashedHandsIn24Hours;
    private String WhenWashedHandsIn24Hours;
    private String WhatUsedToWashYourHands;
    private Integer NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek;
    private String CommonIllnessAffectingChildrenUnder5;
    private Integer NoChildrenWithVomitingOrDiarrheaIn7days;
    private String DidSickChildrenGoToHospital;
    private String DidSickChildrenGoToHospitalYes;
    private String SickChildrenBreastfeeding;
    private String OutcomeMostRecentVomiting_DiarrheaAtHospital;
    private Integer NoDaysNoWorkBecauseOfOwnIllness;
    private Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers;
    private Integer MoneySpentMedicalTreatmentLast4weeks;
    private String HealthChangeInAYear;
    private String HealthChangeFamilyInAYear;
    private String BenefitSWP;
    private Temporal.Date date;
    @Override
     public FollowUpSurvey build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FollowUpSurvey(
          id,
          Namebwe,
          Country,
          Community,
          SurveyId,
          date,
          HeadHouseholdName,
          PersonBeingInterviewed,
          WaterTreatmentBeforeDrinking,
          MainReasonNoWaterTreatmentBeforeDrinking,
          WaterTreatmentMethod,
          HowLongUsingWaterTreatment,
          FrequencyWaterTreatment,
          WaterStorageAtHome,
          TakingWaterFromStorage,
          WashedHandsIn24Hours,
          WhenWashedHandsIn24Hours,
          WhatUsedToWashYourHands,
          NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek,
          CommonIllnessAffectingChildrenUnder5,
          NoChildrenWithVomitingOrDiarrheaIn7days,
          DidSickChildrenGoToHospital,
          DidSickChildrenGoToHospitalYes,
          SickChildrenBreastfeeding,
          OutcomeMostRecentVomiting_DiarrheaAtHospital,
          NoDaysNoWorkBecauseOfOwnIllness,
          NoDaysNoWorkBecauseOfIllnessFamilyMembers,
          MoneySpentMedicalTreatmentLast4weeks,
          HealthChangeInAYear,
          HealthChangeFamilyInAYear,
          BenefitSWP);
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
     public WaterTreatmentBeforeDrinkingStep personBeingInterviewed(String personBeingInterviewed) {
        Objects.requireNonNull(personBeingInterviewed);
        this.PersonBeingInterviewed = personBeingInterviewed;
        return this;
    }
    
    @Override
     public MainReasonNoWaterTreatmentBeforeDrinkingStep waterTreatmentBeforeDrinking(String waterTreatmentBeforeDrinking) {
        Objects.requireNonNull(waterTreatmentBeforeDrinking);
        this.WaterTreatmentBeforeDrinking = waterTreatmentBeforeDrinking;
        return this;
    }
    
    @Override
     public WaterTreatmentMethodStep mainReasonNoWaterTreatmentBeforeDrinking(String mainReasonNoWaterTreatmentBeforeDrinking) {
        Objects.requireNonNull(mainReasonNoWaterTreatmentBeforeDrinking);
        this.MainReasonNoWaterTreatmentBeforeDrinking = mainReasonNoWaterTreatmentBeforeDrinking;
        return this;
    }
    
    @Override
     public HowLongUsingWaterTreatmentStep waterTreatmentMethod(String waterTreatmentMethod) {
        Objects.requireNonNull(waterTreatmentMethod);
        this.WaterTreatmentMethod = waterTreatmentMethod;
        return this;
    }
    
    @Override
     public FrequencyWaterTreatmentStep howLongUsingWaterTreatment(String howLongUsingWaterTreatment) {
        Objects.requireNonNull(howLongUsingWaterTreatment);
        this.HowLongUsingWaterTreatment = howLongUsingWaterTreatment;
        return this;
    }
    
    @Override
     public WaterStorageAtHomeStep frequencyWaterTreatment(String frequencyWaterTreatment) {
        Objects.requireNonNull(frequencyWaterTreatment);
        this.FrequencyWaterTreatment = frequencyWaterTreatment;
        return this;
    }
    
    @Override
     public TakingWaterFromStorageStep waterStorageAtHome(String waterStorageAtHome) {
        Objects.requireNonNull(waterStorageAtHome);
        this.WaterStorageAtHome = waterStorageAtHome;
        return this;
    }
    
    @Override
     public WashedHandsIn24HoursStep takingWaterFromStorage(String takingWaterFromStorage) {
        Objects.requireNonNull(takingWaterFromStorage);
        this.TakingWaterFromStorage = takingWaterFromStorage;
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
     public NoTotalSchoolDaysMissedByAllChildrenIn2LastWeekStep whatUsedToWashYourHands(String whatUsedToWashYourHands) {
        Objects.requireNonNull(whatUsedToWashYourHands);
        this.WhatUsedToWashYourHands = whatUsedToWashYourHands;
        return this;
    }
    
    @Override
     public CommonIllnessAffectingChildrenUnder5Step noTotalSchoolDaysMissedByAllChildrenIn2LastWeek(Integer noTotalSchoolDaysMissedByAllChildrenIn2LastWeek) {
        Objects.requireNonNull(noTotalSchoolDaysMissedByAllChildrenIn2LastWeek);
        this.NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek = noTotalSchoolDaysMissedByAllChildrenIn2LastWeek;
        return this;
    }
    
    @Override
     public NoChildrenWithVomitingOrDiarrheaIn7daysStep commonIllnessAffectingChildrenUnder5(String commonIllnessAffectingChildrenUnder5) {
        Objects.requireNonNull(commonIllnessAffectingChildrenUnder5);
        this.CommonIllnessAffectingChildrenUnder5 = commonIllnessAffectingChildrenUnder5;
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
     public HealthChangeInAYearStep moneySpentMedicalTreatmentLast4weeks(Integer moneySpentMedicalTreatmentLast4weeks) {
        Objects.requireNonNull(moneySpentMedicalTreatmentLast4weeks);
        this.MoneySpentMedicalTreatmentLast4weeks = moneySpentMedicalTreatmentLast4weeks;
        return this;
    }
    
    @Override
     public HealthChangeFamilyInAYearStep healthChangeInAYear(String healthChangeInAYear) {
        Objects.requireNonNull(healthChangeInAYear);
        this.HealthChangeInAYear = healthChangeInAYear;
        return this;
    }
    
    @Override
     public BenefitSwpStep healthChangeFamilyInAYear(String healthChangeFamilyInAYear) {
        Objects.requireNonNull(healthChangeFamilyInAYear);
        this.HealthChangeFamilyInAYear = healthChangeFamilyInAYear;
        return this;
    }
    
    @Override
     public BuildStep benefitSwp(String benefitSwp) {
        Objects.requireNonNull(benefitSwp);
        this.BenefitSWP = benefitSwp;
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
    private CopyOfBuilder(String id, String namebwe, String country, String community, Integer surveyId, Temporal.Date date, String headHouseholdName, String personBeingInterviewed, String waterTreatmentBeforeDrinking, String mainReasonNoWaterTreatmentBeforeDrinking, String waterTreatmentMethod, String howLongUsingWaterTreatment, String frequencyWaterTreatment, String waterStorageAtHome, String takingWaterFromStorage, String washedHandsIn24Hours, String whenWashedHandsIn24Hours, String whatUsedToWashYourHands, Integer noTotalSchoolDaysMissedByAllChildrenIn2LastWeek, String commonIllnessAffectingChildrenUnder5, Integer noChildrenWithVomitingOrDiarrheaIn7days, String didSickChildrenGoToHospital, String didSickChildrenGoToHospitalYes, String sickChildrenBreastfeeding, String outcomeMostRecentVomitingDiarrheaAtHospital, Integer noDaysNoWorkBecauseOfOwnIllness, Integer noDaysNoWorkBecauseOfIllnessFamilyMembers, Integer moneySpentMedicalTreatmentLast4weeks, String healthChangeInAYear, String healthChangeFamilyInAYear, String benefitSwp) {
      super.id(id);
      super.namebwe(namebwe)
        .country(country)
        .community(community)
        .surveyId(surveyId)
        .headHouseholdName(headHouseholdName)
        .personBeingInterviewed(personBeingInterviewed)
        .waterTreatmentBeforeDrinking(waterTreatmentBeforeDrinking)
        .mainReasonNoWaterTreatmentBeforeDrinking(mainReasonNoWaterTreatmentBeforeDrinking)
        .waterTreatmentMethod(waterTreatmentMethod)
        .howLongUsingWaterTreatment(howLongUsingWaterTreatment)
        .frequencyWaterTreatment(frequencyWaterTreatment)
        .waterStorageAtHome(waterStorageAtHome)
        .takingWaterFromStorage(takingWaterFromStorage)
        .washedHandsIn24Hours(washedHandsIn24Hours)
        .whenWashedHandsIn24Hours(whenWashedHandsIn24Hours)
        .whatUsedToWashYourHands(whatUsedToWashYourHands)
        .noTotalSchoolDaysMissedByAllChildrenIn2LastWeek(noTotalSchoolDaysMissedByAllChildrenIn2LastWeek)
        .commonIllnessAffectingChildrenUnder5(commonIllnessAffectingChildrenUnder5)
        .noChildrenWithVomitingOrDiarrheaIn7days(noChildrenWithVomitingOrDiarrheaIn7days)
        .didSickChildrenGoToHospital(didSickChildrenGoToHospital)
        .didSickChildrenGoToHospitalYes(didSickChildrenGoToHospitalYes)
        .sickChildrenBreastfeeding(sickChildrenBreastfeeding)
        .outcomeMostRecentVomitingDiarrheaAtHospital(outcomeMostRecentVomitingDiarrheaAtHospital)
        .noDaysNoWorkBecauseOfOwnIllness(noDaysNoWorkBecauseOfOwnIllness)
        .noDaysNoWorkBecauseOfIllnessFamilyMembers(noDaysNoWorkBecauseOfIllnessFamilyMembers)
        .moneySpentMedicalTreatmentLast4weeks(moneySpentMedicalTreatmentLast4weeks)
        .healthChangeInAYear(healthChangeInAYear)
        .healthChangeFamilyInAYear(healthChangeFamilyInAYear)
        .benefitSwp(benefitSwp)
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
     public CopyOfBuilder waterTreatmentBeforeDrinking(String waterTreatmentBeforeDrinking) {
      return (CopyOfBuilder) super.waterTreatmentBeforeDrinking(waterTreatmentBeforeDrinking);
    }
    
    @Override
     public CopyOfBuilder mainReasonNoWaterTreatmentBeforeDrinking(String mainReasonNoWaterTreatmentBeforeDrinking) {
      return (CopyOfBuilder) super.mainReasonNoWaterTreatmentBeforeDrinking(mainReasonNoWaterTreatmentBeforeDrinking);
    }
    
    @Override
     public CopyOfBuilder waterTreatmentMethod(String waterTreatmentMethod) {
      return (CopyOfBuilder) super.waterTreatmentMethod(waterTreatmentMethod);
    }
    
    @Override
     public CopyOfBuilder howLongUsingWaterTreatment(String howLongUsingWaterTreatment) {
      return (CopyOfBuilder) super.howLongUsingWaterTreatment(howLongUsingWaterTreatment);
    }
    
    @Override
     public CopyOfBuilder frequencyWaterTreatment(String frequencyWaterTreatment) {
      return (CopyOfBuilder) super.frequencyWaterTreatment(frequencyWaterTreatment);
    }
    
    @Override
     public CopyOfBuilder waterStorageAtHome(String waterStorageAtHome) {
      return (CopyOfBuilder) super.waterStorageAtHome(waterStorageAtHome);
    }
    
    @Override
     public CopyOfBuilder takingWaterFromStorage(String takingWaterFromStorage) {
      return (CopyOfBuilder) super.takingWaterFromStorage(takingWaterFromStorage);
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
     public CopyOfBuilder noTotalSchoolDaysMissedByAllChildrenIn2LastWeek(Integer noTotalSchoolDaysMissedByAllChildrenIn2LastWeek) {
      return (CopyOfBuilder) super.noTotalSchoolDaysMissedByAllChildrenIn2LastWeek(noTotalSchoolDaysMissedByAllChildrenIn2LastWeek);
    }
    
    @Override
     public CopyOfBuilder commonIllnessAffectingChildrenUnder5(String commonIllnessAffectingChildrenUnder5) {
      return (CopyOfBuilder) super.commonIllnessAffectingChildrenUnder5(commonIllnessAffectingChildrenUnder5);
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
     public CopyOfBuilder healthChangeInAYear(String healthChangeInAYear) {
      return (CopyOfBuilder) super.healthChangeInAYear(healthChangeInAYear);
    }
    
    @Override
     public CopyOfBuilder healthChangeFamilyInAYear(String healthChangeFamilyInAYear) {
      return (CopyOfBuilder) super.healthChangeFamilyInAYear(healthChangeFamilyInAYear);
    }
    
    @Override
     public CopyOfBuilder benefitSwp(String benefitSwp) {
      return (CopyOfBuilder) super.benefitSwp(benefitSwp);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}