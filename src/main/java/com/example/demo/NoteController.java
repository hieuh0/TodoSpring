package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Iterable<Notes> addnotes(@RequestBody Notes user) {
		if(user != null) {
			notesRepository.save(user);
		}
		return notesRepository.findAll();
	}

	@GetMapping(path = "/notes")
	public @ResponseBody Iterable<Notes> getAll() {
		return notesRepository.findAll();
	}
	@GetMapping(path="/note/{id}")
	public Iterable<Notes> deletenote(@PathVariable(value = "ID") Integer id){
		Optional<Notes> note = notesRepository.findById(id);
		if(note != null) {
			System.out.print(note.toString());
		}
	//	notesRepository.deleteById(id);
		return  notesRepository.findAll();
	}
}
