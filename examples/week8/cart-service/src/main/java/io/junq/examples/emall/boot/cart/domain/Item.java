package io.junq.examples.emall.boot.cart.domain;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Item {
	
	@NotBlank
	@JsonProperty("item_id")
	private String itemId;
	
	/**
	 * 数量
	 */
	@Min(0)
	private int quantity;
	
	/**
	 * 单价(分)
	 */
	@Min(0)
	private int price;
	
	protected Item() {
		super();
	}
	
	public static final class Builder {
		private String itemId;
		private int quantity;
		private int price;
		
		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder withItemId(String itemId) {
			this.itemId = itemId;
			return this;
		}
		
		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public Builder withPrice(int price) {
			this.price = price;
			return this;
		}
		
		public Item build() {
			Item item = new Item();
			item.setItemId(itemId);
			item.setQuantity(quantity);
			item.setPrice(price);
			return item;
		}
	}
}
