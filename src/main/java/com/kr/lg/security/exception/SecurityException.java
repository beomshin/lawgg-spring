package com.kr.lg.security.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Spring Security exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityException extends Exception {

	private SecurityResultCode resultCode;

	public SecurityException(SecurityResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
