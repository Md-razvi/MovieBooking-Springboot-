package model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.logging.log4j.util.Lazy;

import java.lang.classfile.Label;
import java.util.List;

@Entity
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theatreName;
    private String theatreLocation;
    private Integer theatreCapacity;
    private String theatreScreenType;
    @OneToMany(mappedBy = "theatre",fetch=FetchType.LAZY)
    private List<Show> Shows;
}
