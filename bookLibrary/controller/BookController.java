package mukroject_library.bookLibrary.controller;

import java.util.List;

import mukroject_library.bookLibrary.model.service.BookService;
import mukroject_library.common.booKvo.Book;

public class BookController {

	BookService bookservice = new BookService();

    public  List<Book> searchAllBooks(){
        return bookservice.selectAllBook();
    }
    		
    public List<Book> searchBooksWithRank(){
    	return bookservice.selectBookOrderByRank();
    }
    
    public Book searchBookByTitle(String title) {
    	return bookservice.selectBookByTitle(title);
    }
    
    public boolean registBook(Book book) {
        if(bookservice.insertBook(book) > 0) {
        	return true;
        }else {
        	return false;
        }
    }
    
    public boolean modifyBook(Book book) {
    	if(bookservice.updateBook(book) > 0) {
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public boolean deleteBook(int bIdx) {
    	if(bookservice.deleteBookByBIdx(bIdx) > 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
	
}
