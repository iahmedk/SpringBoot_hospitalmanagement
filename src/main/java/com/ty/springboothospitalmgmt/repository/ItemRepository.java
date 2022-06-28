package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboothospitalmgmt.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
