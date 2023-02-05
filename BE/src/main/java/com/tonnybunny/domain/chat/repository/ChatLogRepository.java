package com.tonnybunny.domain.chat.repository;


import com.tonnybunny.domain.chat.entity.ChatLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatLogRepository extends JpaRepository<ChatLogEntity, Long> {
}
