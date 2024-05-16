package api.async;

import com.pfairplay.mysql.core.entity.AsyncProduct;
import com.pfairplay.mysql.core.repository.member.AsyncAdRepository;
import com.pfairplay.mysql.core.repository.member.AsyncMemberRepository;
import com.pfairplay.mysql.core.repository.member.AsyncProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final AsyncMemberRepository asyncMemberRepository;

    private final AsyncProductRepository asyncProductRepository;

    private final AsyncAdRepository asyncAdRepository;

    // 상품 전체조회
    @Async
    public CompletableFuture<List<AsyncProductDto>> findAllProduct() {
        CompletableFuture<List<AsyncProduct>> products = asyncProductRepository.findAllAsync();

        return products.thenApply(productList ->
                productList.stream()
                        .map(AsyncProductDto::fromEntity)
                        .toList()
        );
    }

    // 멤버, 상품, 광고 각각 전체조회
    @Async
    public CompletableFuture<AsyncAllDto> findAllAsync() {
        // 멤버, 상품, 광고 조회 3개의 작업이 각각 비동기로 처리
        CompletableFuture<List<AsyncMemberDto>> membersFuture = this.findAllMembers();
        CompletableFuture<List<AsyncProductDto>> productsFuture = this.findAllProducts();
        CompletableFuture<List<AsyncAdDto>> adsFuture = this.findAllAds();

        // 작업을 합쳐서 변환 후 return
        return CompletableFuture.allOf(membersFuture, productsFuture, adsFuture)
                .thenApply(all -> {
                    AsyncAllDto asyncAllDto = new AsyncAllDto();
                    asyncAllDto.setMembers(membersFuture.join());
                    asyncAllDto.setProducts(productsFuture.join());
                    asyncAllDto.setAd(adsFuture.join());
                    return asyncAllDto;
                });
    }

    // 멤버, 멤버와 관련된 상품, 광고 조회
    @Async
    public CompletableFuture<AsyncAllDto> findByMemberWithProduct() {
        Long id = 1L;

        // 멤버 조회
        CompletableFuture<AsyncMemberDto> memberFuture = this.findByIdAsync(id);

        // 멤버와 관련된 상품 조회 memberId를 사용해서 조회하기 때문에 thenCompose 사용
        CompletableFuture<List<AsyncProductDto>> productsFuture = memberFuture.thenCompose(member ->
                findAllProductsByMemberId(member.getId())
        );

        // 광고 조회
        CompletableFuture<List<AsyncAdDto>> adsFuture = findAllAds();

        // 작업을 합쳐서 변환 후 return
        return CompletableFuture.allOf(memberFuture, productsFuture, adsFuture)
                .thenApply(all -> {
                    AsyncAllDto asyncAllDto = new AsyncAllDto();
                    asyncAllDto.setMembers(List.of(memberFuture.join()));
                    asyncAllDto.setProducts(productsFuture.join());
                    asyncAllDto.setAd(adsFuture.join());
                    return asyncAllDto;
                });
    }

    // 멤버, 멤버와 관련된 상품, 멤버와 관련된 광고 조회
    @Async
    public CompletableFuture<AsyncAllDto> findByMemberWithProductAndAd() {
        Long id = 1L;

        // 멤버 조회
        CompletableFuture<AsyncMemberDto> memberFuture = this.findByIdAsync(id);

        // 멤버와 관련된 상품 조회 memberId를 사용해서 조회하기 때문에 thenCompose 사용
        CompletableFuture<List<AsyncProductDto>> productsFuture = memberFuture.thenCompose(member ->
                findAllProductsByMemberId(member.getId())
        );

        // 멤버와 관련된 광고 조회 memberId를 사용해서 조회하기 때문에 thenCompose 사용
        CompletableFuture<List<AsyncAdDto>> adsFuture = memberFuture.thenCompose(member ->
                this.findAllAdsByMemberId(member.getId())
        );

        // 작업을 합쳐서 변환 후 return
        return CompletableFuture.allOf(memberFuture, productsFuture, adsFuture)
                .thenApply(all -> {
                    AsyncAllDto asyncAllDto = new AsyncAllDto();
                    asyncAllDto.setMembers(List.of(memberFuture.join()));
                    asyncAllDto.setProducts(productsFuture.join());
                    asyncAllDto.setAd(adsFuture.join());
                    return asyncAllDto;
                });
    }

    // 멤버 전체 조회
    @Async
    public CompletableFuture<List<AsyncMemberDto>> findAllMembers() {
        return asyncMemberRepository.findAllAsync()
                .thenApply(members -> members.stream()
                        .map(AsyncMemberDto::fromEntity)
                        .toList()
                );
    }

    // 멤버 id로 조회
    @Async
    public CompletableFuture<AsyncMemberDto> findByIdAsync(Long id) {
        return asyncMemberRepository.findByIdAsync(id)
                .thenApply(AsyncMemberDto::fromEntity);
    }

    // 상품 전체 조회
    @Async
    public CompletableFuture<List<AsyncProductDto>> findAllProducts() {
        return asyncProductRepository.findAllAsync()
                .thenApply(products -> products.stream()
                        .map(AsyncProductDto::fromEntity)
                        .toList()
                );
    }

    // 멤버id로 관련 상품 조회
    @Async
    public CompletableFuture<List<AsyncProductDto>> findAllProductsByMemberId(Long memberId) {
        return asyncProductRepository.findAllByMemberIdAsync(memberId)
                .thenApply(products -> products.stream()
                        .map(AsyncProductDto::fromEntity)
                        .toList()
                );
    }

    // 광고 전체 조회
    @Async
    public CompletableFuture<List<AsyncAdDto>> findAllAds() {
        return asyncAdRepository.findAllAsync()
                .thenApply(ads -> ads.stream()
                        .map(AsyncAdDto::fromEntity)
                        .toList()
                );
    }

    // 멤버id로 관련 상품 조회
    @Async
    public CompletableFuture<List<AsyncAdDto>> findAllAdsByMemberId(Long memberId) {
        return asyncAdRepository.findAllByMemberIdAsync(memberId)
                .thenApply(ads -> ads.stream()
                        .map(AsyncAdDto::fromEntity)
                        .toList()
                );
    }

}
