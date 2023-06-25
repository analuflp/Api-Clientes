package ayty.analuisa.projeto.Controller;

import ayty.analuisa.projeto.Controller.Dto.ServicoPrestadoDTO;
import ayty.analuisa.projeto.models.Cliente;
import ayty.analuisa.projeto.models.ServicoPrestado;
import ayty.analuisa.projeto.repositories.ClienteRepository;
import ayty.analuisa.projeto.repositories.ServicoPrestadoRepository;
import ayty.analuisa.projeto.util.BigDecimalConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto){
       LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData( data );
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(servicoPrestado);

    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(   @RequestParam(value = "nome", required = false, defaultValue = "")String nome,
                                              @RequestParam(value = "mes", required = false) Integer mes){

        return repository.findByNomeClienteAndMes("%"+ nome + "%", mes);


    }
}
