/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

public class MQTTJava {
    public static final String BROKER_IP = "tcp://localhost";
    public static final int BROKER_PORT = 1883;
    
    public static void main(String args[]){
        reader.ReaderBuilder builder = new reader.ReaderBuilder();
        reader.Reader reader = builder.getReader("humidity");
        if(reader!=null){
            System.out.println(reader.getTopic());
            reader.start();
        }else{
            System.out.println("Undefined Request");
        }
        //Gui Starter
        new gui.Client().setVisible(true);
    }
}
