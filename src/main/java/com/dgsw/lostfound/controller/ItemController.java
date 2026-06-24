package com.dgsw.lostfound.controller;

import com.dgsw.lostfound.domain.Item;
import com.dgsw.lostfound.dto.ItemDto;
import com.dgsw.lostfound.model.ItemStatus;
import com.dgsw.lostfound.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@Tag(name = "분실물 API", description = "분실물 등록/조회/수정/삭제 기능")
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 1. 등록
    @Operation(summary = "분실물 등록")
    @PostMapping
    public ItemDto createItem(@RequestBody ItemDto dto) {
        Item item = new Item(
                dto.getName(),
                dto.getLocation(),
                dto.getDescription(),
                dto.getStatus() != null ? dto.getStatus() : ItemStatus.LOST.name()
        );
        return toDto(itemRepository.save(item));
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

    // 3. 수정
    @Operation(summary = "분실물 수정")
    @PutMapping("/{id}")
    public ItemDto updateItem(@PathVariable Long id, @RequestBody ItemDto dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 아이템 없음: " + id));

        if (dto.getName() != null) item.setName(dto.getName());
        if (dto.getLocation() != null) item.setLocation(dto.getLocation());
        if (dto.getDescription() != null) item.setDescription(dto.getDescription());
        if (dto.getStatus() != null) item.setStatus(dto.getStatus());

        return toDto(itemRepository.save(item));
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

    //4 상태변경
    @Operation(summary = "분실물 상태 변경")
    @PatchMapping("/{id}/status")
    public ItemDto updateStatus(@PathVariable Long id,
                                @RequestParam ItemStatus status) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 아이템 없음: " + id));

        item.setStatus(status.name());
        itemRepository.save(item);

        return toDto(item);
    }

    //6 상태별 필터 조회
    @Operation(summary = "상태별 분실물 조회")
    @GetMapping("/filter")
    public List<ItemDto> getItemsByStatus(@RequestParam ItemStatus status) {
        return itemRepository.findAll()
                .stream()
                .filter(item -> item.getStatus().equals(status.name()))
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}