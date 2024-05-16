package com.pfairplay.mysql.core.repository.member;


import com.pfairplay.mysql.core.entity.AsyncProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Repository
public interface AsyncProductRepository extends JpaRepository<AsyncProduct, Long> {
    CompletableFuture<List<AsyncProduct>> findAllAsync();

    CompletableFuture<List<AsyncProduct>> findAllByMemberIdAsync(Long memberId);
}
