package model;

import com.j256.ormlite.stmt.*;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

import model.entity.Prontuario;
import model.entity.Paciente;

public class ProntuarioDao
{
    private static Database database;
    private static Dao<Prontuario, Integer> dao;
    private List<Prontuario> ProntuariosCarregados;
    private Prontuario ProntuarioCarregado; 

    public ProntuarioDao(Database database) 
    {
        this.setDatabase(database);
        ProntuariosCarregados = new ArrayList<Prontuario>();
    }

    public static void setDatabase(Database database) 
    {
        ProntuarioDao.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Prontuario.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Prontuario.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    } 

    public Prontuario create(Prontuario prontuario) 
    {
        int nrows = 0;
        try {
            nrows = dao.create(prontuario);
            if ( nrows == 0 )
                throw new SQLException("Erro ao salvar o objeto.");
            this.ProntuarioCarregado = prontuario;
            ProntuariosCarregados.add(prontuario);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return prontuario;
    } 

    public Prontuario readFromId(int id) {
        try {
            this.ProntuarioCarregado = dao.queryForId(id);
            if (this.ProntuarioCarregado != null)
                this.ProntuariosCarregados.add(this.ProntuarioCarregado);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.ProntuarioCarregado;
    }    

    public List<Prontuario> readAll() {
        try {
            this.ProntuariosCarregados = dao.queryForAll();
            if (this.ProntuariosCarregados.size() != 0)
                this.ProntuarioCarregado = this.ProntuariosCarregados.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.ProntuariosCarregados;
    }  
    
    public List<Prontuario> readProntuariosDoPaciente(int idPaciente) 
    {
        List<Prontuario> listaProntuariosDoPaciente = new ArrayList<Prontuario>();
        
        try {
            QueryBuilder<Prontuario,Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq("paciente_id",idPaciente);
            listaProntuariosDoPaciente = dao.query(queryBuilder.prepare());
                       
        } catch (SQLException e) 
        {
            System.out.println(e);
        }
        
        return listaProntuariosDoPaciente;
    }  

    public void update(Prontuario Prontuario) 
    {
        try
        {
            dao.update(Prontuario);
        }
        catch (java.sql.SQLException e)
        {
            System.out.println(e);
        }
    }

    public void delete(Prontuario Prontuario) 
    {
        try
        {
            dao.deleteById(Prontuario.getId());
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }  
}