package com.kr.lg.exception;

import com.kr.lg.web.dto.global.GlobalCode;
import lombok.Getter;

@Getter
public class LgException extends Exception {

	private final GlobalCode code;

	public LgException(GlobalCode code) {
		super(code.getMsg());
		this.code = code;
	}

}
