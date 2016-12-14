package bldg5.jj.itp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AutoItems {




    @SerializedName("auto_items")
    @Expose
    private List<AutoItem> autoItems = null;

    /**
     *
     * @return
     * The autoItems
     */
    public List<AutoItem> getAutoItems() {
        return autoItems;
    }

    /**
     *
     * @param autoItems
     * The auto_items
     */
    public void setAutoItems(List<AutoItem> autoItems) {
        this.autoItems = autoItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
