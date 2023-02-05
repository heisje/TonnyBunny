package com.tonnybunny.domain.chat.repository;


import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {

	Optional<ChatRoomEntity> findByUserLessSeqAndUserLargerSeq(Long userLessSeq, Long userLargerSeq);

	List<ChatRoomEntity> findByUserLessSeqOrUserLargerSeq(Long userSeq1, Long userSeq2);

}
