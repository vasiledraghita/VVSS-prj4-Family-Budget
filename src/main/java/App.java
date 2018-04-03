
import controller.EntryController;
import repository.EntryRepository;
import repository.MemberRepository;
import controller.MemberController;
import ui.BudgetUI;

public class App {
	public static void main(String[] args) {

		MemberRepository memberRepo = new MemberRepository();
		EntryRepository entryRepo = new EntryRepository(memberRepo);

		MemberController mCtrl = new MemberController(memberRepo);
		EntryController eCtrl = new EntryController(entryRepo);

		BudgetUI console = new BudgetUI(mCtrl, eCtrl);
		console.Run();
	}
}
