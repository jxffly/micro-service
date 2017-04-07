package io.junq.examples.emall.boot.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Pagination {
	//TODO json property next_url
	private String nextUrl;
	//TODO json property next_max_id
	private Long next_max_id;
}
