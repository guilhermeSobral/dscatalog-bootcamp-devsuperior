package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		var user = repository.findByEmail(dto.getEmail());
		
		if(isPutRequest()) {
			@SuppressWarnings("unchecked")
			var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);			
			var userId = Long.parseLong(uriVars.get("id"));
			
			if (user != null && userId != user.getId()) {
				list.add(new FieldMessage("email", "Email já existe"));
			}
		}
		
		if(isPostRequest()) {
			if (user != null) {
				list.add(new FieldMessage("email", "Email já existe"));
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	private boolean isPutRequest() {
		return request.getMethod().equals("PUT");
		
	}
	
	private boolean isPostRequest() {
		return request.getMethod().equals("POST");
		
	}
}