package bldg5.jj.itp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Item
        implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("replacement_value")
    @Expose
    private String replacementValue;
    @SerializedName("value_units")
    @Expose
    private String valueUnits;
    @SerializedName("viewed")
    @Expose
    private String viewed;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("description")
    @Expose
    private String description;

    public Item(Integer nID) {
        this.id = nID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(String replacementValue) {
        this.replacementValue = replacementValue;
    }

    public String getValueUnits() {
        return valueUnits;
    }

    public void setValueUnits(String valueUnits) {
        this.valueUnits = valueUnits;
    }

    public String getViewed() {
        return viewed;
    }

    public void setViewed(String viewed) {
        this.viewed = viewed;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();

        /*      .append(photo)
                .append(title)
                .append(replacementValue)
                .append(valueUnits)
                .append(viewed)
                .append(condition)
                .append(description)*/
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(id, rhs.id).isEquals();

                /*.append(photo, rhs.photo)
                .append(title, rhs.title)
                .append(replacementValue, rhs.replacementValue)
                .append(valueUnits, rhs.valueUnits)
                .append(viewed, rhs.viewed)
                .append(condition, rhs.condition)
                .append(description, rhs.description)*/
    }
}