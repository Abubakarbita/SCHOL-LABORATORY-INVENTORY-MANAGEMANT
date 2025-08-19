package com.example.invproject.service;

import com.example.invproject.model.Item;
import com.example.invproject.model.School;
import com.example.invproject.model.Users;
import com.example.invproject.repository.ItemRepository;
import com.example.invproject.repository.SchoolRepository;
import com.example.invproject.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public List<Item> getItemsForSchool(Long schoolId) {
        return itemRepository.findBySchool_SchoolId(schoolId);
    }

    public Optional<Item> getItemByIdForSchool(Long id, Long schoolId) {
        return itemRepository.findById(id)
                .filter(item -> item.getSchool() != null && item.getSchool().getSchoolId().equals(schoolId));
    }

    public Optional<Item> createItem(Item item, Long userId, String role, Long schoolId) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) return Optional.empty();

        item.setCreatedAt(LocalDateTime.now());
        item.setUsers(user);

        if ("LabTechnician".equalsIgnoreCase(role)) {
            if (schoolId == null) return Optional.empty();
            School school = schoolRepository.findById(schoolId).orElse(null);
            if (school == null) return Optional.empty();
            item.setSchool(school);
        } else {
            if (item.getSchool() == null) return Optional.empty();
        }

        return Optional.of(itemRepository.save(item));
    }

    public Optional<Item> updateItem(Long id, Item itemDetails, Long schoolId) {
        return itemRepository.findById(id)
                .filter(item -> item.getSchool() != null && item.getSchool().getSchoolId().equals(schoolId))
                .map(item -> {
                    item.setItemName(itemDetails.getItemName());
                    item.setUnit(itemDetails.getUnit());
                    item.setStatus(itemDetails.getStatus());
                    item.setStorage(itemDetails.getStorage());
                    item.setQuantity(itemDetails.getQuantity());
                    item.setUploadedAt(LocalDateTime.now());
                    return itemRepository.save(item);
                });
    }

    public boolean deleteItem(Long id, Long schoolId) {
        Optional<Item> optionalItem = itemRepository.findById(id)
                .filter(item -> item.getSchool() != null && item.getSchool().getSchoolId().equals(schoolId));
        if (optionalItem.isPresent()) {
            itemRepository.delete(optionalItem.get());
            return true;
        }
        return false;
    }
}
