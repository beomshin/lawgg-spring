package com.kr.lg.module.login.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginException extends Exception {

	private LoginResultCode resultCode;

	public LoginException(LoginResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
