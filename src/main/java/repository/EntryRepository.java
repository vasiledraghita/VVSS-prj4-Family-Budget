package repository;

import java.io.BufferedReader;

import exceptions.MemberNotExistsException;
import model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.InvalidBudgetException;
import exceptions.InvalidNameException;
import exceptions.InvalidTypeException;

public class EntryRepository {
    private List<Entry> entries = new ArrayList<>();
    private MemberRepository memberRepo;

    private final static String filenameBudget = "budgetF.txt";

    @SuppressWarnings("resource")
    public EntryRepository(MemberRepository mRepo) {
        this.memberRepo = mRepo;

        try{
            String currentLineEntry;

            FileReader fileReaderEntry = new FileReader(filenameBudget);
            BufferedReader bufferedReaderEntry = new BufferedReader(fileReaderEntry);

            while ((currentLineEntry = bufferedReaderEntry.readLine()) != null) {
                String line[] = currentLineEntry.split(";");
                int valueEntry = Integer.parseInt(line[1]);
                int idEntryMember = Integer.parseInt(line[2]);
                Entry e = new Entry(line[0],valueEntry,idEntryMember);
                this.entries.add(e);
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    private void writeEntryToFile(Entry entry) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filenameBudget, true));
        writer.append('\n');
        String newEntry = entry.getType() + ';' + entry.getValue() + ';' + entry.getIdMember();
        writer.append(newEntry);

        writer.close();
    }

    private boolean memberIdExists(int id) {
        for (Member member : memberRepo.getAllMembers()) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void addEntry(Entry e) throws InvalidTypeException, MemberNotExistsException, NumberFormatException {
        if (! (e.getType().toLowerCase().equals("income") || e.getType().toLowerCase().equals("outcome")) )
            throw new InvalidTypeException("Invalid type! Needs to be either income or outcome");

        if (! this.memberIdExists(e.getIdMember()))
            throw new MemberNotExistsException("Member with this id does not exist");

        if (! (e.getType() instanceof String))
            throw new NumberFormatException("Invalid type! Needs to be either income or outcome");

        entries.add(e);

        try {
            writeEntryToFile(e);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    public List<Entry> getEntryForOneMember(int id)  throws MemberNotExistsException {
        if (! this.memberIdExists(id))
            throw new MemberNotExistsException("Member with this id does not exist");

        List<Entry> entriesForOneMember = new ArrayList<>();

        for (Entry entry : entries) {
            if (entry.getIdMember() == id) {
                entriesForOneMember.add(entry);
            }
        }

        return entriesForOneMember;
    }

    public List<Entry> getAllEntries(){

        return entries;
    }
}
