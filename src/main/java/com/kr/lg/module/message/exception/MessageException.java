package com.kr.lg.module.message.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 메세지 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageException extends Exception {

	private MessageResultCode resultCode;

	public MessageException(MessageResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
