package controller;

import java.util.List;
import java.util.ArrayList;

import model.entity.Prontuario;
import model.entity.Paciente;
import model.ProntuarioDao;
import model.PacienteDao;
import model.Database;

public class ProntuarioController
{
    private ProntuarioDao dao;
    private PacienteDao daoPaciente;

    public ProntuarioController(Database database)
    {
        this.dao = new ProntuarioDao(database);
    }

    public Prontuario create(Prontuario item) 
    {
        return dao.create(item);
    }    
    
    public List<Prontuario> readAll()
    {
        return dao.readAll();
    }    
    
    // public List<Paciente> readPacienteDaDisciplina(int idDisciplina)
    // {
        // return dao.readPacientesDaDisciplina(idDisciplina);
    // }
    
    public List<Prontuario> readProntuariosDoPaciente(int idPaciente)
    {
         return dao.readProntuariosDoPaciente(idPaciente);
    }
    
    public void update(Prontuario item) 
    {
        dao.update(item);
    }

    public void delete(Prontuario item) 
    {
        dao.delete(item);
    } 
}