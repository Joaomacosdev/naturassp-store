package br.com.joao.naturassp.repository;

import br.com.joao.naturassp.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //public List<Pedido> findAllByCliente();
}
