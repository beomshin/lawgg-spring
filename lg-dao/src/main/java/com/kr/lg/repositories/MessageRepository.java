package com.kr.lg.repositories;

import com.kr.lg.enums.entity.element.DelEnum;
import com.kr.lg.enums.entity.element.ReadEnum;
import com.kr.lg.enums.entity.element.ReplyEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends RootMessageRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE MessageTb SET readFlag = :readFlag WHERE messageId = :messageId")
    int readMessage(@Param("messageId") Long messageId, @Param("readFlag") ReadEnum readFlag);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MessageTb SET replyFlag = :replyFlag WHERE messageId = :messageId")
    int reply(@Param("messageId") Long messageId, @Param("replyFlag") ReplyEnum replyFlag);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MessageTb SET senderDelFlag = :senderDelFlag WHERE messageId = :messageId")
    int deleteSender(@Param("messageId") Long messageId, @Param("senderDelFlag") DelEnum senderDelFlag);


    @Transactional
    @Modifying
    @Query(value = "UPDATE MessageTb SET receiverDelFlag = :receiverDelFlag WHERE messageId = :messageId")
    int deleteReceiver(@Param("messageId") Long messageId, @Param("receiverDelFlag") DelEnum receiverDelFlag);

}
