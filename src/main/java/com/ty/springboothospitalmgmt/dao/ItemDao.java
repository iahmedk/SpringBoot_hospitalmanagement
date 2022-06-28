package com.ty.springboothospitalmgmt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.Item;
import com.ty.springboothospitalmgmt.repository.ItemRepository;

@Repository
public class ItemDao {

	@Autowired
	private ItemRepository itemRepository;

	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	public void deleteItemById(int id) {
		itemRepository.deleteById(id);
	}

	public Item updateItemById(Item item) {
		return itemRepository.save(item);
	}
}
