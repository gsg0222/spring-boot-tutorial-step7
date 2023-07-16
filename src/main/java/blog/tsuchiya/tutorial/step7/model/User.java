package blog.tsuchiya.tutorial.step7.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name="USER_DATA")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=255)
	private String name;
	
	@NotNull
	@Size(max=255)
	private String password;

	@NotNull
	@Size(max=10)
	private String role;

}
