package com.jatheon.ergo.ai.assistant.service.util;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.fromString;

@Getter
public class PagingRequest {

  public static final int DEFAULT_PAGE = 0;
  public static final int DEFAULT_SIZE = 50;
  public static final int MAX_SIZE = 100;
  private static final String SORT_DESCENDING_PREFIX = "-";

  public static final String PARAM_SORT = "sort";
  public static final String PARAM_PAGE = "page";
  public static final String PARAM_SIZE = "size";

  public static final String FILTER = "filter";

  private int page;
  private int size;
  private Sort sort = Sort.unsorted();

  public PagingRequest(final Integer page, final Integer size, String sorting) {
    this.page = DEFAULT_PAGE;
    if (page != null && page > 0) {
      this.page = page - 1;
    }

    this.size = DEFAULT_SIZE;
    if (size != null && size > 0) {
      this.size = size > MAX_SIZE ? MAX_SIZE : size;
    }

    if (!StringUtils.isBlank(sorting)) {
      String direction = "ASC";
      if (sorting.startsWith(SORT_DESCENDING_PREFIX)) {
        direction = "DESC";
        sorting = sorting.substring(1);
      }
      this.sort = Sort.by(fromString(direction), sorting);
    }
  }

  public static PagingRequest of(final Integer page, final Integer size, String sorting) {
    return new PagingRequest(page, size, sorting);
  }

  public Pageable toPageable() {
    return PageRequest.of(page, size, sort);
  }
}
