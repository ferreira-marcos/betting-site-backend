// package com.backend.backend.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.SequenceGenerator;

// @Entity
// public class Punter {

//     @Id
//     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
//     @SequenceGenerator(name="seq", sequenceName="ID_SEQ", allocationSize=1, initialValue=1000)
//     private long id;
//     private String name;
//     private Integer cpf;

//     public Long getId() {
//         return id;
//     }
    
//     public String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public Integer getCpf() {
//         return cpf;
//     }
//     public void setCpf(Integer cpf) {
//         this.cpf = cpf;
//     }

//     @Override
//     public String toString() {
//         return "Punter [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
//     }

    
    
// }
