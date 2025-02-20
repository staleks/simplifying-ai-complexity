package com.jatheon.ergo.ai.assistant.model.datatables;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ColumnParameter {
  @NotBlank private String data;
  private String name;
  @NotNull private Boolean searchable;
  @NotNull private Boolean orderable;
  @NotNull private SearchParameter search;
}
