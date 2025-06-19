package org.example.applicacaospring.Service;

import org.example.applicacaospring.dto.UserDTO;
import org.example.applicacaospring.model.User;
import org.example.applicacaospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> usuarios = userRepository.findAll(); // ira retornar uma lista de usuarios, instancias da entidade User
        return usuarios
                .stream()
                .map(UserDTO::convert)  // converte a lista stream em UserDTO
                .collect(Collectors.toList());   // retorna a lista de DTOs
    }

    // funcoes findById, save, delete

    public UserDTO findById(long userId) {
        Optional<User> usuario = userRepository.findById(userId);
        if (usuario.isPresent()) {
            return UserDTO.convert(usuario.get());
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(long userId) {
        Optional<User> user = userRepository.findById(userId); if (user.isPresent()) {
            userRepository.delete(user.get());
        }
        return null;
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf); if (user != null) {
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name); return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }


}
