package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the ConfigDef type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ConfigDefs")
public final class ConfigDef implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("Name");
  public static final QueryField VALUE = field("Value");
  public static final QueryField DESCRIPTION = field("Description");
  public static final QueryField PARENT_NAME = field("ParentName");
  public static final QueryField PARENT_VALUE = field("ParentValue");
  public static final QueryField PARENT_DESCRIPTION = field("ParentDescription");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Name;
  private final @ModelField(targetType="String", isRequired = true) String Value;
  private final @ModelField(targetType="String", isRequired = true) String Description;
  private final @ModelField(targetType="String", isRequired = true) String ParentName;
  private final @ModelField(targetType="String", isRequired = true) String ParentValue;
  private final @ModelField(targetType="String", isRequired = true) String ParentDescription;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return Name;
  }
  
  public String getValue() {
      return Value;
  }
  
  public String getDescription() {
      return Description;
  }
  
  public String getParentName() {
      return ParentName;
  }
  
  public String getParentValue() {
      return ParentValue;
  }
  
  public String getParentDescription() {
      return ParentDescription;
  }
  
  private ConfigDef(String id, String Name, String Value, String Description, String ParentName, String ParentValue, String ParentDescription) {
    this.id = id;
    this.Name = Name;
    this.Value = Value;
    this.Description = Description;
    this.ParentName = ParentName;
    this.ParentValue = ParentValue;
    this.ParentDescription = ParentDescription;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ConfigDef configDef = (ConfigDef) obj;
      return ObjectsCompat.equals(getId(), configDef.getId()) &&
              ObjectsCompat.equals(getName(), configDef.getName()) &&
              ObjectsCompat.equals(getValue(), configDef.getValue()) &&
              ObjectsCompat.equals(getDescription(), configDef.getDescription()) &&
              ObjectsCompat.equals(getParentName(), configDef.getParentName()) &&
              ObjectsCompat.equals(getParentValue(), configDef.getParentValue()) &&
              ObjectsCompat.equals(getParentDescription(), configDef.getParentDescription());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getValue())
      .append(getDescription())
      .append(getParentName())
      .append(getParentValue())
      .append(getParentDescription())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ConfigDef {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Name=" + String.valueOf(getName()) + ", ")
      .append("Value=" + String.valueOf(getValue()) + ", ")
      .append("Description=" + String.valueOf(getDescription()) + ", ")
      .append("ParentName=" + String.valueOf(getParentName()) + ", ")
      .append("ParentValue=" + String.valueOf(getParentValue()) + ", ")
      .append("ParentDescription=" + String.valueOf(getParentDescription()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
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
  public static ConfigDef justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new ConfigDef(
      id,
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
      Name,
      Value,
      Description,
      ParentName,
      ParentValue,
      ParentDescription);
  }
  public interface NameStep {
    ValueStep name(String name);
  }
  

  public interface ValueStep {
    DescriptionStep value(String value);
  }
  

  public interface DescriptionStep {
    ParentNameStep description(String description);
  }
  

  public interface ParentNameStep {
    ParentValueStep parentName(String parentName);
  }
  

  public interface ParentValueStep {
    ParentDescriptionStep parentValue(String parentValue);
  }
  

  public interface ParentDescriptionStep {
    BuildStep parentDescription(String parentDescription);
  }
  

  public interface BuildStep {
    ConfigDef build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements NameStep, ValueStep, DescriptionStep, ParentNameStep, ParentValueStep, ParentDescriptionStep, BuildStep {
    private String id;
    private String Name;
    private String Value;
    private String Description;
    private String ParentName;
    private String ParentValue;
    private String ParentDescription;
    @Override
     public ConfigDef build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ConfigDef(
          id,
          Name,
          Value,
          Description,
          ParentName,
          ParentValue,
          ParentDescription);
    }
    
    @Override
     public ValueStep name(String name) {
        Objects.requireNonNull(name);
        this.Name = name;
        return this;
    }
    
    @Override
     public DescriptionStep value(String value) {
        Objects.requireNonNull(value);
        this.Value = value;
        return this;
    }
    
    @Override
     public ParentNameStep description(String description) {
        Objects.requireNonNull(description);
        this.Description = description;
        return this;
    }
    
    @Override
     public ParentValueStep parentName(String parentName) {
        Objects.requireNonNull(parentName);
        this.ParentName = parentName;
        return this;
    }
    
    @Override
     public ParentDescriptionStep parentValue(String parentValue) {
        Objects.requireNonNull(parentValue);
        this.ParentValue = parentValue;
        return this;
    }
    
    @Override
     public BuildStep parentDescription(String parentDescription) {
        Objects.requireNonNull(parentDescription);
        this.ParentDescription = parentDescription;
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
    private CopyOfBuilder(String id, String name, String value, String description, String parentName, String parentValue, String parentDescription) {
      super.id(id);
      super.name(name)
        .value(value)
        .description(description)
        .parentName(parentName)
        .parentValue(parentValue)
        .parentDescription(parentDescription);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder value(String value) {
      return (CopyOfBuilder) super.value(value);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder parentName(String parentName) {
      return (CopyOfBuilder) super.parentName(parentName);
    }
    
    @Override
     public CopyOfBuilder parentValue(String parentValue) {
      return (CopyOfBuilder) super.parentValue(parentValue);
    }
    
    @Override
     public CopyOfBuilder parentDescription(String parentDescription) {
      return (CopyOfBuilder) super.parentDescription(parentDescription);
    }
  }
  
}
