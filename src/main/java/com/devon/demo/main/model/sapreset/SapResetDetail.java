package com.devon.demo.main.model.sapreset;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by diwenlao on 2/28/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "d"
})
public class SapResetDetail {

    @JsonProperty("d")
    private D d;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("d")
    public D getD() {
        return d;
    }

    @JsonProperty("d")
    public void setD(D d) {
        this.d = d;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}