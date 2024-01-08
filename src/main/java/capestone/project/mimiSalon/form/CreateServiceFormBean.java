package capestone.project.mimiSalon.form;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceFormBean {
    private Integer id;
    private String name;
    private String image_url;
    private double price;

}