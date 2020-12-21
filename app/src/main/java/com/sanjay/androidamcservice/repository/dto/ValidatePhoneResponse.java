package com.sanjay.androidamcservice.repository.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidatePhoneResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("detail")
    @Expose
    private String detail;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}