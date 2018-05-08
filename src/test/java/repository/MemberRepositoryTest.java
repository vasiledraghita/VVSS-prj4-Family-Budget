package repository;

import exceptions.InvalidNameException;
import model.Member;
import org.junit.Before;
import org.junit.Test;

public class MemberRepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MemberRepository memberRepository = new MemberRepository();
		
		Member member= new Member("name",1);
		try {
			memberRepository.addMember(member);
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		assert(memberRepository.getAllMembers().size()==4);
		
	}
	@Test
	public void test2(){
		MemberRepository memberRepository = new MemberRepository();
		Member member= new Member("name",1);
		try {
			memberRepository.addMember(member);
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		assert(memberRepository.getAllMembers().get(3).getName().equals(member.getName()));
	}
}
