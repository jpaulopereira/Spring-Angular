package br.com.spring.angular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;

    private String senha;

    private String nome;

    //mappedBy: indica que a relação é bidirecional, mas controlada por outra entidade
    //orphanRemoval: Se você remover um Usuario, os Telefones relacionados não serão removidos automaticamente.
    @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();
}
