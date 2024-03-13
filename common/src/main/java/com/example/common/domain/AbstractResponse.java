package com.example.common.domain;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public abstract class AbstractResponse implements Serializable {
    private String id;
    private OffsetDateTime createdTime;
    private String createdBy;
    private String updatedBy;
    private OffsetDateTime updatedTime;

    public AbstractResponse() {

    }
}
