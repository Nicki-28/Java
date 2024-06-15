package domain;

import utils.PackUtils;

public class Author {

    public static final int NAME_LIMIT = 20;

    public static final int SIZE = 8+NAME_LIMIT*2+4+8*2; // TODO

    private final long id;
    private final String name;

    private int  numBooks;
    private long firstBookId;
    private long lastBookId;

/**
* Represents authors
* */
    public Author(long id, String name) {
        //throw new UnsupportedOperationException("paso 1");
        this.id=id;
        this.name=name;
        this.numBooks=0;
        this.firstBookId=-1L;
        this.lastBookId=-1L;

    }
/**
 * Constructs a new Author with the given parameters
 * @param id The identifier of the author
 * @param name The name of the author
 * @param numBooks number of books of said author
 *  If we are introducing a book for the first time, these are going to be -1L
 * @param firstBookId the identifier of the author's first book
 * @param lastBookId the identifier of the author's last book
 * */
    public Author(long id, String name, int numBooks, long firstBookId, long lastBookId) {
        //throw new UnsupportedOperationException("paso 1");
        this.id=id;
        this.name=name;
        this.numBooks=numBooks;
        this.firstBookId=firstBookId;
        this.lastBookId=lastBookId;

    }
 /**
  * Adds a book Id to the list of the books authored by a certain author
  * @param idBook is the Id of the book to add
  * */

    public void addBookId(long idBook) {
       // throw new UnsupportedOperationException("paso 1");
        if(this.numBooks==0){
            this.numBooks+=1;
            this.firstBookId=idBook;
            this.lastBookId=idBook;
        }else{
            this.numBooks+=1;
            this.lastBookId=idBook;
        }
    }
/**
 * Puts each param of an Author into bytes
 * @return the array of the bytes
 * */
    public byte[] toBytes() {
        //throw new UnsupportedOperationException("paso 1");
        byte [] pack= new byte[SIZE];
        int offset=0;
        PackUtils.packLong(this.id,pack,offset);
        offset+=8;
        PackUtils.packLimitedString(this.name,NAME_LIMIT,pack,offset);
        offset+=NAME_LIMIT*2;
        PackUtils.packInt(this.numBooks,pack,offset);
        offset+=4;
        PackUtils.packLong(this.firstBookId,pack,offset);
        offset+=8;
        PackUtils.packLong(this.lastBookId,pack,offset);
        return pack;
    }

 /**
  * @return the author and its params after turning them from bytes
  * */

    public static Author fromBytes(byte[] record) {
        //throw new UnsupportedOperationException("paso 1");
        int offset=0;
        Long id=PackUtils.unpackLong(record,offset);
        offset+=8;
        String name=PackUtils.unpackLimitedString(NAME_LIMIT,record,offset);
        offset+=NAME_LIMIT*2;
        int numBooks=PackUtils.unpackInt(record,offset);
        offset+=4;
        Long firstBookId=PackUtils.unpackLong(record,offset);
        offset+=8;
        Long lastBookId=PackUtils.unpackLong(record,offset);
        return new Author(id,name,numBooks,firstBookId,lastBookId);
    }

/**
 * @return the id of the author
 * */

    public long getId() {
        //throw new UnsupportedOperationException("paso 1");
        return this.id;
    }

/**
 * @return the name of the author
 * */

    public String getName() {
        //throw new UnsupportedOperationException("paso 1");
        return this.name;
    }

/**
 * @return the id of the last book
 * */

    public long getLastBookId() {
        //throw new UnsupportedOperationException("paso 1");
        return this.lastBookId;
    }

/**
 * @return The number of books
 * */

    public int getNumBooks() {
        //throw new UnsupportedOperationException("paso 1");
        return this.numBooks;
    }

/**
  * @return the author's first book id
  * */

    public long getFirstBookId() {
        //throw new UnsupportedOperationException("paso 1");
        return this.firstBookId;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numBooks=" + numBooks +
                ", firstBookId=" + firstBookId +
                ", lastBookId=" + lastBookId +
                '}';
    }
}
