/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Steward;

/**
 *
 * @author 11315
 */
public class StewardTool {
    
    public static int xcoin2ftime(int xcoin){
        switch(xcoin){
            case 1:return        3600; //一小时
            case 5:return       21600;//六小时   
            case 10:return      46800;//十三小时 
            case 20:return     108000;//三十小时 
            case 50:return     324000;//九十小时 
            case 100:return    720000;//二百小时  
            case 200:return   1620000;//四百五十小时
            case 500:return   4320000;//一千二百小时
            case 1000:return 10800000;//三千小时
        }
        return 0;
    }
    private StewardTool(){}
}
