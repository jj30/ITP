package bldg5.jj.itp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AutoItem {

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

    public AutoItem(Integer nID) {
        this.id = nID;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The replacementValue
     */
    public String getReplacementValue() {
        return replacementValue;
    }

    /**
     *
     * @param replacementValue
     * The replacement_value
     */
    public void setReplacementValue(String replacementValue) {
        this.replacementValue = replacementValue;
    }

    /**
     *
     * @return
     * The valueUnits
     */
    public String getValueUnits() {
        return valueUnits;
    }

    /**
     *
     * @param valueUnits
     * The value_units
     */
    public void setValueUnits(String valueUnits) {
        this.valueUnits = valueUnits;
    }

    /**
     *
     * @return
     * The viewed
     */
    public String getViewed() {
        return viewed;
    }

    /**
     *
     * @param viewed
     * The viewed
     */
    public void setViewed(String viewed) {
        this.viewed = viewed;
    }

    /**
     *
     * @return
     * The condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     *
     * @param condition
     * The condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
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
        if ((other instanceof AutoItem) == false) {
            return false;
        }
        AutoItem rhs = ((AutoItem) other);
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