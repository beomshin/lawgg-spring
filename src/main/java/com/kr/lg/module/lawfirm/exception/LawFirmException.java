package com.kr.lg.module.lawfirm.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로펌 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LawFirmException extends Exception {

	private LawFirmResultCode resultCode;

	public LawFirmException(LawFirmResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
