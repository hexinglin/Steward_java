/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import database.node.OrderInfro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOKGO
 */
public class OrderDB extends DB{
    
    private final String Addsql = "insert into autorder (orderno,useid,state,weight,type,localbegin,localend,cost) values (?,?,?,?,?,?,?,?)" ;
    private final String AllInformsql = "select* from autorder";
    private final String QueryOrdersql = "select * from autorder where orderno=";
    private final String QueryUsersql = "select * from autorder where driverid=";
    private final String Deletesql = "delete from autorder where orderno=";
    private final String AlterState = "update autorder set ";
    
    public List<OrderInfro> GetAllInform() throws SQLException{
        List<OrderInfro> informlist = new ArrayList();
        OrderInfro order = new OrderInfro();
        this.link(this.AllInformsql);
        this.rs = this.statement.executeQuery();
        while(rs.next()){
            ToOrderData(rs,order);
            informlist.add(order);
        }
        return informlist;
    }
    
    public List<OrderInfro> QueryforDriverID(String ID) throws SQLException{
         List<OrderInfro> informlist = new ArrayList();
          OrderInfro order =null;
        this.link(this.QueryUsersql +"'" +ID+"'");
        rs = statement.executeQuery();
         while(rs.next()){
            order= new OrderInfro();
            ToOrderData(rs,order);
            informlist.add(order);
        }
        con.close();
        return informlist;
    }
    public OrderInfro Queryorderno(String orderno) throws SQLException{
         OrderInfro order =new OrderInfro();
        this.link(this.QueryOrdersql +"'" +orderno+"'");
        rs = statement.executeQuery();
        rs.next();
        ToOrderData(rs,order);
        con.close();
        return order;
    }
    
    public OrderInfro Queryorder(String orderno ,String driverid) throws SQLException{
          OrderInfro order =new OrderInfro();
        this.link(this.QueryOrdersql +"'" +orderno+"'and driverid='"+driverid+"'");
        rs = statement.executeQuery();
        rs.next();
        ToOrderData(rs,order);
        con.close();
        return order;
    }
     public OrderInfro Queryorder(String orderno ) throws SQLException{
          OrderInfro order =new OrderInfro();
        this.link(this.QueryOrdersql +"'" +orderno+"'");
        rs = statement.executeQuery();
        rs.next();
        ToOrderData(rs,order);
        con.close();
        return order;
    }
    public void AlterOrderState(String ordernoString, String state) throws SQLException{
        this.link(this.AlterState + "state= '" +state+"' where orderno= '"+ordernoString+"'");
        this.Close();
    }
    public void Setorderdriverid(String ordernoString, String driveid) throws SQLException{
        this.link(this.AlterState + "driverid= '" +driveid+"' where orderno= '"+ordernoString+"'");
        this.Close();
    }
    
    public void DeleteOrder(String id) throws SQLException{
        this.link(this.Deletesql + "'" +id+"'");
        this.Close();
    }
    
    public void AddNewOrder(String oderno,String userid, double weight,String type
                    ,String localbegin ,String localend,double cost) throws SQLException{
        this.link(this.Addsql);
        statement.setString(1, oderno);
        statement.setString(2, userid);
        statement.setString(3, "用户下单");
        statement.setDouble(4, weight);
        statement.setString(5, type);
        statement.setString(6, localbegin);
        statement.setString(7, localend);
        statement.setDouble(8, cost);
        this.Close();
    }
    
    private void ToOrderData(ResultSet rs,OrderInfro order) throws SQLException{
        order.orderno = rs.getString("orderno");
        order.driverid = rs.getString("driverid");
        order.state = rs.getString("state");
        order.type = rs.getString("type");
        order.userid = rs.getString("useid");
        order.weight = rs.getString("weight");
        order.localbegin=rs.getString("localbegin");
        order.localend=rs.getString("localend");
        order.cost=rs.getString("cost");
    }
    
}
