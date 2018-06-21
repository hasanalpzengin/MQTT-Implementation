/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

/**
 *
 * @author hasalp
 */
public class ReaderBuilder{
    public Reader getReader(String type){
        Reader reader;
        switch(type){
            case "temperature":{
                reader = new TemperatureReader();
                reader.setReader(5);
                break;
            }
            case "humidity":{
                reader = new HumidityReader();
                reader.setReader(5);
                break;
            }
            default:{
                reader = null;
            }
        }
        return reader;
    }
}
