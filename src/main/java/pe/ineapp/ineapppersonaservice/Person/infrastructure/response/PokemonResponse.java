package pe.ineapp.ineapppersonaservice.Person.infrastructure.response;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {

    private List<PokemonResponseDetail> results;
}
