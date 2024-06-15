package domain;

import utils.PackUtils;

public class Book {

    public static final int TITLE_LIMIT = 20;

    public static final int SIZE = 8+TITLE_LIMIT*2+8*2; // TODO

    private final long id;
    private final String title;
    private final long authorId;

    private long nextBookId;

/**
 * Represents a book written by an author
 * */

    public Book(long id, String title, long authorId) {
        //throw new UnsupportedOperationException("paso 2");
        this.id=id;
        this.title=title;
        this.authorId=authorId;
        this.nextBookId=-1L;
    }

/**
 * Contructs a new book
 * @param id the books identifier
 * @param title the title of the said book
 * @param authorId the identifier of the author of the book
 * if this is the first book of an author the next book identifier will be -1L
 * @param nextBookId the identifier of the next book of the author
 * */

    public Book(long id, String title, long authorId, long nextBookId) {
        //throw new UnsupportedOperationException("paso 2");
        this.id=id;
        this.title=title;
        this.authorId=authorId;
        this.nextBookId=nextBookId;
    }

/**
 *  Puts each param of a Book into bytes
 *  @return the array of the bytes
 * */

    public byte[] toBytes() {
        //throw new UnsupportedOperationException("paso 2");
        byte [] pack= new byte[SIZE];
        int offset=0;
        PackUtils.packLong(this.id,pack,offset);
        offset+=8;
        PackUtils.packLimitedString(this.title,TITLE_LIMIT,pack,offset);
        offset+=TITLE_LIMIT*2;
        PackUtils.packLong(this.authorId,pack,offset);
        offset+=8;
        PackUtils.packLong(this.nextBookId,pack,offset);
        return pack;
    }

 /**
  * @return the book and its params after turning them from bytes
  * */
    public static Book fromBytes(byte[] record) {
        //throw new UnsupportedOperationException("paso 2");
        int offset=0;
        Long id= PackUtils.unpackLong(record,offset);
        offset+=8;
        String title= PackUtils.unpackLimitedString(TITLE_LIMIT,record,offset);
        offset+=TITLE_LIMIT*2;
        Long authorId= PackUtils.unpackLong(record,offset);
        offset+=8;
        Long nextBookId= PackUtils.unpackLong(record,offset);

        return new Book(id,title,authorId,nextBookId);
    }

/**
 * @return the book's id
 * */

    public long getId() {
        //throw new UnsupportedOperationException("paso 2");
        return this.id;
    }
/**
 * @return the book's title
 * */

    public String getTitle() {
        //throw new UnsupportedOperationException("paso 2");
        return this.title;
    }
/**
 * @return the identifier of the author's book
 * */

    public long getAuthorId() {
        //throw new UnsupportedOperationException("paso 2");
        return this.authorId;
    }

/**
 * @return the id of the next book
 * */

    public long getNextBookId() {
        //throw new UnsupportedOperationException("paso 2");
        return this.nextBookId;
    }
/**
 * Sets the next book's id as the id of the book
 * */
    public void setNextBookId(long idBook) {
        //throw new UnsupportedOperationException("paso 2");
        this.nextBookId=idBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", nextBookId=" + nextBookId +
                '}';
    }
}
