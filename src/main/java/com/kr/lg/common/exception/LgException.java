package com.kr.lg.common.exception;

import com.kr.lg.web.common.global.GlobalCode;
import lombok.Getter;

@Getter
public class LgException extends Exception {

	private final GlobalCode code;

	public LgException(GlobalCode code) {
		super(code.getMsg());
		this.code = code;
	}

}
