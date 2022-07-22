import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception{

        //Fazer uma conexão Http na API IMDB e buscar os tops 250 filmes
        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=f4c42d7f1d3d45b8375f9d29e4c9afbc";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //Exibir e manipular os dados
//        var extrator = new ExtratorDeConteudoDaNasa();
        var extrator = new ExtratorDeConteudosDoTMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for(Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
