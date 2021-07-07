package controller;

import java.util.List;
import java.util.ArrayList;


import model.entity.Paciente;
import model.PacienteDao;
import model.Database;

public class PacienteController
{
    private PacienteDao dao;

    public PacienteController(Database database)
    {
        this.dao = new PacienteDao(database);
    }

    public Paciente create(Paciente Paciente) 
    {
        return dao.create(Paciente);
    }
    
    public Paciente readFromId(int id) 
    {
        return dao.readFromId(id);
    } 
    
    public List<Paciente> readAll()
    {
        return dao.readAll();
    }

    public void update(Paciente Paciente) 
    {
        dao.update(Paciente);
    }

    public void delete(Paciente Paciente) 
    {
        dao.delete(Paciente);
    }  
}