package com.dgsw.lostfound.dto;

public class ItemDto {
    private Long id;
    private String name;        // 물건명
    private String location;    // 분실/보관 장소
    private String description; // 설명
    private String status;      // 상태 (분실중, 보관중, 반환완료 등)

    public ItemDto() {
    }

    public ItemDto(Long id, String name, String location, String description, String status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}