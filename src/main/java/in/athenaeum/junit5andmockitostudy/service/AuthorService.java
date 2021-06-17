package in.athenaeum.junit5andmockitostudy.service;

import in.athenaeum.junit5andmockitostudy.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    private final List<Author> authorList = new ArrayList<>();

    //  return value
    public Author getRandomAuthor() {
        return new Author(12, "Rajendra", "Prashant", "India");
    }

    //  possible exception
    public Author getById(int id) {
        if (id % 2 == 0) {
            return new Author(12, "Rajendra", "Prashant", "India");
        } else {
            throw new IllegalArgumentException("Cannot find the author id");
        }
    }

    //  side-effect
    public void addAuthor(Author newAuthor) {
        authorList.add(newAuthor);
    }
}
