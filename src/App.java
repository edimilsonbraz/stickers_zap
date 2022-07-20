import java.net.URI;
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
        for(Map<String, String> filme : listaDeFilmes) {
            System.out.println("Titulo: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m Classificação: \u001b[m" + filme.get("imDbRating"));
//           if(filme.get("imDbRating") >= ("9.0") ) {
//               System.out.println("\u2B50 \u2B50 \u2B50 \u2B50 \u2B50 ");
//           } else{
//               System.out.println("\u2B50 \u2B50 \u2B50 \u2B50");
//           }
            System.out.println();

        }
    }
}
