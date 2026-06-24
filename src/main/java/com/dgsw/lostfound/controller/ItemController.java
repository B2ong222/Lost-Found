package com.dgsw.lostfound.controller;

import com.dgsw.lostfound.domain.Item;
import com.dgsw.lostfound.dto.ItemDto;
import com.dgsw.lostfound.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@Tag(name = "분실물 API", description = "분실물 등록/조회/수정/삭제 기능")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 2. 전체 조회
    @Operation(summary = "분실물 전체 조회")
    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 5. 삭제
    @Operation(summary = "분실물 삭제")
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        if (!itemRepository.existsById(id)) {
            return "해당 id를 찾을 수 없습니다 (id=" + id + ")";
        }
        itemRepository.deleteById(id);
        return "삭제 완료 (id=" + id + ")";
    }

    private ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getLocation(),
                item.getDescription(),
                item.getStatus()
        );
    }
}