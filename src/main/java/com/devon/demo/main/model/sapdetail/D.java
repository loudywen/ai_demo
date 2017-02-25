package com.devon.demo.main.model.sapdetail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by diwenlao on 2/25/17.
 */
public class D {

    @JsonProperty("__metadata")
    private _metadata _metadata;
    @JsonProperty("Username")
    private String username;
    @JsonProperty("AgrName")
    private String agrname;
    @JsonProperty("FromDat")
    private String fromdat;
    @JsonProperty("ToDat")
    private String todat;
    @JsonProperty("AgrText")
    private String agrtext;
    @JsonProperty("OrgFlag")
    private String orgflag;

    public void set_metadata(_metadata _metadata) {
        this._metadata = _metadata;
    }

    public _metadata get_metadata() {
        return _metadata;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAgrname(String agrname) {
        this.agrname = agrname;
    }

    public String getAgrname() {
        return agrname;
    }

    public void setFromdat(String fromdat) {
        this.fromdat = fromdat;
    }

    public String getFromdat() {
        return fromdat;
    }

    public void setTodat(String todat) {
        this.todat = todat;
    }

    public String getTodat() {
        return todat;
    }

    public void setAgrtext(String agrtext) {
        this.agrtext = agrtext;
    }

    public String getAgrtext() {
        return agrtext;
    }

    public void setOrgflag(String orgflag) {
        this.orgflag = orgflag;
    }

    public String getOrgflag() {
        return orgflag;
    }

}
