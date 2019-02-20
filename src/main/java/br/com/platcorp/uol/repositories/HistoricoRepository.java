package br.com.platcorp.uol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.platcorp.uol.domain.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer>{

}
