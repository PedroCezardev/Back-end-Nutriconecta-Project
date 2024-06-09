    package com.nutriconecta.demo.model;
    
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.MappedSuperclass;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import lombok.ToString;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @MappedSuperclass
    public abstract class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;
            
        private String cnpj;

        private String endereco;

        private String telefone;

        private String email;

        private String descricao;

        private String password;
 
    }