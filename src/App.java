import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
//imports do Users>>>>
import users.Funcionario;
import users.Gerente;
import users.Usuario;


public class App{
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        carregarUsuarios();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao sistema de login!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastro");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (opcao == 1) {
            fazerLogin(scanner);
            
        } else if (opcao == 2) {
            fazerCadastro(scanner);
        } else {
            System.out.println("Opção inválida!");
        }

        salvarUsuarios();
    }
//SEPARAR METODOS
    private static void fazerLogin(Scanner scanner) {
        System.out.println("Escolha o tipo de login:");
        System.out.println("1 - Funcionario");
        System.out.println("2 - Gerente");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Nickname: ");
        String nickname = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNickname().equals(nickname) && usuario.getSenha().equals(senha)) {
                if (tipo == 1 && usuario instanceof Funcionario) {
                    System.out.println("Login de Funcionario realizado com sucesso!");
                    return;
                } else if (tipo == 2 && usuario instanceof Gerente) {
                    System.out.println("Login de Gerente realizado com sucesso!");
                    return;
                }
            }
        }

        System.out.println("Nickname ou senha incorretos, ou tipo de usuário inválido!");
    }

    private static void fazerCadastro(Scanner scanner) {
        System.out.println("Escolha o tipo de cadastro:");
        System.out.println("1 - Funcionario");
        System.out.println("2 - Gerente");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Nickname: ");
        String nickname = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario;
        if (tipo == 1) {
            novoUsuario = new Funcionario(nickname, senha);
        } else if (tipo == 2) {
            novoUsuario = new Gerente(nickname, senha);
        } else {
            System.out.println("Opção inválida!");
            return;
        }

        usuarios.add(novoUsuario);
        System.out.println("Cadastro realizado com sucesso!");
    }

    private static void carregarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipo = dados[0];
                String nickname = dados[1];
                String senha = dados[2];

                if (tipo.equals("Funcionario")) {
                    usuarios.add(new Funcionario(nickname, senha));
                } else if (tipo.equals("Gerente")) {
                    usuarios.add(new Gerente(nickname, senha));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado. Criando novo arquivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                String tipo = usuario instanceof Funcionario ? "Funcionario" : "Gerente";
                bw.write(tipo + "," + usuario.getNickname() + "," + usuario.getSenha());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
