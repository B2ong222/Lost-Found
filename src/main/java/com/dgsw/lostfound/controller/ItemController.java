package com.dgsw.lostfound.controller;

import com.dgsw.lostfound.dto.ItemDto;
import com.dgsw.lostfound.model.ItemStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private List<ItemDto> items = new ArrayList<>();

    public ItemController() {
        items.add(new ItemDto(1L, "지갑", ItemStatus.LOST));
        items.add(new ItemDto(2L, "에어팟", ItemStatus.FOUND));
    }


    // 4, 6
    @PatchMapping("/{id}/status")
    public ItemDto updateStatus(@PathVariable Long id,
                                @RequestParam ItemStatus status) {

        for (ItemDto item : items) {
            if (item.getId().equals(id)) {
                item.setStatus(status);
                return item;
            }
        }

        throw new RuntimeException("해당 아이템 없음: " + id);
    }

    @GetMapping
    public List<ItemDto> getItems(@RequestParam(required = false) ItemStatus status) {

        if (status == null) {
            return items;
        }

        return items.stream()
                .filter(item -> item.getStatus() == status)
                .collect(Collectors.toList());
    }
}