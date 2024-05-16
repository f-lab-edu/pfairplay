package com.pfairplay.mysql.core.repository.member;


import com.pfairplay.mysql.core.entity.AsyncMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsyncMemberRepository extends JpaRepository<AsyncMember, Long> {
    CompletableFuture<AsyncMember> findByIdAsync(Long id);
    CompletableFuture<List<AsyncMember>> findAllAsync();
}
