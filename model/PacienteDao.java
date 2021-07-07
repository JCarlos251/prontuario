package model;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

import model.entity.Paciente;

public class PacienteDao
{
    private static Database database;
    private static Dao<Paciente, Integer> dao;
    private List<Paciente> PacientesCarregados;
    private Paciente PacienteCarregado; 

    public PacienteDao(Database database) 
    {
        this.setDatabase(database);
        PacientesCarregados = new ArrayList<Paciente>();
    }

    public static void setDatabase(Database database) 
    {
        PacienteDao.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Paciente.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Paciente.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    } 

    public Paciente create(Paciente Paciente) 
    {
        int nrows = 0;
        try {
            nrows = dao.create(Paciente);
            if ( nrows == 0 )
                throw new SQLException("Erro ao salvar o objeto.");
            this.PacienteCarregado = Paciente;
            PacientesCarregados.add(Paciente);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Paciente;
    } 

    public Paciente readFromId(int id) {
        try {
            this.PacienteCarregado = dao.queryForId(id);
            if (this.PacienteCarregado != null)
                this.PacientesCarregados.add(this.PacienteCarregado);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.PacienteCarregado;
    }    

    public List<Paciente> readAll() {
        try {
            this.PacientesCarregados = dao.queryForAll();
            if (this.PacientesCarregados.size() != 0)
                this.PacienteCarregado = this.PacientesCarregados.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.PacientesCarregados;
    }  

    public void update(Paciente Paciente) 
    {
        try
        {
            dao.update(Paciente);
        }
        catch (java.sql.SQLException e)
        {
            System.out.println(e);
        }
    }

    public void delete(Paciente Paciente) 
    {
        try
        {
            dao.deleteById(Paciente.getId());
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }  
}