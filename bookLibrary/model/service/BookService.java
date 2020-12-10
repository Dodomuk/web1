package mukroject_library.bookLibrary.model.service;

import java.util.ArrayList;
import java.util.List;

import mukroject_library.bookLibrary.controller.common.JDBCTemplate;
import mukroject_library.bookLibrary.model.dao.BookDao;
import mukroject_library.common.booKvo.Book;

public class BookService {

	List<Book> bookList = new ArrayList<>();
    BookDao bookdao = new BookDao();
    JDBCTemplate jbt =  JDBCTemplate.getInstance();
    
	public List<Book> selectAllBook(){
		
		return bookdao.selectAllBooks(jbt.getConnection());
	}
	
	public List<Book> selectBookOrderByRank(){
		return bookdao.selectBookOrderByRank(jbt.getConnection());
		
	}
	
	public Book selectBookByTitle(String title) {
		return bookdao.selectBookByTitle(jbt.getConnection(), title);
	}
	
	public int insertBook(Book book) {
		return bookdao.insertBook(jbt.getConnection(), book);
	}
	
	public int updateBook(Book book) {
		return bookdao.updateBook(jbt.getConnection(), book);
	}
	
	public int deleteBookByBIdx(int bIdx) {
		return bookdao.deleteBookByBidx(jbt.getConnection(), bIdx);
	}
	
}
