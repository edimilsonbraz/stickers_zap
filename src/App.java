import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception{

        //Fazer uma conexão Http na API IMDB e buscar os tops 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies"; // usando endereço alternativo
        URI endereco = URI.create(url); // identificação unica associando a url
        var client = HttpClient.newHttpClient(); //Pode ser usado para enviar solicitações e resposta
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

//        System.out.println(body);

        //Extrair só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for(Map<String, String> filme : listaDeFilmes) {
//
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(filme.get("title"));
            System.out.println();
        }
    }
}
