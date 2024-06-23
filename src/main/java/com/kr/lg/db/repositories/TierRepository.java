package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TierTb;

public interface TierRepository extends RootTierRepository {

    TierTb findByKey(String key);
}
