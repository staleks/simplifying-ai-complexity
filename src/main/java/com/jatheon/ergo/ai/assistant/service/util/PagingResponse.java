package com.jatheon.ergo.ai.assistant.service.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class PagingResponse<T> {

  private List<T> items;
  private int totalPages;
  private int page;
  private int size;
  private int total;

  public <R> PagingResponse(final Page<R> pageObject, final Function<R, T> mapper) {
    this.items = pageObject.getContent().stream().map(mapper).collect(toList());
    this.totalPages = pageObject.getTotalPages();
    this.page = pageObject.getNumber() + 1;
    this.size = pageObject.getNumberOfElements();
    this.total = (int) pageObject.getTotalElements();
  }

  /**
   *
   * @param items - items of result
   * @param totalPages - totalPages
   * @param page - curentPage
   * @param size - currentSize
   * @param total - ?
   */
  public PagingResponse(
      final List<T> items, final int totalPages, final int page, final int size, final int total) {
    this.items = items;
    this.totalPages = totalPages;
    this.page = page;
    this.size = size;
    this.total = total;
  }
}
