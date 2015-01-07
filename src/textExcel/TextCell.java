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
    	this.text = text.substring(1,text.length()-1);
    }
    @Override
    public String abbreviatedCellText() {
    	
        return (text + "            ").substring(0,10);
    }

    @Override
    public String fullCellText() {
        return "\"" + text + "\"";
    }
}