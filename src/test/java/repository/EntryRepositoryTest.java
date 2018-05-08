package repository;

import exceptions.InvalidNameException;
import exceptions.InvalidTypeException;
import exceptions.MemberNotExistsException;
import model.Member;
import org.junit.Before;
import org.junit.Test;
import model.Entry;

public class EntryRepositoryTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        MemberRepository mRepo = new MemberRepository();
        EntryRepository eRepo = new EntryRepository(mRepo);

        Entry entry = new Entry("income",100,1);
        try {
            eRepo.addEntry(entry);
        } catch (InvalidTypeException | MemberNotExistsException e) {
            e.printStackTrace();
        }
        assert(eRepo.getAllEntries().size()==10);
    }
    @Test
    public void test2(){
        MemberRepository mRepo = new MemberRepository();
        EntryRepository eRepo = new EntryRepository(mRepo);

        Entry entry = new Entry("income",100,1);
        try {
            eRepo.addEntry(entry);
        } catch (InvalidTypeException | MemberNotExistsException e) {
            e.printStackTrace();
        }
        assert(eRepo.getAllEntries().get(9).getIdMember()==1);
        assert("income".equals(eRepo.getAllEntries().get(9).getType()));
    }
    @Test
    public void integrationTest1() {
        MemberRepository mRepo = new MemberRepository();

        Member member = new Member("testName", 4);
        try {
            mRepo.addMember(member);
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
        assert(mRepo.getAllMembers().get(3).getName().equals(member.getName()));
    }
    @Test
    public void integrationTest2() {
        integrationTest1();

        MemberRepository mRepo = new MemberRepository();
        EntryRepository eRepo = new EntryRepository(mRepo);

        Entry entry = new Entry("income", 100, 4);
        try {
            eRepo.addEntry(entry);
        } catch (InvalidTypeException | MemberNotExistsException e) {
            e.printStackTrace();
        }
        assert (eRepo.getAllEntries().get(9).getIdMember() == 4);
    }
    @Test
    public void integrationTest3() throws MemberNotExistsException {
        integrationTest1();
        integrationTest2();

        MemberRepository mRepo = new MemberRepository();
        EntryRepository eRepo = new EntryRepository(mRepo);

        assert (eRepo.getEntryForOneMember(4).get(0).getValue() == 100);
    }
}
