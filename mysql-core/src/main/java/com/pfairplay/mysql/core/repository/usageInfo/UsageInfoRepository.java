package com.pfairplay.mysql.core.repository.usageInfo;

import com.pfairplay.mysql.core.entity.UsageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsageInfoRepository extends JpaRepository<UsageInfo, Long> {
    Optional<UsageInfo> findByMemberId(String id);
}
