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

/** This is an auto generated class representing the InitialSurvey type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "InitialSurveys")
public final class InitialSurvey implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAMEBWE = field("Namebwe");
  public static final QueryField COUNTRY = field("Country");
  public static final QueryField COMMUNITY = field("Community");
  public static final QueryField SURVEY_ID = field("SurveyId");
  public static final QueryField DATE = field("date");
  public static final QueryField HEAD_HOUSEHOLD_NAME = field("HeadHouseholdName");
  public static final QueryField HEAD_HOUSEHOLD_SEX = field("HeadHouseholdSex");
  public static final QueryField HEAD_HOUSEHOLD_MARITAL_STATUS = field("HeadHouseholdMaritalStatus");
  public static final QueryField HEAD_HOUSEHOLD_AGE = field("HeadHouseholdAge");
  public static final QueryField HEAD_HOUSEHOLD_OCCUPATION = field("HeadHouseholdOccupation");
  public static final QueryField HEAD_HOUSEHOLD_EDUCATION = field("HeadHouseholdEducation");
  public static final QueryField PERSON_BEING_INTERVIEWED = field("PersonBeingInterviewed");
  public static final QueryField TOTAL_NO_PEOPLE_HOUSEHOLD = field("TotalNoPeopleHousehold");
  public static final QueryField NO_HOUSEHOLD_MALE0_1_YEAR = field("NoHouseholdMale0_1Year");
  public static final QueryField NO_HOUSEHOLD_FEMALE0_1_YEAR = field("NoHouseholdFemale0_1Year");
  public static final QueryField NO_HOUSEHOLD_MALE1_5_YEAR = field("NoHouseholdMale1_5Year");
  public static final QueryField NO_HOUSEHOLD_FEMALE1_5_YEAR = field("NoHouseholdFemale1_5Year");
  public static final QueryField NO_HOUSEHOLD_MALE5_12_YEAR = field("NoHouseholdMale5_12Year");
  public static final QueryField NO_HOUSEHOLD_FEMALE5_12_YEAR = field("NoHouseholdFemale5_12Year");
  public static final QueryField NO_HOUSEHOLD_MALE13_17_YEAR = field("NoHouseholdMale13_17Year");
  public static final QueryField NO_HOUSEHOLD_FEMALE13_17_YEAR = field("NoHouseholdFemale13_17Year");
  public static final QueryField NO_HOUSEHOLD_MALE18_YEAR = field("NoHouseholdMale18_Year");
  public static final QueryField NO_HOUSEHOLD_FEMALE18_YEAR = field("NoHouseholdFemale18_Year");
  public static final QueryField REASON_NO_SCHOOL_CHILDREN5_17_YEAR = field("ReasonNoSchoolChildren5_17Year");
  public static final QueryField MAIN_SOURCE_DRINKING_WATER = field("MainSourceDrinkingWater");
  public static final QueryField MAIN_SOURCE_OTHER_PURPOSE_WATER = field("MainSourceOtherPurposeWater");
  public static final QueryField TIME_TO_WATER_SOURCE_GET_RETURN = field("TimeToWaterSourceGetReturn");
  public static final QueryField HOUSEHOLD_FREQUENCY_AT_WATER_SOURCE = field("HouseholdFrequencyAtWaterSource");
  public static final QueryField USUAL_HOUSEHOLD_WATER_FETCHER = field("UsualHouseholdWaterFetcher");
  public static final QueryField CONTAINER_CARRY_WATER = field("ContainerCarryWater");
  public static final QueryField WATER_TREATMENT_BEFORE_DRINKING = field("WaterTreatmentBeforeDrinking");
  public static final QueryField MAIN_REASON_NO_WATER_TREATMENT_BEFORE_DRINKING = field("MainReasonNoWaterTreatmentBeforeDrinking");
  public static final QueryField WATER_TREATMENT_METHOD = field("WaterTreatmentMethod");
  public static final QueryField HOW_LONG_USING_WATER_TREATMENT = field("HowLongUsingWaterTreatment");
  public static final QueryField FREQUENCY_WATER_TREATMENT = field("FrequencyWaterTreatment");
  public static final QueryField WATER_STORAGE_AT_HOME = field("WaterStorageAtHome");
  public static final QueryField TAKING_WATER_FROM_STORAGE = field("TakingWaterFromStorage");
  public static final QueryField RUBBISH_DISPOSAL = field("RubbishDisposal");
  public static final QueryField HOUSEHOLD_DEFECATION_METHOD = field("HouseholdDefecationMethod");
  public static final QueryField SATISFACTION_HOUSEHOLD_DEFECATION_METHOD = field("SatisfactionHouseholdDefecationMethod");
  public static final QueryField WASTE_DISPOSAL_YOUNGEST_CHILD = field("WasteDisposalYoungestChild");
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
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Namebwe;
  private final @ModelField(targetType="String", isRequired = true) String Country;
  private final @ModelField(targetType="String", isRequired = true) String Community;
  private final @ModelField(targetType="Int", isRequired = true) Integer SurveyId;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdName;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdSex;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdMaritalStatus;
  private final @ModelField(targetType="Int", isRequired = true) Integer HeadHouseholdAge;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdOccupation;
  private final @ModelField(targetType="String", isRequired = true) String HeadHouseholdEducation;
  private final @ModelField(targetType="String", isRequired = true) String PersonBeingInterviewed;
  private final @ModelField(targetType="Int", isRequired = true) Integer TotalNoPeopleHousehold;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdMale0_1Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdFemale0_1Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdMale1_5Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdFemale1_5Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdMale5_12Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdFemale5_12Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdMale13_17Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdFemale13_17Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdMale18_Year;
  private final @ModelField(targetType="Int", isRequired = true) Integer NoHouseholdFemale18_Year;
  private final @ModelField(targetType="String", isRequired = true) String ReasonNoSchoolChildren5_17Year;
  private final @ModelField(targetType="String", isRequired = true) String MainSourceDrinkingWater;
  private final @ModelField(targetType="String", isRequired = true) String MainSourceOtherPurposeWater;
  private final @ModelField(targetType="Int", isRequired = true) Integer TimeToWaterSourceGetReturn;
  private final @ModelField(targetType="String", isRequired = true) String HouseholdFrequencyAtWaterSource;
  private final @ModelField(targetType="String", isRequired = true) String UsualHouseholdWaterFetcher;
  private final @ModelField(targetType="String", isRequired = true) String ContainerCarryWater;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatmentBeforeDrinking;
  private final @ModelField(targetType="String", isRequired = true) String MainReasonNoWaterTreatmentBeforeDrinking;
  private final @ModelField(targetType="String", isRequired = true) String WaterTreatmentMethod;
  private final @ModelField(targetType="String", isRequired = true) String HowLongUsingWaterTreatment;
  private final @ModelField(targetType="String", isRequired = true) String FrequencyWaterTreatment;
  private final @ModelField(targetType="String", isRequired = true) String WaterStorageAtHome;
  private final @ModelField(targetType="String", isRequired = true) String TakingWaterFromStorage;
  private final @ModelField(targetType="String", isRequired = true) String RubbishDisposal;
  private final @ModelField(targetType="String", isRequired = true) String HouseholdDefecationMethod;
  private final @ModelField(targetType="String", isRequired = true) String SatisfactionHouseholdDefecationMethod;
  private final @ModelField(targetType="String", isRequired = true) String WasteDisposalYoungestChild;
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
  
  public String getHeadHouseholdSex() {
      return HeadHouseholdSex;
  }
  
  public String getHeadHouseholdMaritalStatus() {
      return HeadHouseholdMaritalStatus;
  }
  
  public Integer getHeadHouseholdAge() {
      return HeadHouseholdAge;
  }
  
  public String getHeadHouseholdOccupation() {
      return HeadHouseholdOccupation;
  }
  
  public String getHeadHouseholdEducation() {
      return HeadHouseholdEducation;
  }
  
  public String getPersonBeingInterviewed() {
      return PersonBeingInterviewed;
  }
  
  public Integer getTotalNoPeopleHousehold() {
      return TotalNoPeopleHousehold;
  }
  
  public Integer getNoHouseholdMale0_1Year() {
      return NoHouseholdMale0_1Year;
  }
  
  public Integer getNoHouseholdFemale0_1Year() {
      return NoHouseholdFemale0_1Year;
  }
  
  public Integer getNoHouseholdMale1_5Year() {
      return NoHouseholdMale1_5Year;
  }
  
  public Integer getNoHouseholdFemale1_5Year() {
      return NoHouseholdFemale1_5Year;
  }
  
  public Integer getNoHouseholdMale5_12Year() {
      return NoHouseholdMale5_12Year;
  }
  
  public Integer getNoHouseholdFemale5_12Year() {
      return NoHouseholdFemale5_12Year;
  }
  
  public Integer getNoHouseholdMale13_17Year() {
      return NoHouseholdMale13_17Year;
  }
  
  public Integer getNoHouseholdFemale13_17Year() {
      return NoHouseholdFemale13_17Year;
  }
  
  public Integer getNoHouseholdMale18Year() {
      return NoHouseholdMale18_Year;
  }
  
  public Integer getNoHouseholdFemale18Year() {
      return NoHouseholdFemale18_Year;
  }
  
  public String getReasonNoSchoolChildren5_17Year() {
      return ReasonNoSchoolChildren5_17Year;
  }
  
  public String getMainSourceDrinkingWater() {
      return MainSourceDrinkingWater;
  }
  
  public String getMainSourceOtherPurposeWater() {
      return MainSourceOtherPurposeWater;
  }
  
  public Integer getTimeToWaterSourceGetReturn() {
      return TimeToWaterSourceGetReturn;
  }
  
  public String getHouseholdFrequencyAtWaterSource() {
      return HouseholdFrequencyAtWaterSource;
  }
  
  public String getUsualHouseholdWaterFetcher() {
      return UsualHouseholdWaterFetcher;
  }
  
  public String getContainerCarryWater() {
      return ContainerCarryWater;
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
  
  public String getRubbishDisposal() {
      return RubbishDisposal;
  }
  
  public String getHouseholdDefecationMethod() {
      return HouseholdDefecationMethod;
  }
  
  public String getSatisfactionHouseholdDefecationMethod() {
      return SatisfactionHouseholdDefecationMethod;
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
  
  private InitialSurvey(String id, String Namebwe, String Country, String Community, Integer SurveyId, Temporal.Date date, String HeadHouseholdName, String HeadHouseholdSex, String HeadHouseholdMaritalStatus, Integer HeadHouseholdAge, String HeadHouseholdOccupation, String HeadHouseholdEducation, String PersonBeingInterviewed, Integer TotalNoPeopleHousehold, Integer NoHouseholdMale0_1Year, Integer NoHouseholdFemale0_1Year, Integer NoHouseholdMale1_5Year, Integer NoHouseholdFemale1_5Year, Integer NoHouseholdMale5_12Year, Integer NoHouseholdFemale5_12Year, Integer NoHouseholdMale13_17Year, Integer NoHouseholdFemale13_17Year, Integer NoHouseholdMale18_Year, Integer NoHouseholdFemale18_Year, String ReasonNoSchoolChildren5_17Year, String MainSourceDrinkingWater, String MainSourceOtherPurposeWater, Integer TimeToWaterSourceGetReturn, String HouseholdFrequencyAtWaterSource, String UsualHouseholdWaterFetcher, String ContainerCarryWater, String WaterTreatmentBeforeDrinking, String MainReasonNoWaterTreatmentBeforeDrinking, String WaterTreatmentMethod, String HowLongUsingWaterTreatment, String FrequencyWaterTreatment, String WaterStorageAtHome, String TakingWaterFromStorage, String RubbishDisposal, String HouseholdDefecationMethod, String SatisfactionHouseholdDefecationMethod, String WasteDisposalYoungestChild, String WashedHandsIn24Hours, String WhenWashedHandsIn24Hours, String WhatUsedToWashYourHands, Integer NoTotalSchoolDaysMissedByAllChildrenIn2LastWeek, String CommonIllnessAffectingChildrenUnder5, Integer NoChildrenWithVomitingOrDiarrheaIn7days, String DidSickChildrenGoToHospital, String DidSickChildrenGoToHospitalYes, String SickChildrenBreastfeeding, String OutcomeMostRecentVomiting_DiarrheaAtHospital, Integer NoDaysNoWorkBecauseOfOwnIllness, Integer NoDaysNoWorkBecauseOfIllnessFamilyMembers, Integer MoneySpentMedicalTreatmentLast4weeks, String HealthChangeInAYear, String HealthChangeFamilyInAYear) {
    this.id = id;
    this.Namebwe = Namebwe;
    this.Country = Country;
    this.Community = Community;
    this.SurveyId = SurveyId;
    this.date = date;
    this.HeadHouseholdName = HeadHouseholdName;
    this.HeadHouseholdSex = HeadHouseholdSex;
    this.HeadHouseholdMaritalStatus = HeadHouseholdMaritalStatus;
    this.HeadHouseholdAge = HeadHouseholdAge;
    this.HeadHouseholdOccupation = HeadHouseholdOccupation;
    this.HeadHouseholdEducation = HeadHouseholdEducation;
    this.PersonBeingInterviewed = PersonBeingInterviewed;
    this.TotalNoPeopleHousehold = TotalNoPeopleHousehold;
    this.NoHouseholdMale0_1Year = NoHouseholdMale0_1Year;
    this.NoHouseholdFemale0_1Year = NoHouseholdFemale0_1Year;
    this.NoHouseholdMale1_5Year = NoHouseholdMale1_5Year;
    this.NoHouseholdFemale1_5Year = NoHouseholdFemale1_5Year;
    this.NoHouseholdMale5_12Year = NoHouseholdMale5_12Year;
    this.NoHouseholdFemale5_12Year = NoHouseholdFemale5_12Year;
    this.NoHouseholdMale13_17Year = NoHouseholdMale13_17Year;
    this.NoHouseholdFemale13_17Year = NoHouseholdFemale13_17Year;
    this.NoHouseholdMale18_Year = NoHouseholdMale18_Year;
    this.NoHouseholdFemale18_Year = NoHouseholdFemale18_Year;
    this.ReasonNoSchoolChildren5_17Year = ReasonNoSchoolChildren5_17Year;
    this.MainSourceDrinkingWater = MainSourceDrinkingWater;
    this.MainSourceOtherPurposeWater = MainSourceOtherPurposeWater;
    this.TimeToWaterSourceGetReturn = TimeToWaterSourceGetReturn;
    this.HouseholdFrequencyAtWaterSource = HouseholdFrequencyAtWaterSource;
    this.UsualHouseholdWaterFetcher = UsualHouseholdWaterFetcher;
    this.ContainerCarryWater = ContainerCarryWater;
    this.WaterTreatmentBeforeDrinking = WaterTreatmentBeforeDrinking;
    this.MainReasonNoWaterTreatmentBeforeDrinking = MainReasonNoWaterTreatmentBeforeDrinking;
    this.WaterTreatmentMethod = WaterTreatmentMethod;
    this.HowLongUsingWaterTreatment = HowLongUsingWaterTreatment;
    this.FrequencyWaterTreatment = FrequencyWaterTreatment;
    this.WaterStorageAtHome = WaterStorageAtHome;
    this.TakingWaterFromStorage = TakingWaterFromStorage;
    this.RubbishDisposal = RubbishDisposal;
    this.HouseholdDefecationMethod = HouseholdDefecationMethod;
    this.SatisfactionHouseholdDefecationMethod = SatisfactionHouseholdDefecationMethod;
    this.WasteDisposalYoungestChild = WasteDisposalYoungestChild;
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
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      InitialSurvey initialSurvey = (InitialSurvey) obj;
      return ObjectsCompat.equals(getId(), initialSurvey.getId()) &&
              ObjectsCompat.equals(getNamebwe(), initialSurvey.getNamebwe()) &&
              ObjectsCompat.equals(getCountry(), initialSurvey.getCountry()) &&
              ObjectsCompat.equals(getCommunity(), initialSurvey.getCommunity()) &&
              ObjectsCompat.equals(getSurveyId(), initialSurvey.getSurveyId()) &&
              ObjectsCompat.equals(getDate(), initialSurvey.getDate()) &&
              ObjectsCompat.equals(getHeadHouseholdName(), initialSurvey.getHeadHouseholdName()) &&
              ObjectsCompat.equals(getHeadHouseholdSex(), initialSurvey.getHeadHouseholdSex()) &&
              ObjectsCompat.equals(getHeadHouseholdMaritalStatus(), initialSurvey.getHeadHouseholdMaritalStatus()) &&
              ObjectsCompat.equals(getHeadHouseholdAge(), initialSurvey.getHeadHouseholdAge()) &&
              ObjectsCompat.equals(getHeadHouseholdOccupation(), initialSurvey.getHeadHouseholdOccupation()) &&
              ObjectsCompat.equals(getHeadHouseholdEducation(), initialSurvey.getHeadHouseholdEducation()) &&
              ObjectsCompat.equals(getPersonBeingInterviewed(), initialSurvey.getPersonBeingInterviewed()) &&
              ObjectsCompat.equals(getTotalNoPeopleHousehold(), initialSurvey.getTotalNoPeopleHousehold()) &&
              ObjectsCompat.equals(getNoHouseholdMale0_1Year(), initialSurvey.getNoHouseholdMale0_1Year()) &&
              ObjectsCompat.equals(getNoHouseholdFemale0_1Year(), initialSurvey.getNoHouseholdFemale0_1Year()) &&
              ObjectsCompat.equals(getNoHouseholdMale1_5Year(), initialSurvey.getNoHouseholdMale1_5Year()) &&
              ObjectsCompat.equals(getNoHouseholdFemale1_5Year(), initialSurvey.getNoHouseholdFemale1_5Year()) &&
              ObjectsCompat.equals(getNoHouseholdMale5_12Year(), initialSurvey.getNoHouseholdMale5_12Year()) &&
              ObjectsCompat.equals(getNoHouseholdFemale5_12Year(), initialSurvey.getNoHouseholdFemale5_12Year()) &&
              ObjectsCompat.equals(getNoHouseholdMale13_17Year(), initialSurvey.getNoHouseholdMale13_17Year()) &&
              ObjectsCompat.equals(getNoHouseholdFemale13_17Year(), initialSurvey.getNoHouseholdFemale13_17Year()) &&
              ObjectsCompat.equals(getNoHouseholdMale18Year(), initialSurvey.getNoHouseholdMale18Year()) &&
              ObjectsCompat.equals(getNoHouseholdFemale18Year(), initialSurvey.getNoHouseholdFemale18Year()) &&
              ObjectsCompat.equals(getReasonNoSchoolChildren5_17Year(), initialSurvey.getReasonNoSchoolChildren5_17Year()) &&
              ObjectsCompat.equals(getMainSourceDrinkingWater(), initialSurvey.getMainSourceDrinkingWater()) &&
              ObjectsCompat.equals(getMainSourceOtherPurposeWater(), initialSurvey.getMainSourceOtherPurposeWater()) &&
              ObjectsCompat.equals(getTimeToWaterSourceGetReturn(), initialSurvey.getTimeToWaterSourceGetReturn()) &&
              ObjectsCompat.equals(getHouseholdFrequencyAtWaterSource(), initialSurvey.getHouseholdFrequencyAtWaterSource()) &&
              ObjectsCompat.equals(getUsualHouseholdWaterFetcher(), initialSurvey.getUsualHouseholdWaterFetcher()) &&
              ObjectsCompat.equals(getContainerCarryWater(), initialSurvey.getContainerCarryWater()) &&
              ObjectsCompat.equals(getWaterTreatmentBeforeDrinking(), initialSurvey.getWaterTreatmentBeforeDrinking()) &&
              ObjectsCompat.equals(getMainReasonNoWaterTreatmentBeforeDrinking(), initialSurvey.getMainReasonNoWaterTreatmentBeforeDrinking()) &&
              ObjectsCompat.equals(getWaterTreatmentMethod(), initialSurvey.getWaterTreatmentMethod()) &&
              ObjectsCompat.equals(getHowLongUsingWaterTreatment(), initialSurvey.getHowLongUsingWaterTreatment()) &&
              ObjectsCompat.equals(getFrequencyWaterTreatment(), initialSurvey.getFrequencyWaterTreatment()) &&
              ObjectsCompat.equals(getWaterStorageAtHome(), initialSurvey.getWaterStorageAtHome()) &&
              ObjectsCompat.equals(getTakingWaterFromStorage(), initialSurvey.getTakingWaterFromStorage()) &&
              ObjectsCompat.equals(getRubbishDisposal(), initialSurvey.getRubbishDisposal()) &&
              ObjectsCompat.equals(getHouseholdDefecationMethod(), initialSurvey.getHouseholdDefecationMethod()) &&
              ObjectsCompat.equals(getSatisfactionHouseholdDefecationMethod(), initialSurvey.getSatisfactionHouseholdDefecationMethod()) &&
              ObjectsCompat.equals(getWasteDisposalYoungestChild(), initialSurvey.getWasteDisposalYoungestChild()) &&
              ObjectsCompat.equals(getWashedHandsIn24Hours(), initialSurvey.getWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhenWashedHandsIn24Hours(), initialSurvey.getWhenWashedHandsIn24Hours()) &&
              ObjectsCompat.equals(getWhatUsedToWashYourHands(), initialSurvey.getWhatUsedToWashYourHands()) &&
              ObjectsCompat.equals(getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek(), initialSurvey.getNoTotalSchoolDaysMissedByAllChildrenIn2LastWeek()) &&
              ObjectsCompat.equals(getCommonIllnessAffectingChildrenUnder5(), initialSurvey.getCommonIllnessAffectingChildrenUnder5()) &&
              ObjectsCompat.equals(getNoChildrenWithVomitingOrDiarrheaIn7days(), initialSurvey.getNoChildrenWithVomitingOrDiarrheaIn7days()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospital(), initialSurvey.getDidSickChildrenGoToHospital()) &&
              ObjectsCompat.equals(getDidSickChildrenGoToHospitalYes(), initialSurvey.getDidSickChildrenGoToHospitalYes()) &&
              ObjectsCompat.equals(getSickChildrenBreastfeeding(), initialSurvey.getSickChildrenBreastfeeding()) &&
              ObjectsCompat.equals(getOutcomeMostRecentVomitingDiarrheaAtHospital(), initialSurvey.getOutcomeMostRecentVomitingDiarrheaAtHospital()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfOwnIllness(), initialSurvey.getNoDaysNoWorkBecauseOfOwnIllness()) &&
              ObjectsCompat.equals(getNoDaysNoWorkBecauseOfIllnessFamilyMembers(), initialSurvey.getNoDaysNoWorkBecauseOfIllnessFamilyMembers()) &&
              ObjectsCompat.equals(getMoneySpentMedicalTreatmentLast4weeks(), initialSurvey.getMoneySpentMedicalTreatmentLast4weeks()) &&
              ObjectsCompat.equals(getHealthChangeInAYear(), initialSurvey.getHealthChangeInAYear()) &&
              ObjectsCompat.equals(getHealthChangeFamilyInAYear(), initialSurvey.getHealthChangeFamilyInAYear());
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
      .append(getHeadHouseholdSex())
      .append(getHeadHouseholdMaritalStatus())
      .append(getHeadHouseholdAge())
      .append(getHeadHouseholdOccupation())
      .append(getHeadHouseholdEducation())
      .append(getPersonBeingInterviewed())
      .append(getTotalNoPeopleHousehold())
      .append(getNoHouseholdMale0_1Year())
      .append(getNoHouseholdFemale0_1Year())
      .append(getNoHouseholdMale1_5Year())
      .append(getNoHouseholdFemale1_5Year())
      .append(getNoHouseholdMale5_12Year())
      .append(getNoHouseholdFemale5_12Year())
      .append(getNoHouseholdMale13_17Year())
      .append(getNoHouseholdFemale13_17Year())
      .append(getNoHouseholdMale18Year())
      .append(getNoHouseholdFemale18Year())
      .append(getReasonNoSchoolChildren5_17Year())
      .append(getMainSourceDrinkingWater())
      .append(getMainSourceOtherPurposeWater())
      .append(getTimeToWaterSourceGetReturn())
      .append(getHouseholdFrequencyAtWaterSource())
      .append(getUsualHouseholdWaterFetcher())
      .append(getContainerCarryWater())
      .append(getWaterTreatmentBeforeDrinking())
      .append(getMainReasonNoWaterTreatmentBeforeDrinking())
      .append(getWaterTreatmentMethod())
      .append(getHowLongUsingWaterTreatment())
      .append(getFrequencyWaterTreatment())
      .append(getWaterStorageAtHome())
      .append(getTakingWaterFromStorage())
      .append(getRubbishDisposal())
      .append(getHouseholdDefecationMethod())
      .append(getSatisfactionHouseholdDefecationMethod())
      .append(getWasteDisposalYoungestChild())
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
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("InitialSurvey {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Namebwe=" + String.valueOf(getNamebwe()) + ", ")
      .append("Country=" + String.valueOf(getCountry()) + ", ")
      .append("Community=" + String.valueOf(getCommunity()) + ", ")
      .append("SurveyId=" + String.valueOf(getSurveyId()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("HeadHouseholdName=" + String.valueOf(getHeadHouseholdName()) + ", ")
      .append("HeadHouseholdSex=" + String.valueOf(getHeadHouseholdSex()) + ", ")
      .append("HeadHouseholdMaritalStatus=" + String.valueOf(getHeadHouseholdMaritalStatus()) + ", ")
      .append("HeadHouseholdAge=" + String.valueOf(getHeadHouseholdAge()) + ", ")
      .append("HeadHouseholdOccupation=" + String.valueOf(getHeadHouseholdOccupation()) + ", ")
      .append("HeadHouseholdEducation=" + String.valueOf(getHeadHouseholdEducation()) + ", ")
      .append("PersonBeingInterviewed=" + String.valueOf(getPersonBeingInterviewed()) + ", ")
      .append("TotalNoPeopleHousehold=" + String.valueOf(getTotalNoPeopleHousehold()) + ", ")
      .append("NoHouseholdMale0_1Year=" + String.valueOf(getNoHouseholdMale0_1Year()) + ", ")
      .append("NoHouseholdFemale0_1Year=" + String.valueOf(getNoHouseholdFemale0_1Year()) + ", ")
      .append("NoHouseholdMale1_5Year=" + String.valueOf(getNoHouseholdMale1_5Year()) + ", ")
      .append("NoHouseholdFemale1_5Year=" + String.valueOf(getNoHouseholdFemale1_5Year()) + ", ")
      .append("NoHouseholdMale5_12Year=" + String.valueOf(getNoHouseholdMale5_12Year()) + ", ")
      .append("NoHouseholdFemale5_12Year=" + String.valueOf(getNoHouseholdFemale5_12Year()) + ", ")
      .append("NoHouseholdMale13_17Year=" + String.valueOf(getNoHouseholdMale13_17Year()) + ", ")
      .append("NoHouseholdFemale13_17Year=" + String.valueOf(getNoHouseholdFemale13_17Year()) + ", ")
      .append("NoHouseholdMale18_Year=" + String.valueOf(getNoHouseholdMale18Year()) + ", ")
      .append("NoHouseholdFemale18_Year=" + String.valueOf(getNoHouseholdFemale18Year()) + ", ")
      .append("ReasonNoSchoolChildren5_17Year=" + String.valueOf(getReasonNoSchoolChildren5_17Year()) + ", ")
      .append("MainSourceDrinkingWater=" + String.valueOf(getMainSourceDrinkingWater()) + ", ")
      .append("MainSourceOtherPurposeWater=" + String.valueOf(getMainSourceOtherPurposeWater()) + ", ")
      .append("TimeToWaterSourceGetReturn=" + String.valueOf(getTimeToWaterSourceGetReturn()) + ", ")
      .append("HouseholdFrequencyAtWaterSource=" + String.valueOf(getHouseholdFrequencyAtWaterSource()) + ", ")
      .append("UsualHouseholdWaterFetcher=" + String.valueOf(getUsualHouseholdWaterFetcher()) + ", ")
      .append("ContainerCarryWater=" + String.valueOf(getContainerCarryWater()) + ", ")
      .append("WaterTreatmentBeforeDrinking=" + String.valueOf(getWaterTreatmentBeforeDrinking()) + ", ")
      .append("MainReasonNoWaterTreatmentBeforeDrinking=" + String.valueOf(getMainReasonNoWaterTreatmentBeforeDrinking()) + ", ")
      .append("WaterTreatmentMethod=" + String.valueOf(getWaterTreatmentMethod()) + ", ")
      .append("HowLongUsingWaterTreatment=" + String.valueOf(getHowLongUsingWaterTreatment()) + ", ")
      .append("FrequencyWaterTreatment=" + String.valueOf(getFrequencyWaterTreatment()) + ", ")
      .append("WaterStorageAtHome=" + String.valueOf(getWaterStorageAtHome()) + ", ")
      .append("TakingWaterFromStorage=" + String.valueOf(getTakingWaterFromStorage()) + ", ")
      .append("RubbishDisposal=" + String.valueOf(getRubbishDisposal()) + ", ")
      .append("HouseholdDefecationMethod=" + String.valueOf(getHouseholdDefecationMethod()) + ", ")
      .append("SatisfactionHouseholdDefecationMethod=" + String.valueOf(getSatisfactionHouseholdDefecationMethod()) + ", ")
      .append("WasteDisposalYoungestChild=" + String.valueOf(getWasteDisposalYoungestChild()) + ", ")
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
      .append("HealthChangeFamilyInAYear=" + String.valueOf(getHealthChangeFamilyInAYear()))
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
  public static InitialSurvey justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new InitialSurvey(
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
      HeadHouseholdSex,
      HeadHouseholdMaritalStatus,
      HeadHouseholdAge,
      HeadHouseholdOccupation,
      HeadHouseholdEducation,
      PersonBeingInterviewed,
      TotalNoPeopleHousehold,
      NoHouseholdMale0_1Year,
      NoHouseholdFemale0_1Year,
      NoHouseholdMale1_5Year,
      NoHouseholdFemale1_5Year,
      NoHouseholdMale5_12Year,
      NoHouseholdFemale5_12Year,
      NoHouseholdMale13_17Year,
      NoHouseholdFemale13_17Year,
      NoHouseholdMale18_Year,
      NoHouseholdFemale18_Year,
      ReasonNoSchoolChildren5_17Year,
      MainSourceDrinkingWater,
      MainSourceOtherPurposeWater,
      TimeToWaterSourceGetReturn,
      HouseholdFrequencyAtWaterSource,
      UsualHouseholdWaterFetcher,
      ContainerCarryWater,
      WaterTreatmentBeforeDrinking,
      MainReasonNoWaterTreatmentBeforeDrinking,
      WaterTreatmentMethod,
      HowLongUsingWaterTreatment,
      FrequencyWaterTreatment,
      WaterStorageAtHome,
      TakingWaterFromStorage,
      RubbishDisposal,
      HouseholdDefecationMethod,
      SatisfactionHouseholdDefecationMethod,
      WasteDisposalYoungestChild,
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
      HealthChangeFamilyInAYear);
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
    HeadHouseholdSexStep headHouseholdName(String headHouseholdName);
  }
  

  public interface HeadHouseholdSexStep {
    HeadHouseholdMaritalStatusStep headHouseholdSex(String headHouseholdSex);
  }
  

  public interface HeadHouseholdMaritalStatusStep {
    HeadHouseholdAgeStep headHouseholdMaritalStatus(String headHouseholdMaritalStatus);
  }
  

  public interface HeadHouseholdAgeStep {
    HeadHouseholdOccupationStep headHouseholdAge(Integer headHouseholdAge);
  }
  

  public interface HeadHouseholdOccupationStep {
    HeadHouseholdEducationStep headHouseholdOccupation(String headHouseholdOccupation);
  }
  

  public interface HeadHouseholdEducationStep {
    PersonBeingInterviewedStep headHouseholdEducation(String headHouseholdEducation);
  }
  

  public interface PersonBeingInterviewedStep {
    TotalNoPeopleHouseholdStep personBeingInterviewed(String personBeingInterviewed);
  }
  

  public interface TotalNoPeopleHouseholdStep {
    NoHouseholdMale0_1YearStep totalNoPeopleHousehold(Integer totalNoPeopleHousehold);
  }
  

  public interface NoHouseholdMale0_1YearStep {
    NoHouseholdFemale0_1YearStep noHouseholdMale0_1Year(Integer noHouseholdMale0_1Year);
  }
  

  public interface NoHouseholdFemale0_1YearStep {
    NoHouseholdMale1_5YearStep noHouseholdFemale0_1Year(Integer noHouseholdFemale0_1Year);
  }
  

  public interface NoHouseholdMale1_5YearStep {
    NoHouseholdFemale1_5YearStep noHouseholdMale1_5Year(Integer noHouseholdMale1_5Year);
  }
  

  public interface NoHouseholdFemale1_5YearStep {
    NoHouseholdMale5_12YearStep noHouseholdFemale1_5Year(Integer noHouseholdFemale1_5Year);
  }
  

  public interface NoHouseholdMale5_12YearStep {
    NoHouseholdFemale5_12YearStep noHouseholdMale5_12Year(Integer noHouseholdMale5_12Year);
  }
  

  public interface NoHouseholdFemale5_12YearStep {
    NoHouseholdMale13_17YearStep noHouseholdFemale5_12Year(Integer noHouseholdFemale5_12Year);
  }
  

  public interface NoHouseholdMale13_17YearStep {
    NoHouseholdFemale13_17YearStep noHouseholdMale13_17Year(Integer noHouseholdMale13_17Year);
  }
  

  public interface NoHouseholdFemale13_17YearStep {
    NoHouseholdMale18YearStep noHouseholdFemale13_17Year(Integer noHouseholdFemale13_17Year);
  }
  

  public interface NoHouseholdMale18YearStep {
    NoHouseholdFemale18YearStep noHouseholdMale18Year(Integer noHouseholdMale18Year);
  }
  

  public interface NoHouseholdFemale18YearStep {
    ReasonNoSchoolChildren5_17YearStep noHouseholdFemale18Year(Integer noHouseholdFemale18Year);
  }
  

  public interface ReasonNoSchoolChildren5_17YearStep {
    MainSourceDrinkingWaterStep reasonNoSchoolChildren5_17Year(String reasonNoSchoolChildren5_17Year);
  }
  

  public interface MainSourceDrinkingWaterStep {
    MainSourceOtherPurposeWaterStep mainSourceDrinkingWater(String mainSourceDrinkingWater);
  }
  

  public interface MainSourceOtherPurposeWaterStep {
    TimeToWaterSourceGetReturnStep mainSourceOtherPurposeWater(String mainSourceOtherPurposeWater);
  }
  

  public interface TimeToWaterSourceGetReturnStep {
    HouseholdFrequencyAtWaterSourceStep timeToWaterSourceGetReturn(Integer timeToWaterSourceGetReturn);
  }
  

  public interface HouseholdFrequencyAtWaterSourceStep {
    UsualHouseholdWaterFetcherStep householdFrequencyAtWaterSource(String householdFrequencyAtWaterSource);
  }
  

  public interface UsualHouseholdWaterFetcherStep {
    ContainerCarryWaterStep usualHouseholdWaterFetcher(String usualHouseholdWaterFetcher);
  }
  

  public interface ContainerCarryWaterStep {
    WaterTreatmentBeforeDrinkingStep containerCarryWater(String containerCarryWater);
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
    RubbishDisposalStep takingWaterFromStorage(String takingWaterFromStorage);
  }
  

  public interface RubbishDisposalStep {
    HouseholdDefecationMethodStep rubbishDisposal(String rubbishDisposal);
  }
  

  public interface HouseholdDefecationMethodStep {
    SatisfactionHouseholdDefecationMethodStep householdDefecationMethod(String householdDefecationMethod);
  }
  

  public interface SatisfactionHouseholdDefecationMethodStep {
    WasteDisposalYoungestChildStep satisfactionHouseholdDefecationMethod(String satisfactionHouseholdDefecationMethod);
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
    BuildStep healthChangeFamilyInAYear(String healthChangeFamilyInAYear);
  }
  

  public interface BuildStep {
    InitialSurvey build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep date(Temporal.Date date);
  }
  

  public static class Builder implements NamebweStep, CountryStep, CommunityStep, SurveyIdStep, HeadHouseholdNameStep, HeadHouseholdSexStep, HeadHouseholdMaritalStatusStep, HeadHouseholdAgeStep, HeadHouseholdOccupationStep, HeadHouseholdEducationStep, PersonBeingInterviewedStep, TotalNoPeopleHouseholdStep, NoHouseholdMale0_1YearStep, NoHouseholdFemale0_1YearStep, NoHouseholdMale1_5YearStep, NoHouseholdFemale1_5YearStep, NoHouseholdMale5_12YearStep, NoHouseholdFemale5_12YearStep, NoHouseholdMale13_17YearStep, NoHouseholdFemale13_17YearStep, NoHouseholdMale18YearStep, NoHouseholdFemale18YearStep, ReasonNoSchoolChildren5_17YearStep, MainSourceDrinkingWaterStep, MainSourceOtherPurposeWaterStep, TimeToWaterSourceGetReturnStep, HouseholdFrequencyAtWaterSourceStep, UsualHouseholdWaterFetcherStep, ContainerCarryWaterStep, WaterTreatmentBeforeDrinkingStep, MainReasonNoWaterTreatmentBeforeDrinkingStep, WaterTreatmentMethodStep, HowLongUsingWaterTreatmentStep, FrequencyWaterTreatmentStep, WaterStorageAtHomeStep, TakingWaterFromStorageStep, RubbishDisposalStep, HouseholdDefecationMethodStep, SatisfactionHouseholdDefecationMethodStep, WasteDisposalYoungestChildStep, WashedHandsIn24HoursStep, WhenWashedHandsIn24HoursStep, WhatUsedToWashYourHandsStep, NoTotalSchoolDaysMissedByAllChildrenIn2LastWeekStep, CommonIllnessAffectingChildrenUnder5Step, NoChildrenWithVomitingOrDiarrheaIn7daysStep, DidSickChildrenGoToHospitalStep, DidSickChildrenGoToHospitalYesStep, SickChildrenBreastfeedingStep, OutcomeMostRecentVomitingDiarrheaAtHospitalStep, NoDaysNoWorkBecauseOfOwnIllnessStep, NoDaysNoWorkBecauseOfIllnessFamilyMembersStep, MoneySpentMedicalTreatmentLast4weeksStep, HealthChangeInAYearStep, HealthChangeFamilyInAYearStep, BuildStep {
    private String id;
    private String Namebwe;
    private String Country;
    private String Community;
    private Integer SurveyId;
    private String HeadHouseholdName;
    private String HeadHouseholdSex;
    private String HeadHouseholdMaritalStatus;
    private Integer HeadHouseholdAge;
    private String HeadHouseholdOccupation;
    private String HeadHouseholdEducation;
    private String PersonBeingInterviewed;
    private Integer TotalNoPeopleHousehold;
    private Integer NoHouseholdMale0_1Year;
    private Integer NoHouseholdFemale0_1Year;
    private Integer NoHouseholdMale1_5Year;
    private Integer NoHouseholdFemale1_5Year;
    private Integer NoHouseholdMale5_12Year;
    private Integer NoHouseholdFemale5_12Year;
    private Integer NoHouseholdMale13_17Year;
    private Integer NoHouseholdFemale13_17Year;
    private Integer NoHouseholdMale18_Year;
    private Integer NoHouseholdFemale18_Year;
    private String ReasonNoSchoolChildren5_17Year;
    private String MainSourceDrinkingWater;
    private String MainSourceOtherPurposeWater;
    private Integer TimeToWaterSourceGetReturn;
    private String HouseholdFrequencyAtWaterSource;
    private String UsualHouseholdWaterFetcher;
    private String ContainerCarryWater;
    private String WaterTreatmentBeforeDrinking;
    private String MainReasonNoWaterTreatmentBeforeDrinking;
    private String WaterTreatmentMethod;
    private String HowLongUsingWaterTreatment;
    private String FrequencyWaterTreatment;
    private String WaterStorageAtHome;
    private String TakingWaterFromStorage;
    private String RubbishDisposal;
    private String HouseholdDefecationMethod;
    private String SatisfactionHouseholdDefecationMethod;
    private String WasteDisposalYoungestChild;
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
    private Temporal.Date date;
    @Override
     public InitialSurvey build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new InitialSurvey(
          id,
          Namebwe,
          Country,
          Community,
          SurveyId,
          date,
          HeadHouseholdName,
          HeadHouseholdSex,
          HeadHouseholdMaritalStatus,
          HeadHouseholdAge,
          HeadHouseholdOccupation,
          HeadHouseholdEducation,
          PersonBeingInterviewed,
          TotalNoPeopleHousehold,
          NoHouseholdMale0_1Year,
          NoHouseholdFemale0_1Year,
          NoHouseholdMale1_5Year,
          NoHouseholdFemale1_5Year,
          NoHouseholdMale5_12Year,
          NoHouseholdFemale5_12Year,
          NoHouseholdMale13_17Year,
          NoHouseholdFemale13_17Year,
          NoHouseholdMale18_Year,
          NoHouseholdFemale18_Year,
          ReasonNoSchoolChildren5_17Year,
          MainSourceDrinkingWater,
          MainSourceOtherPurposeWater,
          TimeToWaterSourceGetReturn,
          HouseholdFrequencyAtWaterSource,
          UsualHouseholdWaterFetcher,
          ContainerCarryWater,
          WaterTreatmentBeforeDrinking,
          MainReasonNoWaterTreatmentBeforeDrinking,
          WaterTreatmentMethod,
          HowLongUsingWaterTreatment,
          FrequencyWaterTreatment,
          WaterStorageAtHome,
          TakingWaterFromStorage,
          RubbishDisposal,
          HouseholdDefecationMethod,
          SatisfactionHouseholdDefecationMethod,
          WasteDisposalYoungestChild,
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
          HealthChangeFamilyInAYear);
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
     public HeadHouseholdSexStep headHouseholdName(String headHouseholdName) {
        Objects.requireNonNull(headHouseholdName);
        this.HeadHouseholdName = headHouseholdName;
        return this;
    }
    
    @Override
     public HeadHouseholdMaritalStatusStep headHouseholdSex(String headHouseholdSex) {
        Objects.requireNonNull(headHouseholdSex);
        this.HeadHouseholdSex = headHouseholdSex;
        return this;
    }
    
    @Override
     public HeadHouseholdAgeStep headHouseholdMaritalStatus(String headHouseholdMaritalStatus) {
        Objects.requireNonNull(headHouseholdMaritalStatus);
        this.HeadHouseholdMaritalStatus = headHouseholdMaritalStatus;
        return this;
    }
    
    @Override
     public HeadHouseholdOccupationStep headHouseholdAge(Integer headHouseholdAge) {
        Objects.requireNonNull(headHouseholdAge);
        this.HeadHouseholdAge = headHouseholdAge;
        return this;
    }
    
    @Override
     public HeadHouseholdEducationStep headHouseholdOccupation(String headHouseholdOccupation) {
        Objects.requireNonNull(headHouseholdOccupation);
        this.HeadHouseholdOccupation = headHouseholdOccupation;
        return this;
    }
    
    @Override
     public PersonBeingInterviewedStep headHouseholdEducation(String headHouseholdEducation) {
        Objects.requireNonNull(headHouseholdEducation);
        this.HeadHouseholdEducation = headHouseholdEducation;
        return this;
    }
    
    @Override
     public TotalNoPeopleHouseholdStep personBeingInterviewed(String personBeingInterviewed) {
        Objects.requireNonNull(personBeingInterviewed);
        this.PersonBeingInterviewed = personBeingInterviewed;
        return this;
    }
    
    @Override
     public NoHouseholdMale0_1YearStep totalNoPeopleHousehold(Integer totalNoPeopleHousehold) {
        Objects.requireNonNull(totalNoPeopleHousehold);
        this.TotalNoPeopleHousehold = totalNoPeopleHousehold;
        return this;
    }
    
    @Override
     public NoHouseholdFemale0_1YearStep noHouseholdMale0_1Year(Integer noHouseholdMale0_1Year) {
        Objects.requireNonNull(noHouseholdMale0_1Year);
        this.NoHouseholdMale0_1Year = noHouseholdMale0_1Year;
        return this;
    }
    
    @Override
     public NoHouseholdMale1_5YearStep noHouseholdFemale0_1Year(Integer noHouseholdFemale0_1Year) {
        Objects.requireNonNull(noHouseholdFemale0_1Year);
        this.NoHouseholdFemale0_1Year = noHouseholdFemale0_1Year;
        return this;
    }
    
    @Override
     public NoHouseholdFemale1_5YearStep noHouseholdMale1_5Year(Integer noHouseholdMale1_5Year) {
        Objects.requireNonNull(noHouseholdMale1_5Year);
        this.NoHouseholdMale1_5Year = noHouseholdMale1_5Year;
        return this;
    }
    
    @Override
     public NoHouseholdMale5_12YearStep noHouseholdFemale1_5Year(Integer noHouseholdFemale1_5Year) {
        Objects.requireNonNull(noHouseholdFemale1_5Year);
        this.NoHouseholdFemale1_5Year = noHouseholdFemale1_5Year;
        return this;
    }
    
    @Override
     public NoHouseholdFemale5_12YearStep noHouseholdMale5_12Year(Integer noHouseholdMale5_12Year) {
        Objects.requireNonNull(noHouseholdMale5_12Year);
        this.NoHouseholdMale5_12Year = noHouseholdMale5_12Year;
        return this;
    }
    
    @Override
     public NoHouseholdMale13_17YearStep noHouseholdFemale5_12Year(Integer noHouseholdFemale5_12Year) {
        Objects.requireNonNull(noHouseholdFemale5_12Year);
        this.NoHouseholdFemale5_12Year = noHouseholdFemale5_12Year;
        return this;
    }
    
    @Override
     public NoHouseholdFemale13_17YearStep noHouseholdMale13_17Year(Integer noHouseholdMale13_17Year) {
        Objects.requireNonNull(noHouseholdMale13_17Year);
        this.NoHouseholdMale13_17Year = noHouseholdMale13_17Year;
        return this;
    }
    
    @Override
     public NoHouseholdMale18YearStep noHouseholdFemale13_17Year(Integer noHouseholdFemale13_17Year) {
        Objects.requireNonNull(noHouseholdFemale13_17Year);
        this.NoHouseholdFemale13_17Year = noHouseholdFemale13_17Year;
        return this;
    }
    
    @Override
     public NoHouseholdFemale18YearStep noHouseholdMale18Year(Integer noHouseholdMale18Year) {
        Objects.requireNonNull(noHouseholdMale18Year);
        this.NoHouseholdMale18_Year = noHouseholdMale18Year;
        return this;
    }
    
    @Override
     public ReasonNoSchoolChildren5_17YearStep noHouseholdFemale18Year(Integer noHouseholdFemale18Year) {
        Objects.requireNonNull(noHouseholdFemale18Year);
        this.NoHouseholdFemale18_Year = noHouseholdFemale18Year;
        return this;
    }
    
    @Override
     public MainSourceDrinkingWaterStep reasonNoSchoolChildren5_17Year(String reasonNoSchoolChildren5_17Year) {
        Objects.requireNonNull(reasonNoSchoolChildren5_17Year);
        this.ReasonNoSchoolChildren5_17Year = reasonNoSchoolChildren5_17Year;
        return this;
    }
    
    @Override
     public MainSourceOtherPurposeWaterStep mainSourceDrinkingWater(String mainSourceDrinkingWater) {
        Objects.requireNonNull(mainSourceDrinkingWater);
        this.MainSourceDrinkingWater = mainSourceDrinkingWater;
        return this;
    }
    
    @Override
     public TimeToWaterSourceGetReturnStep mainSourceOtherPurposeWater(String mainSourceOtherPurposeWater) {
        Objects.requireNonNull(mainSourceOtherPurposeWater);
        this.MainSourceOtherPurposeWater = mainSourceOtherPurposeWater;
        return this;
    }
    
    @Override
     public HouseholdFrequencyAtWaterSourceStep timeToWaterSourceGetReturn(Integer timeToWaterSourceGetReturn) {
        Objects.requireNonNull(timeToWaterSourceGetReturn);
        this.TimeToWaterSourceGetReturn = timeToWaterSourceGetReturn;
        return this;
    }
    
    @Override
     public UsualHouseholdWaterFetcherStep householdFrequencyAtWaterSource(String householdFrequencyAtWaterSource) {
        Objects.requireNonNull(householdFrequencyAtWaterSource);
        this.HouseholdFrequencyAtWaterSource = householdFrequencyAtWaterSource;
        return this;
    }
    
    @Override
     public ContainerCarryWaterStep usualHouseholdWaterFetcher(String usualHouseholdWaterFetcher) {
        Objects.requireNonNull(usualHouseholdWaterFetcher);
        this.UsualHouseholdWaterFetcher = usualHouseholdWaterFetcher;
        return this;
    }
    
    @Override
     public WaterTreatmentBeforeDrinkingStep containerCarryWater(String containerCarryWater) {
        Objects.requireNonNull(containerCarryWater);
        this.ContainerCarryWater = containerCarryWater;
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
     public RubbishDisposalStep takingWaterFromStorage(String takingWaterFromStorage) {
        Objects.requireNonNull(takingWaterFromStorage);
        this.TakingWaterFromStorage = takingWaterFromStorage;
        return this;
    }
    
    @Override
     public HouseholdDefecationMethodStep rubbishDisposal(String rubbishDisposal) {
        Objects.requireNonNull(rubbishDisposal);
        this.RubbishDisposal = rubbishDisposal;
        return this;
    }
    
    @Override
     public SatisfactionHouseholdDefecationMethodStep householdDefecationMethod(String householdDefecationMethod) {
        Objects.requireNonNull(householdDefecationMethod);
        this.HouseholdDefecationMethod = householdDefecationMethod;
        return this;
    }
    
    @Override
     public WasteDisposalYoungestChildStep satisfactionHouseholdDefecationMethod(String satisfactionHouseholdDefecationMethod) {
        Objects.requireNonNull(satisfactionHouseholdDefecationMethod);
        this.SatisfactionHouseholdDefecationMethod = satisfactionHouseholdDefecationMethod;
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
     public BuildStep healthChangeFamilyInAYear(String healthChangeFamilyInAYear) {
        Objects.requireNonNull(healthChangeFamilyInAYear);
        this.HealthChangeFamilyInAYear = healthChangeFamilyInAYear;
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
    private CopyOfBuilder(String id, String namebwe, String country, String community, Integer surveyId, Temporal.Date date, String headHouseholdName, String headHouseholdSex, String headHouseholdMaritalStatus, Integer headHouseholdAge, String headHouseholdOccupation, String headHouseholdEducation, String personBeingInterviewed, Integer totalNoPeopleHousehold, Integer noHouseholdMale0_1Year, Integer noHouseholdFemale0_1Year, Integer noHouseholdMale1_5Year, Integer noHouseholdFemale1_5Year, Integer noHouseholdMale5_12Year, Integer noHouseholdFemale5_12Year, Integer noHouseholdMale13_17Year, Integer noHouseholdFemale13_17Year, Integer noHouseholdMale18Year, Integer noHouseholdFemale18Year, String reasonNoSchoolChildren5_17Year, String mainSourceDrinkingWater, String mainSourceOtherPurposeWater, Integer timeToWaterSourceGetReturn, String householdFrequencyAtWaterSource, String usualHouseholdWaterFetcher, String containerCarryWater, String waterTreatmentBeforeDrinking, String mainReasonNoWaterTreatmentBeforeDrinking, String waterTreatmentMethod, String howLongUsingWaterTreatment, String frequencyWaterTreatment, String waterStorageAtHome, String takingWaterFromStorage, String rubbishDisposal, String householdDefecationMethod, String satisfactionHouseholdDefecationMethod, String wasteDisposalYoungestChild, String washedHandsIn24Hours, String whenWashedHandsIn24Hours, String whatUsedToWashYourHands, Integer noTotalSchoolDaysMissedByAllChildrenIn2LastWeek, String commonIllnessAffectingChildrenUnder5, Integer noChildrenWithVomitingOrDiarrheaIn7days, String didSickChildrenGoToHospital, String didSickChildrenGoToHospitalYes, String sickChildrenBreastfeeding, String outcomeMostRecentVomitingDiarrheaAtHospital, Integer noDaysNoWorkBecauseOfOwnIllness, Integer noDaysNoWorkBecauseOfIllnessFamilyMembers, Integer moneySpentMedicalTreatmentLast4weeks, String healthChangeInAYear, String healthChangeFamilyInAYear) {
      super.id(id);
      super.namebwe(namebwe)
        .country(country)
        .community(community)
        .surveyId(surveyId)
        .headHouseholdName(headHouseholdName)
        .headHouseholdSex(headHouseholdSex)
        .headHouseholdMaritalStatus(headHouseholdMaritalStatus)
        .headHouseholdAge(headHouseholdAge)
        .headHouseholdOccupation(headHouseholdOccupation)
        .headHouseholdEducation(headHouseholdEducation)
        .personBeingInterviewed(personBeingInterviewed)
        .totalNoPeopleHousehold(totalNoPeopleHousehold)
        .noHouseholdMale0_1Year(noHouseholdMale0_1Year)
        .noHouseholdFemale0_1Year(noHouseholdFemale0_1Year)
        .noHouseholdMale1_5Year(noHouseholdMale1_5Year)
        .noHouseholdFemale1_5Year(noHouseholdFemale1_5Year)
        .noHouseholdMale5_12Year(noHouseholdMale5_12Year)
        .noHouseholdFemale5_12Year(noHouseholdFemale5_12Year)
        .noHouseholdMale13_17Year(noHouseholdMale13_17Year)
        .noHouseholdFemale13_17Year(noHouseholdFemale13_17Year)
        .noHouseholdMale18Year(noHouseholdMale18Year)
        .noHouseholdFemale18Year(noHouseholdFemale18Year)
        .reasonNoSchoolChildren5_17Year(reasonNoSchoolChildren5_17Year)
        .mainSourceDrinkingWater(mainSourceDrinkingWater)
        .mainSourceOtherPurposeWater(mainSourceOtherPurposeWater)
        .timeToWaterSourceGetReturn(timeToWaterSourceGetReturn)
        .householdFrequencyAtWaterSource(householdFrequencyAtWaterSource)
        .usualHouseholdWaterFetcher(usualHouseholdWaterFetcher)
        .containerCarryWater(containerCarryWater)
        .waterTreatmentBeforeDrinking(waterTreatmentBeforeDrinking)
        .mainReasonNoWaterTreatmentBeforeDrinking(mainReasonNoWaterTreatmentBeforeDrinking)
        .waterTreatmentMethod(waterTreatmentMethod)
        .howLongUsingWaterTreatment(howLongUsingWaterTreatment)
        .frequencyWaterTreatment(frequencyWaterTreatment)
        .waterStorageAtHome(waterStorageAtHome)
        .takingWaterFromStorage(takingWaterFromStorage)
        .rubbishDisposal(rubbishDisposal)
        .householdDefecationMethod(householdDefecationMethod)
        .satisfactionHouseholdDefecationMethod(satisfactionHouseholdDefecationMethod)
        .wasteDisposalYoungestChild(wasteDisposalYoungestChild)
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
     public CopyOfBuilder headHouseholdSex(String headHouseholdSex) {
      return (CopyOfBuilder) super.headHouseholdSex(headHouseholdSex);
    }
    
    @Override
     public CopyOfBuilder headHouseholdMaritalStatus(String headHouseholdMaritalStatus) {
      return (CopyOfBuilder) super.headHouseholdMaritalStatus(headHouseholdMaritalStatus);
    }
    
    @Override
     public CopyOfBuilder headHouseholdAge(Integer headHouseholdAge) {
      return (CopyOfBuilder) super.headHouseholdAge(headHouseholdAge);
    }
    
    @Override
     public CopyOfBuilder headHouseholdOccupation(String headHouseholdOccupation) {
      return (CopyOfBuilder) super.headHouseholdOccupation(headHouseholdOccupation);
    }
    
    @Override
     public CopyOfBuilder headHouseholdEducation(String headHouseholdEducation) {
      return (CopyOfBuilder) super.headHouseholdEducation(headHouseholdEducation);
    }
    
    @Override
     public CopyOfBuilder personBeingInterviewed(String personBeingInterviewed) {
      return (CopyOfBuilder) super.personBeingInterviewed(personBeingInterviewed);
    }
    
    @Override
     public CopyOfBuilder totalNoPeopleHousehold(Integer totalNoPeopleHousehold) {
      return (CopyOfBuilder) super.totalNoPeopleHousehold(totalNoPeopleHousehold);
    }
    
    @Override
     public CopyOfBuilder noHouseholdMale0_1Year(Integer noHouseholdMale0_1Year) {
      return (CopyOfBuilder) super.noHouseholdMale0_1Year(noHouseholdMale0_1Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdFemale0_1Year(Integer noHouseholdFemale0_1Year) {
      return (CopyOfBuilder) super.noHouseholdFemale0_1Year(noHouseholdFemale0_1Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdMale1_5Year(Integer noHouseholdMale1_5Year) {
      return (CopyOfBuilder) super.noHouseholdMale1_5Year(noHouseholdMale1_5Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdFemale1_5Year(Integer noHouseholdFemale1_5Year) {
      return (CopyOfBuilder) super.noHouseholdFemale1_5Year(noHouseholdFemale1_5Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdMale5_12Year(Integer noHouseholdMale5_12Year) {
      return (CopyOfBuilder) super.noHouseholdMale5_12Year(noHouseholdMale5_12Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdFemale5_12Year(Integer noHouseholdFemale5_12Year) {
      return (CopyOfBuilder) super.noHouseholdFemale5_12Year(noHouseholdFemale5_12Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdMale13_17Year(Integer noHouseholdMale13_17Year) {
      return (CopyOfBuilder) super.noHouseholdMale13_17Year(noHouseholdMale13_17Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdFemale13_17Year(Integer noHouseholdFemale13_17Year) {
      return (CopyOfBuilder) super.noHouseholdFemale13_17Year(noHouseholdFemale13_17Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdMale18Year(Integer noHouseholdMale18Year) {
      return (CopyOfBuilder) super.noHouseholdMale18Year(noHouseholdMale18Year);
    }
    
    @Override
     public CopyOfBuilder noHouseholdFemale18Year(Integer noHouseholdFemale18Year) {
      return (CopyOfBuilder) super.noHouseholdFemale18Year(noHouseholdFemale18Year);
    }
    
    @Override
     public CopyOfBuilder reasonNoSchoolChildren5_17Year(String reasonNoSchoolChildren5_17Year) {
      return (CopyOfBuilder) super.reasonNoSchoolChildren5_17Year(reasonNoSchoolChildren5_17Year);
    }
    
    @Override
     public CopyOfBuilder mainSourceDrinkingWater(String mainSourceDrinkingWater) {
      return (CopyOfBuilder) super.mainSourceDrinkingWater(mainSourceDrinkingWater);
    }
    
    @Override
     public CopyOfBuilder mainSourceOtherPurposeWater(String mainSourceOtherPurposeWater) {
      return (CopyOfBuilder) super.mainSourceOtherPurposeWater(mainSourceOtherPurposeWater);
    }
    
    @Override
     public CopyOfBuilder timeToWaterSourceGetReturn(Integer timeToWaterSourceGetReturn) {
      return (CopyOfBuilder) super.timeToWaterSourceGetReturn(timeToWaterSourceGetReturn);
    }
    
    @Override
     public CopyOfBuilder householdFrequencyAtWaterSource(String householdFrequencyAtWaterSource) {
      return (CopyOfBuilder) super.householdFrequencyAtWaterSource(householdFrequencyAtWaterSource);
    }
    
    @Override
     public CopyOfBuilder usualHouseholdWaterFetcher(String usualHouseholdWaterFetcher) {
      return (CopyOfBuilder) super.usualHouseholdWaterFetcher(usualHouseholdWaterFetcher);
    }
    
    @Override
     public CopyOfBuilder containerCarryWater(String containerCarryWater) {
      return (CopyOfBuilder) super.containerCarryWater(containerCarryWater);
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
     public CopyOfBuilder rubbishDisposal(String rubbishDisposal) {
      return (CopyOfBuilder) super.rubbishDisposal(rubbishDisposal);
    }
    
    @Override
     public CopyOfBuilder householdDefecationMethod(String householdDefecationMethod) {
      return (CopyOfBuilder) super.householdDefecationMethod(householdDefecationMethod);
    }
    
    @Override
     public CopyOfBuilder satisfactionHouseholdDefecationMethod(String satisfactionHouseholdDefecationMethod) {
      return (CopyOfBuilder) super.satisfactionHouseholdDefecationMethod(satisfactionHouseholdDefecationMethod);
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
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
  }
  
}