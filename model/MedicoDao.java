package model;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

import model.entity.Medico;

public class MedicoDao
{
    private static Database database;
    private static Dao<Medico, Integer> dao;
    private List<Medico> MedicosCarregados;
    private Medico MedicoCarregado; 

    public MedicoDao(Database database) 
    {
        this.setDatabase(database);
        MedicosCarregados = new ArrayList<Medico>();
    }

    public static void setDatabase(Database database) 
    {
        MedicoDao.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Medico.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Medico.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    } 

    public Medico create(Medico Medico) 
    {
        int nrows = 0;
        try {
            nrows = dao.create(Medico);
            if ( nrows == 0 )
                throw new SQLException("Erro ao salvar o objeto.");
            this.MedicoCarregado = Medico;
            MedicosCarregados.add(Medico);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Medico;
    } 

    public Medico readFromId(int id) {
        try {
            this.MedicoCarregado = dao.queryForId(id);
            if (this.MedicoCarregado != null)
                this.MedicosCarregados.add(this.MedicoCarregado);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.MedicoCarregado;
    }    

    public List<Medico> readAll() {
        try {
            this.MedicosCarregados = dao.queryForAll();
            if (this.MedicosCarregados.size() != 0)
                this.MedicoCarregado = this.MedicosCarregados.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.MedicosCarregados;
    }  

    public void update(Medico Medico) 
    {
        try
        {
            dao.update(Medico);
        }
        catch (java.sql.SQLException e)
        {
            System.out.println(e);
        }
    }

    public void delete(Medico Medico) 
    {
        try
        {
            dao.deleteById(Medico.getId());
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }  
}