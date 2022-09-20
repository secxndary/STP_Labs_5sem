package by.valdaitsevv.stp_lab1.controller;
import by.valdaitsevv.stp_lab1.forms.BookForm;
import by.valdaitsevv.stp_lab1.forms.UpdateBookForm;
import by.valdaitsevv.stp_lab1.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BookController
{
    private static List<Book> books = new ArrayList<Book>();
    static
    {
        books.add(new Book("Full Stack Development with JHipster", "Deepu K Sasidharan, Sendil Kumar N"));
        books.add(new Book("Pro Spring Security", "Carlo Scarioni, Massimo Nardone"));
    }

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    @GetMapping(value = {"/", "/index", "/cock"})
    public ModelAndView index(Model model)
    {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called GET");
        return modelAndView;
    }


    @GetMapping(value = {"/allbooks"})          // маршрутизация
    public ModelAndView personList(Model model)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");      // файл
        model.addAttribute("books", books);
        log.info("/allbooks was called GET");
        return modelAndView;
    }


    @GetMapping(value = {"/addbook"})
    public ModelAndView showAddPersonPage(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("addbook");
        BookForm bookForm = new BookForm();
        model.addAttribute("bookform", bookForm);
        log.info("/addbook was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/addbook"})
    public ModelAndView savePerson(Model model, @ModelAttribute("bookform") BookForm bookForm)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0)
        {
            Book newBook = new Book(title, author);
            books.add(newBook);
            model.addAttribute("books", books);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addbook");
        log.info("/addbook was called POST");
        return modelAndView;
    }


    @GetMapping(value = {"/deletebook"})
    public ModelAndView showDeletePerson(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("deletebook");
        BookForm bookForm = new BookForm();
        model.addAttribute("bookform", bookForm);
        log.info("/deletebook was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/deletebook"})
    public ModelAndView deletePerson(Model model, @ModelAttribute("bookform") BookForm bookForm)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0)
        {
            Book newBook = new Book(title, author);
            if (books.contains(newBook))
            {
                books.remove(newBook);
                model.addAttribute("books", books);
                return modelAndView;
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("deletebook");
        log.info("/deletebook was called POST");
        return modelAndView;
    }


    @GetMapping(value = {"/updatebook"})
    public ModelAndView showUpdatePerson(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("updatebook");
        UpdateBookForm bookForm = new UpdateBookForm();
        model.addAttribute("bookform", bookForm);
        log.info("/updatebook was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/updatebook"})
    public ModelAndView updatePerson(Model model, @ModelAttribute("bookform") UpdateBookForm updateBookForm)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        String oldTitle = updateBookForm.getOldTitle();
        String oldAuthor = updateBookForm.getOldAuthor();
        var title = updateBookForm.getTitle();
        var author = updateBookForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0 &&
            oldTitle != null && oldTitle.length() > 0 && oldAuthor != null && oldAuthor.length() > 0)
        {
            Book newBook = new Book(title, author);
            Book oldBook = new Book(oldTitle, oldAuthor);
            System.out.println("oldbook: title = " + oldTitle + ", author = " + oldAuthor);
            System.out.println("newbook: title = " + title + ", author = " + author);
            if (books.contains(oldBook))
            {
                System.out.println("it contains");
                for (var bookSearch: books)
                {
                    System.out.println("search....");
                    System.out.println("old: " + oldAuthor + " " + oldTitle);
                    System.out.println("search: " + bookSearch.getAuthor() + " " + bookSearch.getTitle());
                    if (bookSearch.getAuthor().equals(oldAuthor) && bookSearch.getTitle().equals(oldTitle))
                    {
                        System.out.println("FOUND BOOK");
                        bookSearch.setAuthor(author);
                        bookSearch.setTitle(title);
                    }
                }
                model.addAttribute("books", books);
                return modelAndView;
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("updatebook");
        log.info("/updatebook was called POST");
        return modelAndView;
    }

}
