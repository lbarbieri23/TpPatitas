package com.spring5.editors;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeEditor extends PropertyEditorSupport {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public void setAsText(String value) {
		try {
			setValue(LocalDateTime.parse(value, formatter));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAsText() {
		String s = "";
		if (getValue() != null) {
			s = formatter.format((LocalDateTime) getValue());
		}
		return s;
	}
}