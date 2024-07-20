package com.kr.lg.module.board.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인증 컨트롤러 exception
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardException extends Exception {

	private BoardResultCode resultCode;

	public BoardException(BoardResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

}
