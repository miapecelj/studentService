package mia.pecelj.be.exception;

import mia.pecelj.be.dto.MyDto;

public class MyEntityExistException extends MyApplicationException {

	private static final long serialVersionUID = 1L;

	private final MyDto dto;

	public MyEntityExistException(String message, MyDto dto) {
		super(message);
		this.dto = dto;
	}

	public Object getEntity() {
		return dto;
	}
}
