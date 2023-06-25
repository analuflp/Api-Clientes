package ayty.analuisa.projeto.repositories;

import ayty.analuisa.projeto.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
