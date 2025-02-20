package com.jatheon.ergo.ai.assistant.model.datatables;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataTablesInput {
  @NotNull
  @Min(0)
  private Integer draw;

  @NotNull
  @Min(0)
  private Integer start;

  @NotNull
  @Min(-1)
  private Integer length;

  @NotNull private SearchParameter search;
  @NotEmpty
  private List<OrderParameter> order = new ArrayList<>();
  @NotEmpty private List<ColumnParameter> columns = new ArrayList<>();

  public Integer getPage() {
    return getStart() / getLength() + 1;
  }
}
