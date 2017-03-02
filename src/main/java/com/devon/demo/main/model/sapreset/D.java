package com.devon.demo.main.model.sapreset;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "__metadata",
        "Username",
        "Type",
        "Id",
        "Number",
        "Message",
        "LogNo",
        "LogMsgNo",
        "MessageV1",
        "MessageV2",
        "MessageV3",
        "MessageV4",
        "Parameter",
        "Row",
        "Field",
        "System"
})
public class D {

    @JsonProperty("__metadata")
    private Metadata metadata;
    @JsonProperty("Username")
    private String username;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Number")
    private String number;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("LogNo")
    private String logNo;
    @JsonProperty("LogMsgNo")
    private String logMsgNo;
    @JsonProperty("MessageV1")
    private String messageV1;
    @JsonProperty("MessageV2")
    private String messageV2;
    @JsonProperty("MessageV3")
    private String messageV3;
    @JsonProperty("MessageV4")
    private String messageV4;
    @JsonProperty("Parameter")
    private String parameter;
    @JsonProperty("Row")
    private Integer row;
    @JsonProperty("Field")
    private String field;
    @JsonProperty("System")
    private String system;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("__metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("__metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("Username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("Username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("Number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("LogNo")
    public String getLogNo() {
        return logNo;
    }

    @JsonProperty("LogNo")
    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    @JsonProperty("LogMsgNo")
    public String getLogMsgNo() {
        return logMsgNo;
    }

    @JsonProperty("LogMsgNo")
    public void setLogMsgNo(String logMsgNo) {
        this.logMsgNo = logMsgNo;
    }

    @JsonProperty("MessageV1")
    public String getMessageV1() {
        return messageV1;
    }

    @JsonProperty("MessageV1")
    public void setMessageV1(String messageV1) {
        this.messageV1 = messageV1;
    }

    @JsonProperty("MessageV2")
    public String getMessageV2() {
        return messageV2;
    }

    @JsonProperty("MessageV2")
    public void setMessageV2(String messageV2) {
        this.messageV2 = messageV2;
    }

    @JsonProperty("MessageV3")
    public String getMessageV3() {
        return messageV3;
    }

    @JsonProperty("MessageV3")
    public void setMessageV3(String messageV3) {
        this.messageV3 = messageV3;
    }

    @JsonProperty("MessageV4")
    public String getMessageV4() {
        return messageV4;
    }

    @JsonProperty("MessageV4")
    public void setMessageV4(String messageV4) {
        this.messageV4 = messageV4;
    }

    @JsonProperty("Parameter")
    public String getParameter() {
        return parameter;
    }

    @JsonProperty("Parameter")
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @JsonProperty("Row")
    public Integer getRow() {
        return row;
    }

    @JsonProperty("Row")
    public void setRow(Integer row) {
        this.row = row;
    }

    @JsonProperty("Field")
    public String getField() {
        return field;
    }

    @JsonProperty("Field")
    public void setField(String field) {
        this.field = field;
    }

    @JsonProperty("System")
    public String getSystem() {
        return system;
    }

    @JsonProperty("System")
    public void setSystem(String system) {
        this.system = system;
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