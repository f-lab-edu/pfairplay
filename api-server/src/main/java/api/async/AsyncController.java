package api.async;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller("/api")
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncService asyncService;

    // 상품 전체조회
    @GetMapping("/async-products")
    public CompletableFuture<List<AsyncProductDto>> findAllProduct() {
        return asyncService.findAllProduct();
    }

    // 멤버, 상품, 광고 각각 전체조회
    @GetMapping("/async-all")
    public CompletableFuture<AsyncAllDto> findAllAsync() {
        return asyncService.findAllAsync();
    }

    // 멤버, 멤버와 관련된 상품, 광고 조회
    @GetMapping("/async-members-products")
    public CompletableFuture<AsyncAllDto> findByMemberWithProduct() {
        return asyncService.findByMemberWithProduct();
    }

    // 멤버, 멤버와 관련된 상품, 멤버와 관련된 광고 조회
    @GetMapping("/async-members-products-ad")
    public CompletableFuture<AsyncAllDto> findByMemberWithProductAndAd() {
        return asyncService.findByMemberWithProductAndAd();
    }
}
