package mukroject_library.view.book;


import java.util.List;
import java.util.Scanner;

import mukroject_library.bookLibrary.controller.BookController;
import mukroject_library.common.booKvo.Book;

public class BookMenu {

	private Scanner sc = new Scanner(System.in);

	BookController bookcontroller = new BookController();

	Book book = new Book();
	
	public void bookMenu() {
		System.out.println("\n*** 도서 관리 ***");
		System.out.println("1. 도서 전체 조회");
		System.out.println("2. 도서 등록");
		System.out.println("3. 도서 수정");
		System.out.println("4. 도서 삭제");
		System.out.println("5. 도서 검색");
		System.out.println("6. 종료");
		System.out.println("번호 선택 : ");

		switch (sc.nextInt()) {
		case 1:
			// bookController의 searchAllBooks() 호출
			// 결과 값을 반환 받아 모든 결과를 출력
			List<Book> booklist = bookcontroller.searchAllBooks();

			for (Book book : booklist) {
				System.out.println(book);
			}
			break;
		case 2:
			// registBook 메서드를 호출해 사용자로부터 입력할 도서 정보를 입력 받고
			// bookController의 registBook() 을 호출한 뒤
			// 도서가 성공적으로 추가되면 "도서 추가 성공"
			// 도서 추가에 실패하면 "도서 추가 실패" 출력

			if (bookcontroller.registBook(registBook()) == true) {
				System.out.println("도서 등록 성공");
			} else {
				System.out.println("도서 등록 실패");
			}

			break;

		case 3:
			// 수정할 도서번호와 수정할 도서 내용(tb_book 테이블의 info컬럼)을 사용자로부터 입력 받아
			// bookController의 modifyBook() 를 호출하고
			// 해당 도서의 도서 내용을 수정하고 수정에 성공하면 "도서 수정 성공", 실패하면 "도서 수정 실패" 출력

		
			System.out.print("도서 번호를 입력하세요 : ");
			book.setbIdx(sc.nextInt());
			System.out.print("수정할 도서 내용을 적어주세요 : ");
			book.setInfo(sc.next());
			if (bookcontroller.modifyBook(book)) {
				System.out.println("도서 수정 성공");
			} else {
				System.out.println("도서 수정 실패");
			}
			break;
		case 4: 
			// 삭제할 도서의 도서번호를 사용자로부터 입력 받아
			// bookController의 deleteBook 메서드를 호출하고
			// 해당 도서의 삭제에 성공하면 "도서 삭제 성공" 실패하면 "도서 삭제 실패" 출력
			System.out.println("삭제할 도서 번호를 입력하세요 : ");
			book.setbIdx(sc.nextInt());
			if (bookcontroller.deleteBook(book.getbIdx())) {
				System.out.println("도서 삭제 성공");
			} else {
				System.out.println("도서 삭제 실패");
			}

			break;

		case 5:
			// searchBookMenu 메서드를 호출해 도서 검색 메뉴창을 출력
			BookMenu bm = new BookMenu();
			bm.searchBookMenu();
			break;
		case 6:
			System.out.println("프로그램을 종료합니다.");
			return;
		}
	}


	// =======================================
	
	  public void searchBookMenu() {

			do {
				System.out.println("*** 도서 검색 메뉴 ***");
				System.out.println("1. 도서명 검색");
				System.out.println("2. 인기순 검색");
				System.out.println("3. 이전 메뉴로 이동");
				System.out.println("번호 선택 : ");
				int no = sc.nextInt();

				switch (no) {
				case 1:

					System.out.print("검색할 도서명 : ");
					System.out.println(bookcontroller.searchBookByTitle(sc.next()));
					break;

				case 2:
					System.out.print("대출순위가 많은 상위 5권의 도서목록입니다.");
					System.out.println();
					for (Book book : bookcontroller.searchBooksWithRank()) {
						System.out.println(book);
					}
					break;

				default:
					System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
				
				}
			} while (true);
		}

	// =======================================
	public Book registBook() {
		Book book = new Book();
		System.out.println("도서 정보를 입력하세요.------------------");
		
		System.out.print("도서 제목 : ");
		book.setTitle(sc.next());

		System.out.print("작가 : ");
		book.setAuthor(sc.next());

		System.out.print("ISBN :");
		book.setIsbn(sc.next());

		System.out.print("카테고리 :");
		book.setCategory(sc.next());
		
		System.out.print("B_IDX : ");
		book.setbIdx(sc.nextInt());
		
		System.out.print("정보 : ");
		book.setInfo(sc.next());
		
		book.setBook_amt(1);
		
		book.setRentCnt(0);
	    
		return book;
	}
}
