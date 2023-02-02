package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.FollowEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from FollowEntity f where (f.user = :user and f.followedUserSeq = :followSeq)")
	@Transactional
	void deleteFollowBySeq(@Param("user") UserEntity user, @Param("followSeq") Long followSeq);

}
