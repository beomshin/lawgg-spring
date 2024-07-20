package com.kr.lg.security.exception;

import com.kr.lg.module.auth.excpetion.AuthResultCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Spring Security exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityException extends Exception {

	private AuthResultCode resultCode;

	public SecurityException(AuthResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
