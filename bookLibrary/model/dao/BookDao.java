package mukroject_library.bookLibrary.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mukroject_library.bookLibrary.controller.common.JDBCTemplate;
import mukroject_library.common.booKvo.Book;


public class BookDao {
	JDBCTemplate jbt = JDBCTemplate.getInstance();

	Book book = null;
	
	public List<Book> selectAllBooks(Connection conn){
		
		List<Book> bookList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
		pstm = conn.prepareStatement("SELECT * FROM TB_BOOK");
		rset = pstm.executeQuery();
		
		while(rset.next()) {
			 book = new Book();
			book.setbIdx(rset.getInt("b_idx"));
			book.setIsbn(rset.getString("isbn"));
			book.setCategory(rset.getString("category"));
			book.setTitle(rset.getString("title"));
			book.setAuthor(rset.getString("author"));
			book.setInfo(rset.getString("info"));
			bookList.add(book);
		}
		conn.commit();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jbt.rollback(conn);
		}finally {
			jbt.close(rset, pstm);
		}
		return bookList;
	}
	
	
	
	public List<Book> selectBookOrderByRank(Connection conn){
		List<Book> bookList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement("select  b_Idx, title, author, info, isbn, category, book_amt from(select rownum rnum," 
		+ "b.*from(select * from tb_book order by rent_cnt desc) b)where rnum between 1 and 5");
			
			
		    rset = pstm.executeQuery();
		    
		    while(rset.next()) {
				book = new Book();
				book.setbIdx(rset.getInt("b_idx"));
				book.setTitle(rset.getString("title"));
				book.setAuthor(rset.getString("author"));
				book.setInfo(rset.getString("info"));
				book.setIsbn(rset.getString("isbn"));
				book.setCategory(rset.getString("category"));
				book.setBook_amt(rset.getInt("book_amt"));
				bookList.add(book);
		    }
		    jbt.commit(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jbt.rollback(conn);
		}finally {
		jbt.close(rset, pstm, conn);
		}
		return bookList;
	}

	public Book selectBookByTitle(Connection conn, String title) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			pstm = conn.prepareStatement("select * from tb_book where title = ?");
			
			pstm.setString(1, title);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				book = new Book();
				book.setbIdx(rset.getInt("b_idx"));
				book.setTitle(rset.getString("title"));
				book.setAuthor(rset.getString("author"));
				book.setInfo(rset.getString("info"));
				book.setIsbn(rset.getString("isbn"));
				book.setCategory(rset.getString("category"));
				book.setBook_amt(rset.getInt("book_amt"));
			}
			jbt.commit(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			jbt.rollback(conn);
			e.printStackTrace();
		}finally {
		jbt.close(rset, pstm, conn);
		}
	return book;
		
	}
	
	public int insertBook(Connection conn, Book book) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = conn.prepareStatement("insert into tb_book(b_idx,isbn,category,title,"
					+ "author,info,book_amt,reg_date,rent_cnt) values(?,?,?,?,?,?,?,?,?)");
			
			pstm.setInt(1, book.getbIdx());
			pstm.setString(2, book.getIsbn()); 
			pstm.setString(3, book.getCategory()); //string이 아닌 char형식일수도 있으니 확인 바람
			pstm.setString(4, book.getTitle());
			pstm.setString(5, book.getAuthor());
			pstm.setString(6, book.getInfo());
			pstm.setInt(7, book.getBook_amt());
			pstm.setDate(8, book.getRegDate());
			pstm.setInt(9, book.getRentCnt());
			
			res = pstm.executeUpdate();
			
			jbt.commit(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jbt.rollback(conn);
		}finally{jbt.close(pstm,conn);}
		
		return res;
	}
	
	public int updateBook(Connection conn, Book book) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
		pstm = conn.prepareStatement("update tb_book set info = ? where b_idx = ?");
		
		pstm.setString(1, book.getInfo());
		pstm.setInt(2, book.getbIdx());
		
		res = pstm.executeUpdate();
		
		jbt.commit(conn);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jbt.rollback(conn);
		}finally {
			jbt.close(pstm);
			}			
		return res;
	}

	
	
	public int deleteBookByBidx(Connection conn, int bIdx){
	    int res = 0;
		PreparedStatement pstm = null;
	    
		try{
			pstm = conn.prepareStatement("delete from tb_book where b_idx = ?");
		pstm.setInt(1, bIdx);
		
		res = pstm.executeUpdate();
		jbt.commit(conn);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jbt.rollback(conn);
		}finally {
				jbt.close(pstm);
		}
		return res;
	}
	
	
}




