package com.kr.lg.module.thirdparty.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서드파티 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThirdPartyException extends Exception {

	private ThirdPartyResultCode resultCode;

	public ThirdPartyException(ThirdPartyResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
