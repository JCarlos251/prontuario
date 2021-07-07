    package model.entity;
    
    import com.j256.ormlite.table.DatabaseTable;
    import com.j256.ormlite.field.DatabaseField;
    import com.j256.ormlite.field.DataType;
    
    public class Prontuario
    {
        @DatabaseField(generatedId = true)
        private int id;
        
        @DatabaseField(canBeNull = false, foreign = true)
        private Paciente paciente;
        
        @DatabaseField(canBeNull = false, foreign = true)
        private Medico medico;
        
        @DatabaseField
        private String dataConsulta;
    
        @DatabaseField
        private String testeCovid;    
        
        @DatabaseField
        private String relato;  
    
        public Prontuario(Medico m, Paciente p)
        {
            medico = m;        
            paciente = p;
        }    
        public Prontuario(){
        }
        public void setId(int i)
        {
            id = i;
        }
        
        public void setPaciente(Paciente p)
        {
            paciente = p;
        }
    
        public void setMedico(Medico m)
        {
            medico = m;
        }
        
        public int getId()
        {
            return id;
        }
        
        public Paciente getPaciente()
        {
            return paciente;
        }
        
        public Medico getMedico()
        {
            return medico;
        }

        public void setTesteCovid(String testeCovid)
            {
                this.testeCovid = testeCovid;
            }
            
        public void setRelato(String rel)
        {
            relato = rel;
        }
        
        public String getCovid()
        {
            return testeCovid;
        }
        
        public String getRelato()
        {
            return relato;
        }
        
        public String getDataConsulta(){
            return this.dataConsulta;
        }//end method getDataConsulta
    
        public void setDataConsulta(String dataConsulta){
            this.dataConsulta = dataConsulta;
        }

        //End GetterSetterExtension Source Code
        //!
}