package model.entity;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "paciente")
public class Paciente
{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String cpf;    
    
    @DatabaseField
    private String sexo;
    
    @DatabaseField
    private String dataNascimento;
        
    public void setId(int i)
    {
        id = i;
    }

    public void setNome(String n)
    {
        nome = n;
    }
    
    public void setCPF(String m)
    {
        cpf = m;
    }    
    
    public void setSexo(String s)
    {
        sexo = s;
    }
    
    public void setDataNascimento(String dn)
    {
        dataNascimento = dn;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public String getCPF()
    {
        return cpf;
    }  
    
    public String getSexo()
    {
        return sexo;
    }
    
    public String getDataNascimento()
    {
        return dataNascimento;
    }
}