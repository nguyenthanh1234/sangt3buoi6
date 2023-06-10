package com.example.sangt3buoi6.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;



@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    //hêlo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    @Length(min = 0,max = 50,message = "Tên hình ảnh không quá 50 ký tự")
    private String image;
    @Column
    private long price;

    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}
