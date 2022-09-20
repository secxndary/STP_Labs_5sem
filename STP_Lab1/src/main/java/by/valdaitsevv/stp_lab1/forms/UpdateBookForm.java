package by.valdaitsevv.stp_lab1.forms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookForm
{
    private String oldTitle;
    private String oldAuthor;
    private String title;
    private String author;
}
