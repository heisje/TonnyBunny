package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.BlockEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BlockRepository extends JpaRepository<BlockEntity, Long> {

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from BlockEntity b where (b.user = :user and b.blockedUserSeq = :blockSeq)")
	@Transactional
	void deleteBlockBySeq(@Param("user") UserEntity user, @Param("blockSeq") Long blockSeq);

}
