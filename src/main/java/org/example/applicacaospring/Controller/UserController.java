package org.example.applicacaospring.Controller;

import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.example.applicacaospring.Service.UserService;
import org.example.applicacaospring.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    @GetMapping("/")
    public String getMessage(){
        return "<h3>hello world, Spring is working</h3>";
    }

    @GetMapping("/user/")
    public List<UserDTO> getUsers() {
        List<UserDTO> usuarios = userService.getAll();         return usuarios;
    }

    @GetMapping("/users/{cpf}")
    public UserDTO getUserFiltro(@PathVariable String cpf){ // metodo que busca usuario especifico pelo cpf
        for(UserDTO user : usuarios){
            if(user.getCpf().equals(cpf)){
                return user;
            }
        }
        return null;
    }

    @GetMapping("/user/{id}")
    UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user")
    UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PostMapping("/newUser")
    UserDTO inserir(@RequestBody UserDTO userDto){
        userDto.setDataCadastro(new Date());
        usuarios.add(userDto);
        return userDto;
    }

    @GetMapping("/user/cpf/{cpf}")
    UserDTO findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @DeleteMapping("/user/{id}")
    UserDTO delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome) {
        return userService.queryByName(nome);
    }


    @DeleteMapping("/users/{cpf}")
    public Boolean deletar(@PathVariable String cpf){
        for(UserDTO user : usuarios){
            if (user.getCpf().equals(cpf)){
                usuarios.remove(user);
                return true;
            }
        }
        return false;
    }

    // depois que o conteiner inicializa, essa anotacao executa o metodo e tudo o que estiver dentro dele
    @PostConstruct
    public void InitiateList(){ // adicionando os 3 primeiros Individuos nessa desgraca
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setNome("Eduardo");
        userDTO1.setCpf("123");
        userDTO1.setEndereco("Rua a");
        userDTO1.setEmail("eduardo@email.com");
        userDTO1.setTelefone("1234-3454");
        userDTO1.setDataCadastro(new Date());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua b");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("1234-3454");
        userDTO2.setDataCadastro(new Date());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua c");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("1234-3454");
        userDTO3.setDataCadastro(new Date());

        usuarios.add(userDTO1);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }
}
