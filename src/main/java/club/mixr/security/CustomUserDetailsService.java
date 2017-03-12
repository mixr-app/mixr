package club.mixr.security;

import club.mixr.data.entity.UserEntity;
import club.mixr.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOne(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found in database!"));
		CustomUserDetails details = userEntityToDetails(userEntity);
		System.out.println("Logging in as " + details.getUsername());
		return details;
	}

	private static CustomUserDetails userEntityToDetails(UserEntity userEntity){
		List<String> authorities = new ArrayList<>();
		if (userEntity.isAdmin()){
			authorities.add("ROLE_ADMIN");
		}
		return new CustomUserDetails(
				userEntity.getUsername(),
				userEntity.getPassword(),
				userEntity.isEnabled(),
				authorities);
	}

}