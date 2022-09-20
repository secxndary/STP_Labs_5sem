package by.valdaitsevv.stp_lab1.controller;
import by.valdaitsevv.stp_lab1.forms.AlbumForm;
import by.valdaitsevv.stp_lab1.forms.UpdateAlbumForm;
import by.valdaitsevv.stp_lab1.model.Album;
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
public class AlbumController
{
    private static List<Album> albums = new ArrayList<Album>();
    static
    {
        albums.add(new Album("Yeezus", "Kanye West"));
        albums.add(new Album("IGOR", "Tyler The Creator"));
    }

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model)
    {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called GET");
        return modelAndView;
    }


    @GetMapping(value = {"/allalbums"})          // маршрутизация
    public ModelAndView albumsList(Model model)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");      // файл
        model.addAttribute("albums", albums);
        log.info("/allalbums was called GET");
        return modelAndView;
    }


    @GetMapping(value = {"/addalbum"})
    public ModelAndView showAddAlbum(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("addalbum");
        var albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);
        log.info("/addalbum was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/addalbum"})
    public ModelAndView saveAlbum(Model model, @ModelAttribute("albumform") AlbumForm albumForm)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String title = albumForm.getTitle();
        String author = albumForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0)
        {
            Album newAlbum = new Album(title, author);
            albums.add(newAlbum);
            model.addAttribute("albums", albums);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addalbum");
        log.info("/addalbum was called POST");
        return modelAndView;
    }


    @GetMapping(value = {"/deletealbum"})
    public ModelAndView showDeleteAlbum(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("deletealbum");
        var albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);
        log.info("/deletealbum was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/deletealbum"})
    public ModelAndView deleteAlbum(Model model, @ModelAttribute("albumform") AlbumForm albumForm)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String title = albumForm.getTitle();
        String author = albumForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0)
        {
            var newAlbum = new Album(title, author);
            if (albums.contains(newAlbum))
            {
                albums.remove(newAlbum);
                model.addAttribute("albums", albums);
                return modelAndView;
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("deletealbum");
        log.info("/deletealbum was called POST");
        return modelAndView;
    }


    @GetMapping(value = {"/updatealbum"})
    public ModelAndView showUpdateAlbum(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("updatealbum");
        var albumForm = new UpdateAlbumForm();
        model.addAttribute("albumform", albumForm);
        log.info("/updatealbum was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/updatealbum"})
    public ModelAndView updateAlbum(Model model, @ModelAttribute("albumform") UpdateAlbumForm updateAlbumForm)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String oldTitle = updateAlbumForm.getOldTitle();
        String oldAuthor = updateAlbumForm.getOldAuthor();
        var title = updateAlbumForm.getTitle();
        var author = updateAlbumForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0 &&
            oldTitle != null && oldTitle.length() > 0 && oldAuthor != null && oldAuthor.length() > 0)
        {
            var newAlbum = new Album(title, author);
            var oldAlbum = new Album(oldTitle, oldAuthor);
            System.out.println("oldbook: title = " + oldTitle + ", author = " + oldAuthor);
            System.out.println("newbook: title = " + title + ", author = " + author);
            if (albums.contains(oldAlbum))
            {
                System.out.println("it contains");
                for (var albumSearch: albums)
                {
                    System.out.println("search....");
                    System.out.println("old: " + oldAuthor + " " + oldTitle);
                    System.out.println("search: " + albumSearch.getAuthor() + " " + albumSearch.getTitle());
                    if (albumSearch.getAuthor().equals(oldAuthor) && albumSearch.getTitle().equals(oldTitle))
                    {
                        System.out.println("FOUND ALBUM");
                        albumSearch.setAuthor(author);
                        albumSearch.setTitle(title);
                    }
                }
                model.addAttribute("albums", albums);
                return modelAndView;
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("updatealbum");
        log.info("/updatealbum was called POST");
        return modelAndView;
    }

}
