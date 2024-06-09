    package com.nutriconecta.demo.model;

    import java.util.List;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.OneToMany;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import lombok.ToString;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public class Fornecedor extends Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; 

        @OneToMany
        private List<Divulgacao> divulgacao;

    }