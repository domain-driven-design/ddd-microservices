package domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@SuperBuilder
@Getter
@AllArgsConstructor
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

    protected AbstractEntity(final AbstractEntityBuilder<?, ?> b) {
        this.id = b.id;
        if (Objects.nonNull(b.createdTime)) {
            this.createdTime = b.createdTime;
        }

        this.createdBy = b.createdBy;
        this.updatedBy = b.updatedBy;
        if (Objects.nonNull(b.updatedTime)) {
            this.updatedTime = b.updatedTime;
        }
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
