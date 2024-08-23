package application;

import db.DB;
import db.DbIntegrityException;
import model.dao.*;
import model.entities.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);
    private static AlunoDAO alunoDAO;
    private static ProfessorDAO professorDAO;
    private static MatriculaDAO matriculaDAO;
    private static NotaDAO notaDAO;
    private static CursoDAO cursoDAO;
    private static final String RESET = "\033[0m";
    private static final String VERDE = "\033[32m";

    public static void main(String[] args) {
        alunoDAO = DaoFactory.criarAlunoDAO();
        professorDAO = DaoFactory.criarProfessorDAO();
        matriculaDAO = DaoFactory.criarMatriculaDAO();
        notaDAO = DaoFactory.criarNotaDAO();
        cursoDAO = DaoFactory.criarCursoDAO();

        while (true) {
            exibirMenuPrincipal();
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println(
                "\033[0m╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║   \033[1;31mO\033[0m \033[1;32m■ ■                    \033[0m║                                                                                ║");
        System.out.println(
                "\033[0m║   \033[1;32m■ ■\033[0m                      ║                                                                                ║");
        System.out.println(
                "║   \033[1;32m■ ■ ■\033[0m INSTITUTO FEDERAL  ║      SISTEMA DE GERENCIAMENTO  ESCOLAR  DO IFPI                                ║");
        System.out.println(
                "║   \033[1;32m■ ■\033[0m   Piauí              ║                                                                                ║");
        System.out.println(
                "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                                                             ║");
        System.out.println(
                "║                                        Escolha uma opção:                                                   ║");
        System.out.println(
                "║                                                                                                             ║");
        System.out.println(
                "\033[0m║\033[1;32m                         1. Aluno                                                                            \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         2. Professor                                                                        \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         3. Matricular                                                                       \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         4. Notas                                                                            \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         5. Cursos                                                                           \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         0. Sair do Sistema                                                                  \033[0m║\033[0m");
        System.out.println(
                "║                                                                                                             \033[0m║");
        System.out.println(
                "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
        System.out.print("\033[0m Digite a opção escolhida: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.println( "\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");

        switch (escolha) {
            case 1:
                menuAluno();
                break;
            case 2:
                menuProfessor();
                break;
            case 3:
                menuMatricular();
                break;
            case 4:
                menuNotas();
                break;
            case 5:
                menuCursos();
                break;
            case 0:
                System.out.println("Saindo do Sistema. Até logo!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuAluno() {
        while (true) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                             ║");
            System.out.println("║ " + VERDE + "                                Menu de Alunos" + RESET + "                                                              ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ " + VERDE + "                             1. Inserir Aluno" + RESET + "                                                               ║");
            System.out.println("║ " + VERDE + "                             2. Atualizar Aluno" + RESET + "                                                             ║");
            System.out.println("║ " + VERDE + "                             3. Excluir Aluno" + RESET + "                                                               ║");
            System.out.println("║ " + VERDE + "                             4. Buscar Aluno" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             5. Buscar Todos" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             0. Voltar" + RESET + "                              1                                       ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
            System.out.print("\033[0m Digite a opção escolhida: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println( "\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");

            try {
                switch (opcao) {
                    case 1:
                        inserirAluno();
                        break;
                    case 2:
                        atualizarAluno();
                        break;
                    case 3:
                        excluirAluno();
                        break;
                    case 4:
                        buscarAluno();
                        break;
                    case 5:
                        buscarTodosAlunos();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void inserirAluno() {
        try {
            System.out.println("Inserir novo Aluno");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Data de Nascimento (yyyy-mm-dd): ");
            String dataNascimento = scanner.nextLine();

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            Alunos aluno = new Alunos(null, nome, LocalDate.parse(dataNascimento), endereco, telefone, email);
            alunoDAO.inserir(aluno);
            System.out.println("Aluno inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro na inserção: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                inserirAluno();
            }
        }
    }

    private static void atualizarAluno() {
        try {
            System.out.println("Atualizar Aluno");

            System.out.print("ID do Aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Alunos aluno = alunoDAO.buscarPorId(id);

            if (aluno != null) {
                System.out.print("Nome (" + aluno.getNome() + "): ");
                String nome = scanner.nextLine();
                aluno.setNome(nome.isEmpty() ? aluno.getNome() : nome);

                System.out.print("Data de Nascimento (" + aluno.getDataNascimento() + "): ");
                String dataNascimento = scanner.nextLine();
                aluno.setDataNascimento(dataNascimento.isEmpty() ? aluno.getDataNascimento() : LocalDate.parse(dataNascimento));

                System.out.print("Endereço (" + aluno.getEndereco() + "): ");
                String endereco = scanner.nextLine();
                aluno.setEndereco(endereco.isEmpty() ? aluno.getEndereco() : endereco);

                System.out.print("Telefone (" + aluno.getTelefone() + "): ");
                String telefone = scanner.nextLine();
                aluno.setTelefone(telefone.isEmpty() ? aluno.getTelefone() : telefone);

                System.out.print("Email (" + aluno.getEmail() + "): ");
                String email = scanner.nextLine();
                aluno.setEmail(email.isEmpty() ? aluno.getEmail() : email);

                alunoDAO.atualizar(aluno);
                System.out.println("Aluno atualizado com sucesso!");
            } else {
                System.out.println("Aluno não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro na atualização: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                atualizarAluno();
            }
        }
    }

    private static void excluirAluno() {
        try {
            System.out.println("Excluir Aluno");

            System.out.print("ID do Aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            alunoDAO.excluirPorId(id);
            System.out.println("Aluno excluído com sucesso!");
        } catch (DbIntegrityException e) {
            System.out.println("Erro ao excluir o aluno: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                excluirAluno();
            }
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void buscarAluno() {
        try {
            System.out.println("Buscar Aluno");

            System.out.print("ID do Aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Alunos aluno = alunoDAO.buscarPorId(id);

            if (aluno == null) {
                System.out.println("Aluno não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro na busca: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                buscarAluno();
            }
        }
    }

    private static void buscarTodosAlunos() {
        try {
            System.out.println("Lista de Todos os Alunos");
            alunoDAO.buscarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao buscar alunos: " + e.getMessage());
        }
    }


    private static void menuProfessor() {
        while (true) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                             ║");
            System.out.println("║ " + VERDE + "                                Menu de Professores" + RESET + "                                                         ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ " + VERDE + "                             1. Inserir Professor" + RESET + "                                                           ║");
            System.out.println("║ " + VERDE + "                             2. Atualizar Professor" + RESET + "                                                         ║");
            System.out.println("║ " + VERDE + "                             3. Excluir Professor" + RESET + "                                                           ║");
            System.out.println("║ " + VERDE + "                             4. Buscar Professor" + RESET + "                                                            ║");
            System.out.println("║ " + VERDE + "                             5. Buscar Todos" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             0. Voltar" + RESET + "                                                                      ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
            System.out.print("\033[0m Digite a opção escolhida: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println( "\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");

            try {
                switch (opcao) {
                    case 1:
                        inserirProfessor();
                        break;
                    case 2:
                        atualizarProfessor();
                        break;
                    case 3:
                        excluirProfessor();
                        break;
                    case 4:
                        buscarProfessor();
                        break;
                    case 5:
                        buscarTodosProfessores();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }


    private static void inserirProfessor() {
        try {
            System.out.println("Inserir novo Professor");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Data de Contratação (yyyy-MM-dd): ");
            String dataContratacao = scanner.nextLine();

            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            Professores professor = new Professores(null, nome, LocalDate.parse(dataContratacao), departamento, telefone, email);
            professorDAO.inserir(professor);
            System.out.println("Professor inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro na inserção: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                inserirProfessor();
            }
        }
    }

    private static void atualizarProfessor() {
        try {
            System.out.println("Atualizar Professor");

            System.out.print("ID do Professor: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Professores professor = professorDAO.buscarPorId(id);

            if (professor != null) {
                System.out.print("Nome (" + professor.getNome() + "): ");
                String nome = scanner.nextLine();
                professor.setNome(nome.isEmpty() ? professor.getNome() : nome);

                System.out.print("Data de Contratação (" + professor.getDataContratacao() + "): ");
                String dataContratacao = scanner.nextLine();
                professor.setDataContratacao(dataContratacao.isEmpty() ? professor.getDataContratacao() : LocalDate.parse(dataContratacao));

                System.out.print("Departamento (" + professor.getDepartamento() + "): ");
                String departamento = scanner.nextLine();
                professor.setDepartamento(departamento.isEmpty() ? professor.getDepartamento() : departamento);

                System.out.print("Telefone (" + professor.getTelefone() + "): ");
                String telefone = scanner.nextLine();
                professor.setTelefone(telefone.isEmpty() ? professor.getTelefone() : telefone);

                System.out.print("Email (" + professor.getEmail() + "): ");
                String email = scanner.nextLine();
                professor.setEmail(email.isEmpty() ? professor.getEmail() : email);

                professorDAO.atualizar(professor);
                System.out.println("Professor atualizado com sucesso!");
            } else {
                System.out.println("Professor não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro na atualização: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                atualizarProfessor();
            }
        }
    }

    private static void excluirProfessor() {
        try {
            System.out.println("Excluir Professor");

            System.out.print("ID do Professor: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            professorDAO.excluirPorId(id);
            System.out.println("Professor excluído com sucesso!");
        } catch (DbIntegrityException e) {
            System.out.println("Erro ao excluir o professor: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                excluirProfessor();
            }
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void buscarProfessor() {
        try {
            System.out.println("Buscar Professor");

            System.out.print("ID do Professor: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Professores professor = professorDAO.buscarPorId(id);

            if (professor == null) {
                System.out.println("Professor não encontrado.");
            } else {
                System.out.println("Professor encontrado:");
                System.out.println(professor);
            }
        } catch (Exception e) {
            System.out.println("Erro na busca: " + e.getMessage());
            System.out.println("Deseja tentar novamente? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                buscarProfessor();
            }
        }
    }

    private static void buscarTodosProfessores() {
        try {
            System.out.println("Lista de Todos os Professores");
            professorDAO.buscarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao buscar professores: " + e.getMessage());
        }
    }


    private static void menuMatricular() {
        while (true) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                             ║");
            System.out.println("║ " + VERDE + "                                Menu de Matrículas" + RESET + "                                                          ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ " + VERDE + "                             1. Inserir Matrícula" + RESET + "                                                           ║");
            System.out.println("║ " + VERDE + "                             2. Atualizar Matrícula" + RESET + "                                                         ║");
            System.out.println("║ " + VERDE + "                             3. Excluir Matrícula" + RESET + "                                                           ║");
            System.out.println("║ " + VERDE + "                             4. Buscar Matrícula" + RESET + "                                                            ║");
            System.out.println("║ " + VERDE + "                             5. Buscar Todas" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             0. Voltar" + RESET + "                                                                      ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
            System.out.print("\033[0m Digite a opção escolhida: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println( "\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
            try {
                switch (opcao) {
                    case 1:
                        inserirMatricula();
                        break;
                    case 2:
                        atualizarMatricula();
                        break;
                    case 3:
                        excluirMatricula();
                        break;
                    case 4:
                        buscarMatricula();
                        break;
                    case 5:
                        buscarTodasMatriculas();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void inserirMatricula() {
        System.out.println("Inserir Matrícula");

        try {
            System.out.print("ID do Aluno: ");
            int idAluno = scanner.nextInt();
            scanner.nextLine();

            System.out.print("ID do Curso: ");
            int idCurso = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Data da Matrícula (YYYY-MM-DD): ");
            String dataMatriculaStr = scanner.nextLine();
            LocalDate dataMatricula = LocalDate.parse(dataMatriculaStr);

            Alunos aluno = alunoDAO.buscarPorId(idAluno);
            Cursos curso = cursoDAO.buscarPorId(idCurso);

            Matriculas matricula = new Matriculas();
            matricula.setAluno(aluno);
            matricula.setCurso(curso);
            matricula.setDataMatricula(dataMatricula);

            matriculaDAO.inserir(matricula);
            System.out.println("Matrícula inserida com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir matrícula: " + e.getMessage());
        }
    }

    private static void atualizarMatricula() {
        System.out.println("Atualizar Matrícula");

        try {
            System.out.print("ID da Matrícula: ");
            int idMatricula = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo ID do Aluno: ");
            int idAluno = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo ID do Curso: ");
            int idCurso = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nova Data da Matrícula (YYYY-MM-DD): ");
            String dataMatriculaStr = scanner.nextLine();
            LocalDate dataMatricula = LocalDate.parse(dataMatriculaStr);

            Alunos aluno = alunoDAO.buscarPorId(idAluno);
            Cursos curso = cursoDAO.buscarPorId(idCurso);

            Matriculas matricula = new Matriculas();
            matricula.setIdMatricula(idMatricula);
            matricula.setAluno(aluno);
            matricula.setCurso(curso);
            matricula.setDataMatricula(dataMatricula);

            matriculaDAO.atualizar(matricula);
            System.out.println("Matrícula atualizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar matrícula: " + e.getMessage());
        }
    }

    private static void excluirMatricula() {
        System.out.println("Excluir Matrícula");

        try {
            System.out.print("ID da Matrícula: ");
            int idMatricula = scanner.nextInt();
            scanner.nextLine();

            matriculaDAO.excluirPorId(idMatricula);
            System.out.println("Matrícula excluída com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao excluir matrícula: " + e.getMessage());
        }
    }

    private static void buscarMatricula() {
        System.out.println("Buscar Matrícula por ID");

        try {
            System.out.print("ID da Matrícula: ");
            int idMatricula = scanner.nextInt();
            scanner.nextLine();

            Matriculas matricula = matriculaDAO.buscarPorId(idMatricula);
            if (matricula != null) {
            } else {
                System.out.println("Matrícula não encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar matrícula: " + e.getMessage());
        }
    }

    private static void buscarTodasMatriculas() {
        System.out.println("Buscar Todas as Matrículas");

        try {
            List<Matriculas> matriculas = matriculaDAO.buscarTodos();
            if (matriculas.isEmpty()) {
                System.out.println("Nenhuma matrícula encontrada.");
            } else {
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar matrículas: " + e.getMessage());
        }
    }

    private static void menuNotas() {
        while (true) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                             ║");
            System.out.println("║ " + VERDE + "                                Menu de Notas" + RESET + "                                                               ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ " + VERDE + "                             1. Inserir Nota" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             2. Atualizar Nota" + RESET + "                                                              ║");
            System.out.println("║ " + VERDE + "                             3. Excluir Nota" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             4. Buscar Nota" + RESET + "                                                                 ║");
            System.out.println("║ " + VERDE + "                             5. Buscar Todas" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             0. Voltar" + RESET + "                                                                      ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
            System.out.print("\033[0m Digite a opção escolhida: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println( "\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");

            try {
                switch (opcao) {
                    case 1:
                        inserirNota(notaDAO);
                        break;
                    case 2:
                        atualizarNota(notaDAO);
                        break;
                    case 3:
                        excluirNota(notaDAO);
                        break;
                    case 4:
                        buscarNota(notaDAO);
                        break;
                    case 5:
                        buscarTodasNotas(notaDAO);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void inserirNota(NotaDAO notaDAO) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID da Matrícula: ");
            int idMatricula = scanner.nextInt();
            System.out.print("Nota: ");
            BigDecimal nota = scanner.nextBigDecimal();
            System.out.print("Data da Avaliação (yyyy-MM-dd): ");
            LocalDate dataAvaliacao = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            scanner.nextLine();

            Matriculas matricula = new Matriculas();
            matricula.setIdMatricula(idMatricula);

            Notas notaObj = new Notas(null, matricula, nota, dataAvaliacao);
            notaDAO.inserir(notaObj);
            System.out.println("Nota inserida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir nota: " + e.getMessage());
        }
    }

    private static void atualizarNota(NotaDAO notaDAO) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID da Nota para Atualizar: ");
            int idNota = scanner.nextInt();
            scanner.nextLine();
            System.out.print("ID da Matrícula: ");
            int idMatricula = scanner.nextInt();
            System.out.print("Nota: ");
            BigDecimal nota = scanner.nextBigDecimal();
            System.out.print("Data da Avaliação (yyyy-MM-dd): ");
            LocalDate dataAvaliacao = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Matriculas matricula = new Matriculas();
            matricula.setIdMatricula(idMatricula);

            Notas notaObj = new Notas(idNota, matricula, nota, dataAvaliacao);
            notaDAO.atualizar(notaObj);
            System.out.println("Nota atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar nota: " + e.getMessage());
        }
    }

    private static void excluirNota(NotaDAO notaDAO) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID da Nota para Excluir: ");
            int idNota = scanner.nextInt();
            scanner.nextLine();

            notaDAO.excluirPorId(idNota);
            System.out.println("Nota excluída com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir nota: " + e.getMessage());
        }
    }

    private static void buscarNota(NotaDAO notaDAO) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID da Nota para Buscar: ");
            int idNota = scanner.nextInt();
            scanner.nextLine();

            Notas nota = notaDAO.buscarPorId(idNota);
            if (nota == null) {
                System.out.println("Nota não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar nota: " + e.getMessage());
        }
    }

    private static void buscarTodasNotas(NotaDAO notaDAO) {
        try {
            List<Notas> notas = notaDAO.buscarTodos();
            if (notas.isEmpty()) {
                System.out.println("Nenhuma nota encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar todas as notas: " + e.getMessage());
        }
    }



    private static void menuCursos() {
        while (true) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                             ║");
            System.out.println("║ " + VERDE + "                                Menu de Cursos" + RESET + "                                                              ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ " + VERDE + "                             1. Inserir Curso" + RESET + "                                                               ║");
            System.out.println("║ " + VERDE + "                             2. Atualizar Curso" + RESET + "                                                             ║");
            System.out.println("║ " + VERDE + "                             3. Excluir Curso" + RESET + "                                                               ║");
            System.out.println("║ " + VERDE + "                             4. Buscar Curso" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             5. Buscar Todos" + RESET + "                                                                ║");
            System.out.println("║ " + VERDE + "                             0. Voltar" + RESET + "                                                                      ║");
            System.out.println("║                                                                                                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");
            System.out.print("\033[0m Digite a opção escolhida: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println( "\033[1;36m╠----------------------------------------------------------------------------------------------------------------╣\033[0m");

            try {
                switch (opcao) {
                    case 1:
                        inserirCurso();
                        break;
                    case 2:
                        atualizarCurso();
                        break;
                    case 3:
                        excluirCurso();
                        break;
                    case 4:
                        buscarCurso();
                        break;
                    case 5:
                        buscarTodosCursos();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void inserirCurso() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nome do Curso: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição do Curso: ");
            String descricao = scanner.nextLine();
            System.out.print("Duração em Horas: ");
            int duracaoHoras = scanner.nextInt();
            System.out.print("ID do Professor: ");
            Integer idProfessor = scanner.nextInt();
            scanner.nextLine();

            if (idProfessor == null) {
                throw new IllegalArgumentException("O ID do professor não pode ser nulo.");
            }

            Professores professor = new Professores();
            professor.setIdProfessor(idProfessor);

           Cursos curso = new Cursos(null, nome, descricao, duracaoHoras, professor);
            cursoDAO.inserir(curso);
            System.out.println("Curso inserido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir curso: " + e.getMessage());
        }
    }


    private static void atualizarCurso() {
        try {
            System.out.print("ID do Curso para Atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome do Curso: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição do Curso: ");
            String descricao = scanner.nextLine();
            System.out.print("Duração em Horas: ");
            int duracaoHoras = scanner.nextInt();
            System.out.print("ID do Professor: ");
            int idProfessor = scanner.nextInt();
            scanner.nextLine();

            Cursos curso = new Cursos(id, nome, descricao, duracaoHoras, new Professores(idProfessor));
            cursoDAO.atualizar(curso);
            System.out.println("Curso atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    private static void excluirCurso() {
        try {
            System.out.print("ID do Curso para Excluir: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            cursoDAO.excluirPorId(id);
            System.out.println("Curso excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage());
        }
    }

    private static void buscarCurso() {
        try {
            System.out.print("ID do Curso para Buscar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Cursos curso = cursoDAO.buscarPorId(id);

            if (curso != null) {
                System.out.println("Curso Encontrado:");
                printCursoHeader();
                printCursoData(curso);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Curso não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        }
    }

    private static void buscarTodosCursos() {
        System.out.println("Buscar Todos os Cursos");

        try {
            List<Cursos> cursos = cursoDAO.buscarTodos();
            if (cursos.isEmpty()) {
                System.out.println("Nenhum curso encontrado.");
            } else {
                System.out.println("Cursos Encontrados:");
                printCursoHeader();
                for (Cursos curso : cursos) {
                    printCursoData(curso);
                }
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar cursos: " + e.getMessage());
        }
    }

    private static void printCursoHeader() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-30s %-15s %-15s%n", "| ID", "| NOME", "| DESCRIÇÃO", "| DURAÇÃO (horas)", "| PROFESSOR");
        System.out.println("----- -------------------- ------------------------------ --------------- ---------------");
    }


    private static void printCursoData(Cursos curso) {
        System.out.printf("  %-5d %-20s %-30s %-15d %-15d%n",
                curso.getIdCurso(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getDuracaoHoras(),
                curso.getProfessores().getIdProfessor()); // Ajuste se precisar do nome do professor
    }


}

