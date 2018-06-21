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
public abstract class Reader {
    public abstract void setReader(float interval);
    public abstract void setLastValue(String lastValue);
    public abstract String getLastValue();
    public abstract String getTopic();
    public abstract float getInterval();
    public abstract String getClientID();
    public abstract void start();
    public abstract void stop();
}

