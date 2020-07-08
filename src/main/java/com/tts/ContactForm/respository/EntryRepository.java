package com.tts.ContactForm.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tts.ContactForm.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
  List<Entry> findAll();
  Optional<Entry> findById(Long id);
}