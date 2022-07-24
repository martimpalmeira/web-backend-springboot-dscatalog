package com.devsuperior.dscatolog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatolog.dto.CategoryDTO;
import com.devsuperior.dscatolog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		CategoryDTO cat = service.findCategoryById(id);
		return ResponseEntity.ok().body(cat);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO cat) {
		cat = service.insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).body(cat);
	}
}
