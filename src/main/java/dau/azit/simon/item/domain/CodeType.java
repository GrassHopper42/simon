package dau.azit.simon.item.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class CodeType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "type")
    private Set<Code> codes;

    public CodeType() {}

    public CodeType(Long id, String description, Set<Code> codes) {
        this.id = id;
        this.description = description;
        this.codes = codes;
    }
}