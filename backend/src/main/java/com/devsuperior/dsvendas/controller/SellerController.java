package com.devsuperior.dsvendas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsvendas.dtos.SellerDTO;
import com.devsuperior.dsvendas.entities.Seller;
import com.devsuperior.dsvendas.services.SellerService;

@RestController
@RequestMapping(value = "/sellers")
public class SellerController {

	@Autowired
	private SellerService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Seller> findById(@PathVariable Long id) {
		Seller sellerId = service.findById(id);
		return ResponseEntity.ok().body(sellerId);
	}

	@GetMapping
	public ResponseEntity<List<SellerDTO>> findAll() {
		List<SellerDTO> sellerDTOs = service.findAll();
		return ResponseEntity.ok(sellerDTOs);
	}

	@PostMapping
	public ResponseEntity<Seller> create(@RequestBody Seller seller) {
		Seller seller2 = service.create(seller);
		URI url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(seller.getId())
				.toUri();
		return ResponseEntity.created(url).body(seller2);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SellerDTO> update(@PathVariable Long id, @RequestBody SellerDTO sellerDTO) {
		Seller seller = service.update(id, sellerDTO);
		return ResponseEntity.ok().body(new SellerDTO(seller));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.findById(id);
		return ResponseEntity.noContent().build();
	}

}
