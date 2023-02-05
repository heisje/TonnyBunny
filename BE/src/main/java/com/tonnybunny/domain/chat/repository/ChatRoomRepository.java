package com.tonnybunny.domain.chat.repository;


import com.tonnybunny.domain.chat.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {
}
