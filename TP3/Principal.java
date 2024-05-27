import java.io.File;

//Código usado para teste
public class Principal {
    public static void main(String[] args) {

        File f = new File("dados/livros.db");
        f.delete();
        Arquivo<Livro> arqTeste;
        Livro l1 = new Livro(-1, "9788563560278", "Odisseia", 15.99F);
        Livro l2 = new Livro(-1, "9788584290482", "Ensino Híbrido", 39.90F);
        Livro l3 = new Livro(-1, "9786559790005", "Modernidade L[iquida]", 48.1F);
        Livro l4 = new Livro(-1, "9788582714911", "Memória", 55.58F);
        Livro l5 = new Livro(-1, "9786587150062", "Com Amor", 48.9F);
        Livro l6 = new Livro(-1, "9786587150063", "Bleach", 31.0F);
        Livro l7 = new Livro(-1, "9786587150063", "Thousand Year Blood War", 31.0F);
        Livro livre;
        int id1, id2, id3, id4, id5, id6,id7;

        try {
            arqTeste = new Arquivo<>("dados/livros.db", Livro.class.getConstructor());

            id1 = arqTeste.create(l1);
            System.out.println("Livro criado com o ID: " + id1);

            id2 = arqTeste.create(l2);
            System.out.println("Livro criado com o ID: " + id2);

            id7 = arqTeste.create(l7);
            System.out.println("Livro criado com o ID: " + id7);

            id4 = arqTeste.create(l4);
            System.out.println("Livro criado com o ID: " + id4);

            id5 = arqTeste.create(l5);
            System.out.println("Livro criado com o ID: " + id5);

            if (arqTeste.delete(id2))
                System.out.println("Livro de ID " + id2 + " excluído!");
            else
                System.out.println("Livro de ID " + id2 + " não encontrado!");

            l4.setTitulo("A Memória");
            if (arqTeste.update(l4))
                System.out.println("Livro de ID " + l4.getID() + " alterado!");
            else
               System.out.println("Livro de ID " + l4.getID() + " não encontrado!");
            
            
               if (arqTeste.delete(id7))
               System.out.println("Livro de ID " + id7 + " excluído!");
           else
               System.out.println("Livro de ID " + id7 + " não encontrado!");


            id3 = arqTeste.create(l3);
            System.out.println("Livro criado com o ID: " + id3);

            
            l4.setTitulo("A Memoria 2");
            if (arqTeste.update(l4))
                System.out.println("Livro de ID " + l4.getID() + " alterado!");
            else
               System.out.println("Livro de ID " + l4.getID() + " não encontrado!");
            
            
            id6 = arqTeste.create(l6);
            System.out.println("Livro criado com o ID: " + id6);
            
            livre = arqTeste.read(id1);
            System.out.println(livre.toString());

            livre = arqTeste.read(id3);
            System.out.println(livre.toString());

            livre = arqTeste.read(id4);
            System.out.println(livre.toString());

            livre = arqTeste.read(id5);
            System.out.println(livre.toString());

            livre = arqTeste.read(id6);
            System.out.println(livre.toString());

            arqTeste.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
