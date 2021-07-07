import javax.swing.JFrame;

import model.Database;
import view.FormPaciente;
import view.FormMedico;
import view.FormProntuario;
import view.FormHistoricoProntuario;
import view.DesktopFrame;

public class App
{
    public static void main (String args [])
    {
        Database db;
        FormPaciente formPaciente;
        FormMedico formMedico;
        FormProntuario formProntuario;
        FormHistoricoProntuario formHistoricoProntuario;
        String dbName = "banco_premoc.db";
        String appName = "PREMOC";        

        db = new Database(dbName);

        DesktopFrame desktop = new DesktopFrame(db,appName);
        desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktop.setExtendedState(DesktopFrame.MAXIMIZED_BOTH);
        desktop.setVisible(true);        

    } 
}