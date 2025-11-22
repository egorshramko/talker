package ru.shramko.talker.data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.shramko.talker.security.data.User;

@Data
@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_generator")
	@SequenceGenerator(name = "profile_id_generator", sequenceName = "profile_pkey_seq",
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String lastname;
	
	private String firstname;
	
	private List<Post> posts;
	
	@NotNull
	@OneToOne
	private User user;
	
}
