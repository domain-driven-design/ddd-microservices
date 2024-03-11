package com.ddd.base.application.dto;

import com.ddd.base.domain.aggregate.branch.Branch;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.UUID;


@Data
@Builder
public class BranchCreateCommand {
    @NotBlank
    private String parentId;
    @NotBlank
    private String name;
    @NotBlank
    private String l1BranchId;
    @NotBlank
    private String parentName;
    @NotBlank
    private String pathName;
    @NotBlank
    private String level;

    public Branch toEntity(String userId) {
        return Branch.builder()
                .id(UUID.randomUUID().toString())
                .parentId(this.parentId)
                .name(this.name)
                .l1BranchId(this.l1BranchId)
                .parentName(this.parentName)
                .pathName(this.pathName)
                .level(this.level)
                .updatedBy(userId)
                .updatedTime(OffsetDateTime.now())
                .createdBy(userId)
                .createdTime(OffsetDateTime.now())
                .build();
    }
}

