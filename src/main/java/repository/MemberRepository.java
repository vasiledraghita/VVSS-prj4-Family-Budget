package repository;

import java.io.BufferedReader;

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

public class MemberRepository {
	private List<Member> members = new ArrayList<>();

	private final static String filenameMember = "membersF.txt";

	@SuppressWarnings("resource")
	public MemberRepository() {
		
	try {
        String currentLine;

        FileReader fileReader = new FileReader(filenameMember);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((currentLine = bufferedReader.readLine()) != null) {
            String line[] = currentLine.split(";");
            Member m = new Member(line[0], Integer.parseInt(line[1]));
            this.members.add(m);
        }
	 }catch(Exception ex){
         System.err.println(ex.getMessage());
     }
	}

	private int getNextId() {
	    int nextId = 1;
        for (Member member : members) {
            int currentId = member.getId();
            if (currentId > nextId) {
                nextId = currentId;
            }
        }

        return nextId + 1;
    }

	private void writeMemberToFile(Member member) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filenameMember, true));
		writer.append('\n');
		String newMember = member.getName() + ';' + member.getId();
		writer.append(newMember);

		writer.close();
	}

    public void addMember(Member m) throws InvalidNameException {
        if (m.getName().isEmpty()) throw new InvalidNameException("Name should not be empty!");
        if (m.getName().matches(".*\\d+.*")) throw new InvalidNameException("Name should not contain numbers!");

        m.setId(this.getNextId());
        members.add(m);

        try {
            writeMemberToFile(m);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        return members;
    }
}
