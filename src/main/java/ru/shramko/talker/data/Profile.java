package ru.shramko.talker.data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.shramko.talker.security.data.User;

@Data
@Entity
@NoArgsConstructor
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_generator")
	@SequenceGenerator(name = "profile_id_generator", sequenceName = "profile_pkey_seq",
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String lastname;
	
	private String firstname;
	
	@Transient
	private List<Post> posts;
	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	public Profile(User user) {
		this.user = user;
	}
	
}
