    package com.nutriconecta.demo.model;

    import java.time.LocalDateTime;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.EqualsAndHashCode;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import lombok.ToString;

    @Table(name="alimento")
    @Entity(name="alimento")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = "id")
    public class Alimento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 50, nullable = false)
        private String nomeAlimento;

        @Column(length = 50, nullable = false)
        private String tipoAlimento;

        @Column(nullable = false)
        private Double quantidadeAlimento;

        @Column(nullable = false)
        private Boolean disponibilidade;

        @Column(nullable = false)
        private LocalDateTime dataTime;

        
    }
