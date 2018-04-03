package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.EntryController;
import exceptions.InvalidNameException;
import exceptions.InvalidTypeException;
import exceptions.MemberNotExistsException;
import model.*;
import controller.MemberController;;

public class BudgetUI {
	private MemberController mCtrl;
	private EntryController eCtrl;
	Scanner in;
	
	public BudgetUI(MemberController mCtrl, EntryController eCtrl)
	{
		this.mCtrl = mCtrl;
		this.eCtrl = eCtrl;
		this.in=new Scanner(System.in);
	}
	
	public MemberController getMemberCtrl()
	{
		return this.mCtrl;
	}
	public EntryController getEntryCtrl()
	{
		return this.eCtrl;
	}

	public Scanner getIn()
	{
		return this.in;
	}
	
	public void setMemberCtrl(MemberController newCtrl)
	{
		this.mCtrl= newCtrl;
	}
	public void setEntryCtrl(EntryController newCtrl)
	{
		this.eCtrl= newCtrl;
	}

	public void setIn(Scanner newIn)
	{
		this.in=newIn;
	}
	
	private void printMenu()
	{
		String menu = "--------------------------\n";
		menu +="Budget Admin Menu: \n";
		menu +="\t 1 - to add a new member; \n";
		menu +="\t 2 - to add a new income/cost; \n";
		menu +="\t 3 - to list all entries for one member; \n";
		menu +="\t 0 - exit \n";
		menu += "--------------------------\n";
		
		System.out.println(menu);
	}

	private void printMembers() {
		System.out.println("All members with name and ids:");
		List<Member> allM = mCtrl.getAllMembers();
		for (Member anAllM : allM) System.out.println(anAllM.toString());
	}
	
	public void Run()
	{
		printMenu();

		int cmd=in.nextInt();
		in.nextLine();
		
		while(cmd!=0)
		{
			if(cmd==1)
			{								
				System.out.println("Enter name:");
				String name = in.nextLine();
				Member aMemebr = new Member(name);
				try {
					mCtrl.addMember(aMemebr);
					System.out.println("Member successfully added!");
				} catch (InvalidNameException err) {
					System.out.println(err.getMessage());
				}
			}
			if(cmd==2)
			{
				System.out.println("Enter type: (income / cost)");
				String type = in.nextLine();
				
				System.out.println("Enter the value:");
				String valueS = in.nextLine();
				int valueInt=Integer.parseInt(valueS);

				this.printMembers();

				System.out.println("Enter the member id:");
				String idS = in.nextLine();
				int idInt=Integer.parseInt(idS);
				
				Entry e= new Entry(type, valueInt, idInt);
				try {
					eCtrl.addEntry(e);
					System.out.println("Entry successfully added!");
				} catch (InvalidTypeException | MemberNotExistsException err) {
					System.out.println(err.getMessage());
				}
			}
			if(cmd==3)
			{
				this.printMembers();

				System.out.println("Enter the member id:");
				String valueS = in.nextLine();
				int valueInt = Integer.parseInt(valueS);

				try {
					List<Entry> allE = eCtrl.allEntriesForMember(valueInt);
					System.out.println("All entries for the member");
					for (Entry anAllE : allE) System.out.println(anAllE.toString());
				} catch (MemberNotExistsException err) {
					System.out.println(err.getMessage());
				}
			}

			printMenu();
			cmd=in.nextInt();
			in.nextLine();
		}
	}
}
