package com.kr.lg.module.trial.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로펌 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrialException extends Exception {

	private TrialResultCode resultCode;

	public TrialException(TrialResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
