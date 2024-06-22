package com.kr.lg.repositories;

import com.kr.lg.entities.NickNameTb;

import java.util.Optional;

public interface NickNameRepository extends RootNickNameRepository {

    Optional<NickNameTb> findByName(String nickName);
}
