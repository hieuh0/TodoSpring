package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class NoteController {
	@Autowired
	private NoteRepository notesRepository;

	@GetMapping(value = "/")
	public String getIndex() {
		return "Api demo todoapp Spring boot";
	}

	@PostMapping(path = "/notes")
	public ResponseEntity<?> addnotes(@RequestBody Notes user) {
		notesRepository.save(user);
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	@GetMapping(path = "/notes")
	public @ResponseBody Iterable<Notes> getAll() {
		return notesRepository.findAll();
	}
}
