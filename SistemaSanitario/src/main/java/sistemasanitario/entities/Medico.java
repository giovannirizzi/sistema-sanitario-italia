/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;

import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "MEDICO")
public class Medico {
    
    public Medico() {
    }
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField
    private String nome;
    
    @DatabaseField
    private String cognome;
    
    @DatabaseField
    private String citta;
    
    @DatabaseField
    private String provincia;
    
    @DatabaseField
    private String codiceMedico;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCodiceMedico() {
        return codiceMedico;
    }
}
