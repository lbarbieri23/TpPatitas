package com.spring5.editors;

import com.dds.mascotas.model.TipoDocumento;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TipoDocumentoEditor extends PropertyEditorSupport {


	public void setAsText(String value) {
		try {
			setValue(Enum.valueOf(TipoDocumento.class, value));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAsText() {
		if (getValue() == null) {
			return "";
		}
		return getValue().toString();
	}
}