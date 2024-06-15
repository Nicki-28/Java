package files;

import domain.Author;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Handles the storage and retrieval of Author objects from a file
 * */

public class AuthorsFile {

    private final RandomAccessFile authors;
/**
 * Constructs an AuthorsFile with the given filename
 * @param fname the name of the file to read or write
 * */

    public AuthorsFile(String fname) throws IOException {
        //throw new UnsupportedOperationException("paso 3");
        this.authors=new RandomAccessFile(fname,"rw");
    }

/**
 * Writes an Author object to the file
 * @param author the Author object to write.
 * @throws IOException if an error occurs.
 * */

    public void writeAuthor(Author author) throws IOException {
        //throw new UnsupportedOperationException("paso 3");
        long pos= (author.getId()-1L)*Author.SIZE;
        this.authors.seek(pos);
        byte [] record= author.toBytes();
        this.authors.write(record);
    }
/**
 * Reads an Author object from the file
 * @param  idAuthor is the ID of the Author to read
 * @return the author read
 * @throws IOException if an error occurs
 *  */

    public Author readAuthor(long idAuthor) throws IOException {
        //throw new UnsupportedOperationException("paso 3");
        long pos= (idAuthor-1L)*Author.SIZE;
        this.authors.seek(pos);
        byte [] record= new byte[Author.SIZE];
        this.authors.read(record);

        return Author.fromBytes(record);
    }

/**
 * @return the number of authors
 * */

    public long numAuthors() throws IOException {
        //throw new UnsupportedOperationException("paso 3");
        //number of id=num of different authors
        return this.authors.length()/Author.SIZE;
    }

/**
 * @return the next author's identifier
 * */

    public long nextAuthorId() throws IOException {
        //throw new UnsupportedOperationException("paso 3");
        return numAuthors()+1;
    }
/**
 *Checks if the given author ID extis in the file
 * @param  idAuthor the Id of author to check.
 * @return true if the author Id exists, false otherwise
 * @throws IOException if an error occurs.
 * */

    public boolean isValidId(long idAuthor) throws IOException {
        //throw new UnsupportedOperationException("paso 3");
       return idAuthor>=1L && idAuthor<=numAuthors();
    }
/**
 * Resets the AuthorsFile, clearing all stored Author objects.
 * @throws IOException if an error occurs
 * */
    public void reset() throws IOException {
        // throw new UnsupportedOperationException("paso 3");
        this.authors.setLength(0);
    }

/**
 * Closes the AuthorsFile, releasing any resources.
 * @throws IOException if an error occurs.
 * */
    public void close() throws IOException {
        //throw new UnsupportedOperationException("paso 3");
        this.authors.close();
    }
}
