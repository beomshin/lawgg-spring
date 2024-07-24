package com.kr.lg.module.comment.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인증 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentException extends Exception {

	private CommentResultCode resultCode;

	public CommentException(CommentResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
