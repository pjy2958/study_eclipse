package kr.ac.kopo.project01;

import java.util.Scanner;

public class LibraryView {
	
	private Scanner sc;		// 스캐너
	private boolean loginCheck;	// 로그인되어있는지 체크하는 변수
	private LibraryManagement libraryManagement;
	private BookManagement bookManagement;
	private BookBorrowUtil bookBorrowUtil;
	private BookReturnUtil bookReturnUtil;
	private BookManagementUtil bookManagementUtil;
	private LoginUtil loginUtil;
	
	public LibraryView() {
		this.sc = new Scanner(System.in);
		this.loginCheck = false;
		this.libraryManagement = new LibraryManagement();
		this.bookManagement = new BookManagement();
		this.bookBorrowUtil = new BookBorrowUtil();
		this.bookReturnUtil = new BookReturnUtil();
		this.bookManagementUtil = new BookManagementUtil();
		this.loginUtil = new LoginUtil();
	}

	// 도서관 메인 화면
	public void mainMenu() {
		while(true) {
			System.out.println("\n ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ<도서관 메인 메뉴>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("| 1.대여하기 | 2.반납하기 | 3.도서관리 | 4.회원관리 | 5.종료 |");
			System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			if (loginCheck == true)
				this.loginUtil.printLoginInfo();
			System.out.print("* 기능을 선택하세요 : ");
			int num = Integer.parseInt(sc.nextLine());
			
			switch(num) {
			case 1:
				borrowBookView();
				break;
			case 2:
				returnBookView();
				break;
			case 3:
				bookManagementView();
				break;
			case 4:
				memberManagementView();
				break;
			case 5:
				System.exit(0);
			case 6:
				this.loginCheck = loginUtil.logout();
				break;
			default :
				System.out.println("** 잘못입력하였습니다.");
			}
		}
	}
	
	// 책 대여 화면
	private void borrowBookView() {
		while (true) {
			System.out.println("\n ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ<도서대출 메뉴>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("| 1.책검색        | 2.책대출        | 3.돌아가기         |");
			System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("* 기능을 선택하세요 : ");
			int num = Integer.parseInt(this.sc.nextLine());
			switch (num) {
				case 1:
					this.bookManagementUtil.printBookList();
					this.bookBorrowUtil.bookBorrow();
					break;
				case 2:
					this.bookManagementUtil.printBookList();
					this.bookBorrowUtil.bookBorrow();
					break;
				case 3:
					return;
			}
		}
	}
	
	// 책 반납 화면
	private void returnBookView() {
		this.bookReturnUtil.bookReturn();
	}
	
	
	// 도서관리 화면
	private void bookManagementView() {
		System.out.println("** 관리자만 접근 가능한 기능입니다.");
		if(!this.loginCheckView())
			return;
		if(!LibraryManagement.loginMember.getGrade().equals("관리자")) {
			System.out.println("\n** 접근권한없음) 관리자가 아닙니다.");
			return;
		}
		while (true) {
			System.out.println("\n ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ<도서관리 메뉴>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("| 1.책등록        | 2.책삭제        | 3.돌아가기         |");
			System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("* 기능을 선택하세요 : ");
			int num = Integer.parseInt(this.sc.nextLine());
			switch (num) {
			case 1:
				this.bookManagementUtil.bookRegister();
				break;
			case 2:
				this.bookManagementUtil.printBookList();
				this.bookManagementUtil.bookRemove();
				break;
			case 3:
				return;
			}
		}
	}
	
	// 회원관리 화면
	private void memberManagementView() {
		if(!this.loginCheckView())
			return;
		while (true) {
			System.out.println("\n ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ<회원관리 메뉴>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("| 1.회원정보변경       | 2.회원탈퇴      | 3.메인메뉴      |");
			System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.print("* 기능을 선택하세요 : ");
			int num = Integer.parseInt(this.sc.nextLine());
			switch (num) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				return;
			}
		}
	}

	// 로그인 화면
	private void loginView() {
		System.out.println("\n ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ<로그인 메뉴>ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println("| 1.로그인        | 2.회원가입       | 3.돌아가기        |");
		System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.print("* 기능을 선택하세요 : ");
		int num = Integer.parseInt(this.sc.nextLine());
		switch (num) {
		case 1:
			this.loginCheck = this.loginUtil.login();
			break;
		case 2:
			this.loginCheck = this.loginUtil.registration();
			break;
		case 3:
			return;
		}
	}
	
	// 로그인이 되었는지 확인하는 뷰
	private boolean loginCheckView() {
		if (!this.loginCheck) { // 로그인이 안되어 있을 경우
			System.out.print("\n* 로그인이 필요한 기능입니다. 로그인하시겠습니까?(Y/N) : ");
			char ch = sc.nextLine().charAt(0);

			if (ch == 'Y' || ch == 'y') {
				this.loginView();
			} else if (ch == 'N' || ch == 'n') {
				return false;
			} else {
				System.out.println("** 잘못 입력하였습니다.");
				return false;
			}
		}
		if (!this.loginCheck)
			return false;
		return true;
	}
}
