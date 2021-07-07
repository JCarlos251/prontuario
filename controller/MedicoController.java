package controller;

import java.util.List;
import java.util.ArrayList;


import model.entity.Medico;
import model.MedicoDao;
import model.Database;

public class MedicoController
{
    private MedicoDao dao;

    public MedicoController(Database database)
    {
        this.dao = new MedicoDao(database);
    }

    public Medico create(Medico Medico) 
    {
        return dao.create(Medico);
    }    
    
    public Medico readFromId(int id) 
    {
        return dao.readFromId(id);
    } 
    
    public List<Medico> readAll()
    {
        return dao.readAll();
    }

    public void update(Medico Medico) 
    {
        dao.update(Medico);
    }

    public void delete(Medico Medico) 
    {
        dao.delete(Medico);
    }  
}