package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.util.Immutable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/** 
 *  Contains the set of model classes that implement {@link Model}
 * interface.
 */

public final class AmplifyModelProvider implements ModelProvider {
  private static final String AMPLIFY_MODEL_VERSION = "4d18b35418e3edb9e15fdfffa36ff311";
  private static AmplifyModelProvider amplifyGeneratedModelInstance;
  private AmplifyModelProvider() {
    
  }
  
  public static AmplifyModelProvider getInstance() {
    if (amplifyGeneratedModelInstance == null) {
      amplifyGeneratedModelInstance = new AmplifyModelProvider();
    }
    return amplifyGeneratedModelInstance;
  }
  
  /** 
   * Get a set of the model classes.
   * 
   * @return a set of the model classes.
   */
  @Override
   public Set<Class<? extends Model>> models() {
    final Set<Class<? extends Model>> modifiableSet = new HashSet<>(
          Arrays.<Class<? extends Model>>asList(InitialSurvey.class, InitialSurveyDeleteControl.class, FollowUpSurvey.class, HealthCheckSurvey.class, SWEMonthlySummary.class, HouseholdWaterTest.class, CommunityWaterTest.class, CommunityWater.class, ConfigDefinitions.class, BWFSURVEYTOTALS.class, VolunteerHouseholdWaterTest.class, VolunteerHousehold.class, Volunteer.class, VolunteerMonthlySummary.class, Meeting.class, HouseholdAttendingMeeting.class)
        );
    
        return Immutable.of(modifiableSet);
        
  }
  
  /** 
   * Get the version of the models.
   * 
   * @return the version string of the models.
   */
  @Override
   public String version() {
    return AMPLIFY_MODEL_VERSION;
  }
}
