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
        int len;
        if(text.length() < 12){
            len = text.length() - 1;
        }else{
            len = 11;
        }
        return String.format("%-10s", text.substring(1, len));
    }

    @Override
    public String fullCellText() {
        return text;
    }
}