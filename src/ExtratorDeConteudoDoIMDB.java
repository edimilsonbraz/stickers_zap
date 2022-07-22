import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{

    @Override
    public List<Conteudo> extraiConteudos(String json){
        //Extrair só os dados que interessam(titulo, imagem)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        //Relacionamento entre a Class Conteudo
        List<Conteudo> conteudos = new ArrayList<>();

        //popular a lista de conteudos
        for(Map<String, String> atributos : listaDeAtributos){
            String titulo = atributos.get("titulo");
            String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }



}