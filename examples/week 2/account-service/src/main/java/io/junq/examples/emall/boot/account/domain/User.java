package io.junq.examples.emall.boot.account.domain;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Long id;

	private String displayId;

	private String displayName;

	private Integer gender;

	private String avatar;

	private String password;

	private String email;

	private String mobilePhone;

	private Date createdAt;

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
		
		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public Builder withChangedAt(Date changedAt) {
			this.changedAt = changedAt;
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
