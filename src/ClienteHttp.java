import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttp {

    public String buscaDados(String url){
        try{
            URI endereco = URI.create(url); // identificação unica associando a url
            var client = HttpClient.newHttpClient(); //Pode ser usado para enviar solicitações e resposta
            var request = HttpRequest.newBuilder(endereco).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return body;

        }catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
