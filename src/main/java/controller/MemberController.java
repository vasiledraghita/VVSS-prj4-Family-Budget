package controller;

import exceptions.InvalidNameException;
import repository.MemberRepository;

import model.Member;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemberController {
	
    private MemberRepository memberRepo;

    public MemberController(MemberRepository newMr){    	
    	this.memberRepo =newMr;
    }
    
    public void addMember(Member aMemebr) throws InvalidNameException {
        memberRepo.addMember(aMemebr);
    }

    public List<Member> getAllMembers() {
        return this.memberRepo.getAllMembers();
    }
}