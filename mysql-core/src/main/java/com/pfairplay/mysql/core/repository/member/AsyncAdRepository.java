package com.pfairplay.mysql.core.repository.member;


import com.pfairplay.mysql.core.entity.AsyncAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsyncAdRepository extends JpaRepository<AsyncAd, Long> {
    CompletableFuture<List<AsyncAd>> findAllAsync();

    CompletableFuture<List<AsyncAd>> findAllByMemberIdAsync(Long memberId);
}
