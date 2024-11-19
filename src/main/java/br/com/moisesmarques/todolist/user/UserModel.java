package br.com.moisesmarques.todolist.user;

import lombok.Data;

@Data //lombok cria os getters e setters.
public class UserModel {
    
    private String username;
    private String name;
    private String password;

}
