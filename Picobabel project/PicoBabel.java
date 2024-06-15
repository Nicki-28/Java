package picobabel;

import acm.program.CommandLineProgram;
import domain.Author;
import domain.Book;
import files.AuthorsFile;
import files.BooksFile;
import files.Logger;

import java.io.*;
import java.util.StringTokenizer;

public class PicoBabel extends CommandLineProgram {

    private static final String MOVEMENTS = "movements.csv";
    private static final String LOG       = "logger.log";
    private static final String AUTHORS   = "authorsDB.dat";
    private static final String BOOKS     = "booksDB.dat";

    private BufferedReader movements;
    private Logger         logger;
    private AuthorsFile    authorsDB;
    private BooksFile      booksDB;

    public static void main(String[] args) {
        new PicoBabel().start(args);
    }

    @Override
    public void run() {
        try {
            openFiles();
            resetData();
            processMovements();
        } catch (IOException e) {
            println("Glups !!!");
            e.printStackTrace(getWriter());
        } finally {
            try {
                closeFiles();
            } catch (IOException e) {
                e.printStackTrace(getWriter());
            }
        }
    }

    private void openFiles() throws IOException {
        //throw new UnsupportedOperationException("paso 5");
        this.movements= new BufferedReader(new FileReader(MOVEMENTS));
        this.logger= new Logger(LOG);
        this.authorsDB= new AuthorsFile(AUTHORS);
        this.booksDB= new BooksFile(BOOKS);
    }

    private void resetData() throws IOException {
        //throw new UnsupportedOperationException("paso 5");
        authorsDB.reset();
        booksDB.reset();
    }

    private void closeFiles() throws IOException {
        //throw new UnsupportedOperationException("paso 5");
        this.movements.close();
        this.logger.close();
        this.authorsDB.close();
        this.booksDB.close();
    }

    private void processMovements() throws IOException {
        //throw new UnsupportedOperationException("paso 5");
            //open files
            openFiles();
            String input; //process each line
            while((input = movements.readLine())!=null) {
                if (input.startsWith("ALTA_AUTOR")) {
                    addAuthor(input);
                } else if (input.startsWith("INFO_AUTOR")) {
                    infAuthor(input);
                } else if (input.startsWith("ALTA_LIBRO")) {
                    addBook(input);
                }else if(input.startsWith("INFO_LIBRO")){
                    infBook(input);
                }else{
                    StringTokenizer token = new StringTokenizer(input,",");
                    logger.errorUnknownOperation(token.nextToken());
                }
            }
            closeFiles();
    }

    // management of the next author
    private long idsAutor = 1L;

    private void addAuthor(String input) throws IOException{
        StringTokenizer token= new StringTokenizer(input,",");
        token.nextToken();// skip the operation name
        String newName= token.nextToken();
        Author newA= new Author(idsAutor,newName);
        newAuthor(newA);
    }
    private void newAuthor(Author newAuth) throws IOException {
        this.authorsDB.writeAuthor(newAuth);
        idsAutor+=1;
        logger.okNewAuthor(newAuth);

    }

    //management of the next book

    private long idsB=1L;

    private void addBook(String input) throws IOException{
        StringTokenizer token= new StringTokenizer(input,",");
        token.nextToken();
        String title= token.nextToken();
        long authorId= Long.parseLong(token.nextToken());

        if(authorsDB.isValidId(authorId)){
            Author authorB= authorsDB.readAuthor(authorId);
            newBook(title,authorId,authorB);
        }else{
            logger.errorNewBook(authorId);
        }
    }

    private void newBook(String line, long authorId, Author authorB) throws IOException{
        Book newBook= new Book(idsB,line,authorId);
        booksDB.writeBook(newBook);

        //if isn't the first book
        if(authorB.getNumBooks()>0){
            //Update the nextBook Id
            Book lastBook= booksDB.readBook(authorB.getLastBookId());
            lastBook.setNextBookId(newBook.getId());
            booksDB.writeBook(lastBook);
        }

        authorB.addBookId(newBook.getId());
        authorsDB.writeAuthor(authorB);
        logger.okNewBook(newBook);
        idsB+=1;
    }

    private void infAuthor(String input) throws IOException{
        StringTokenizer token= new StringTokenizer(input,",");
        token.nextToken();
        long ifIdAuthor= Long.parseLong(token.nextToken());

        if(authorsDB.isValidId(ifIdAuthor)){
            Author ifAuthor= authorsDB.readAuthor(ifIdAuthor);
            Book [] authorB= booksDB.getBooksForAuthor(ifAuthor);
            logger.okInfoAutor(ifAuthor,authorB);
        }else{
            logger.errorInfoAutor(ifIdAuthor);
        }

    }

    private void infBook(String input) throws IOException{
        StringTokenizer token= new StringTokenizer(input,",");
        token.nextToken();
        long ifIdBook= Long.parseLong(token.nextToken());

        if(booksDB.isValidId(ifIdBook)){
            Book bookAuthor= booksDB.readBook(ifIdBook);
            Author author= authorsDB.readAuthor(bookAuthor.getAuthorId());
            logger.okInfoBook(bookAuthor,author);
        }else{
            logger.errorInfoBook(ifIdBook);
        }

    }


}
