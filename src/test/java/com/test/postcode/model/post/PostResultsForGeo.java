package com.test.postcode.model.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResultsForGeo {

    private int status;
    private List<PostSubResultsForGeo> result;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PostSubResultsForGeo> getResult() {
        return result;
    }

    public void setResult(List<PostSubResultsForGeo> result) {
        this.result = result;
    }

}
