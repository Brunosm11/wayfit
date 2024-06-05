package br.com.projeto.api.Config;


	import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	@Configuration
	public class Configurationcors implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(@NonNull CorsRegistry registry) {
	        registry.addMapping("/**") // Mapeia todas as URLs
	                .allowedOrigins("*") // Permite todas as origens (você pode restringir a origem conforme necessário)
	                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
	                .allowedHeaders("*"); // Cabeçalhos permitidos
	    }
	}