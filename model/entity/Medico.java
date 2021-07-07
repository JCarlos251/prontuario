package model.entity;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "medico")
public class Medico
{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String crm;    
    
    @DatabaseField
    private String estado;  
        
    public void setId(int i)
    {
        id = i;
    }

    public void setNome(String n)
    {
        nome = n;
    }
    
    public void setCRM(String m)
    {
        crm = m;
    }    
    
    public void setEstado(String e)
    {
        estado = e;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public String getCRM()
    {
        return crm;
    }  
    
    public String getEstado()
    {
        return estado;
    }
}