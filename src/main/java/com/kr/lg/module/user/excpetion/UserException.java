package com.kr.lg.module.user.excpetion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserException extends Exception {

	private UserResultCode resultCode;

	public UserException(UserResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
