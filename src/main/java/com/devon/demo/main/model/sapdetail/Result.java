
package com.devon.demo.main.model.sapdetail;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result implements Serializable
{

    @SerializedName("__metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("AgrName")
    @Expose
    private String agrName;
    @SerializedName("FromDat")
    @Expose
    private String fromDat;
    @SerializedName("ToDat")
    @Expose
    private String toDat;
    @SerializedName("AgrText")
    @Expose
    private String agrText;
    @SerializedName("OrgFlag")
    @Expose
    private String orgFlag;
    private final static long serialVersionUID = -6322112294319552855L;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAgrName() {
        return agrName;
    }

    public void setAgrName(String agrName) {
        this.agrName = agrName;
    }

    public String getFromDat() {
        return fromDat;
    }

    public void setFromDat(String fromDat) {
        this.fromDat = fromDat;
    }

    public String getToDat() {
        return toDat;
    }

    public void setToDat(String toDat) {
        this.toDat = toDat;
    }

    public String getAgrText() {
        return agrText;
    }

    public void setAgrText(String agrText) {
        this.agrText = agrText;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(metadata).append(username).append(agrName).append(fromDat).append(toDat).append(agrText).append(orgFlag).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(metadata, rhs.metadata).append(username, rhs.username).append(agrName, rhs.agrName).append(fromDat, rhs.fromDat).append(toDat, rhs.toDat).append(agrText, rhs.agrText).append(orgFlag, rhs.orgFlag).isEquals();
    }

}
