package com.dgsw.lostfound.dto;

import com.dgsw.lostfound.model.ItemStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String itemName;
    private ItemStatus status;

}