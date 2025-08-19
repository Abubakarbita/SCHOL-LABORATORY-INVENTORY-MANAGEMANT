package com.example.invproject.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.invproject.model.Item;
import com.example.invproject.service.ItemService;
import com.example.invproject.repository.ItemRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private final ItemService itemService;
     private final ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> getItems(HttpSession session) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId == null) return ResponseEntity.status(401).build();

        List<Item> items = itemService.getItemsForSchool(schoolId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id, HttpSession session) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId == null) return ResponseEntity.status(401).build();

        return itemService.getItemByIdForSchool(id, schoolId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(403).build());
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");
        Long schoolId = (Long) session.getAttribute("schoolId");

        if (userId == null || role == null) return ResponseEntity.status(401).build();

        return itemService.createItem(item, userId, role, schoolId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails, HttpSession session) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId == null) return ResponseEntity.status(401).build();

        return itemService.updateItem(id, itemDetails, schoolId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(403).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id, HttpSession session) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId == null) return ResponseEntity.status(401).build();

        boolean deleted = itemService.deleteItem(id, schoolId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }

    // Hii method mpya ya kupata summary ya inventory kwa school
   @GetMapping("/summary")
public ResponseEntity<?> getInventorySummary(HttpSession session) {
    Long schoolId = (Long) session.getAttribute("schoolId");
    if (schoolId == null) {
        return ResponseEntity.status(401).build();
    }

    List<Item> items = itemService.getItemsForSchool(schoolId);

    int totalQuantity = 0;
    int inStockCount = 0;
    int lowStockCount = 0;
    int outOfStockCount = 0;
    int expiredCount = 0;

    for (Item item : items) {
        totalQuantity += item.getQuantity();

        String status = item.getStatus();
        if (status == null) continue;

        String normalizedStatus = status.trim().toUpperCase().replace(" ", "_");

        switch (normalizedStatus) {
            case "IN_STOCK":
                inStockCount++;
                break;
            case "LOW_STOCK":
                lowStockCount++;
                break;
            case "OUT_OF_STOCK":
                outOfStockCount++;
                break;
            case "EXPIRED":
                expiredCount++;
                break;
            case "NEW":
                // optionally count New items or ignore
                break;
            default:
                // ignore unknown statuses or handle if needed
                break;
        }
    }

    Map<String, Object> response = new HashMap<>();
    response.put("totalQuantity", totalQuantity);
    response.put("inStockCount", inStockCount);
    response.put("lowStockCount", lowStockCount);
    response.put("outOfStockCount", outOfStockCount);
    response.put("expiredCount", expiredCount);
    response.put("items", items);

    return ResponseEntity.ok(response);
}

// ItemController.java
@GetMapping("/school/{schoolId}")
    public ResponseEntity<?> getItemsBySchool(@PathVariable Long schoolId) {
        List<Item> items = itemRepository.findBySchool_SchoolId(schoolId);
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

}
