package com.devsuperior.dsvendas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devsuperior.dsvendas.dtos.SellerDTO;
import com.devsuperior.dsvendas.entities.Seller;
import com.devsuperior.dsvendas.repositories.SellerRepository;
import com.devsuperior.dsvendas.services.exception.ObjectNotFoundException;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;

	public Seller findById(Long id) {
		Optional<Seller> sellerId = repository.findById(id);
		return sellerId.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Seller.class.getName()));
	}

	public List<SellerDTO> findAll() {
		List<Seller> sellers = repository.findAll();
		return sellers.stream().map(dto -> new SellerDTO(dto)).collect(Collectors.toList());
		
	}

	public Seller create(Seller seller) {
		seller.setId(null);
		return repository.save(seller);
	}

	public Seller update(Long id, SellerDTO sellerDTO) {
		Seller seller = findById(id);
		seller.setId(sellerDTO.getId());
		seller.setName(sellerDTO.getName());
		return repository.save(seller);
	}

	public void delete(Long id) {

		Seller sellerId = findById(id);
		try {
			repository.delete(sellerId);
		} catch (DataIntegrityViolationException e) {
			throw new com.devsuperior.dsvendas.services.exception.DataIntegrityViolationException(
					"Error trying to remove seller! has Sale associated.");
		}
	}

}
