package stormsprid.endtermtest.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;
import stormsprid.endtermtest.Repository.UserRepository;
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        stormsprid.endtermtest.Entity.User user = userRepository.findByUsername(username).orElseThrow(() ->
         new UsernameNotFoundException("Не найден:" + username));
        return  User.builder().username(user.getUsername()).password(user.getPassword()).build();
    }
}
