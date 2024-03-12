package domain;


import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;

@SuperBuilder
public abstract class AbstractEntity implements Serializable {
    protected final String id;
    protected OffsetDateTime createdTime = OffsetDateTime.now();
    protected String createdBy;
    protected String updatedBy;
    protected OffsetDateTime updatedTime = OffsetDateTime.now();

    public void update(String operatorId) {
        this.updatedBy = operatorId;
        this.updatedTime = OffsetDateTime.now();
    }

    public String getId() {
        return id;
    }

    public OffsetDateTime getCreatedTime() {
        return createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public OffsetDateTime getUpdatedTime() {
        return updatedTime;
    }
}
