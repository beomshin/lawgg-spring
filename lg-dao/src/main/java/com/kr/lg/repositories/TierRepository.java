package com.kr.lg.repositories;

import com.kr.lg.entities.TierTb;

public interface TierRepository extends RootTierRepository {

    TierTb findByKey(String key);
}
