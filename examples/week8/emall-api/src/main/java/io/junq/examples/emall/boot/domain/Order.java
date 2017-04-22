package io.junq.examples.emall.boot.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Order {
	
	public enum Status {
		
		PAID("已付款", 100),
		NOT_PAID("未付款", 110),
		FAIL_PAY("付款失败", 120),
		FINISHED("已完成", 200),
		CANCELED("已取消", 210),
		SHIPPED("已发货", 220)
		//TODO 退货状态时该如何处理？
		;
		
		private final int code;
		
		private final String name;
		
		Status(String name, int code) {
			this.name = name;
			this.code = code;
		}
	}
	
	private Long id;
	
	@NotBlank
	@JsonProperty("user_id")
	private String userId;
	
	@NotBlank
	private String products;
	
	@Min(0)
	private Integer amount;
	
	@NotNull
	private Integer status;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date createdAt;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date changedAt;
	
	@JsonProperty("payment_type")
	private String paymentType;
	
	private String note;
	
	protected Order() {
		super();
	}
	
	public static final class Builder {
		private Long id;
		private String userId;
		private String products;
		private Integer amount;
		private Integer status;
		private String paymentType;
		private String note;
		private Date createdAt;
		private Date changedAt;
		
		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder withId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder withUserId(String userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder withProducts(String products) {
			this.products = products;
			return this;
		}
		
		public Builder withAmount(Integer amount) {
			this.amount = amount;
			return this;
		}
		
		public Builder withStatus(Integer status) {
			this.status = status;
			return this;
		}
		
		public Builder withPaymentType(String paymentType) {
			this.paymentType = paymentType;
			return this;
		}
		
		public Builder withNote(String note) {
			this.note = note;
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
		
		public Order build() {
			Order order = new Order();
			order.setId(id);
			order.setUserId(userId);
			order.setProducts(products);
			order.setAmount(amount);
			order.setStatus(status);
			order.setPaymentType(paymentType);
			order.setNote(note);
			order.setCreatedAt(createdAt);
			order.setChangedAt(changedAt);
			return order;
		}
	}
}
