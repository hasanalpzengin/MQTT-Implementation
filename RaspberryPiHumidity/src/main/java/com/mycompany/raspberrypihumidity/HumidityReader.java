/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raspberrypihumidity;

import com.pi4j.io.gpio.GpioFactory;
import static com.pi4j.wiringpi.Gpio.HIGH;
import static com.pi4j.wiringpi.Gpio.INPUT;
import static com.pi4j.wiringpi.Gpio.LOW;
import static com.pi4j.wiringpi.Gpio.OUTPUT;
import static com.pi4j.wiringpi.Gpio.delayMicroseconds;
import static com.pi4j.wiringpi.Gpio.digitalRead;
import static com.pi4j.wiringpi.Gpio.digitalWrite;
import static com.pi4j.wiringpi.Gpio.pinMode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author hasalp
 */
public class HumidityReader{
    
    private static String line;
    private static String[] data;
    static int humidity=0;
    static int temperature=0;
    public int[] read() throws Exception{
        // TODO Auto-generated method stub
        Runtime rt= Runtime.getRuntime();
        Process p=rt.exec("python3 /home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py 11 4");
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        if((line = bri.readLine()) != null){
            String[] values = line.split("  ");
            String temp = values[0].split("=")[1];
            String humi = values[1].split("=")[1];
            temperature = Integer.parseInt(temp.substring(0, 2));
            humidity = Integer.parseInt(humi.substring(0, 2));
            System.out.println("Humidity: "+humidity+" Temperature: "+temperature);
        }
        bri.close();
        p.waitFor();
        return new int[]{humidity, temperature};
    }
}
