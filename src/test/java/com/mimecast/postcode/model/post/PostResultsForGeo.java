package com.mimecast.postcode.model.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResultsForGeo {

    public int status;
    public List<PostSubResultsForGeo> result;

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
