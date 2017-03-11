
package com.devon.demo.main.model.sapdetailerror;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErrorResolution implements Serializable
{

    @SerializedName("SAP_Transaction")
    @Expose
    private String sAPTransaction;
    @SerializedName("SAP_Note")
    @Expose
    private String sAPNote;
    private final static long serialVersionUID = -2043506267910333451L;

    public String getSAPTransaction() {
        return sAPTransaction;
    }

    public void setSAPTransaction(String sAPTransaction) {
        this.sAPTransaction = sAPTransaction;
    }

    public String getSAPNote() {
        return sAPNote;
    }

    public void setSAPNote(String sAPNote) {
        this.sAPNote = sAPNote;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sAPTransaction).append(sAPNote).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorResolution) == false) {
            return false;
        }
        ErrorResolution rhs = ((ErrorResolution) other);
        return new EqualsBuilder().append(sAPTransaction, rhs.sAPTransaction).append(sAPNote, rhs.sAPNote).isEquals();
    }

}
