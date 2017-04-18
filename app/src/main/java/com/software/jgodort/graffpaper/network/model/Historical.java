
package com.software.jgodort.graffpaper.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historical {

    @SerializedName("change")
    @Expose
    private Integer change;
    @SerializedName("resolution")
    @Expose
    private String resolution;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("values")
    @Expose
    private List<Value> values = null;

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer change) {
        this.change = change;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}
