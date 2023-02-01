package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BlockRepository extends JpaRepository {

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from BlockEntity f where (f.user = :user and f.blockedUserSeq = :blockSeq)")
	@Transactional
	void deleteFollowBySeq(@Param("user") UserEntity user, @Param("blockSeq") Long blockSeq);

}
