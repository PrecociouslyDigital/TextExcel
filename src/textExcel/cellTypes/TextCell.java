/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textExcel.cellTypes;

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
        return String.format("%7s...", text);
    }

    @Override
    public String fullCellText() {
        return text;
    }
}