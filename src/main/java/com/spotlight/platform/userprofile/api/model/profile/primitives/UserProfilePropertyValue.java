package com.spotlight.platform.userprofile.api.model.profile.primitives;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class UserProfilePropertyValue {

    private final Object value;

    @JsonCreator
    private UserProfilePropertyValue(Object value) {
        this.value = value;
    }

    public static UserProfilePropertyValue valueOf(Object value) {
        return new UserProfilePropertyValue(value);
    }

    @JsonValue
    protected Object getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return value.equals(((UserProfilePropertyValue) obj).getValue());
    }

    public boolean IsInt() {
        return value instanceof Integer;
    }

    public static UserProfilePropertyValue Increment(UserProfilePropertyValue propertyValue, 
    UserProfilePropertyValue increment) {
        var newValue = (int)propertyValue.value + (int)increment.value;
        return new UserProfilePropertyValue(newValue);
    }

    public boolean IsArrayList() {
        return value instanceof ArrayList<?>;
    }

    public static UserProfilePropertyValue MergeLists(UserProfilePropertyValue propertyValue, 
    UserProfilePropertyValue increment) {
        var updatedProperty = new ArrayList<Object>();

        if(propertyValue.value instanceof ArrayList)
            updatedProperty.addAll((ArrayList<?>)propertyValue.value);
        else
            updatedProperty.add(propertyValue.value);

        if(increment.value instanceof ArrayList)
            updatedProperty.addAll((ArrayList<?>)increment.value);
        else
            updatedProperty.add(increment.value);

        return new UserProfilePropertyValue(updatedProperty);
    }
}

