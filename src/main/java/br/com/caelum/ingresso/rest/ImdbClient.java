package br.com.caelum.ingresso.rest;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.config.OmdbApiConfig;
import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {

	public <T> Optional<T> request(Filme filme, Class<T> tClass) {
		RestTemplate client = new RestTemplate();

		MappingJackson2HttpMessageConverter mapping = new MappingJackson2HttpMessageConverter();
		mapping.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		client.getMessageConverters().add(mapping);

		String titulo = filme.getNome().replace(" ", "+");

		// Busca os dados da API em um arquivo de configuracoes
		// (para nao ficarem disponiveis no GITHUB)
		OmdbApiConfig omdbApiConfig = new OmdbApiConfig();
		String url = String.format("http://www.omdbapi.com/?i=%1$s&apikey=%2$s&t=%3$s", omdbApiConfig.getId(), omdbApiConfig.getKey(), titulo);
		
		try {
			return Optional.of(client.getForObject(url, tClass));
		} catch (RestClientException e) {
			System.out.println("Erro: " + e.getMessage());
			return Optional.empty();
		}
	}
}
