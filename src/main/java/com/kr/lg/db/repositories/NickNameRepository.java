package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.NickNameTb;

import java.util.Optional;

public interface NickNameRepository extends RootNickNameRepository {

    Optional<NickNameTb> findByName(String nickName);
}
