package controller;

import exceptions.InvalidTypeException;
import exceptions.MemberNotExistsException;
import repository.EntryRepository;

import model.Member;
import model.Entry;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntryController {

    private EntryRepository entryRepo;

    public EntryController(EntryRepository newEr){
        this.entryRepo = newEr;
    }

    public void addEntry(Entry oneEntry) throws InvalidTypeException, MemberNotExistsException {
        entryRepo.addEntry(oneEntry);
    }

    public List<Entry> allEntriesForMember(int memberId) throws MemberNotExistsException {
        return entryRepo.getEntryForOneMember(memberId);
    }

    public List<Entry> allEntries() {
        return this.entryRepo.getAllEntries();
    }
}