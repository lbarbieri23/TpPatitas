package com.spring5;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dds.mascotas.model.TipoDocumento;
import com.spring5.editors.LocalDateEditor;
import com.spring5.editors.LocalDateTimeEditor;
import com.spring5.editors.TipoDocumentoEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


@ControllerAdvice
public class GlobalBindingInitializer {

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(5000);
		binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
		binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor());
		binder.registerCustomEditor(TipoDocumento.class, new TipoDocumentoEditor());
	}

}
