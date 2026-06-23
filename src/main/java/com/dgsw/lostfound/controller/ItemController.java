package com.dgsw.lostfound.controller;

import com.dgsw.lostfound.dto.ItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private List<ItemDto> items = new ArrayList<>();

    public ItemController() {
        items.add(new ItemDto(1L, "지갑"));
        items.add(new ItemDto(2L, "에어팟"));
    }
}