package repository;

import exceptions.InvalidTypeException;
import exceptions.MemberNotExistsException;
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
}
