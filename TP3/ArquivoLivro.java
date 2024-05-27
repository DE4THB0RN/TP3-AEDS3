import java.io.RandomAccessFile;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;


//Classe extendida do Arquivo genérico
public class ArquivoLivro extends Arquivo<Livro> {


    private List<String> stopwords;

    ArquivoLivro(String na) throws Exception {
        super(na, Livro.class.getConstructor());
        try {
            String stwrds = "de a o que e do da em um para é com não uma os no se na por mais as dos como mas foi ao ele das tem à seu sua ou ser quando muito há nos já está eu também só pelo pela até isso ela entre era depois sem mesmo aos ter seus quem nas me esse eles estão você tinha foram essa num nem suas meu às minha têm numa pelos elas havia seja qual será nós tenho lhe deles essas esses pelas este fosse dele tu te vocês vos lhes meus minhas teu tua teus tuas nosso nossa nossos nossas dela delas esta estes estas aquele aquela aqueles aquelas isto aquilo estou está estamos estão estive esteve estivemos estiveram estava estávamos estavam estivera estivéramos esteja estejamos estejam estivesse estivéssemos estivessem estiver estivermos estiverem hei há havemos hão houve houvemos houveram houvera houvéramos haja hajamos hajam houvesse houvéssemos houvessem houver houvermos houverem houverei houverá houveremos houverão houveria houveríamos houveriam sou somos são era éramos eram fui foi fomos foram fora fôramos seja sejamos sejam fosse fôssemos fossem for formos forem serei será seremos serão seria seríamos seriam tenho tem temos tém tinha tínhamos tinham tive teve tivemos tiveram tivera tivéramos tenha tenhamos tenham tivesse tivéssemos tivessem tiver tivermos tiverem terei terá teremos terão teria teríamos teriam";
            String normalizada = Normalizer.normalize(stwrds, Normalizer.Form.NFD);
            Pattern padrao = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            normalizada = padrao.matcher(normalizada).replaceAll("");
            String[] partes = normalizada.split(" ");
            stopwords = new ArrayList<>();

            for (String s : partes) {
                stopwords.add(s);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int createLivro(Livro novo) {
        int resp = -1;
        try {

            // Código original
            resp = this.create(novo);

        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public boolean updateLivro(Livro novoLivro) {

        boolean resp = false;

        try {
            // Código original
            resp = this.update(novoLivro);

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public boolean deleteLivro(int id) {
        boolean resp = false;
        try {
            // Código original
            resp = this.delete(id);

        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public void comprime(){
        
        try {

            //Primeiro pega a data e hora atuais
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

            //Cria o arquivo de backup
            RandomAccessFile backup = new RandomAccessFile("backups/"+ timeStamp, nomeArquivo);
            byte[] conteudo = new byte[(int)(arquivo.length() - TAM_CABECALHO)];

            //Pega todos os registros do arquivo (sem cabeçalho)
            arquivo.seek(TAM_CABECALHO);
            arquivo.readFully(conteudo);

            //Comprime em LZW
            byte[] comprimido = LZW.codifica(conteudo);

            //Coloca no arquivo backup
            backup.write(comprimido);

            backup.close();
        } catch (Exception e) {
            MyIO.println("" + e);
        }
    }

}
