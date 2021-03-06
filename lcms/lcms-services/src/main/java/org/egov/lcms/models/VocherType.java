package org.egov.lcms.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Shubham
 *	This Enum holds information about the voucher type
 */
public enum VocherType {

	PAYMENT("Payment"),

	RECEIPT("Receipt");

	private String value;

	VocherType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return name().toUpperCase();
	}

	@JsonCreator
	public static VocherType fromValue(String text) {
		for (VocherType vocherType : VocherType.values()) {
			if (String.valueOf(vocherType.value).equals(text)) {
				return vocherType;
			}
		}
		return null;
	}

}
