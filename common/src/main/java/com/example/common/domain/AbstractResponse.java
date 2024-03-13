package com.example.common.domain;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;

@SuperBuilder
@Data
public abstract class AbstractResponse implements Serializable {
    protected final String id;
    protected OffsetDateTime createdTime;
    protected String createdBy;
    protected String updatedBy;
}
