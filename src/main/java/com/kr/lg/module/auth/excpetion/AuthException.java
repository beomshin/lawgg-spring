package com.kr.lg.module.auth.excpetion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인증 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthException extends Exception {

	private AuthResultCode resultCode;

	public AuthException(AuthResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
