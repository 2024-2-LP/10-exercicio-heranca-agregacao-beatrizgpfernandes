package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {

    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        desenvolvedores = new ArrayList<>();
    }

    public Consultoria() {
        desenvolvedores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public List<Desenvolvedor> getDesenvolvedores() {
        return desenvolvedores;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
            return true;
        } else {
            return false;
        }
    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if(desenvolvedor.isFullstack()) {
            desenvolvedores.add(desenvolvedor);
            return true;
        } else {
            return false;
        }
    }

    public Double getTotalSalarios() {
        Double salarioTotal = 0.0;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor devDaVez = desenvolvedores.get(i);
            salarioTotal += devDaVez.calcularSalario();
        }
        return salarioTotal;
    }

    public Integer qtdDesenvolvedoresMobile() {
        Integer cont = 0;

        for (Desenvolvedor devDaVez : desenvolvedores) {
            if (devDaVez instanceof DesenvolvedorMobile) {
                cont++;
            }
        }
        return cont;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> desenvolvedorsSalarioMaior = new ArrayList<>();

        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor devDaVez = desenvolvedores.get(i);
            if (devDaVez.calcularSalario() >= salario) {
                desenvolvedorsSalarioMaior.add(devDaVez);
            }
        }
        return desenvolvedorsSalarioMaior;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        } else {
            Desenvolvedor devMenorSalario = desenvolvedores.get(0);
            for (int i = 0; i < desenvolvedores.size(); i++) {
                Desenvolvedor devDaVez = desenvolvedores.get(i);
                if (devDaVez.calcularSalario() < devMenorSalario.calcularSalario()) {
                    devMenorSalario = devDaVez;
                }
            }
            return devMenorSalario;
        }
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> devsPorTecnologia = new ArrayList<>();

        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor devDaVez = desenvolvedores.get(i);
            if (devDaVez instanceof DesenvolvedorWeb) {
                if (((DesenvolvedorWeb) devDaVez).getBackend().equalsIgnoreCase(tecnologia) || ((DesenvolvedorWeb) devDaVez).getFrontend().equalsIgnoreCase(tecnologia) || ((DesenvolvedorWeb) devDaVez).getSgbd().equalsIgnoreCase(tecnologia)) {
                    devsPorTecnologia.add(devDaVez);
                }
            }
            if (devDaVez instanceof DesenvolvedorMobile) {
                if (((DesenvolvedorMobile) devDaVez).getLinguagem().equalsIgnoreCase(tecnologia) || ((DesenvolvedorMobile) devDaVez).getPlataforma().equalsIgnoreCase(tecnologia)) {
                    devsPorTecnologia.add(devDaVez);
                }
            }
        }
        return devsPorTecnologia;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        List<Desenvolvedor> devsSalarioPorTecnologia = buscarPorTecnologia(tecnologia);
        Double salarioTotal = 0.0;
        for (int i = 0; i < devsSalarioPorTecnologia.size(); i++) {
            Desenvolvedor devDaVez = devsSalarioPorTecnologia.get(i);
            salarioTotal += devDaVez.calcularSalario();
        }
        return salarioTotal;
    }
}
