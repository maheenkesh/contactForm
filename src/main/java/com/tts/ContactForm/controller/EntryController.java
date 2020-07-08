package com.tts.ContactForm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.ContactForm.model.Entry;
import com.tts.ContactForm.respository.EntryRepository;


@Controller
public class EntryController {

  @Autowired
  private EntryRepository entryRepository;

  @GetMapping(value = {"/", "/entries"})
  public String index(Entry entry, Model model) {
    Iterable<Entry> entries = entryRepository.findAll();
    model.addAttribute("entries", entries);
    return "/index";
  }
  
  @GetMapping(value = "/entries/{id}")
  public String showById(@PathVariable("id") Long id, Entry entry, Model model) {
	Optional<Entry> optionEntry = entryRepository.findById(id);
    Entry entryFound = optionEntry.get();
    model.addAttribute("firstName", entryFound.getFirstName());
    model.addAttribute("lastName", entryFound.getLastName());
    model.addAttribute("emailAddress", entryFound.getEmailAddress());
    model.addAttribute("entry", entryFound.getEntry());
    model.addAttribute("createdAt", entryFound.getCreatedAt());
    return "/index";
  }
  
  @GetMapping(value = "/entries/newEntry")
  public String getNewContactForm(Model model) {
    model.addAttribute("entry", new Entry());
    return "/newEntry";
  }

  @PostMapping(value = "/entries")
  public String createNewEntry(Entry entry,
      Model model) {
    Entry entryToAdd = new Entry(entry.getFirstName(),
        entry.getLastName(), entry.getEmailAddress(), entry.getEntry());
    entryRepository.save(entryToAdd);
    model.addAttribute("firstName", entryToAdd.getFirstName());
    model.addAttribute("lastName", entryToAdd.getLastName());
    model.addAttribute("emailAddress", entryToAdd.getEmailAddress());
    model.addAttribute("entry", entryToAdd.getEntry());
    model.addAttribute("createdAt", entryToAdd.getCreatedAt());
    return "/index";
  }
}
