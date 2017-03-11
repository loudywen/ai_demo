
package com.devon.demo.main.model.sapunlock;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class D implements Serializable
{

    @SerializedName("__metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("LogNo")
    @Expose
    private String logNo;
    @SerializedName("LogMsgNo")
    @Expose
    private String logMsgNo;
    @SerializedName("MessageV1")
    @Expose
    private String messageV1;
    @SerializedName("MessageV2")
    @Expose
    private String messageV2;
    @SerializedName("MessageV3")
    @Expose
    private String messageV3;
    @SerializedName("MessageV4")
    @Expose
    private String messageV4;
    @SerializedName("Parameter")
    @Expose
    private String parameter;
    @SerializedName("Row")
    @Expose
    private Integer row;
    @SerializedName("Field")
    @Expose
    private String field;
    @SerializedName("System")
    @Expose
    private String system;
    private final static long serialVersionUID = -4561647230625221788L;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    public String getLogMsgNo() {
        return logMsgNo;
    }

    public void setLogMsgNo(String logMsgNo) {
        this.logMsgNo = logMsgNo;
    }

    public String getMessageV1() {
        return messageV1;
    }

    public void setMessageV1(String messageV1) {
        this.messageV1 = messageV1;
    }

    public String getMessageV2() {
        return messageV2;
    }

    public void setMessageV2(String messageV2) {
        this.messageV2 = messageV2;
    }

    public String getMessageV3() {
        return messageV3;
    }

    public void setMessageV3(String messageV3) {
        this.messageV3 = messageV3;
    }

    public String getMessageV4() {
        return messageV4;
    }

    public void setMessageV4(String messageV4) {
        this.messageV4 = messageV4;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(metadata).append(username).append(type).append(id).append(number).append(message).append(logNo).append(logMsgNo).append(messageV1).append(messageV2).append(messageV3).append(messageV4).append(parameter).append(row).append(field).append(system).toHashCode();
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
        return new EqualsBuilder().append(metadata, rhs.metadata).append(username, rhs.username).append(type, rhs.type).append(id, rhs.id).append(number, rhs.number).append(message, rhs.message).append(logNo, rhs.logNo).append(logMsgNo, rhs.logMsgNo).append(messageV1, rhs.messageV1).append(messageV2, rhs.messageV2).append(messageV3, rhs.messageV3).append(messageV4, rhs.messageV4).append(parameter, rhs.parameter).append(row, rhs.row).append(field, rhs.field).append(system, rhs.system).isEquals();
    }

}
