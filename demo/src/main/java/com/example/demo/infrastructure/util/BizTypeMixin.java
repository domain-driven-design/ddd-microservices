package com.example.demo.infrastructure.util;

import com.example.demo.domain.aggregate.DemoBizType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DemoBizType.class)
public interface BizTypeMixin {
}
