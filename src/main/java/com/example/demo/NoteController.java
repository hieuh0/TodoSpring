package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		if (user != null) {
			notesRepository.save(user);
		}
		return notesRepository.findAll();
	}

	@GetMapping(path = "/notes")
	public @ResponseBody Iterable<Notes> getAll() {
		return notesRepository.findAll();
	}

	@RequestMapping(path = "/notes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Notes>> delete(@PathVariable(value = "id") Integer id) {
		Notes contact = notesRepository.getOne(id);
		notesRepository.delete(contact);
		List<Notes> listContact = notesRepository.findAll();
		return new ResponseEntity<List<Notes>>(listContact, HttpStatus.OK);
	}
	@RequestMapping(path = "/notes/", method = RequestMethod.PUT)
	public ResponseEntity<Notes> update(@PathVariable(value = "id") Integer id,@RequestBody Notes body) {
		Notes note = notesRepository.getOne(id);
//		notesRepository.(contact);
		note.setNotes(body.getNotes());
		Notes c = notesRepository.save(note);
		List<Notes> listContact = notesRepository.findAll();
		 return ResponseEntity.ok(c);
	}
}
