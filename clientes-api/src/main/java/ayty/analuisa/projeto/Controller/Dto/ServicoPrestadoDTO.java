package ayty.analuisa.projeto.Controller.Dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

    @NotEmpty(message = "{campo.descricao}")
    private String descricao;

    @NotEmpty(message = "{campo.preco}")
    private String preco;

    @NotEmpty(message = "{campo.data}")
    private String data;

    @NotNull(message = "{campo.cliente}")
    private Integer idCliente;
}
