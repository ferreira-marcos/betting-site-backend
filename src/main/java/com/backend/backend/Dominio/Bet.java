package com.backend.backend.Dominio;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bet {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @SequenceGenerator(name="seq", sequenceName="ID_SEQ", allocationSize=1, initialValue=1000)
    private Long id;

    // private String name;
    // private Long cpf;
    private String numbers;

    @ManyToOne(cascade = CascadeType.ALL) 
    private Punter punter;

    // @Autowired
    // public Bet(Punter punter, String numbers) {
    //     this.punter = punter;
    //     this.numbers = numbers;
    // }

    public Bet() {
        // Você pode inicializar atributos padrão aqui, se necessário
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    // public String getNumbers() {
    //     return numbers;
    // }
    // public void setNumbers(String numbers) {
    //     this.numbers = numbers;
    // }
    // public String getName() {
    //     return name;
    // }
    // public void setName(String name) {
    //     this.name = name;
    // }
    // public Long getCpf() {
    //     return cpf;
    // }
    // public void setCpf(Long cpf) {
    //     this.cpf = cpf;
    // }
    
    
  

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Punter getPunter() {
        return punter;
    }

    public void setPunter(Punter punter) {
        this.punter = punter;
    }


    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bet other = (Bet) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Bet [id=" + id + ", numbers=" + numbers + ", name=" + punter.getName() + ", cpf=" + punter.getCpf() + "]";
    }
    
   
}
