import Entidades.Animal;
import File.Write;
import Repository.AnimalRepository;
import Repository.MedicamentoRepository;
import Repository.VeterinarioRepository;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Write write = new Write();
            AnimalRepository animalRepository = new AnimalRepository();
            MedicamentoRepository medicamentoRepository = new MedicamentoRepository();
            VeterinarioRepository veterinarioRepository = new VeterinarioRepository();

            Scanner leitor = new Scanner(System.in);
            switch (tela(leitor)) {
                case 1:
                    for (Object animal: animalRepository.listarAnimais()) {
                        System.out.println(animal.toString());
                    }
                    write.writeTxt("animais", animalRepository.listarAnimais());
                    write.writeJson("animais", animalRepository.listarAnimais());
                    write.writeXml("animais", animalRepository.listarAnimais());
                    break;
                case 2:
                    for (Object veterinario: veterinarioRepository.listarVeterinarios()) {
                        System.out.println(veterinario.toString());
                    }
                    write.writeTxt("veterinarios", veterinarioRepository.listarVeterinarios());
                    write.writeJson("veterinarios", veterinarioRepository.listarVeterinarios());
                    write.writeXml("veterinarios", veterinarioRepository.listarVeterinarios());
                    break;
                case 3:
                    for (Object medicamento: medicamentoRepository.listarMedicamentos()) {
                        System.out.println(medicamento.toString());
                    }
                    write.writeTxt("medicamentos", medicamentoRepository.listarMedicamentos());
                    write.writeJson("medicamentos", medicamentoRepository.listarMedicamentos());
                    write.writeXml("medicamentos", medicamentoRepository.listarMedicamentos());
                    break;
                case 4:
                    animalRepository.adicionarAnimaisTxt();
                    animalRepository.adicionarAnimaisJson();
                    animalRepository.adicionarAnimaisXML();
                    break;
                case 5:
                    veterinarioRepository.adicionarMembrosTxt();
                    veterinarioRepository.adicionarMembrosJson();
                    veterinarioRepository.adicionarVeterinarioXML();
                    break;
                case 6:
                    medicamentoRepository.adicionarMedicamentosTxt();
                    medicamentoRepository.adicionarMedicamentosJson();
                    medicamentoRepository.adicionarMedicamentosXML();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int tela(Scanner leitor) {
        System.out.println("\n            Sistema GESTVET");
        System.out.println("=======================================\n");
        System.out.println("Escolha uma das opções:");
        System.out.println("1 - Gerar txt/json/xml com dados de Animais");
        System.out.println("2 - Gerar txt/json/xml com dados de Membros");
        System.out.println("3 - Gerar txt/json/xml com dados de Medicamentos");
        System.out.println("4 - Ler txt/json/xml com dados de Animal");
        System.out.println("5 - Ler txt/json/xml com dados de Membro");
        System.out.println("6 - Ler txt/json/xml com dados de Medicamento\n");

        return leitor.nextInt();
    }

}
