	package com.duncancodes.librarymanagement.entities;

	import com.duncancodes.librarymanagement.utils.Address;
	import com.duncancodes.librarymanagement.utils.Contact;

	import javax.persistence.*;

	@Entity
	@Table(name = "users")
	public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.TABLE)
		private Long id;

		private String title;
		private String firstName;
		private String familyName;

		@Embedded
		private Address address;

		@Embedded
		private Contact contact;

		public User(String title, String firstName, String familyName, Address address, Contact contact) {
			this.title = title;
			this.firstName = firstName;
			this.familyName = familyName;
			this.address = address;
			this.contact = contact;
		}

		public User() {		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}
	}
