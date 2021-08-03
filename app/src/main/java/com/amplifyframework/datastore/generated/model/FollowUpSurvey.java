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

/** This is an auto generated class representing the FollowUpSurvey type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FollowUpSurveys", authRules = {
        @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
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
  public static final QueryField FREQUENCY_WATER_TREATMENT = field("FrequencyWaterTreatment");
  public static final QueryField LAST_TIME_TREATED_HOUSEHOLD_WATER_WITH_CHLORINE = field("LastTimeTreatedHouseholdWaterWithChlorine");
  public static final QueryField WHERE_DID_YOU_GET_CHLORINE_TO_TREAT_HOUSEHOLD_WATER = field("WhereDidYouGetChlorineToTreatHouseholdWater");
  public static final QueryField AMOUNT_SPEND_PER_WEEK_FOR_CHLORINE_TO_TREAT_WATER = field("AmountSpendPerWeekForChlorineToTreatWater");
  public static final QueryField HOW_DIFFICULT_TO_OBTAIN_CHLORINE = field("HowDifficultToObtainChlorine");
  public static final QueryField TAKING_WATER_FROM_STORAGE = field("TakingWaterFromStorage");
  public static final QueryField WHEN_WASHED_HANDS_IN24_HOURS = field("WhenWashedHandsIn24Hours");
  public static final QueryField WHAT_USED_TO_WASH_YOUR_HANDS = field("WhatUsedToWashYourHands");
  public static final QueryField COMMON_ILLNESS_AFFECTING_ALL_CHILDREN_IN_HOUSEHOLD = field("CommonIllnessAffectingAllChildrenInHousehold");
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
  public static final QueryField HEALTH_CHANGE_IN_A_YEAR = field("HealthChangeInAYear");
  public static final QueryField HEALTH_CHANGE_FAMILY_IN_A_YEAR = field("HealthChangeFamilyInAYear");
  public static final QueryField BENEFIT_SWP = field("BenefitSWP");
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
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatmentBeforeDrinking;
  private final @ModelField(targetType="String", isRequired = true) String MainReasonNoWaterTreatmentBeforeDrinking;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatmentMethod;
  private final @ModelField(targetType="String", isRequired = true) String FrequencyWaterTreatment;
  private final @ModelField(targetType="String", isRequired = true) String LastTimeTreatedHouseholdWaterWithChlorine;
  private final @ModelField(targetType="String", isRequired = true) String WhereDidYouGetChlorineToTreatHouseholdWater;
  private final @ModelField(targetType="Int", isRequired = true) Integer AmountSpendPerWeekForChlorineToTreatWater;
  private final @ModelField(targetType="String", isRequired = true) String HowDifficultToObtainChlorine;
  private final @ModelField(targetType="String", isRequired = true) String TakingWaterFromStorage;
  private final @ModelField(targetType="String", isRequired = true) String WhenWashedHandsIn24Hours;
  private final @ModelField(targetType="String", isRequired = true) String WhatUsedToWashYourHands;
  private final @ModelField(targetType="String", isRequired = true) String CommonIllnessAffectingAllChildrenInHousehold;
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
  private final @ModelField(targetType="String", isRequired = true) String HealthChangeInAYear;
  private final @ModelField(targetType="String", isRequired = true) String HealthChangeFamilyInAYear;
  private final @ModelField(targetType="String", isRequired = true) String BenefitSWP;
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

  public String getWaterTreatmentBeforeDrinking() {
    return WaterTreatmentBeforeDrinking;
  }

  public String getMainReasonNoWaterTreatmentBeforeDrinking() {
    return MainReasonNoWaterTreatmentBeforeDrinking;
  }

  public String getWaterTreatmentMethod() {
    return WaterTreatmentMethod;
  }

  public String getFrequencyWaterTreatment() {
    return FrequencyWaterTreatment;
  }

  public String getLastTimeTreatedHouseholdWaterWithChlorine() {
    return LastTimeTreatedHouseholdWaterWithChlorine;
  }

  public String getWhereDidYouGetChlorineToTreatHouseholdWater() {
    return WhereDidYouGetChlorineToTreatHouseholdWater;
  }

  public Integer getAmountSpendPerWeekForChlorineToTreatWater() {
    return AmountSpendPerWeekForChlorineToTreatWater;
  }

  public String getHowDifficultToObtainChlorine() {
    return HowDifficultToObtainChlorine;
  }

  public String getTakingWaterFromStorage() {
    return TakingWaterFromStorage;
  }

  public String getWhenWashedHandsIn24Hours() {
    return WhenWashedHandsIn24Hours;
  }

  public String getWhatUsedToWashYourHands() {
    return WhatUsedToWashYourHands;
  }

  public String getCommonIllnessAffectingAllChildrenInHousehold() {
    return CommonIllnessAffectingAllChildrenInHousehold;
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

  public String getOutcomeMostRecentVomiting_DiarrheaAtHospital() {
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

  public String getBenefitSWP() {
    return BenefitSWP;
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

  private FollowUpSurvey(String id, String Namebwe, String Country, String Community, Integer SurveyId, Temporal.Date date, String HeadHouseholdName, String PersonBeingInterviewed, String WaterTreatmentBeforeDrinking, String MainReasonNoWaterTreatmentBeforeDrinking, String WaterTreatmentMethod, String FrequencyWaterTreatment, String LastTimeTreatedHouseholdWaterWithChlorine, String WhereDidYouGetChlorineToTreatHouseholdWater, Integer AmountSpendPerWeekForChlorineToTreatWater, String HowDifficultToObtainChlorine, String TakingWaterFromStorage, String WhenWashedHandsIn24Hours, String WhatUsedToWashYourHands, String CommonIllnessAffectingAllChildrenInHousehold, Integer NoChildrenWithVomitingOrDiarrheaIn14days, Integer NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek, Integer NoChildrenWithVomitingOrDiarrheaIn7days, String DidSickChildrenGoToHospital, String DidSickChildrenGoToHospitalYes, String SickChildrenBreastfeeding, String OutcomeMostRecentVomiting_DiarrheaAtHospital, Integer NoDaysNoWorkBecauseOfOwnIllness, Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers, Integer MoneySpentMedicalTreatmentLast4weeks, String HealthChangeInAYear, String HealthChangeFamilyInAYear, String BenefitSWP, Integer Completed, String Lat, String Lng) {
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
    this.FrequencyWaterTreatment = FrequencyWaterTreatment;
    this.LastTimeTreatedHouseholdWaterWithChlorine = LastTimeTreatedHouseholdWaterWithChlorine;
    this.WhereDidYouGetChlorineToTreatHouseholdWater = WhereDidYouGetChlorineToTreatHouseholdWater;
    this.AmountSpendPerWeekForChlorineToTreatWater = AmountSpendPerWeekForChlorineToTreatWater;
    this.HowDifficultToObtainChlorine = HowDifficultToObtainChlorine;
    this.TakingWaterFromStorage = TakingWaterFromStorage;
    this.WhenWashedHandsIn24Hours = WhenWashedHandsIn24Hours;
    this.WhatUsedToWashYourHands = WhatUsedToWashYourHands;
    this.CommonIllnessAffectingAllChildrenInHousehold = CommonIllnessAffectingAllChildrenInHousehold;
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
    this.HealthChangeInAYear = HealthChangeInAYear;
    this.HealthChangeFamilyInAYear = HealthChangeFamilyInAYear;
    this.BenefitSWP = BenefitSWP;
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
              ObjectsCompat.equals(getFrequencyWaterTreatment(), followUpSurvey.getFrequencyWaterTreatment()) &&
              ObjectsCompat.equals(getLastTimeTreatedHouseholdWaterWithChlorine(), followUpSurvey.getLastTimeTreatedHouseholdWaterWithChlorine()) &&
              ObjectsCompat.equals(getWhereDidYouGetChlorineToTreatHouseholdWater(), followUpSurvey.getWhereDidYouGetChlorineToTreatHouseholdWater()) &&
              ObjectsCompat.equals(getAmountSpendPerWeekForChlorineToTreatWater(), followUpSurvey.getAmountSpendPerWeekForChlorineToTreatWater()) &&
              ObjectsCompat.equals(getHowDifficultToObtainChlorine(), followUpSurvey.getHowDifficultToObtainChlorine()) &&
              ObjectsCompat.equals(getTakingWaterFromStorage(), followUpSurvey.getTakingWaterFromStorage()) &&
              ObjectsCompat.equals(getWhenWashedHandsIn24Hours(), followUpSurvey.getWhenWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhatUsedToWashYourHands(), followUpSurvey.getWhatUsedToWashYourHands()) &&
              ObjectsCompat.equals(getCommonIllnessAffectingAllChildrenInHousehold(), followUpSurvey.getCommonIllnessAffectingAllChildrenInHousehold()) &&
              ObjectsCompat.equals(getNoChildrenWithVomitingOrDiarrheaIn14days(), followUpSurvey.getNoChildrenWithVomitingOrDiarrheaIn14days()) &&
              ObjectsCompat.equals(getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek(), followUpSurvey.getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek()) &&
              ObjectsCompat.equals(getNoChildrenWithVomitingOrDiarrheaIn7days(), followUpSurvey.getNoChildrenWithVomitingOrDiarrheaIn7days()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospital(), followUpSurvey.getDidSickChildrenGoToHospital()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospitalYes(), followUpSurvey.getDidSickChildrenGoToHospitalYes()) &&
              ObjectsCompat.equals(getSickChildrenBreastfeeding(), followUpSurvey.getSickChildrenBreastfeeding()) &&
              ObjectsCompat.equals(getOutcomeMostRecentVomiting_DiarrheaAtHospital(), followUpSurvey.getOutcomeMostRecentVomiting_DiarrheaAtHospital()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfOwnIllness(), followUpSurvey.getNoDaysNoWorkBecauseOfOwnIllness()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfIllnessFamilyMembers(), followUpSurvey.getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) &&
              ObjectsCompat.equals(getMoneySpentMedicalTreatmentLast4weeks(), followUpSurvey.getMoneySpentMedicalTreatmentLast4weeks()) &&
              ObjectsCompat.equals(getHealthChangeInAYear(), followUpSurvey.getHealthChangeInAYear()) &&
              ObjectsCompat.equals(getHealthChangeFamilyInAYear(), followUpSurvey.getHealthChangeFamilyInAYear()) &&
              ObjectsCompat.equals(getBenefitSWP(), followUpSurvey.getBenefitSWP()) &&
              ObjectsCompat.equals(getCompleted(), followUpSurvey.getCompleted()) &&
              ObjectsCompat.equals(getLat(), followUpSurvey.getLat()) &&
              ObjectsCompat.equals(getLng(), followUpSurvey.getLng());
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
            .append(getFrequencyWaterTreatment())
            .append(getLastTimeTreatedHouseholdWaterWithChlorine())
            .append(getWhereDidYouGetChlorineToTreatHouseholdWater())
            .append(getAmountSpendPerWeekForChlorineToTreatWater())
            .append(getHowDifficultToObtainChlorine())
            .append(getTakingWaterFromStorage())
            .append(getWhenWashedHandsIn24Hours())
            .append(getWhatUsedToWashYourHands())
            .append(getCommonIllnessAffectingAllChildrenInHousehold())
            .append(getNoChildrenWithVomitingOrDiarrheaIn14days())
            .append(getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek())
            .append(getNoChildrenWithVomitingOrDiarrheaIn7days())
            .append(getDidSickChildrenGoToHospital())
            .append(getDidSickChildrenGoToHospitalYes())
            .append(getSickChildrenBreastfeeding())
            .append(getOutcomeMostRecentVomiting_DiarrheaAtHospital())
            .append(getNoDaysNoWorkBecauseOfOwnIllness())
            .append(getNoDaysNoWorkBecauseOfIllnessFamilyMembers())
            .append(getMoneySpentMedicalTreatmentLast4weeks())
            .append(getHealthChangeInAYear())
            .append(getHealthChangeFamilyInAYear())
            .append(getBenefitSWP())
            .append(getCompleted())
            .append(getLat())
            .append(getLng())
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
            .append("FrequencyWaterTreatment=" + String.valueOf(getFrequencyWaterTreatment()) + ", ")
            .append("LastTimeTreatedHouseholdWaterWithChlorine=" + String.valueOf(getLastTimeTreatedHouseholdWaterWithChlorine()) + ", ")
            .append("WhereDidYouGetChlorineToTreatHouseholdWater=" + String.valueOf(getWhereDidYouGetChlorineToTreatHouseholdWater()) + ", ")
            .append("AmountSpendPerWeekForChlorineToTreatWater=" + String.valueOf(getAmountSpendPerWeekForChlorineToTreatWater()) + ", ")
            .append("HowDifficultToObtainChlorine=" + String.valueOf(getHowDifficultToObtainChlorine()) + ", ")
            .append("TakingWaterFromStorage=" + String.valueOf(getTakingWaterFromStorage()) + ", ")
            .append("WhenWashedHandsIn24Hours=" + String.valueOf(getWhenWashedHandsIn24Hours()) + ", ")
            .append("WhatUsedToWashYourHands=" + String.valueOf(getWhatUsedToWashYourHands()) + ", ")
            .append("CommonIllnessAffectingAllChildrenInHousehold=" + String.valueOf(getCommonIllnessAffectingAllChildrenInHousehold()) + ", ")
            .append("NoChildrenWithVomitingOrDiarrheaIn14days=" + String.valueOf(getNoChildrenWithVomitingOrDiarrheaIn14days()) + ", ")
            .append("NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek=" + String.valueOf(getNoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek()) + ", ")
            .append("NoChildrenWithVomitingOrDiarrheaIn7days=" + String.valueOf(getNoChildrenWithVomitingOrDiarrheaIn7days()) + ", ")
            .append("DidSickChildrenGoToHospital=" + String.valueOf(getDidSickChildrenGoToHospital()) + ", ")
            .append("DidSickChildrenGoToHospitalYes=" + String.valueOf(getDidSickChildrenGoToHospitalYes()) + ", ")
            .append("SickChildrenBreastfeeding=" + String.valueOf(getSickChildrenBreastfeeding()) + ", ")
            .append("OutcomeMostRecentVomiting_DiarrheaAtHospital=" + String.valueOf(getOutcomeMostRecentVomiting_DiarrheaAtHospital()) + ", ")
            .append("NoDaysNoWorkBecauseOfOwnIllness=" + String.valueOf(getNoDaysNoWorkBecauseOfOwnIllness()) + ", ")
            .append("NoDaysNoWorkBecauseOfIllnessFamilyMembers=" + String.valueOf(getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) + ", ")
            .append("MoneySpentMedicalTreatmentLast4weeks=" + String.valueOf(getMoneySpentMedicalTreatmentLast4weeks()) + ", ")
            .append("HealthChangeInAYear=" + String.valueOf(getHealthChangeInAYear()) + ", ")
            .append("HealthChangeFamilyInAYear=" + String.valueOf(getHealthChangeFamilyInAYear()) + ", ")
            .append("BenefitSWP=" + String.valueOf(getBenefitSWP()) + ", ")
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
            FrequencyWaterTreatment,
            LastTimeTreatedHouseholdWaterWithChlorine,
            WhereDidYouGetChlorineToTreatHouseholdWater,
            AmountSpendPerWeekForChlorineToTreatWater,
            HowDifficultToObtainChlorine,
            TakingWaterFromStorage,
            WhenWashedHandsIn24Hours,
            WhatUsedToWashYourHands,
            CommonIllnessAffectingAllChildrenInHousehold,
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
            HealthChangeInAYear,
            HealthChangeFamilyInAYear,
            BenefitSWP,
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
    WaterTreatmentBeforeDrinkingStep personBeingInterviewed(String personBeingInterviewed);
  }


  public interface WaterTreatmentBeforeDrinkingStep {
    MainReasonNoWaterTreatmentBeforeDrinkingStep waterTreatmentBeforeDrinking(String waterTreatmentBeforeDrinking);
  }


  public interface MainReasonNoWaterTreatmentBeforeDrinkingStep {
    WaterTreatmentMethodStep mainReasonNoWaterTreatmentBeforeDrinking(String mainReasonNoWaterTreatmentBeforeDrinking);
  }


  public interface WaterTreatmentMethodStep {
    FrequencyWaterTreatmentStep waterTreatmentMethod(String waterTreatmentMethod);
  }


  public interface FrequencyWaterTreatmentStep {
    LastTimeTreatedHouseholdWaterWithChlorineStep frequencyWaterTreatment(String frequencyWaterTreatment);
  }


  public interface LastTimeTreatedHouseholdWaterWithChlorineStep {
    WhereDidYouGetChlorineToTreatHouseholdWaterStep lastTimeTreatedHouseholdWaterWithChlorine(String lastTimeTreatedHouseholdWaterWithChlorine);
  }


  public interface WhereDidYouGetChlorineToTreatHouseholdWaterStep {
    AmountSpendPerWeekForChlorineToTreatWaterStep whereDidYouGetChlorineToTreatHouseholdWater(String whereDidYouGetChlorineToTreatHouseholdWater);
  }


  public interface AmountSpendPerWeekForChlorineToTreatWaterStep {
    HowDifficultToObtainChlorineStep amountSpendPerWeekForChlorineToTreatWater(Integer amountSpendPerWeekForChlorineToTreatWater);
  }


  public interface HowDifficultToObtainChlorineStep {
    TakingWaterFromStorageStep howDifficultToObtainChlorine(String howDifficultToObtainChlorine);
  }


  public interface TakingWaterFromStorageStep {
    WhenWashedHandsIn24HoursStep takingWaterFromStorage(String takingWaterFromStorage);
  }


  public interface WhenWashedHandsIn24HoursStep {
    WhatUsedToWashYourHandsStep whenWashedHandsIn24Hours(String whenWashedHandsIn24Hours);
  }


  public interface WhatUsedToWashYourHandsStep {
    CommonIllnessAffectingAllChildrenInHouseholdStep whatUsedToWashYourHands(String whatUsedToWashYourHands);
  }


  public interface CommonIllnessAffectingAllChildrenInHouseholdStep {
    NoChildrenWithVomitingOrDiarrheaIn14daysStep commonIllnessAffectingAllChildrenInHousehold(String commonIllnessAffectingAllChildrenInHousehold);
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
    HealthChangeInAYearStep moneySpentMedicalTreatmentLast4weeks(Integer moneySpentMedicalTreatmentLast4weeks);
  }


  public interface HealthChangeInAYearStep {
    HealthChangeFamilyInAYearStep healthChangeInAYear(String healthChangeInAYear);
  }


  public interface HealthChangeFamilyInAYearStep {
    BenefitSwpStep healthChangeFamilyInAYear(String healthChangeFamilyInAYear);
  }


  public interface BenefitSwpStep {
    CompletedStep benefitSwp(String benefitSwp);
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
    FollowUpSurvey build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }


  public static class Builder implements NamebweStep, CountryStep, CommunityStep, SurveyIdStep, HeadHouseholdNameStep, PersonBeingInterviewedStep, WaterTreatmentBeforeDrinkingStep, MainReasonNoWaterTreatmentBeforeDrinkingStep, WaterTreatmentMethodStep, FrequencyWaterTreatmentStep, LastTimeTreatedHouseholdWaterWithChlorineStep, WhereDidYouGetChlorineToTreatHouseholdWaterStep, AmountSpendPerWeekForChlorineToTreatWaterStep, HowDifficultToObtainChlorineStep, TakingWaterFromStorageStep, WhenWashedHandsIn24HoursStep, WhatUsedToWashYourHandsStep, CommonIllnessAffectingAllChildrenInHouseholdStep, NoChildrenWithVomitingOrDiarrheaIn14daysStep, NoTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeekStep, NoChildrenWithVomitingOrDiarrheaIn7daysStep, DidSickChildrenGoToHospitalStep, DidSickChildrenGoToHospitalYesStep, SickChildrenBreastfeedingStep, OutcomeMostRecentVomitingDiarrheaAtHospitalStep, NoDaysNoWorkBecauseOfOwnIllnessStep, NoDaysNoWorkBecauseOfIllnessFamilyMembersStep, MoneySpentMedicalTreatmentLast4weeksStep, HealthChangeInAYearStep, HealthChangeFamilyInAYearStep, BenefitSwpStep, CompletedStep, LatStep, LngStep, BuildStep {
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
    private String FrequencyWaterTreatment;
    private String LastTimeTreatedHouseholdWaterWithChlorine;
    private String WhereDidYouGetChlorineToTreatHouseholdWater;
    private Integer AmountSpendPerWeekForChlorineToTreatWater;
    private String HowDifficultToObtainChlorine;
    private String TakingWaterFromStorage;
    private String WhenWashedHandsIn24Hours;
    private String WhatUsedToWashYourHands;
    private String CommonIllnessAffectingAllChildrenInHousehold;
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
    private String HealthChangeInAYear;
    private String HealthChangeFamilyInAYear;
    private String BenefitSWP;
    private Integer Completed;
    private String Lat;
    private String Lng;
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
              FrequencyWaterTreatment,
              LastTimeTreatedHouseholdWaterWithChlorine,
              WhereDidYouGetChlorineToTreatHouseholdWater,
              AmountSpendPerWeekForChlorineToTreatWater,
              HowDifficultToObtainChlorine,
              TakingWaterFromStorage,
              WhenWashedHandsIn24Hours,
              WhatUsedToWashYourHands,
              CommonIllnessAffectingAllChildrenInHousehold,
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
              HealthChangeInAYear,
              HealthChangeFamilyInAYear,
              BenefitSWP,
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
    public FrequencyWaterTreatmentStep waterTreatmentMethod(String waterTreatmentMethod) {
      Objects.requireNonNull(waterTreatmentMethod);
      this.WaterTreatmentMethod = waterTreatmentMethod;
      return this;
    }

    @Override
    public LastTimeTreatedHouseholdWaterWithChlorineStep frequencyWaterTreatment(String frequencyWaterTreatment) {
      Objects.requireNonNull(frequencyWaterTreatment);
      this.FrequencyWaterTreatment = frequencyWaterTreatment;
      return this;
    }

    @Override
    public WhereDidYouGetChlorineToTreatHouseholdWaterStep lastTimeTreatedHouseholdWaterWithChlorine(String lastTimeTreatedHouseholdWaterWithChlorine) {
      Objects.requireNonNull(lastTimeTreatedHouseholdWaterWithChlorine);
      this.LastTimeTreatedHouseholdWaterWithChlorine = lastTimeTreatedHouseholdWaterWithChlorine;
      return this;
    }

    @Override
    public AmountSpendPerWeekForChlorineToTreatWaterStep whereDidYouGetChlorineToTreatHouseholdWater(String whereDidYouGetChlorineToTreatHouseholdWater) {
      Objects.requireNonNull(whereDidYouGetChlorineToTreatHouseholdWater);
      this.WhereDidYouGetChlorineToTreatHouseholdWater = whereDidYouGetChlorineToTreatHouseholdWater;
      return this;
    }

    @Override
    public HowDifficultToObtainChlorineStep amountSpendPerWeekForChlorineToTreatWater(Integer amountSpendPerWeekForChlorineToTreatWater) {
      Objects.requireNonNull(amountSpendPerWeekForChlorineToTreatWater);
      this.AmountSpendPerWeekForChlorineToTreatWater = amountSpendPerWeekForChlorineToTreatWater;
      return this;
    }

    @Override
    public TakingWaterFromStorageStep howDifficultToObtainChlorine(String howDifficultToObtainChlorine) {
      Objects.requireNonNull(howDifficultToObtainChlorine);
      this.HowDifficultToObtainChlorine = howDifficultToObtainChlorine;
      return this;
    }

    @Override
    public WhenWashedHandsIn24HoursStep takingWaterFromStorage(String takingWaterFromStorage) {
      Objects.requireNonNull(takingWaterFromStorage);
      this.TakingWaterFromStorage = takingWaterFromStorage;
      return this;
    }

    @Override
    public WhatUsedToWashYourHandsStep whenWashedHandsIn24Hours(String whenWashedHandsIn24Hours) {
      Objects.requireNonNull(whenWashedHandsIn24Hours);
      this.WhenWashedHandsIn24Hours = whenWashedHandsIn24Hours;
      return this;
    }

    @Override
    public CommonIllnessAffectingAllChildrenInHouseholdStep whatUsedToWashYourHands(String whatUsedToWashYourHands) {
      Objects.requireNonNull(whatUsedToWashYourHands);
      this.WhatUsedToWashYourHands = whatUsedToWashYourHands;
      return this;
    }

    @Override
    public NoChildrenWithVomitingOrDiarrheaIn14daysStep commonIllnessAffectingAllChildrenInHousehold(String commonIllnessAffectingAllChildrenInHousehold) {
      Objects.requireNonNull(commonIllnessAffectingAllChildrenInHousehold);
      this.CommonIllnessAffectingAllChildrenInHousehold = commonIllnessAffectingAllChildrenInHousehold;
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
    public CompletedStep benefitSwp(String benefitSwp) {
      Objects.requireNonNull(benefitSwp);
      this.BenefitSWP = benefitSwp;
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
    private CopyOfBuilder(String id, String namebwe, String country, String community, Integer surveyId, Temporal.Date date, String headHouseholdName, String personBeingInterviewed, String waterTreatmentBeforeDrinking, String mainReasonNoWaterTreatmentBeforeDrinking, String waterTreatmentMethod, String frequencyWaterTreatment, String lastTimeTreatedHouseholdWaterWithChlorine, String whereDidYouGetChlorineToTreatHouseholdWater, Integer amountSpendPerWeekForChlorineToTreatWater, String howDifficultToObtainChlorine, String takingWaterFromStorage, String whenWashedHandsIn24Hours, String whatUsedToWashYourHands, String commonIllnessAffectingAllChildrenInHousehold, Integer noChildrenWithVomitingOrDiarrheaIn14days, Integer noTotalSchoolDaysMissedBySchoolAgeChildrenIn2LastWeek, Integer noChildrenWithVomitingOrDiarrheaIn7days, String didSickChildrenGoToHospital, String didSickChildrenGoToHospitalYes, String sickChildrenBreastfeeding, String outcomeMostRecentVomitingDiarrheaAtHospital, Integer noDaysNoWorkBecauseOfOwnIllness, Integer noDaysNoWorkBecauseOfIllnessFamilyMembers, Integer moneySpentMedicalTreatmentLast4weeks, String healthChangeInAYear, String healthChangeFamilyInAYear, String benefitSwp, Integer completed, String lat, String lng) {
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
              .frequencyWaterTreatment(frequencyWaterTreatment)
              .lastTimeTreatedHouseholdWaterWithChlorine(lastTimeTreatedHouseholdWaterWithChlorine)
              .whereDidYouGetChlorineToTreatHouseholdWater(whereDidYouGetChlorineToTreatHouseholdWater)
              .amountSpendPerWeekForChlorineToTreatWater(amountSpendPerWeekForChlorineToTreatWater)
              .howDifficultToObtainChlorine(howDifficultToObtainChlorine)
              .takingWaterFromStorage(takingWaterFromStorage)
              .whenWashedHandsIn24Hours(whenWashedHandsIn24Hours)
              .whatUsedToWashYourHands(whatUsedToWashYourHands)
              .commonIllnessAffectingAllChildrenInHousehold(commonIllnessAffectingAllChildrenInHousehold)
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
              .healthChangeInAYear(healthChangeInAYear)
              .healthChangeFamilyInAYear(healthChangeFamilyInAYear)
              .benefitSwp(benefitSwp)
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
    public CopyOfBuilder frequencyWaterTreatment(String frequencyWaterTreatment) {
      return (CopyOfBuilder) super.frequencyWaterTreatment(frequencyWaterTreatment);
    }

    @Override
    public CopyOfBuilder lastTimeTreatedHouseholdWaterWithChlorine(String lastTimeTreatedHouseholdWaterWithChlorine) {
      return (CopyOfBuilder) super.lastTimeTreatedHouseholdWaterWithChlorine(lastTimeTreatedHouseholdWaterWithChlorine);
    }

    @Override
    public CopyOfBuilder whereDidYouGetChlorineToTreatHouseholdWater(String whereDidYouGetChlorineToTreatHouseholdWater) {
      return (CopyOfBuilder) super.whereDidYouGetChlorineToTreatHouseholdWater(whereDidYouGetChlorineToTreatHouseholdWater);
    }

    @Override
    public CopyOfBuilder amountSpendPerWeekForChlorineToTreatWater(Integer amountSpendPerWeekForChlorineToTreatWater) {
      return (CopyOfBuilder) super.amountSpendPerWeekForChlorineToTreatWater(amountSpendPerWeekForChlorineToTreatWater);
    }

    @Override
    public CopyOfBuilder howDifficultToObtainChlorine(String howDifficultToObtainChlorine) {
      return (CopyOfBuilder) super.howDifficultToObtainChlorine(howDifficultToObtainChlorine);
    }

    @Override
    public CopyOfBuilder takingWaterFromStorage(String takingWaterFromStorage) {
      return (CopyOfBuilder) super.takingWaterFromStorage(takingWaterFromStorage);
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
    public CopyOfBuilder commonIllnessAffectingAllChildrenInHousehold(String commonIllnessAffectingAllChildrenInHousehold) {
      return (CopyOfBuilder) super.commonIllnessAffectingAllChildrenInHousehold(commonIllnessAffectingAllChildrenInHousehold);
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