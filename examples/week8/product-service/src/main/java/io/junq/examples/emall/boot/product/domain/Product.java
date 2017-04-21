package io.junq.examples.emall.boot.product.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Product {
	
	public final static String SEPERATOR = ",";
	
	private Long id;
	
	@Min(0)
	private Integer type;
	
	@NotNull
	@Size(min = 1, max = 64)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 128)
	private String summary;
	
	@NotNull
	@Size(min = 1, max = 512)
	private String description;
	
	@Min(0)
	private Integer price;
	
	@NotEmpty
	private List<String> images;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date createdAt;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date changedAt;

	protected Product() {
		super();
	}
	
	public static final class Builder {
		private Long id;
		private Integer type;
		private String name;
		private String summary;
		private String description;
		private Integer price;
		private List<String> images;
		private Date createdAt;
		private Date changedAt;

		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder withId(long id) {
			this.id = id;
			return this;
		}
		
		public Builder withType(int type) {
			this.type = type;
			return this;
		}
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder withSummary(String summary) {
			this.summary = summary;
			return this;
		}
		
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		
		public Builder withPrice(int price) {
			this.price = price;
			return this;
		}
		
		public Builder withImages(List<String> images) {
			this.images = images;
			return this;
		}
		
		public Builder addImage(String image) {
			if (StringUtils.isEmpty(image))
				return this;
			
			if (images == null)
				images = new ArrayList<String>();
			
			images.add(image);
			
			return this;
		}
		
		public Builder withCreatedAt(long createdTimestamp) {
			this.createdAt = new DateTime(createdTimestamp).toDate();
			return this;
		}
		
		public Builder withChangedAt(long changedTimestamp) {
			this.changedAt = new DateTime(changedTimestamp).toDate();
			return this;
		}
		
		public Product build() {
			Product product = new Product();
			product.setId(id);
			product.setType(type);
			product.setName(name);
			product.setSummary(summary);
			product.setDescription(description);
			product.setPrice(price);
			product.setImages(images);
			product.setCreatedAt(createdAt);
			product.setChangedAt(changedAt);
			return product;
		}
		
	}
}
