
package com.devon.demo.main.model.sapdetail;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class D implements Serializable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    private final static long serialVersionUID = 3356736698439260759L;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(results).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof D) == false) {
            return false;
        }
        D rhs = ((D) other);
        return new EqualsBuilder().append(results, rhs.results).isEquals();
    }

}
