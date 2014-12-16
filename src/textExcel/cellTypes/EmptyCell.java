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
public class EmptyCell implements Cell{
    public EmptyCell(){};
    @Override
    public String abbreviatedCellText() {
        return "          ";
    }

    @Override
    public String fullCellText() {
        return "   ";
    }
}