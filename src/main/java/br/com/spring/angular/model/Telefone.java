package br.com.spring.angular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Telefone implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;

    @JsonIgnore // evitando recursividade
    //optional = false: A associação com a entidade é obrigatória(não pode cadastrar número de telefone "soltos")
    @ManyToOne(optional = false) //ManyToOne combinada com @JoinColumn(name = "usuario_id")
    //cria uma chave estrangeira (foreign key - FK) na tabela telefone
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
