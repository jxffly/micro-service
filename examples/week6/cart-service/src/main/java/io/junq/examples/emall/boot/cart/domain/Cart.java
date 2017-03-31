package io.junq.examples.emall.boot.cart.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Cart {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Cart.class);
	
	private Long id;
	
	@NotNull
    public String customerId;
	
	public List<Item> items;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date createdAt;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date changedAt;
	
	public void removeItem(String itemId) {
		if (!hasItem(itemId))
			return;
		
		Item oldItem = findItem(itemId);
		int index = this.items.indexOf(oldItem);
		this.items.remove(index);
	}
	
	public void addItem(Item item) {
		if (hasItem(item.getItemId())) {
			Item oldItem = findItem(item.getItemId());
			int index = this.items.indexOf(oldItem);
			
			Item newItem = Item.Builder.newBuilder()
					.withItemId(item.getItemId())
					.withPrice(item.getPrice())
					.withQuantity(item.getQuantity() + oldItem.getQuantity())
					.build()
					;
			this.items.set(index, newItem);
		} else {
			addItemIntoCart(item);
		}
	}
	
	public Item findItem(String itemId) {
		if (CollectionUtils.isEmpty(items) || StringUtils.isEmpty(itemId))
			return null;
		
		for (Item item : items) {
			if (item.getItemId().equalsIgnoreCase(itemId))
				return item;
		}
		
		return null;
	}
	
	private void addItemIntoCart(Item item) {
		if (CollectionUtils.isEmpty(items))
			this.items = new ArrayList<Item>();
		
		this.items.add(item);
	}
	
	public boolean hasItem(String itemId) {
		return findItem(itemId) == null ? false : true;
	}
	
	public static final class Builder {
		private Long id;
		private String customerId;
		private List<Item> items;
		private Date createdAt;
		private Date changedAt;
		
		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder withId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder withCustomerId(String customerId) {
			this.customerId = customerId;
			return this;
		}
		
		public Builder withItems(List<Item> items) {
			this.items = items;
			return this;
		}
		
		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public Builder withCreatedAt(long createdTimestamp) {
			this.createdAt = new DateTime(createdTimestamp).toDate();
			return this;
		}
		
		public Builder withChangedAt(Date changedAt) {
			this.changedAt = changedAt;
			return this;
		}
		
		public Builder withChangeAt(long changedTimestamp) {
			this.changedAt = new DateTime(changedTimestamp).toDate();
			return this;
		}

		public Builder withItems(String itemsStr) {
			if (StringUtils.isEmpty(itemsStr)) {
				this.items = null;
				return this;
			}

			try {
				this.items = new ObjectMapper().readValue(itemsStr, new TypeReference<List<Item>>(){});
			} catch (Exception e) {
				LOGGER.warn(e.getMessage());
			}
			return this;
		}
		
		public Cart build() {
			Cart cart = new Cart();
			cart.setId(id);
			cart.setCustomerId(customerId);
			cart.setItems(items);
			cart.setCreatedAt(createdAt);
			cart.setChangedAt(changedAt);
			return cart;
		}
	}
}
