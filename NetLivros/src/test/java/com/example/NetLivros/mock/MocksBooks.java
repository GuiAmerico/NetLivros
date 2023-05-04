package com.example.NetLivros.mock;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;

import com.example.NetLivros.book.model.Book;
import com.example.NetLivros.book.model.dto.BookDTO;
import com.example.NetLivros.book.model.enums.Genrer;

public class MocksBooks {

	public static final Book BOOK_1 = Book.builder().title("Title 1").genrer(Genrer.ADVENTURE).numberOfPages(100)
			.author(MocksAuthor.AUTHOR_1).copies(new ArrayList<>()).build();
	public static final Book BOOK_2 = Book.builder().title("Title 2").genrer(Genrer.COMEDY).numberOfPages(200)
			.author(MocksAuthor.AUTHOR_2).build();
	public static final Book BOOK_3 = Book.builder().title("Title 3").genrer(Genrer.ROMANCE).numberOfPages(300)
			.author(MocksAuthor.AUTHOR_3).build();
	public static Book INVALID_BOOK = Book.builder().title("").genrer(null).numberOfPages(null).author(null).build();

	public static List<Book> BOOKS = new ArrayList<Book>() {
		private static final long serialVersionUID = 1L;
		{
			add(BOOK_1);
			add(BOOK_2);
			add(BOOK_3);
		}
	};
	public static BookDTO BOOK_DTO_1 = BookDTO.builder().title("Title 1").genrer(Genrer.ADVENTURE).numberOfPages(100)
			.author(MocksAuthor.AUTHOR_1.getName()).build();
	public static BookDTO BOOK_DTO_2 = BookDTO.builder().title("Title 2").genrer(Genrer.COMEDY).numberOfPages(200)
			.author(MocksAuthor.AUTHOR_2.getName()).build();
	public static BookDTO BOOK_DTO_3 = BookDTO.builder().title("Title 3").genrer(Genrer.ROMANCE).numberOfPages(300)
			.author(MocksAuthor.AUTHOR_3.getName()).build();
	public static BookDTO INVALID_BOOK_DTO = BookDTO.builder().title("").genrer(null).numberOfPages(null).author(null)
			.build();
	public static List<BookDTO> BOOKS_DTO = new ArrayList<BookDTO>() {
		private static final long serialVersionUID = 1L;
		{
			add(BOOK_DTO_1);
			add(BOOK_DTO_2);
			add(BOOK_DTO_3);
		}
	};
}
