/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textExcel;

/**
 *
 * @author s-yinb
 */
public class TextCell implements Cell{
    String text;
    public TextCell(String text){
        this.text = text;
    }
    @Override
    public String abbreviatedCellText() {
        return String.format("%7f..." text);
    }

    @Override
    public String fullCellText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
