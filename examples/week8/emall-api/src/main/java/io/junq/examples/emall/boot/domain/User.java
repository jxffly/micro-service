package io.junq.examples.emall.boot.domain;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class User {
	@JsonIgnore
	private Long id;

	private String displayId;

	@NotBlank
	private String displayName;

	@Min(0)
	private Integer gender;

	@NotBlank
	private String avatar;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotBlank
	private String mobilePhone;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date createdAt;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ZZZZZ")
	private Date changedAt;

	protected User() {
		super();
	}

	public static final class Builder {
		private String displayId;
		private String displayName;
		private Integer gender;
		private String avatar;
		private String password;
		private String email;
		private String mobilePhone;
		private Date createdAt;
		private Date changedAt;

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder withDisplayId(String displayId) {
			this.displayId = displayId;
			return this;
		}
		
		public Builder withDisplayName(String displayName) {
			this.displayName = displayName;
			return this;
		}
		
		public Builder withGender(int gender) {
			this.gender = gender;
			return this;
		}
		
		public Builder withAvatar(String avatar) {
			this.avatar = avatar;
			return this;
		}
		
		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}
		
		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Builder withMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
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
		
		public User build() {
			User user = new User();
			user.setDisplayId(displayId);
			user.setDisplayName(displayName);
			user.setGender(gender);
			user.setAvatar(avatar);
			user.setPassword(password);
			user.setEmail(email);
			user.setMobilePhone(mobilePhone);
			user.setCreatedAt(createdAt);
			user.setChangedAt(changedAt);
			return user;
		}
	}
}
