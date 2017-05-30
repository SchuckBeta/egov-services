package org.egov.collection.domain.model;

public enum UserType {
	EMPLOYEE("EMPLOYEE"), CITIZEN("CITIZEN"), SYSTEM("SYSTEM");

	private String value;

	UserType(String value) {
		this.value = value;
	}
}
