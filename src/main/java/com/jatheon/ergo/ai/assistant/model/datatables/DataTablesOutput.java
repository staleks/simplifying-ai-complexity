package com.jatheon.ergo.ai.assistant.model.datatables;

import lombok.Data;

import java.util.List;

@Data
public class DataTablesOutput<T> {
  private Integer draw;
  private Integer recordsTotal;
  private Integer recordsFiltered;
  private List<T> data;

  private DataTablesOutput(
      final Integer draw,
      final Integer recordsTotal,
      final Integer recordsFiltered,
      final List<T> data) {
    this.draw = draw;
    this.recordsTotal = recordsTotal;
    this.recordsFiltered = recordsFiltered;
    this.data = data;
  }

  public static <T> DataTablesOutput<T> of(
      final Integer draw,
      final Integer recordsTotal,
      final Integer recordsFiltered,
      final List<T> data) {
    return new DataTablesOutput<>(draw, recordsTotal, recordsFiltered, data);
  }
}
