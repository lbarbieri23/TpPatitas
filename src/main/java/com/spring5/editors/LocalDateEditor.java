package com.spring5.editors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateEditor extends PropertyEditorSupport {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void setAsText(String value) {
		try {
			setValue(LocalDate.parse(value, formatter));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAsText() {
		String s = "";
		if (getValue() != null) {
			s = formatter.format((LocalDate) getValue());
		}
		return s;
	}
}