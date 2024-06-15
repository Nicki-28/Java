package files;

import domain.Author;
import domain.Book;

import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * Handles the storage and retrieval of Book objects from a file
 * */
public class BooksFile {

    private final RandomAccessFile books;
/**
 * Contructs a BooksFile and retrieval of Book objects from a file
 * @param fname The name of the file to read or write
 * */
    public BooksFile(String fname) throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        this.books= new RandomAccessFile(fname,"rw");
    }
/**
 * Writes a Book object to the file
 *
 * @param book The book object to write
 * @throws IOException if an error occurs
 * */
    public void writeBook(Book book) throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        long pos= (book.getId()-1L)*Book.SIZE;
        this.books.seek(pos);
        byte[] record= book.toBytes();
        this.books.write(record);
    }

    /**
     * Reads a Book object from the file
     *
     * @param idBook The Id of the Book to read
     * @return The Book object with the given ID
     * @throws IOException if an error occurs.
     * */
    public Book readBook(long idBook) throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        long pos= (idBook-1L)*Book.SIZE;
        this.books.seek(pos);
        byte [] record= new byte[Book.SIZE];
        this.books.read(record);

        return Book.fromBytes(record);
    }

/**
 * Retrieves an array of Book objects authored by specific author
 *
 * @param author The Author object to retrieve books for
 * @return An array of Book objects authored by the specified author
 * @throws IOException if an error occurs
 * */

    public Book[] getBooksForAuthor(Author author) throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        Book [] authorBooks= new Book[author.getNumBooks()];

        long current= author.getFirstBookId();

        for(int i=0; i<author.getNumBooks(); i++){
            authorBooks[i]= readBook(current);
            current= authorBooks[i].getNextBookId();

        }
        return authorBooks;
    }
/**
 * @return the number of books of the file
 * */
    public long numBooks() throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        return this.books.length()/Book.SIZE;
    }
/**
 * @return the id of the next book that follows the number of books
 * */
    public long nextBookId() throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        return numBooks()+1;
    }

/**
 * Checks if the given book ID exists in the file
 * @param idBook the ID of the book to check
 * @return true if the book ID exists, false otherwise
 * @throws IOException if an error occurs
 * */
    public boolean isValidId(long idBook) throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        return idBook>=1L && idBook<=numBooks();
    }

/**
 * Resets the BooksFile, clearing all stored Books objects.
 * @throws IOException if an error occurs
 * */
    public void reset() throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        this.books.setLength(0);
    }
/**
 * Closes the BooksFile, releasing any resources.
 * @throws IOException if an error occurs.
 * */
    public void close() throws IOException {
        //throw new UnsupportedOperationException("paso 4");
        this.books.close();
    }
}
