package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Femme extends Personne {
    @OneToMany(mappedBy = "femme")
    private List<Mariage> mariages;

    // Getters et Setters


    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}
