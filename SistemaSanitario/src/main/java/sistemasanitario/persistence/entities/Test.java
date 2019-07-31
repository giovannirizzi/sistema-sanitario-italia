/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.persistence.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "test")
public class Test {

    public Test() {
    }
    
    @DatabaseField
    private String text;
    
    @DatabaseField
    private Integer id;   

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }
}
